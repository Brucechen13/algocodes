package algo.performance;


import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 实现一个 LRU 缓存，当缓存数据达到 N 之后需要淘汰掉最近最少使用的数据。
 * N 小时之内没有被访问的数据也需要淘汰掉。
 */
public class LRUAbstractMap extends java.util.AbstractMap {

    /**
     * 检查是否超期线程
     */
    private ExecutorService checkTimePool ;

    /**
     * map 最大size
     */
    private final static int MAX_SIZE = 1024 ;

    private final static ArrayBlockingQueue<Node> QUEUE = new ArrayBlockingQueue<>(MAX_SIZE) ;

    /**
     * 默认大小
     */
    private final static int DEFAULT_ARRAY_SIZE =1024 ;


    /**
     * 数组长度
     */
    private int arraySize ;

    /**
     * 数组
     */
    private Object[] arrays ;


    /**
     * 判断是否停止 flag
     */
    private volatile boolean flag = true ;


    /**
     * 超时时间
     */
    private final static Long EXPIRE_TIME = 60 * 60 * 1000L ;

    /**
     * 整个 Map 的大小
     */
    private volatile AtomicInteger size  ;


    public LRUAbstractMap() {


        arraySize = DEFAULT_ARRAY_SIZE;
        arrays = new Object[arraySize] ;

        //开启一个线程检查最先放入队列的值是否超期
        executeCheckTime();
    }

    /**
     * 开启一个线程检查最先放入队列的值是否超期 设置为守护线程
     */
    private void executeCheckTime() {
//        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
//                .setNameFormat("check-thread-%d")
//                .setDaemon(true)
//                .build();
        checkTimePool = new ThreadPoolExecutor(1, 1, 0L,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1),Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        checkTimePool.execute(new CheckTimeThread()) ;

    }

    @Override
    public Set<Entry> entrySet() {
        return super.keySet();
    }

    @Override
    public Object put(Object key, Object value) {
        int hash = hash(key);
        int index = hash % arraySize ;
        Node currentNode = (Node) arrays[index] ;

        if (currentNode == null){
            arrays[index] = new Node(null,null, key, value);

            //写入队列
            QUEUE.offer((Node) arrays[index]) ;

            sizeUp();
        }else {
            Node cNode = currentNode ;
            Node nNode = cNode ;

            //存在就覆盖
            if (nNode.key == key){
                cNode.val = value ;
            }

            while (nNode.next != null){
                //key 存在 就覆盖 简单判断
                if (nNode.key == key){
                    nNode.val = value ;
                    break ;
                }else {
                    //不存在就新增链表
                    sizeUp();
                    Node node = new Node(nNode,null,key,value) ;

                    //写入队列
                    QUEUE.offer(currentNode) ;

                    cNode.next = node ;
                }

                nNode = nNode.next ;
            }

        }

        return null ;
    }


    @Override
    public Object get(Object key) {

        int hash = hash(key) ;
        int index = hash % arraySize ;
        Node currentNode = (Node) arrays[index] ;

        if (currentNode == null){
            return null ;
        }
        if (currentNode.next == null){

            //更新时间
            currentNode.setUpdateTime(System.currentTimeMillis());

            //没有冲突
            return currentNode ;

        }

        Node nNode = currentNode ;
        while (nNode.next != null){

            if (nNode.key == key){

                //更新时间
                currentNode.setUpdateTime(System.currentTimeMillis());

                return nNode ;
            }

            nNode = nNode.next ;
        }

        return super.get(key);
    }


    @Override
    public Object remove(Object key) {

        int hash = hash(key) ;
        int index = hash % arraySize ;
        Node currentNode = (Node) arrays[index] ;

        if (currentNode == null){
            return null ;
        }

        if (currentNode.key == key){
            sizeDown();
            arrays[index] = null ;

            //移除队列
            QUEUE.poll();
            return currentNode ;
        }

        Node nNode = currentNode ;
        while (nNode.next != null){

            if (nNode.key == key){
                sizeDown();
                //在链表中找到了 把上一个节点的 next 指向当前节点的下一个节点
                nNode.pre.next = nNode.next ;
                nNode = null ;

                //移除队列
                QUEUE.poll();

                return nNode;
            }

            nNode = nNode.next ;
        }

        return super.remove(key);
    }

    /**
     * 增加size
     */
    private void sizeUp(){

        //在put值时候认为里边已经有数据了
        flag = true ;

        if (size == null){
            size = new AtomicInteger() ;
        }
        int size = this.size.incrementAndGet();
        if (size >= MAX_SIZE) {
            //找到队列头的数据
            Node node = QUEUE.poll() ;
            if (node == null){
                throw new RuntimeException("data error") ;
            }

            //移除该 key
            Object key = node.key ;
            remove(key);
            lruCallback() ;
        }

    }

    /**
     * 数量减小
     */
    private void sizeDown(){

        if (QUEUE.size() == 0){
            flag = false ;
        }

        this.size.decrementAndGet() ;
    }

    @Override
    public int size() {
        return size.get() ;
    }

    /**
     * 链表
     */
    private class Node{
        private Node next ;
        private Node pre ;
        private Object key ;
        private Object val ;
        private Long updateTime ;

        public Node(Node pre,Node next, Object key, Object val) {
            this.pre = pre ;
            this.next = next;
            this.key = key;
            this.val = val;
            this.updateTime = System.currentTimeMillis() ;
        }

        public void setUpdateTime(Long updateTime) {
            this.updateTime = updateTime;
        }

        public Long getUpdateTime() {
            return updateTime;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", val=" + val +
                    '}';
        }
    }


    /**
     * copy HashMap 的 hash 实现
     * @param key
     * @return
     */
    public int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private void lruCallback(){
    }


    private class CheckTimeThread implements Runnable{

        @Override
        public void run() {
            while (flag){
                try {
                    Node node = QUEUE.poll();
                    if (node == null){
                        continue ;
                    }
                    Long updateTime = node.getUpdateTime() ;

                    if ((updateTime - System.currentTimeMillis()) >= EXPIRE_TIME){
                        remove(node.key) ;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }



    class LRUMap<K, V> {
        private final Map<K, Node<K, V>> cacheMap = new HashMap<>();

        /**
         * 最大缓存大小
         */
        private int cacheSize;

        /**
         * 节点大小
         */
        private int nodeCount;


        /**
         * 头结点
         */
        private Node<K, V> header;

        /**
         * 尾结点
         */
        private Node<K, V> tailer;

        public LRUMap(int cacheSize) {
            this.cacheSize = cacheSize;
            //头结点的下一个结点为空
            header = new Node<>();
            header.next = null;

            //尾结点的上一个结点为空
            tailer = new Node<>();
            tailer.tail = null;

            //双向链表 头结点的上结点指向尾结点
            header.tail = tailer;

            //尾结点的下结点指向头结点
            tailer.next = header;


        }

        public void put(K key, V value) {
            //双向链表中添加结点
            Node node = addNode(key, value);

            cacheMap.put(key, node);
        }

        public V get(K key){

            Node<K, V> node = cacheMap.get(key);

            //移动到头结点
            moveToHead(node) ;

            return node.value;
        }

        private void moveToHead(Node<K,V> node){

            //如果是最后的一个节点
            if (node.tail == null){
                node.next.tail = null ;
                tailer = node.next ;
                nodeCount -- ;
            }

            //如果是本来就是头节点 不作处理
            if (node.next == null){
                return ;
            }

            //如果处于中间节点
            if (node.tail != null && node.next != null){
                //它的上一节点指向它的下一节点 也就删除当前节点
                node.tail.next = node.next ;
                nodeCount -- ;
            }

            //最后在头部增加当前节点
            //注意这里需要重新 new 一个对象，不然原本的node 还有着下面的引用，会造成内存溢出。
            node = new Node<>(node.getKey(),node.getValue()) ;
            addHead(node) ;

        }


        /**
         * 写入头结点
         * @param key
         * @param value
         */
        private Node addNode(K key, V value) {

            Node<K, V> node = new Node<>(key, value);

            //容量满了删除最后一个
            if (cacheSize == nodeCount) {
                //删除尾结点
                delTail();
            }

            //写入头结点
            addHead(node);
            return node;

        }



        /**
         * 添加头结点
         *
         * @param node
         */
        private void addHead(Node<K, V> node) {

            //写入头结点
            header.next = node;
            node.tail = header;
            header = node;
            nodeCount++;

            //如果写入的数据大于2个 就将初始化的头尾结点删除
            if (nodeCount == 2) {
                tailer.next.next.tail = null;
                tailer = tailer.next.next;
            }

        }

        private void delTail() {
            //把尾结点从缓存中删除
            cacheMap.remove(tailer.getKey());

            //删除尾结点
            tailer.next.tail = null;
            tailer = tailer.next;

            nodeCount--;

        }

        private class Node<K, V> {
            private K key;
            private V value;
            Node<K, V> tail;
            Node<K, V> next;

            public Node(K key, V value) {
                this.key = key;
                this.value = value;
            }

            public Node() {
            }

            public K getKey() {
                return key;
            }

            public void setKey(K key) {
                this.key = key;
            }

            public V getValue() {
                return value;
            }

            public void setValue(V value) {
                this.value = value;
            }

        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder() ;
            Node<K,V> node = tailer ;
            while (node != null){
                sb.append(node.getKey()).append(":")
                        .append(node.getValue())
                        .append("-->") ;

                node = node.next ;
            }


            return sb.toString();
        }
    }

    class LRULinkedMap<K,V> {


        /**
         * 最大缓存大小
         */
        private int cacheSize;

        private LinkedHashMap<K,V> cacheMap ;


        public LRULinkedMap(int cacheSize) {
            this.cacheSize = cacheSize;

            cacheMap = new LinkedHashMap(16,0.75F,true){
                @Override
                protected boolean removeEldestEntry(Map.Entry eldest) {
                    if (cacheSize + 1 == cacheMap.size()){
                        return true ;
                    }else {
                        return false ;
                    }
                }
            };
        }

        public void put(K key,V value){
            cacheMap.put(key,value) ;
        }

        public V get(K key){
            return cacheMap.get(key) ;
        }


        public Collection<Entry<K, V>> getAll() {
            return new ArrayList<Entry<K, V>>(cacheMap.entrySet());
        }
    }

}

package algo.leetcode;


import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LRUCache {

    public class LFUCache2 {
        HashMap<Integer, Integer> vals;
        HashMap<Integer, Integer> counts;
        HashMap<Integer, LinkedHashSet<Integer>> lists;
        int cap;
        int min = -1;
        public LFUCache2(int capacity) {
            cap = capacity;
            vals = new HashMap<>();
            counts = new HashMap<>();
            lists = new HashMap<>();
            lists.put(1, new LinkedHashSet<>());
        }

        public int get(int key) {
            if(!vals.containsKey(key))
                return -1;
            int count = counts.get(key);
            counts.put(key, count+1);
            lists.get(count).remove(key);
            if(count==min && lists.get(count).size()==0)
                min++;
            if(!lists.containsKey(count+1))
                lists.put(count+1, new LinkedHashSet<>());
            lists.get(count+1).add(key);
            return vals.get(key);
        }

        public void put(int key, int value) {
            if(cap<=0)
                return;
            if(vals.containsKey(key)) {
                vals.put(key, value);
                get(key);
                return;
            }
            if(vals.size() >= cap) {
                int evit = lists.get(min).iterator().next();
                lists.get(min).remove(evit);
                vals.remove(evit);
            }
            vals.put(key, value);
            counts.put(key, 1);
            min = 1;
            lists.get(1).add(key);
        }
    }

    class Node {
        int key;
        int value;
        Node pre;
        Node next;
    }

    private Map<Integer, Node> data = new HashMap<>();
    private Node head, tail;
    private int capacity = 0;

    public LRUCache(int capacity) {
        head = new Node();
        tail = new Node();
        head.pre = null;
        head.next = tail;
        tail.pre = head;
        tail.next = null;
        this.capacity = capacity;
    }

    public int get(int key) {
        if(data.containsKey(key)){
            Node node = data.get(key);
            node.pre.next = node.next;
            node.next.pre = node.pre;
            putFirst(node);
            return node.value;
        }
        return -1;
    }

    public void putFirst(Node node){
        node.pre = head;
        head.next.pre = node;
        node.next = head.next;
        node.pre = head;
        head.next = node;
        if(node.next == tail){
            tail.pre = node;
        }
    }

    public void put(int key, int value) {
        if(data.containsKey(key)){
            Node leftNode = data.get(key);
            leftNode.pre.next = leftNode.next;
            leftNode.next.pre = leftNode.pre;
            capacity ++;
        }
        Node node = new Node();
        node.key = key;
        node.value = value;

        if (capacity > 0) {
            capacity --;
        } else {
            if(head.next == tail)return;
            data.remove(tail.pre.key);
            tail.pre = tail.pre.pre;
            tail.pre.next = tail;
        }
        tail.pre.next = node;
        node.pre = tail.pre;
        tail.pre = node;
        node.next = tail;
        data.put(key, node);
    }

    public static void main(String[] args){
        LRUCache cache = new LRUCache(2);
        cache.put(1,1);
        cache.put(2,2);
        System.out.println(cache.get(1));
        cache.put(3,3);
        System.out.println(cache.get(2));
        cache.put(4,4);
    }
}

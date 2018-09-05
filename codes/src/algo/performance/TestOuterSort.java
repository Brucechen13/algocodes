package algo.performance;

import java.io.*;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.*;

public class TestOuterSort {
    public static void generate_big_files() throws IOException {
        int len = 100000000;
        int[] arrys = new int[len];
        for(int i = 0; i < arrys.length; i ++){
            arrys[i] = i;
        }
        permutations(arrys);
        for(int i = 0; i < 10; i ++){
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(
                    new File("file" + i + ".txt")));
            for (int j = len/10*i; j < Math.min(len, len/10*(i+1)); j++){
                bufferedWriter.write(arrys[j] + "\n");
            }
        }
    }

    public static void permutations(int[] arrs){
        int len = arrs.length;
        for(int i = 0; i < len; i ++){
            int index = (int)(Math.random() * len);
            int temp = arrs[i];
            arrs[i] = arrs[index];
            arrs[index] = temp;
        }
    }

    public static void outerSortAll() throws IOException {
        List<String> fileNames=new ArrayList<String>();//保存所有分割文件的名称
        SortAndSaveThread[] threads = new SortAndSaveThread[10];
        for(int i = 0; i < 10; i ++){
            // fileNames.addAll(outerSort("file" + i + ".txt"));
            threads[i] = new SortAndSaveThread("file" + i + ".txt");
            threads[i].start();
        }
        for(int i = 0; i < 10; i ++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int i = 0; i < 10; i ++){
            fileNames.addAll(threads[i].tempFileNames);
        }
        mergeSort(fileNames);//将所有fileNames的文件进行合并
    }

    public static void outerSortAllThread() throws IOException{
        ExecutorService pool = Executors.newFixedThreadPool(16);
        List<String> fileNames=new ArrayList<String>();//保存所有分割文件的名称
        SortAndSaveThread[] threads = new SortAndSaveThread[10];
        for(int i = 0; i < 10; i ++){
            // fileNames.addAll(outerSort("file" + i + ".txt"));
            threads[i] = new SortAndSaveThread("file" + i + ".txt");
            threads[i].start();
        }
        for(int i = 0; i < 10; i ++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int i = 0; i < 10; i ++){
            fileNames.addAll(threads[i].tempFileNames);
        }
        while (fileNames.size() != 1){
            System.out.println("pool: " + fileNames.size());
            List<String> tmpFiles = new ArrayList<>();
            List<Callable<String>> runs = new ArrayList<>();
            for(int i = 0; i < fileNames.size(); i += 2){
                if(i+1 < fileNames.size()){
                    runs.add(new MergeSOrtThread(fileNames.get(i), fileNames.get(i+1)));
                }else{
                    tmpFiles.add(fileNames.get(i));
                }
            }
            try {
                List<Future<String>> res = pool.invokeAll(runs);
                for (Future<String> f:
                     res) {
                    try {
                        tmpFiles.add(f.get());
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
                fileNames = tmpFiles;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        pool.shutdown();
        System.out.print(fileNames.get(0));

    }

    public static List<String> outerSort(String inFile) throws IOException{
        File file=new File(inFile);
        BufferedReader fr=new BufferedReader(new FileReader(file));//源数据文件读取。
        final int SIZE=100000;//这里是定义我们将源文件中以100000条记录作为单位进行分割。
        int[] nums=new int[SIZE];//临时存放分割时的记录
        List<String> fileNames=new ArrayList<String>();//保存所有分割文件的名称
        int index=0;
        while(true){
            String num=fr.readLine();//从原文件中读取一条记录
            if(num==null){//如果读取完毕后，进行一次排序并保存
                fileNames.add(sortAndSave(nums,index));
                break;
            }
            nums[index]=Integer.valueOf(num);
            index++;
            if(index==SIZE){//当nums里面读的数字到达长度边界时，排序，存储
                fileNames.add(sortAndSave(nums,index));//sortAndSave是将nums中前index条记录先快速排序，然后存入文件，最好将文件名返回。
                index=0;//重置index
            }
        }
        fr.close();
        return fileNames;
    }

    static class SortAndSaveThread extends Thread {
        private String path;
        private List<String> tempFileNames;

        public SortAndSaveThread(String path){
            this.path = path;
        }

        @Override
        public void run() {
            try {
                tempFileNames = outerSort(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class MergeSOrtThread implements Callable<String> {

        private String file1, file2;

        public MergeSOrtThread(String file1, String file2){
            this.file1 = file1;
            this.file2 = file2;
        }

        @Override
        public String call() throws Exception {
            String fileName="temp-"+System.nanoTime()+".txt";
            File rf=new File(fileName);
            BufferedWriter bw=new BufferedWriter(new FileWriter(rf));
            BufferedReader buff1 = new BufferedReader(new FileReader(new File(file1)));
            BufferedReader buff2 = new BufferedReader(new FileReader(new File(file2)));
            String line1 = buff1.readLine();
            String line2 = buff2.readLine();
            while (line1 != null && line2 != null){
                int val1 = Integer.parseInt(line1);
                int val2 = Integer.parseInt(line2);
                if(val1 > val2){
                    bw.write(val2+"\n");
                    line2 = buff2.readLine();
                }else{
                    bw.write(val1 + "\n");
                    line1 = buff1.readLine();
                }
            }
            if(line1 != null || line2 != null){
                if(line1 == null){
                    line1 = line2;
                    buff1.close();
                    buff1 = buff2;
                }
                while (line1 != null){
                    int val1 = Integer.parseInt(line1);
                    bw.write(val1 + "\n");
                    line1 = buff1.readLine();
                }
            }
            bw.flush();
            bw.close();
            return fileName;
        }
    }

    //sortAndSave是将nums中前index条记录先快速排序，然后存入文件，最好将文件名返回
    public static String sortAndSave(int[] nums,int size) throws IOException{
        sort(nums,0, size-1);
        String fileName="temp-"+System.nanoTime()+".txt";
        File rf=new File(fileName);
        BufferedWriter bw=new BufferedWriter(new FileWriter(rf));
        for(int i=0;i<nums.length;i++)bw.write(nums[i]+ "\n");
        bw.close();
        return fileName;
    }

    public static void mergeSort(List<String> fileNames) throws IOException{
        List<String> tempFileNames=new ArrayList<String>();
        Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                return t1[1] - t2[1];
            }
        });
        int count = 0;
        int index = 0;
        BufferedReader[] buffs = new BufferedReader[fileNames.size()];

        BufferedWriter bw=new BufferedWriter(new FileWriter(new File("result" + index + ".txt")));
        for(int i = 0; i < fileNames.size(); i ++){
            buffs[i] = new BufferedReader(new FileReader(new File(fileNames.get(i))));
            queue.add(new int[]{i,Integer.valueOf(buffs[i].readLine())});
        }
        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            bw.write(cur[1]+"\n");
            String nt = buffs[cur[0]].readLine();
            if(nt == null)continue;
            queue.add(new int[]{cur[0],Integer.valueOf(nt)});
            count++;
            if(count > 10000000){
                count = 0;
                index ++;
                bw.flush();
                bw.close();
                bw = new BufferedWriter(new FileWriter(new File("result" + index + ".txt")));
            }
        }

        for(int i = 0; i < fileNames.size(); i ++){
            buffs[i].close();
        }
        bw.flush();
        bw.close();
    }

    public static void sort(int data[],int low,int hight){
        quicksort(data, low, hight);
    }
    private static int partition(int sortArray[], int low, int hight) {
        int key = sortArray[low];
        while (low < hight) {
            while (low < hight && sortArray[hight] >= key)hight--;
            sortArray[low] = sortArray[hight];
            while (low < hight && sortArray[low] <= key)low++;
            sortArray[hight] = sortArray[low];
        }
        sortArray[low] = key;
        return low;
    }
    public static void quicksort(int data[], int low, int hight) {
        if (low < hight) {
            int result = partition(data, low, hight);
            quicksort(data, low, result - 1);
            quicksort(data, result + 1, hight);
        }
    }

    /**
     * outerSortAll
     * 2018-09-01T14:28:47.481+08:00[Asia/Shanghai]
     * 2018-09-01T14:29:26.163+08:00[Asia/Shanghai]
     * outerSortAllThread
     * 2018-09-01T14:59:51.935+08:00[Asia/Shanghai]
     * 2018-09-01T15:01:01.400+08:00[Asia/Shanghai]
     * @param args
     * @throws IOException
     */

    public static void main(String[] args) throws IOException {
        System.out.println(ZonedDateTime.now());
        outerSortAllThread();
        System.out.println(ZonedDateTime.now());
    }
}

package algo.hiho;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class Hiho25SPFA {


    static Queue<Integer> q = new LinkedBlockingQueue<>();
    static int t  =0;
    static int[] head = new int[100005];
    static int[] v = new int[2000005];
    static int[] next = new int[2000005];
    static int[] w = new int[2000005];
    static int[] dis= new int[100005];
    static int[] inq = new int[100005];

    static void add(int a,int b,int c){
        v[t]=b;
        w[t]=c;
        next[t]=head[a];
        head[a]=t++;
    }

    static int spfa(int s,int t){
        Arrays.fill(inq, 0);
        Arrays.fill(dis, -1);
        inq[s]=1;dis[s]=0;
        q.add(s);
        while(q.size() > 0){
            int x=q.poll();
            inq[x]=0;
            for(int i=head[x];i!=-1;i=next[i]){
                int ww=dis[x]+w[i];
                if(dis[v[i]]==-1 || dis[v[i]]>ww){
                    dis[v[i]]=ww;
                    if(inq[v[i]] != 0){
                        inq[v[i]]=1;
                        q.add(v[i]);
                    }
                }
            }
        }
        return dis[t];
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int s = scanner.nextInt();
        int e = scanner.nextInt();
        for(int i = 0; i < m; i ++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int len = scanner.nextInt();
            add(x,y,len);add(y,x,len);

        }
        System.out.println(spfa(s,e));
    }
}

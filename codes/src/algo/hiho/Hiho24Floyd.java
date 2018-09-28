package algo.hiho;

import java.util.Arrays;
import java.util.Scanner;

public class Hiho24Floyd {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] dis = new int[n][n];
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                dis[i][j]=i==j?0:8948309;
        for(int i =0 ; i < m ; i ++){
            int x = scanner.nextInt()-1;
            int y = scanner.nextInt()-1;
            int len = scanner.nextInt();
            dis[x][y] = Math.min(dis[x][y], len);
            dis[y][x] = Math.min(dis[y][x], len);
        }

        for(int i = 0; i < n; i ++){
            for(int j = 0; j < n; j ++){
                for(int k = 0; k < n; k ++){
                    dis[i][j] = Math.min(dis[i][j], dis[i][k]+dis[k][j]);
                }
            }
        }
        for(int i = 0; i < n; i ++){
            for(int j = 0; j < n; j ++){
                if(i == j)dis[i][j] = 0;
                System.out.print(dis[i][j] + " ");
            }
            System.out.println();
        }
    }
}

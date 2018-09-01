package algo.exam;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Scanner;

public class TouExam1 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        int[] sum = new int[n+1];
        int[] min = new int[n];
        int maxVal = 0;
        for(int i = 0; i < n; i ++){
            nums[i] = scanner.nextInt();
            sum[i+1] = sum[i] + nums[i];
            maxVal = Math.max(maxVal, nums[i]*nums[i]);
        }
        for(int i = 0; i < n; i ++){
            min[i] = nums[i];
            for(int j = i+1; j < n; j ++){
                min[j] = nums[j] < min[j-1] ? nums[j] : min[j-1];
                maxVal = Math.max(maxVal, min[j] * (sum[j+1] - sum[i]));
            }
        }
        System.out.print(maxVal);
    }
}

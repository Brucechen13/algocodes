package algo.competition;

public class LeetCode905 {
    public int[] sortArrayByParity(int[] A) {
        int i = 0, j = A.length - 1;
        while (i < j){
            while (i<j && A[i] % 2 == 0)i++;
            while (i<j && A[j] % 2 == 1)j--;
            if(i < j)
                swap(A, i, j);
        }
        return A;
    }

    public void swap(int[] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}

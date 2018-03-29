package algo.array;

public class SortColors {
    public void sortColors(int[] nums) {
        int i = 0, j = nums.length-1;
        for(int index = 0; index < 2; index ++) {
            while (i < j) {
                while (i<nums.length && nums[i] == index) {
                    i++;
                }
                while (j >= 0 && nums[j] != index) {
                    j--;
                }
                if(i < j){
                    nums[j--] = nums[i];
                    nums[i++] = index;
                }
            }
            j = nums.length - 1;
        }
    }

    public void sortColors2(int[] nums) {
        int i1  = 0;
        int i2 = 0;
        for(int i = 0; i < nums.length; i ++){
            int temp = nums[i];
            nums[i] = 2;
            if(temp < 2){
                nums[i2]=1;
                i2++;
            }
            if(temp == 0){
                nums[i1]=0;
                i1++;
            }
        }
    }

    public static void main(String[] args){
        System.out.println("test");
    }
}

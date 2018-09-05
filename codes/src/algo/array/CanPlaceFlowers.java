package algo.array;

/**
 * Created by chenc on 2017/6/25.
 */
public class CanPlaceFlowers {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if(flowerbed==null || flowerbed.length < 1){
            return false;
        }
        if(flowerbed.length == 1 && n <=1){
            return flowerbed[0]==0 || n==0;
        }
        int index = 0;
        while(n>0){
            if(index >= flowerbed.length){
                break;
            }
            if((index+2<flowerbed.length && flowerbed[index]==0&&flowerbed[index+1]==0&&flowerbed[index+2]==0) ||
                    ((index+2==flowerbed.length||index==0)&& flowerbed[index]==0&&flowerbed[index+1]==0)){
                n--;
                if(index != 0)
                    index++;
            }
            index++;
        }
        return n==0;
    }
}

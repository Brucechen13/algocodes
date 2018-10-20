package algo.leetcode;

import java.util.*;

public class StickersSpellWord {
    public int[] getStringFinger(String s){
        int[] res = new int[26];
        for(int i = 0; i < s.length(); i ++){
            res[s.charAt(i)-'a'] ++;
        }
        return res;
    }

    public int minStickers(String[] stickers, String target) {
        int[][] sticks = new int[stickers.length][26];
        List<Integer> lefters = new ArrayList<>();
        int[] targ = getStringFinger(target);
        for(int i = 0; i < stickers.length; i ++){
            sticks[i] = getStringFinger(stickers[i]);
            if(checkTargetExist(sticks[i], targ)){
                lefters.add(i);
            }
        }
        int res = getStickersCover(sticks, targ, lefters);
        return res==Integer.MAX_VALUE?-1:res;
    }

    public int[] removeTargetCount(int[] origin, int[] target){
        int[] temp = new int[26];
        for(int j = 0; j < 26; j ++){
            if(origin[j] != 0 && target[j]!=0){
                temp[j] = Math.max(0, target[j] - origin[j]);
            }else{
                temp[j] = target[j];
            }
        }
        return temp;
    }

    public boolean checkTargetExist(int[] origin, int[] target){
        for(int j = 0; j < 26; j ++){
            if(origin[j] != 0 && target[j]!=0){
                return true;
            }
        }
        return false;
    }

    public boolean checkTargetZero(int[] target){
        for(int i = 0; i < 26; i ++){
            if(target[i] != 0)return false;
        }
        return true;
    }

    public int getStickersCover(int[][] stickers, int[] target, List<Integer> lefters){
        if(checkTargetZero(target))return 0;
        int minStep = Integer.MAX_VALUE;
        for (int index:
             lefters) {
            List<Integer> left = new ArrayList<>();
            int[] temp = removeTargetCount(stickers[index], target);
            for (int ii:
                    lefters) {
                if(checkTargetExist(stickers[ii], temp)){
                    left.add(ii);
                }
            }
            int step = getStickersCover(stickers, temp, left);
            if(step != Integer.MAX_VALUE){
                minStep = Math.min(minStep, 1 + step);
            }

        }
        return minStep;
    }


    String toKey(int[] arr){
        StringBuilder sb=new StringBuilder();
        boolean allZ=true;
        for (int i=0;i<26;++i){
            int n=arr[i];
            sb.append(n).append(",");
            if (n>0)allZ=false;
        }
        if (allZ)return "";
        return sb.toString();
    }
    public int minStickers2(String[] stickers, String target) {
        if (target.isEmpty())return 0;
        int ns=stickers.length;
        int tl=target.length();
        int[][] letters=new int[ns][26];
        for (int i=0;i<stickers.length;++i){
            String s=stickers[i];
            for (char c:s.toCharArray())++letters[i][c-'a'];
        }
        int[] targetLetters=new int[27];
        for (char c:target.toCharArray())++targetLetters[c-'a'];
        targetLetters[26]=0;
        String key=toKey(targetLetters);
        int ans=0;
        if (key.isEmpty())return 0;
        Queue<int[]> q=new LinkedList<>();
        Set<String> seenKey= new HashSet<>();
        seenKey.add(key);
        q.add(targetLetters);
        while (!q.isEmpty()){
            int[]cur=q.remove();
            for (int i=0;i<ns;++i){
                int[] next=cur.clone();
                int[]letter=letters[i];
                for (int j=0;j<26;++j){
                    if (letter[j]>=0)
                        next[j]=Math.max(next[j]-letter[j], 0);
                }
                ++next[26];
                String nextKey=toKey(next);
                if (nextKey.isEmpty())return next[26];
                if (seenKey.add(nextKey)){
                    q.add(next);
                }
            }
        }
        return -1;
    }

    public static void main(String[] args){
        String[] vals = {"with", "example", "science"};
        System.out.print(new StickersSpellWord().minStickers(vals, "thehat"));
    }
}

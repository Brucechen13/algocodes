package algo.string;

public class CompareVersionNumbers {
    public int compareVersion(String version1, String version2) {
        int index1 = 0, index2 = 0;
        while (index1 < version1.length() && index2 < version2.length()){
            int index1_tmp = version1.substring(index1).indexOf(".");
            index1_tmp = (index1_tmp==-1?version1.length():index1 + index1_tmp);
            int val1 = Integer.parseInt(version1.substring(index1, index1_tmp));
            int index2_tmp = version2.substring(index2).indexOf(".");
            index2_tmp = (index2_tmp==-1?version2.length():index2 + index2_tmp);
            int val2 = Integer.parseInt(version2.substring(index2, index2_tmp));
            int cmp = Integer.compare(val1, val2);
            if(cmp != 0){
                return cmp;
            }
            index1 = index1_tmp+1;index2 = index2_tmp+1;
        }
        while(index1 < version1.length()){
            int index1_tmp = version1.substring(index1).indexOf(".");
            index1_tmp = (index1_tmp==-1?version1.length():index1 + index1_tmp);
            int val1 = Integer.parseInt(version1.substring(index1, index1_tmp));
            if(val1 > 0){
                return 1;
            }
            index1 = index1_tmp+1;
        }
        while (index2 < version2.length()){
            int index2_tmp = version2.substring(index2).indexOf(".");
            index2_tmp = (index2_tmp==-1?version2.length():index2 + index2_tmp);
            int val2 = Integer.parseInt(version2.substring(index2, index2_tmp));
            if(val2 > 0){
                return -1;
            }
            index2 = index2_tmp+1;
        }
        return 0;
    }
    public static void main(String[] args){
        System.out.println(new CompareVersionNumbers().compareVersion("01", "1.0.1"));
    }
}

package algo.string;

public class ZigZagConversion {
    public String convert(String s, int numRows) {
        if(numRows <= 1){
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < numRows; i ++){
            int index = i;
            while (index < s.length()){
                sb.append(s.charAt(index));
                if(i !=0 && i != (numRows-1)){
                    int nextIndex = index + (numRows*2-2) - index%(numRows*2-2)*2;
                    if(nextIndex < s.length()) {
                        sb.append(s.charAt(nextIndex));
                    }
                }
                index += (numRows*2-2);
            }
        }
        return sb.toString();
    }
}

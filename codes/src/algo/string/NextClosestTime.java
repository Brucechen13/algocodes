package algo.string;

public class NextClosestTime {
    public String nextClosestTime(String time) {
        int[] vals = new int[10];
        int[] ts = new int[4];
        int minValue = Integer.MAX_VALUE;
        for(int i = 0; i < time.length(); i ++){
            if(i == 2){
                continue;
            }
            vals[time.charAt(i)-'0'] = 1;
            minValue = Math.min(minValue, time.charAt(i)-'0');
            ts[i>2?i-1:i] = time.charAt(i)-'0';
        }
        boolean isFind = false;
        for(int i = 3; i >= 0; i --){
            for(int j = ts[i]+1; j < 10; j ++){
                if(vals[j] == 0)continue;
                if(i == 3){
                    ts[3] = j;
                    isFind = true;
                    break;
                }else if(i == 2){
                    if(j<6){
                        ts[2] = j;
                        ts[3] = minValue;
                        isFind = true;
                        break;
                    }
                }else if(i == 1){
                    if(ts[0]<2 || (ts[0]==2 && j<4) ||
                            (ts[0] == 2 && j==4 && minValue == 0)){
                        ts[1] = j;
                        ts[2] = ts[3] = minValue;
                        isFind = true;
                        break;
                    }
                }else {
                    if(j == 2){
                        ts[0] = 2;
                        ts[1]=ts[2]=ts[3] = minValue;
                        isFind = true;
                        break;
                    }

                }
            }
            if(isFind)break;
        }
        if(!isFind){
            ts[0]=ts[1]=ts[2]=ts[3]=minValue;
        }

        return ""+ts[0] + ts[1] +":"+ ts[2] + ts[3];
    }

    public static void main(String[] args){
        System.out.println(new NextClosestTime().nextClosestTime("20:56"));
    }
}

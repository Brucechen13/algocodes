package algo.base;

import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArraySet;

public class SearchN {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()){
            String[] vals = scanner.nextLine().split(",");
            StringBuilder sb = new StringBuilder();
            sb.append(vals[0] + ", ");
            sb.append(vals[6] + " years old, is from ");
            sb.append(vals[5] + " and is interested in ");
            sb.append(vals[3]);
            System.out.println(sb.toString());
        }
    }
}

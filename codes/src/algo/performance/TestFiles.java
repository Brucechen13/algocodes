package algo.performance;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;

class TestFiles {
    static List<String> r(Path p){
        try{
            return Files.readAllLines(p);
        }catch(Exception e){
            return new Stack<>();
        }
    }
    public static void main(String[] args) {
        String root = "/home/chenchi/codes/";

        try {
            Set<String> S = new TreeSet<>();
            Set<String> res = new TreeSet<>();
            for (String L : r(Paths.get(root, "invite.info")))
                Files.walk(Paths.get(root, L.replace(".", "/"))).
                        forEach(x -> S.addAll(r(x)));
            for (String L : r(Paths.get(root, "ban.info")))
                Files.list(Paths.get(root, L.replace(".", "/"))).
                        forEach(x -> S.removeAll(r(x)));
            S.forEach(System.out::println);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

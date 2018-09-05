package algo.string;

import java.util.*;

public class FindDuplicateFile {
    class Solution {
        class ZimuTree{
            Map<Character ,ZimuTree> next;
            List<String> vals;
            public ZimuTree(){
                next = new HashMap<Character ,ZimuTree>();
                vals = new LinkedList<String>();
            }
        }
        public List<List<String>> findDuplicate(String[] paths) {
            String path = null;
            List<List<String>> res = new LinkedList<List<String>>();
            ZimuTree root = new ZimuTree();
            for (int i = 0; i < paths.length; i++) {
                String[] files = paths[i].split(" ");
                for (int j = 1; j < files.length; j++) {
                    String[] file_content = files[j].split("\\(");
                    buildTree(root, file_content[1].substring(0, file_content[1].length()-1).toCharArray(), files[0]+"/"+file_content[0]);
                }
            }
            searchTree(res, root);
            Set<List<String>> setRes = new HashSet<List<String>>(res);
            return new ArrayList<List<String>>(setRes);

        }
        public void buildTree(ZimuTree root, char[] vals, String path){
            ZimuTree cur = root;
            int i = 0;
            while (i < vals.length){
                if(!cur.next.keySet().contains(vals[i])){
                    ZimuTree tmp = new ZimuTree();
                    cur.next.put(vals[i], tmp);
                }
                cur = cur.next.get(vals[i++]);
            }
            cur.vals.add(path);
        }
        public void searchTree(List<List<String>> res, ZimuTree root){
            ZimuTree cur = root;
            if (cur.next.size() > 0){
                if(cur.vals.size() > 1){
                    res.add(cur.vals);
                }
                for (char key:
                        cur.next.keySet()) {
                    searchTree(res, cur.next.get(key));
                }
            }
            if(cur.vals.size() > 1){
                res.add(cur.vals);
            }
        }
    }

    public static void main(String[] args){
        String[] paths = {
                "root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"
        };
    }
}

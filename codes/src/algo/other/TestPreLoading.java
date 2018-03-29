package algo.other;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TestPreLoading {
    public static String[] EmotionTypeName = {"D://comments//output//positive//",
            "D://comments//output//middle//",
            "D://comments//output//negative//"};//文件路径
    public static int EmotionTypeMaxNum = 200;//情感类别种子评论最大个数，最开始读取文件最多行数
    public static int MAXEmotionCommentSize = 1000000;//预加载单种形式的最大评论数

    public static String[] ContentsTypeName = {"D://index.txt"};
    public static int[] ContentsSubLen = {0, 5,10,15, Integer.MAX_VALUE};
    public static int ContentsTypeMaxNum = 200;//新闻类别种子评论类别最大个数，最开始读取文件最多行数
    public static int MAXContentCommentSize = 1000000;//预加载单种形式的最大评论数

    private static Map<Integer, Map<Integer, List<String>>> emotionCached = null;
    private static Map<Integer, List<String>> emotionCommentsCached = null;
    private static Map<Integer, Map<Integer, List<String>>> contentCached = null;
    private static Map<Integer, List<String>> contentCommentsCached = null;

	
	static{
		//initEmotionLoad();
		initContentLoad();
	}
	
	// 情感极性评论，读取种子文件，并进行融合生成评论
	public static void initEmotionLoad() {
        emotionCached = new HashMap<Integer, Map<Integer, List<String>>>();
        emotionCommentsCached = new HashMap<Integer, List<String>>();
        for (int itype = 0; itype < EmotionTypeName.length; itype++) {
            String fileName = EmotionTypeName[itype];
            Map<Integer, List<String>> words = new HashMap<Integer, List<String>>();
            for (int i = 1; i < 5; i++) {
                int key = i == 1 ? 0 : i;
                words.put(key, new ArrayList<String>());
                readFile(words, key, String.format(fileName + "%d.txt", key), EmotionTypeMaxNum);
            }
            emotionCached.put(itype, words);

            //组合评论
            emotionCommentsCached.put(itype, combineEmotionComments(itype));
        }
    }

    // 领域分类评论，读取种子文件，并进行融合生成评论
    public static void initContentLoad() {
        contentCached = new HashMap<Integer, Map<Integer, List<String>>>();
        contentCommentsCached = new HashMap<Integer, List<String>>();
        for (int itype = 0; itype < ContentsTypeName.length; itype++) {
            String fileName = ContentsTypeName[itype];
            Map<Integer, List<String>> words = new HashMap<Integer, List<String>>();
            for(int i = 0; i < ContentsSubLen.length; i ++) {
                int key = ContentsSubLen[i];
                words.put(key, new ArrayList<String>());
            }
            readFile(words, ContentsTypeName[itype], ContentsTypeMaxNum);
            contentCached.put(itype, words);
            List<String> comms = new LinkedList<String>();
            for (Integer key:
                    words.keySet()) {
                comms.addAll(words.get(key));
            }
            Collections.shuffle(comms);
            contentCommentsCached.put(itype, comms);
            //组合评论
            //contentCommentsCached.put(itype, combineContentComments(itype));
        }
    }
	
	
	
    private static void readFile(Map<Integer, List<String>> words, int key, String filename, int MaxNum) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null) {
                line = line.split("\\s")[0];
                //line = line.replaceAll("[\\s,$%^*(+\"\']+|[+—！!，。？、；.~@#￥%…&*（）]+", "");
                //System.out.println(line + " " + line.length());
                if (words.get(key).contains(line)) {
                    continue;
                }
                words.get(key).add(line);
            }
            if (words.get(key).size() > MaxNum) {
                Collections.shuffle(words.get(key));
                words.put(key, words.get(key).subList(0, MaxNum));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readFile(Map<Integer, List<String>> words, String filename, int MaxNum) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null) {
                line = line.split("\\s")[0];
                String temp = line.replaceAll("[\\s,$%^*(+\"\']+|[+—！!，。？、；.~@#￥%…&*（）]+", "");
                //System.out.println(line + " " + temp.length());
                int key = 0;
                for(int i = 1; i < ContentsSubLen.length; i ++){
                    if(temp.length() > ContentsSubLen[i]){
                        key = ContentsSubLen[i];
                    }else{
                        break;
                    }
                }
                if (words.get(key).contains(line)) {
                    continue;
                }
                words.get(key).add(line);
            }
            for(int i = 0; i < ContentsSubLen.length; i ++) {
                int key = ContentsSubLen[i];
                if (words.get(key).size() > MaxNum) {
                    Collections.shuffle(words.get(key));
                    words.put(key, words.get(key).subList(0, MaxNum));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> parseComments(List<List<String>> res, int size) {
        String[] chars = {"，", "！", "。", "~"};
        String[] chars_end = {"。", "！", "。。", "~", "！！！"};
        List<String> comments = new LinkedList<String>();
        Random random = new Random();
        for (List<String> com :
                res) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < com.size(); i++) {
                String item = com.get(i);
                sb.append(item);
                //sb.append("**");//test
                if (!item.matches(".*[，！。。.~!]")) {
                    if (i == com.size() - 1) {
                        int index = random.nextInt(chars_end.length);
                        sb.append(chars_end[index]);
                    } else {
                        int index = random.nextInt(chars.length);
                        sb.append(chars[index]);
                    }
                }
            }
            comments.add(sb.toString());
        }
        if (size != -1 && comments.size() > size) {
            Collections.shuffle(comments);
            comments = comments.subList(0, size);
        }
//        for (String comm:
//                comments) {
//            System.out.println(comm);
//        }
        return comments;
    }


    /**
     * 获取情感类别的评论种子
     * @param type
     * @return
     */
    private static Map<Integer, List<String>> getEmotionSeeds(int type) {
        if (emotionCached == null) {
            initEmotionLoad();
        }

        return emotionCached.get(type);
    }

    /**
     * 获取类别的评论种子
     * @param type
     * @return
     */
    private static Map<Integer, List<String>> getContentSeeds(int type) {
        if (contentCached == null) {
            initContentLoad();
        }
        return contentCached.get(type);
    }
    /**
     * 根据新闻类别返回特定类别经过各种组合的评论
     *
     * @param type        新闻类别
     * @param commentSize 评论数量
     * @return size数量的评论
     */
    private static List<String> combineEmotionComments(int type) {
        List<String> comments = new LinkedList<String>();
        Map<Integer, List<String>> words = TestPreLoading.getEmotionSeeds(type);//预加载
        List<List<String>> res = getCommentsType(words, 2, 3);
        int commentSize = MAXEmotionCommentSize;//最大评论数
        //System.out.println("2*3: " + res.size());
        int subSize = (int) (commentSize * 0.1);
        comments.addAll(parseComments(res, res.size() > subSize ? subSize : -1));
        res = getCommentsType(words, 2, 4);
        comments.addAll(parseComments(res, res.size() > subSize ? subSize : -1));
        //System.out.println("2*4: " + res.size());
        res = getCommentsType(words, 2, 0);
        comments.addAll(parseComments(res, res.size() > subSize ? subSize : -1));
        //System.out.println("2*5: " + res.size());
        res = getCommentsType(words, 3, 0);
        comments.addAll(parseComments(res, res.size() > subSize ? subSize : -1));

        int leftSize = (commentSize - comments.size()) / 3;
        res = getCommentsType(words, 2, 2, 3);
        //System.out.println("2*2*3: " + res.size());
        comments.addAll(parseComments(res, leftSize));
        res = getCommentsType(words, 2, 2, 4);
        //System.out.println("2*2*4: " + res.size());
        comments.addAll(parseComments(res, leftSize));
        res = getCommentsType(words, 2, 3, 4);
        //System.out.println("2*3*4: " + res.size());
        comments.addAll(parseComments(res, leftSize));

        Collections.shuffle(comments);
        if (comments.size() < commentSize) {
            res = getCommentsType(words, 2, 3, 0);
            //System.out.println("2*3*5: " + res.size());
            comments.addAll(parseComments(res, commentSize - comments.size()));
        }
        return comments;
    }

    private static List<String> combineContentComments(int type) {
        List<String> comments = new LinkedList<String>();
        Map<Integer, List<String>> words = TestPreLoading.getContentSeeds(type);//预加载
        List<List<String>> res = getCommentsType(words, 0, 5);
        int subSize = Integer.MAX_VALUE;
        comments.addAll(parseComments(res, res.size() > subSize ? subSize : -1));
        res = getCommentsType(words, 0, 10);
        comments.addAll(parseComments(res, res.size() > subSize ? subSize : -1));
        res = getCommentsType(words, 0, 15);
        comments.addAll(parseComments(res, res.size() > subSize ? subSize : -1));
        res = getCommentsType(words, 0, 0);
        comments.addAll(parseComments(res, res.size() > subSize ? subSize : -1));
        res = getCommentsType(words, 5, 5);
        comments.addAll(parseComments(res, res.size() > subSize ? subSize : -1));
        res = getCommentsType(words, 5, 10);
        comments.addAll(parseComments(res, res.size() > subSize ? subSize : -1));
        //subSize = (commentSize - comments.size()) / 2;
        res = getCommentsType(words, 0, 0, 5);
        comments.addAll(parseComments(res, res.size() > subSize ? subSize : -1));
        res = getCommentsType(words, 0, 0, 10);
        //subSize = commentSize - comments.size();
        comments.addAll(parseComments(res, res.size() > subSize ? subSize : -1));

        Collections.shuffle(comments);
        if(comments.size() > MAXContentCommentSize){
            comments.subList(0, MAXContentCommentSize);
        }
        return comments;
    }

    /**
     * 根据组合返回评论
     *
     * @param type 新闻类别
     * @param args 评论类别的组合
     * @return size数量的评论
     */
    private static List<List<String>> getCommentsType(Map<Integer, List<String>> words, int... args) {

        Set<List<Integer>> lens = new HashSet<List<Integer>>();
        List<List<String>> ws = new ArrayList<List<String>>();
        if (args.length == 2) {
            Integer[] ll = new Integer[]{args[0], args[1]};
            lens.add(Arrays.asList(ll));
            if (args[1] != 5) {
                ll = new Integer[]{args[1], args[0]};
            }
            lens.add(Arrays.asList(ll));
            for (List<Integer> indexs :
                    lens) {
                int index1 = indexs.get(0);
                int index2 = indexs.get(1);
                for (int i = 0; i < words.get(index1).size(); i++) {
                    for (int j = 0; j < words.get(index2).size(); j++) {
                        List<String> res = new ArrayList<String>(2);
                        res.add(words.get(index1).get(i));
                        res.add(words.get(index2).get(j));
                        ws.add(res);
                    }
                }
            }
        } else if (args.length == 3) {
            Integer[] ll = new Integer[]{args[0], args[1], args[2]};
            lens.add(Arrays.asList(ll));
            ll = new Integer[]{args[1], args[0], args[2]};
            lens.add(Arrays.asList(ll));
            ll = new Integer[]{args[0], args[2], args[1]};
            lens.add(Arrays.asList(ll));
            ll = new Integer[]{args[1], args[2], args[0]};
            lens.add(Arrays.asList(ll));
            ll = new Integer[]{args[2], args[1], args[0]};
            lens.add(Arrays.asList(ll));
            ll = new Integer[]{args[2], args[0], args[1]};
            lens.add(Arrays.asList(ll));
            for (List<Integer> indexs :
                    lens) {
                int index1 = indexs.get(0);
                int index2 = indexs.get(1);
                int index3 = indexs.get(2);
                for (int i = 0; i < words.get(index1).size(); i++) {
                    for (int j = 0; j < words.get(index2).size(); j++) {
                        for (int k = 0; k < words.get(index3).size(); k++) {
                            List<String> res = new ArrayList<String>(3);
                            res.add(words.get(index1).get(i));
                            res.add(words.get(index2).get(j));
                            res.add(words.get(index3).get(k));
                            ws.add(res);
                        }
                    }
                }
            }
        }
        return ws;
    }





    public static List<String> getEmotionComments(int type) {
        if (emotionCached == null) {
            initEmotionLoad();
        }
        return emotionCommentsCached.get(type);
    }
    public static List<String> getContentComments(int type) {
        if (contentCached == null) {
            initContentLoad();
        }
        return contentCommentsCached.get(type);
    }
}

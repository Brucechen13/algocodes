package algo.other;

import java.io.*;
import java.util.*;

public class TestComments {
    private static double[][] EmotionTypeChances = new double[][]{
            {0.85, 0.10, 0.05},//积极
            {0.6, 0.3, 0.1},//中性
            {0.05, 0.10, 0.85},//消极
    };//同组中每个类别选取概率

    private static int[][] EmotionTypes = new int[][]{
            {0, 1, 2},//积极
            {0, 1, 2},//中性
            {0, 1, 2},//消极
    };//输入类别所属的组

    /**
     * 接口函数，根据新闻类别返回size数量的评论
     * 可以根据TypeChances的比例返回同组中其他类别的评论
     *
     * @param type 新闻类别
     * @param size 评论数量
     * @return TypeChances比例的评论
     */
    public static List<String> getEmotionComments(int type, int size) {
        List<String> comments = new LinkedList<String>();
        double[] chances = EmotionTypeChances[type];
        int[] types = EmotionTypes[type];
        int index = 0;
        for (int i = 0; i < types.length; i++) {
            int s1 = (int) (size * chances[index++]);
            if (i == types.length - 1) {
                s1 = size - comments.size();
            }
            comments.addAll(getSubComments(TestPreLoading.getEmotionComments(i), s1));
        }
        return comments;
    }

    public static List<String> getContentComments(int type, int commentSize) {
        List<String> comments = getSubComments(TestPreLoading.getContentComments(type), commentSize);
        return comments;
    }

    /**
     * 根据新闻类别返回特定类别经过各种组合的评论
     *
     * @param type        新闻类别
     * @param commentSize 评论数量
     * @return size数量的评论
     */
    private static List<String> getSubComments(List<String> comments, int commentSize) {
        if (comments.size() > commentSize) {
            return comments.subList(0, commentSize);
        }
        return comments;
    }




    public static void main(String[] args) {
        //RedisTest test = new RedisTest();
        //test.getAllWords();

        boolean isMatchReg = "科技能改变一切，这是真理。".matches(".*[，！。.~!]");
        System.out.println(isMatchReg);

        System.out.println(new Date());
        List<String> comms = getContentComments(0, 10000);
        System.out.println(comms.size());
        System.out.println(new Date());
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("d://comments.txt"));
            for (String comm :
                    comms) {
                writer.write(comm + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


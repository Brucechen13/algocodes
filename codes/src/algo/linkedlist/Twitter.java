package algo.linkedlist;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicReference;

public class Twitter {

    private Map<Integer, List<int[]>> posts;
    private Map<Integer, List<Integer>> follows;

    private int timeId = 1;

    /** Initialize your data structure here. */
    public Twitter() {
        posts = new HashMap<>();
        follows = new HashMap<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {

        if(!posts.containsKey(userId)){
            posts.put(userId, new ArrayList<>());
        }
        if(!follows.containsKey(userId)){
            follows.put(userId, new ArrayList<>());
            follows.get(userId).add(userId);
        }
        posts.get(userId).add(new int[]{tweetId, timeId++});
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        if(!posts.containsKey(userId)){
            posts.put(userId, new ArrayList<>());
        }
        if(!follows.containsKey(userId)){
            follows.put(userId, new ArrayList<>());
            follows.get(userId).add(userId);
        }
        List<Integer> fls = follows.get(userId);
        Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });
        for(int ii = 0; ii < fls.size(); ii ++){
            List<int[]> post = posts.get(fls.get(ii));
            for(int i = 0; i < Math.min(10, post.size()); i ++){
                int[] p = post.get(post.size() - 1 - i);
                queue.add(p);
            }
        }
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < 10; i ++){
            if(queue.isEmpty())break;
            int[] p = queue.poll();
            res.add(p[0]);
        }
        return res;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(!posts.containsKey(followerId)){
            posts.put(followerId, new ArrayList<>());
        }
        if(!posts.containsKey(followeeId)){
            posts.put(followeeId, new ArrayList<>());
        }
        if(!follows.containsKey(followerId)){
            follows.put(followerId, new ArrayList<>());
            follows.get(followerId).add(followerId);
        }
        follows.get(followerId).add(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        follows.get(followerId).remove((Integer) followeeId);
    }

    public static void main(String[] args){
        Twitter t = new Twitter();
        t.postTweet(1, 1);
        System.out.println(t.getNewsFeed(1));
        t.follow(2,1);
        //t.postTweet(2, 6);
        System.out.println(t.getNewsFeed(2));
        t.unfollow(2,1);
        System.out.println(t.getNewsFeed(2));
    }
}

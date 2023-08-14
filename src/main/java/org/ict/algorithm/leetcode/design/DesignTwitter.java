package org.ict.algorithm.leetcode.design;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Design a simplified version of Twitter where users can post tweets,
 * follow/unfollow another user, and is able to see the 10 most recent tweets in the user's news feed.
 *
 * Implement the Twitter class:
 *
 * Twitter() Initializes your twitter object.
 * void postTweet(int userId, int tweetId) Composes a new tweet with ID tweetId by the user userId. Each call to this function will be made with a unique tweetId.
 * List<Integer> getNewsFeed(int userId) Retrieves the 10 most recent tweet IDs in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user themself. Tweets must be ordered from most recent to least recent.
 * void follow(int followerId, int followeeId) The user with ID followerId started following the user with ID followeeId.
 * void unfollow(int followerId, int followeeId) The user with ID followerId started unfollowing the user with ID followeeId.
 *
 *
 * Example 1:
 *
 * Input
 * ["Twitter", "postTweet", "getNewsFeed", "follow", "postTweet", "getNewsFeed", "unfollow", "getNewsFeed"]
 * [[], [1, 5], [1], [1, 2], [2, 6], [1], [1, 2], [1]]
 * Output
 * [null, null, [5], null, null, [6, 5], null, [5]]
 *
 * Explanation
 * Twitter twitter = new Twitter();
 * twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
 * twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5]. return [5]
 * twitter.follow(1, 2);    // User 1 follows user 2.
 * twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6).
 * twitter.getNewsFeed(1);  // User 1's news feed should return a list with 2 tweet ids -> [6, 5]. Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
 * twitter.unfollow(1, 2);  // User 1 unfollows user 2.
 * twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5], since user 1 is no longer following user 2.
 *
 *
 * Constraints:
 *
 * 1 <= userId, followerId, followeeId <= 500
 * 0 <= tweetId <= 104
 * All the tweets have unique IDs.
 * At most 3 * 104 calls will be made to postTweet, getNewsFeed, follow, and unfollow.
 * @author sniper
 * @date 18 Sep, 2022
 * LC328, Medium
 */
public class DesignTwitter {

    public static void main(String[] args) {
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
        twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5]. return [5]
        twitter.follow(1, 2);    // User 1 follows user 2.
        twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6).
        twitter.getNewsFeed(1);  // User 1's news feed should return a list with 2 tweet ids -> [6, 5]. Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
        twitter.unfollow(1, 2);  // User 1 unfollows user 2.
        twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5], since user 1 is no longer following user 2.
    }

    static class TwitterV2 {
        public TwitterV2() {}

        public void postTweet(int userId, int tweetId) {

        }

        public List<Integer> getNewsFeed(int userId) {
            return null;
        }

        public void follow(int followerId, int followeeId) {

        }

        public void unfollow(int followerId, int followeeId) {

        }
    }

    /**
     * Time Cost 11ms
     */
    static class TwitterV1 {

        public TwitterV1() {}

        public void postTweet(int userId, int tweetId) {
            follow(userId, userId);
            tweets.computeIfAbsent(userId, k -> new TreeMap<>()).putIfAbsent(time++, tweetId);
        }

        public List<Integer> getNewsFeed(int userId) {
            TreeMap<Long, Integer> top10 = new TreeMap<>();
            for (Integer id : friends.getOrDefault(userId, new HashSet<>())) {
                Map<Long, Integer> map = tweets.get(id);
                if (map == null || map.isEmpty()) {
                    continue;
                }
                for (Map.Entry<Long, Integer> e : map.entrySet()) {
                    top10.put(e.getKey(), e.getValue());
                    if (top10.size() > 10) {
                        top10.remove(top10.firstKey());
                    }
                }
            }

            LinkedList<Integer> res = new LinkedList<>();
            for (Map.Entry<Long, Integer> entry : top10.entrySet()) {
                res.addFirst(entry.getValue());
            }
            return res;
        }

        public void follow(int followerId, int followeeId) {
            friends.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
        }

        public void unfollow(int followerId, int followeeId) {
            if (followerId != followeeId) {
                friends.computeIfAbsent(followerId, k -> new HashSet<>()).remove(followeeId);
            }
        }

        private long time;

        Map<Integer, Set<Integer>> friends = new HashMap<>();

        Map<Integer, TreeMap<Long, Integer>> tweets = new HashMap<>();

    }

    /**
     * Time Cost 10ms
     */
    static class Twitter {

        public Twitter() {}

        public void postTweet(int userId, int tweetId) {
            follow(userId, userId);
            TreeMap<Long, Integer> map = tweets.getOrDefault(userId, new TreeMap<>());
            map.put(time++, tweetId);
            tweets.put(userId, map);
        }

        public List<Integer> getNewsFeed(int userId) {
            TreeMap<Long, Integer> top10 = new TreeMap<>();
            for (Integer id : friends.getOrDefault(userId, new HashSet<>())) {
                Map<Long, Integer> map = tweets.get(id);
                if (map == null || map.isEmpty()) {
                    continue;
                }
                for (Map.Entry<Long, Integer> e : map.entrySet()) {
                    top10.put(e.getKey(), e.getValue());
                    if (top10.size() > 10) {
                        top10.remove(top10.firstKey());
                    }
                }
            }

            LinkedList<Integer> res = new LinkedList<>();
            for (Map.Entry<Long, Integer> entry : top10.entrySet()) {
                res.addFirst(entry.getValue());
            }
            return res;
        }

        public void follow(int followerId, int followeeId) {
            Set<Integer> set = friends.getOrDefault(followerId, new HashSet<>());
            set.add(followeeId);
            friends.put(followerId, set);
        }

        public void unfollow(int followerId, int followeeId) {
            if (followerId != followeeId) {
                Set<Integer> set = friends.get(followerId);
                if (set == null || set.isEmpty()) {
                    return;
                }
                set.remove(followeeId);
                friends.put(followerId, set);
            }
        }

        private long time;

        Map<Integer, Set<Integer>> friends = new HashMap<>();

        Map<Integer, TreeMap<Long, Integer>> tweets = new HashMap<>();
    }

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */

}

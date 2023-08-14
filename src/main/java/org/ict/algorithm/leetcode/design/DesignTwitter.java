package org.ict.algorithm.leetcode.design;

import java.util.*;

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

    /**
     * Time Cost 10ms
     */
    static class TwitterV3 {
        public TwitterV3() {}

        public void postTweet(int userId, int tweetId) {
            users.putIfAbsent(userId, new User(userId));
            users.get(userId).post(tweetId, timestamp++);
        }

        /**
         * Retrieve the 10 most recent tweet ids in the user's news feed.
         * Each item in the news feed must be posted by users
         * who the user followed or by the user
         * herself.
         * Tweets must be ordered from most recent to least recent.
         */
        public List<Integer> getNewsFeed(int userId) {
            if (!users.containsKey(userId)) {
                return new ArrayList<>();
            }

            PriorityQueue<Tweet> maxHeap = new PriorityQueue<>((a, b) -> (int)(b.timestamp - a.timestamp));
            for (int followeeId : users.get(userId).followeeIds) {
                Tweet head = users.get(followeeId).tweetHead;
                if (head != null) {
                    maxHeap.offer(head);
                }
            }

            List<Integer> res = new ArrayList<>();
            int i = 0;
            while (!maxHeap.isEmpty() && i++ < 10) {
                Tweet t = maxHeap.poll();
                res.add(t.id);
                if (t.next != null) {
                    maxHeap.offer(t.next);
                }
            }
            return res;
        }

        public void follow(int followerId, int followeeId) {
            if (followerId == followeeId) {
                return;
            }
            users.putIfAbsent(followerId, new User(followerId));
            users.putIfAbsent(followeeId, new User(followeeId));
            users.get(followerId).follow(followeeId);
        }

        public void unfollow(int followerId, int followeeId) {
            if (followerId == followeeId) {
                return;
            }
            if (users.containsKey(followerId) && users.containsKey(followeeId)) {
                users.get(followerId).unfollow(followeeId);
            }
        }

        private long timestamp;
        /**
         * {userId : User}
         */
        private Map<Integer, User> users = new HashMap<>();
    }

    static class Tweet {
        private int id;
        private long timestamp;

        private Tweet next = null;

        public Tweet(int id, long timestamp) {
            this.id = id;
            this.timestamp = timestamp;
        }
    }

    static class User {
        private int id;
        private Set<Integer> followeeIds = new HashSet<>();
        private Tweet tweetHead = null;

        public User(int id) {
            this.id = id;
            follow(id);
        }

        public void follow(int followeeId) {
            followeeIds.add(followeeId);
        }

        public void unfollow(int followeeId) {
            followeeIds.remove(followeeId);
        }

        public void post(int tweetId, long timestamp) {
            Tweet oldHead = tweetHead;
            tweetHead = new Tweet(tweetId, timestamp);
            tweetHead.next = oldHead;
        }

    }

    /**
     * Time Cost 11ms
     */
    static class TwitterV2 {
        public TwitterV2() {}

        public void postTweet(int userId, int tweetId) {
            follow(userId, userId);
            tweets.computeIfAbsent(userId, k -> new ArrayList<>()).add(new Pair<>(time++, tweetId));
        }

        public List<Integer> getNewsFeed(int userId) {
            PriorityQueue<Pair<Long, Integer>> maxHeap = new PriorityQueue<>((o1, o2) -> {
                if (o1.first > o2.first) {
                    return -1;
                } else if (o1.first < o2.first) {
                    return 1;
                } else {
                    return 0;
                }
            });

            for (Integer id : friends.getOrDefault(userId, new HashSet<>())) {
                for (Pair<Long, Integer> p : tweets.getOrDefault(id, new ArrayList<>())) {
                    maxHeap.offer(p);
                }
            }

            List<Integer> res = new ArrayList<>();
            int i = 0;
            while (!maxHeap.isEmpty()) {
                Pair<Long, Integer> p = maxHeap.poll();
                res.add(p.second);
                if (i == 9) {
                    return res;
                }
                i++;
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

        Map<Integer, List<Pair<Long, Integer>>> tweets = new HashMap<>();
    }

    static class Pair<T1, T2> {
        private T1 first;

        private T2 second;

        private Pair() {}

        public Pair(T1 first, T2 second) {
            this.first = first;
            this.second = second;
        }

        public T1 getFirst() {
            return first;
        }

        public T2 getSecond() {
            return second;
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

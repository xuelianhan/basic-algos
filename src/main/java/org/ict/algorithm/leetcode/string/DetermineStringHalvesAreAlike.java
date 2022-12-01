package org.ict.algorithm.leetcode.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * You are given a string s of even length.
 * Split this string into two halves of equal lengths,
 * and let a be the first half and b be the second half.
 *
 * Two strings are alike if they have the same number of vowels ('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U').
 * Notice that s contains uppercase and lowercase letters.
 *
 * Return true if a and b are alike. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "book"
 * Output: true
 * Explanation: a = "bo" and b = "ok". a has 1 vowel and b has 1 vowel. Therefore, they are alike.
 * Example 2:
 *
 * Input: s = "textbook"
 * Output: false
 * Explanation: a = "text" and b = "book". a has 1 vowel whereas b has 2. Therefore, they are not alike.
 * Notice that the vowel o is counted twice.
 *
 *
 * Constraints:
 *
 * 2 <= s.length <= 1000
 * s.length is even.
 * s consists of uppercase and lowercase letters.
 * @author sniper
 * @date 2022/8/16
 * LC1704
 */
public class DetermineStringHalvesAreAlike {

    public static void main(String[] args) {
        String s = "gfLpdQfBhLSNWKBvRWpNlRWTSMQYTSyPFTwWHptvnJHFWQDQLdYyHzKJjYrpHbNQyPFWpKhChZXsvYfPBVMpRSfLZwHMBqsbPhZBhwfmjDqgXVkZrtyXbpXWVLRnpGPWjvKNHmxqxPSlvxxsxsnbQvKJDwKtWgFDrjsgvTScXYPsMBgkWktkdthwsQdCpddrgksxlZMYDcPyvMLqztnYGQbrKcKPWqtjdklXZBvNbZfNdNRmbDGpxybGdzghpSmGvmZGpJlfwTbLhQXZSfgSJTNvrQGdWyQgJjngKLXNpkMtFWgpcrYHZHJdgDfmkfplDYjWRmBNyFNzgGbRcGBQXWcskPpXPlBkdsVwRMNZCLvkdXwDrlcTTNPPMvjPChWHQPJMPSLSzQLlkQWrmLLnknVdWKrYZRymTQTRDbsgtFjZQjMNdrZVqQdBdywVqSWkkHHmbrwnlzXwYCpbfJSxBPdwDjKQFgYPChQWdJTHRVYRDrLtswMnTNQCjZNsqZBpXjZxWKblqZFxtZgHCjYsbqJZFjQJZlFptgMXVDykQpHlmPzxpKnQNtYDJNhHZkMLVCXJjgRGYwCbNGmkqgRkYjzpBMJHRLkbsgXpMkMWCDncmGXBxzZsSrGshcYKClqTyZPcGBJthqXjVlJWNYtPgXkFQSxXxGwsvbgPQQZQfllFfQbXMCkqXtTxDlSkgBGfVSSfWCwbzgFnLlMKLQgccrQSyxRyqyXvCzCBGdzPhxLnvJMyDhpWXWNFXwcwHCCMsccvrxbtsjcThqsLMrgkxlLLGKCbtdHqvBKjxlmntDrvCKxwpMrWZycsvDjCRjPXQPZxmvHnxGWpBqkJCkcqfmyRHPSgGxxkHgSLXNsfVxQRwbftyCxvzHrCzXKXfghSwTMpDzBhmjXLdxFCfpSggVkTVFPQTJCrCwfyVLNQGSLJKVRKtHCwHMNyclLNHHZTzbLJdtkQRzrPVgXSLhJKVZlqYVzPsmwZYPmqKhQC";
        boolean result = halvesAreAlike(s);
        System.out.println(result);
    }

    public static boolean halvesAreAlikeV1(String s) {
        Set<Character> set = Stream.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U').collect(Collectors.toSet());
        int l = 0, r = 0, half = s.length() / 2;
        int i = 0, j = half;
        while (i < half) {
            if (set.contains(s.charAt(i++))) {
                l++;
            }
            if (set.contains(s.charAt(j++))) {
                r++;
            }
        }
        return l == r;
    }


    public static boolean halvesAreAlike(String s) {
        Set<Character> vowels = new HashSet<>(Arrays.asList(new Character[] {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'}));
        int leftVowelCnt = 0;
        int rightVowelCnt = 0;
        int half = s.length() / 2;
        for (int i = 0, j = half; i < half && j < s.length(); i++, j++) {
            if (vowels.contains(s.charAt(i))) {
                leftVowelCnt++;
            }
            if (vowels.contains(s.charAt(j))) {
                rightVowelCnt++;
            }
        }
        //System.out.println("leftVowelCnt:" + leftVowelCnt);
        //System.out.println("rightVowelCnt:" + rightVowelCnt);
        /**
         * No need to judge zero
         * <code>if (leftVowelCnt == 0 || rightVowelCnt == 0) </code>
         */
        return (leftVowelCnt == rightVowelCnt);
    }


}

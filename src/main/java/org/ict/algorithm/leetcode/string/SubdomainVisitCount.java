package org.ict.algorithm.leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A website domain "discuss.leetcode.com" consists of various subdomains.
 * At the top level, we have "com", at the next level,
 * we have "leetcode.com" and at the lowest level, "discuss.leetcode.com".
 * When we visit a domain like "discuss.leetcode.com",
 * we will also visit the parent domains "leetcode.com" and "com" implicitly.
 * A count-paired domain is a domain that has one of the two formats "rep d1.d2.d3" ,
 * or "rep d1.d2" where rep is the number of visits to the domain and d1.d2.d3 is the domain itself.
 * For example, "9001 discuss.leetcode.com" is a count-paired domain that indicates that discuss.leetcode.com was visited 9001 times.
 * Given an array of count-paired domains cp-domains,
 * return an array of the count-paired domains of each subdomain in the input.
 * You may return the answer in any order.
 *
 * Example 1:
 * Input: cp-domains = ["9001 discuss.leetcode.com"]
 * Output: ["9001 leetcode.com","9001 discuss.leetcode.com","9001 com"]
 * Explanation: We only have one website domain: "discuss.leetcode.com".
 * As discussed above, the subdomain "leetcode.com" and "com" will also be visited.
 * So they will all be visited 9001 times.
 *
 * Example 2:
 * Input: cp-domains = ["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
 * Output: ["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]
 * Explanation: We will visit "google.mail.com" 900 times, "yahoo.com" 50 times, "intel.mail.com" once and "wiki.org" 5 times.
 * For the subdomains, we will visit "mail.com" 900 + 1 = 901 times, "com" 900 + 50 + 1 = 951 times, and "org" 5 times.
 *
 *
 * Constraints:
 * 1 <= cp-domain.length <= 100
 * 1 <= cp-domain[i].length <= 100
 * cp-domain[i] follows either the "repi d1i.d2i.d3i" format or the "repi d1i.d2i" format.
 * repi is an integer in the range [1, 104].
 * d1i, d2i, and d3i consist of lowercase English letters.
 *
 * @author sniper
 * @date 25 Apr, 2023
 * LC811, Medium, frequency=76
 */
public class SubdomainVisitCount {

    public static void main(String[] args) {
        String[] cpdomains = {"9001 discuss.leetcode.com"};
        SubdomainVisitCount instance = new SubdomainVisitCount();
        List<String> res = instance.subdomainVisits(cpdomains);
        res.forEach(System.out::println);
    }

    public List<String> subdomainVisitsV1(String[] cpdomains) {
        //todo
        return null;
    }

    /**
     * Keep using map.put(key, map.getOrDefault(key, 0) + cnt);
     * @param cpdomains
     * @return
     */
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> freq = new HashMap<>();
        for (String domain : cpdomains) {
            String[] arr = domain.split("\\s+");
            Integer cnt = Integer.parseInt(arr[0]);
            freq.put(arr[1], freq.getOrDefault(arr[1], 0) + cnt);
            /**
             * Notice using \\. instead of .
             */
            String[] dArr = arr[1].split("\\.");
            if (dArr.length == 3) {
                String d1 = dArr[1] + "." + dArr[2];
                freq.put(d1, freq.getOrDefault(d1, 0) + cnt);
                freq.put(dArr[2], freq.getOrDefault(dArr[2], 0) + cnt);
            } else if (dArr.length == 2) {
                freq.put(dArr[1], freq.getOrDefault(dArr[1], 0) + cnt);
            }
        }

        return freq.entrySet().stream()
                .map(entry -> entry.getValue() + " " + entry.getKey())
                .collect(Collectors.toList());
    }
}

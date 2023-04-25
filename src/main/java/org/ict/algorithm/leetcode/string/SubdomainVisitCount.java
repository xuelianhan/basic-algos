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

    /**
     * Understanding the following solution
     * # In Python both %s and %d are placeholders for a string and a number respectively.
     * # %s will return the string and %d will return number, the values are passed using % operator.
     * # This % operator formatting is used in C language also.
     * e.g.
     * # name = 'Robey’
     * # number = 454
     * # print '%s %d' % (name, number)
     *
     * def subdomainVisits(self, cpdomains):
     *        count = collections.Counter()
     *         for cd in cpdomains:
     *             n, s = cd.split()
     *             count[s] += int(n)
     *             for i in range(len(s)):
     *                 if s[i] == '.':
     *                     count[s[i + 1:]] += int(n)
     *         return ["%d %s" % (count[k], k) for k in count]
     * --------------------------------------------------------
     * # print('.'.join(domains[i:])) likes the following:
     * # com
     * # leetcode.com
     * # discuss.leetcode.com
     *
     * def subdomainVisits(self, cpdomains: List[str]) -> List[str]:
     *     count = collections.Counter()
     *
     *     for cpdomain in cpdomains:
     *       num, domains = cpdomain.split()
     *       num, domains = int(num), domains.split('.')
     *       for i in reversed(range(len(domains))):
     *         #print('.'.join(domains[i:]))
     *         count['.'.join(domains[i:])] += num
     *
     *     return [str(freq) + ' ' + domain for domain, freq in count.items()]
     * ---------------------------------------------------------
     * # The following return statement is OK too:
     * # return ["%d %s" % (count[k], k) for k in count]
     * def subdomainVisits(self, cpdomains: List[str]) -> List[str]:
     *         count = collections.Counter()
     *         for cpdomain in cpdomains:
     *             num, domain = cpdomain.split()
     *             count[domain] += int(num)
     *             for i in range(len(domain)):
     *                 if domain[i] == '.':
     *                     count[domain[i + 1:]] += int(num)
     *
     *         return [str(freq) + ' ' + domain for domain, freq in count.items()]
     *
     * @param cpdomains
     * @return
     */
    public List<String> subdomainVisitsV3(String[] cpdomains) {
        Map<String, Integer> freq = new HashMap<>();
        for (String cpDomain : cpdomains) {
            int space = cpDomain.indexOf(" ");
            int num = Integer.valueOf(cpDomain.substring(0, space));
            String domain = cpDomain.substring(space + 1);

            for (int i = 0; i < domain.length(); i++) {
                if (domain.charAt(i) == '.') {
                    String subDomain = domain.substring(i + 1);
                    freq.put(subDomain, freq.getOrDefault(subDomain, 0) + num);
                }
            }
            freq.put(domain, freq.getOrDefault(domain, 0) + num);
        }

        List<String> res = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : freq.entrySet()) {
            res.add(entry.getValue() + " " + entry.getKey());
        }
        return res;
    }

    /**
     * Understanding the following solution
     * @param cpdomains
     * @return
     */
    public List<String> subdomainVisitsV2(String[] cpdomains) {
        Map<String, Integer> freq = new HashMap<>();
        for (String cpDomain : cpdomains) {
            int space = cpDomain.indexOf(" ");
            int num = Integer.valueOf(cpDomain.substring(0, space));
            String domain = cpDomain.substring(space + 1);

            for (int i = 0; i < domain.length(); i++) {
                if (domain.charAt(i) == '.') {
                    String subDomain = domain.substring(i + 1);
                    freq.put(subDomain, freq.getOrDefault(subDomain, 0) + num);
                }
            }
            freq.put(domain, freq.getOrDefault(domain, 0) + num);
        }
        return freq.entrySet().stream()
                .map(entry -> entry.getValue() + " " + entry.getKey())
                .collect(Collectors.toList());
    }

    /**
     * Understanding the following solution
     *
     * Time Cost 16ms
     *
     * @param cpdomains
     * @return
     */
    public List<String> subdomainVisitsV1(String[] cpdomains) {
        Map<String, Integer> freq = new HashMap<>();
        for (String cpDomain : cpdomains) {
            int space = cpDomain.indexOf(" ");
            int num = Integer.valueOf(cpDomain.substring(0, space));
            String domain = cpDomain.substring(space + 1);

            freq.merge(domain, num, Integer::sum);

            for (int i = 0; i < domain.length(); i++) {
                if (domain.charAt(i) == '.') {
                    String subDomain = domain.substring(i + 1);
                    freq.merge(subDomain, num, Integer::sum);
                }
            }
        }
        return freq.entrySet().stream()
                .map(entry -> entry.getValue() + " " + entry.getKey())
                .collect(Collectors.toList());
    }

    /**
     * Keep using map.put(key, map.getOrDefault(key, 0) + cnt);
     * @param cpdomains
     * @return
     */
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> freq = new HashMap<>();
        for (String cpDomain : cpdomains) {
            String[] arr = cpDomain.split("\\s+");
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

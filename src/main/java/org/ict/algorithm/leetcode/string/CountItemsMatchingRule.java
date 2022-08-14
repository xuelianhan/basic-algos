package org.ict.algorithm.leetcode.string;

import java.util.List;

/**
 *
 * @author sniper
 * @date 14 Aug, 2022
 * LC1773
 */
public class CountItemsMatchingRule {

    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int cnt = 0;
        for (List<String> list : items) {
            String type = list.get(0);
            String color = list.get(1);
            String name = list.get(2);
            if ("type".equals(ruleKey)) {
                if (type.equals(ruleValue)) {
                    cnt++;
                }
            }
            if ("color".equals(ruleKey)) {
                if (color.equals(ruleValue)) {
                    cnt++;
                }
            }
            if ("name".equals(ruleKey)) {
                if (name.equals(ruleValue)) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}

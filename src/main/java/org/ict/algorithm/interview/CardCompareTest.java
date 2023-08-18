package org.ict.algorithm.interview;

import java.util.*;

/**
 *
 * 4 种 花色，
 * 牌点数由小到大  2,3,4,5,6,7,8,9,10, J, Q, K, A
 * 花色: w,x,y,z
 * 一副扑克
 *     1. 皇家同花顺   10，J, Q, K, A 花色一样
 *     2. 同花顺         5 6 7 8 9        花色一样
 *     3. 四条            5 5 5 5 7        四张点数一样
 *     4. 葫芦            5 5 5 6 6        三条+对子(66655)
 *     5. 同花                             花色一样
 *     6. 顺子            5 6 7 8 9        花色不一样，顺子
 *     7. 三条            5 5 5 7 8         三张点数一样
 *     8. 两对            5 5 6 6 7
 *     9. 一对            5 5 6 7 8
 *     10. 高牌            2 6 7 9 A        A 最大，2最小，花色不一样
 * 测试用例:
 *
 * T1: "x2 y3 y4 y6 y7"
 * T2: "x2 x3 x8 x9 xA"
 * T2
 *
 *
 * T1: "x2 y2 z2 y6 y7"
 * T2: "x2 z2 x3 y3 yA"
 * T1
 *
 *
 * @author sniper
 * @date 18 Aug 2023
 */
public class CardCompareTest {

    private static final Set<String> TOP_SET = new HashSet<>();
    public int compare(String[] arr1, String[] arr2) {
        Card c1 = convert(arr1);
        Card c2 = convert(arr2);

        return 0;
    }

    private Card convert(String[] arr) {
        int n = arr.length;
        //10，J, Q, K, A
        TOP_SET.add("10");
        TOP_SET.add("J");
        TOP_SET.add("Q");
        TOP_SET.add("K");
        TOP_SET.add("A");
        Set<Character> colors = new HashSet<>();
        Map<Character, Integer> freq = new HashMap<>();
        List<Character> codes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] item = arr[i].toCharArray();
            colors.add(item[0]);
            codes.add(item[1]);
            freq.put(item[1], freq.getOrDefault(item[1], 0) + 1);

        }
        Card card = new Card(colors, codes, freq);
        // Check which group of this card in?
        fillGroup(card);
        // Compare rules here.
        return card;
    }

    private void fillGroup(Card card) {
        /**
         * 花色一样
         * 1. 皇家同花顺   10，J, Q, K, A 花色一样
         * 2. 同花顺       5 6 7 8 9        花色一样
         * 5. 同花                          花色一样
         */
        if (card.colors.size() == 1) {
            if (isTopSequence(card.codes)) {
                card.setGroup(1);
            } else if (isSameColorSeq(card.codes)) {
                card.setGroup(2);
            } else {
                card.setGroup(5);
            }
        } else {
            /**
             *  3. 四条            5 5 5 5 7        四张点数一样
             *  4. 葫芦            5 5 5 6 6        三条+对子(66655)
             *  6. 顺子            5 6 7 8 9        花色不一样，顺子
             *  7. 三条            5 5 5 7 8         三张点数一样
             *  8. 两对            5 5 6 6 7
             *  9. 一对            5 5 6 7 8
             *  10. 高牌            2 6 7 9 A        A 最大，2最小，花色不一样
             */
        }
    }



    private boolean isTopSequence(List<Character> codes) {
        for (char ch : codes) {
            if (!TOP_SET.contains(ch)) {
                return false;
            }
        }
        return true;
    }

    private boolean isSameColorSeq(List<Character> codes) {
        return true;
    }


    static class Card {
        /**
         *
         */
        private Set<Character> colors;

        private List<Character> codes;

        private Map<Character, Integer> freq;

        private int group;

        public Card(Set<Character> colors, List<Character> codes, Map<Character, Integer> freq) {
            this.colors = colors;
            this.codes = codes;
            this.freq = freq;
        }

        public int getGroup() {
            return group;
        }

        public void setGroup(int group) {
            this.group = group;
        }

    }


}

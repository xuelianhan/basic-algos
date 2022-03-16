package org.ict.algorithm.leetcode.string;


import java.util.Stack;

/**
 * Given a string s and an integer k,
 * reverse the first k characters for every 2k characters counting from the start of the string.
 *
 * If there are fewer than k characters left,
 * reverse all of them.
 *
 * If there are less than 2k but greater than or equal to k characters,
 * then reverse the first k characters and leave the other as original.
 *
 *
 *
 * Example 1:
 * Input: s = "abcdefg", k = 2
 * Output: "bacdfeg"
 *
 * Example 2:
 * Input: s = "abcd", k = 2
 * Output: "bacd"
 *
 * Example3:
 * Input:s = "abc", k = 2
 * Output: "bac"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^4
 * s consists of only lowercase English letters.
 * 1 <= k <= 10^4
 * @author sniper
 * @date 2022/3/10
 */
public class ReverseStringII {

    /**
     *
     * input: k = 20
     * "krmyfshbspcgtesxnnljhfursyissjnsocgdhgfxubewllxzqhpasguvlrxtkgatzfybprfmmfithphckksnvjkcvnsqgsgosfxc"
     * output:
     * "jlnnxsetgcpsbhsfymrkhfursyissjnsocgdhgfxtxrlvugsaphqzxllwebukgatzfybprfmmfithphckksnvjkcvnsqgsgosfxc"
     * expected:
     * "jlnnxsetgcpsbhsfymrkhfursyissjnsocgdhgfxtxrlvugsaphqzxllwebukgatzfybprfmmfithphccxfsogsgqsnvckjvnskk"
     *
     *
     * input: k=39
     * hyzqyljrnigxvdtneasepfahmtyhlohwxmkqcdfehybknvdmfrfvtbsovjbdhevlfxpdaovjgunjqlimjkfnqcqnajmebeddqsgl
     * output:
     * fdcqkmxwholhytmhafpesaentdvxginrjlyqzyhehybknvdmfrfvtbsovjbdhevlfxpdaovjgunjqlimjkfnqcqnajmebeddqsgl
     * expected: 39,39,22
     * fdcqkmxwholhytmhafpesaentdvxginrjlyqzyhehybknvdmfrfvtbsovjbdhevlfxpdaovjgunjqllgsqddebemjanqcqnfkjmi
     *
     *
     * input: abcdefg, k= 4;
     * output:
     * dcbagfe
     * expected: 4,3
     * dcbaefg
     *
     * @param args
     */
    public static void main(String[] args) {
        String s = "uxzpsogzkwgwacxxvvzlhkaahjaqagdfjkmyutvhxclzskvxckjvfgzptlzldjwhrpocfugzqkpaxexezbvggtkoxriysqivupofrcoxbrdgccvphvdtvrjtsbospmgyfduvaslnvxwuepleziodaaqmonsxjszyjwjmvgdqgowjjtwdmynvirnlujimedfyntgacntvyqujvehhvruiolfkeprqpzdvmapeukemmzxdtyolxeixatgsupvpidmeyifjyxkzudxvsunghtklzgxsjhrxgxgqcdebukrarpkpqmusempvulagashxpaisfvetrmiqiordsyjgyjmkvavxorrmnxbiikuxmezpkhgkjzmapldnmjvfxtmckskiwhdnuqpqrsrdspxuixxnibjxoyagijmlbhjtuchzbdpaxommfvlbpxfnzkkcdentdhhxracunvrtqxrbqanufaglncjqiwofanuznfmbtjalehlqidtcmqbsgppqyoaoglifareljluigqyxtveukstzepridpmdltpxjmzdvatgzmqexrauywreoslyoydmiyipyqiaihfjqncelefiaxjhdaamrvahbvoznsfvsdknlktsifioxjdsqldzlyzqkqxkwjfrehqbhlaanbcvxomxyypqfxbwmtaiegcjlzeslmpghirzsaprxdcbobflvbupwahxwjgrcqskewvjsjyyggozkvwwytrwpmuguclssmrshlwukkjapiwnkybydergdqkhttbakooghbskiqlesocfrjuxotecnhkfmwtmzcysppmffnskvfabunfzsibqrerfstonzjhtcpnscpteflsnmqqphelpngnlnczritcjxewlhftujrpaeaxylqkswaisvzgciaemvodvcnqtuwcjkmzjzkikaqifymwwlvyxndgwwlauwiyiflgoahyaavkudvemfftzwlxdltwicouwboeaddxmvind";
        int k = 22;
        //String s = "hyzqyljrnigxvdtneasepfahmtyhlohwxmkqcdfehybknvdmfrfvtbsovjbdhevlfxpdaovjgunjqlimjkfnqcqnajmebeddqsgl";
        //int k = 39;
        //String s = "abcdefg";
        //int k = 4;
        //String s = "krmyfshbspcgtesxnnljhfursyissjnsocgdhgfxubewllxzqhpasguvlrxtkgatzfybprfmmfithphckksnvjkcvnsqgsgosfxc";
        //int k = 20;
        System.out.println(s.length());
        System.out.println(s);
        String result = reverseStr(s, k);
        System.out.println(result);
    }

    /**
     * Recommend this solution, it's very concise and clear
     * @param s
     * @param k
     * @return
     */
    public String reverseStrV2(String s, int k) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        int i = 0;
        while(i < n) {
            int j = Math.min(i + k - 1, n - 1);
            swap(arr, i, j);
            i += 2 * k;
        }
        return String.valueOf(arr);
    }
    private void swap(char[] arr, int l, int r) {
        while (l < r) {
            char temp = arr[l];
            arr[l++] = arr[r];
            arr[r--] = temp;
        }
    }

    public static String reverseStr(String s, int k) {
        if (s.length() == 1 || k == 1) {
            return s;
        }
        if (s.length() <= k) {
            return reverseString(s);
        }
        int i = 0;
        int j = k;
        StringBuffer sb = new StringBuffer();
        boolean flip = true;
        for(; i < s.length() && j < s.length(); ) {
            if (flip) {
                String rs = reverseString(s.substring(i, j));
                sb.append(rs);
                flip = false;
                //System.out.println("rs:" + rs);
            } else {
                String rs = s.substring(i, j);
                sb.append(rs);
                //System.out.println("rs:" + rs);
                flip = true;
            }
            //System.out.println("sb:" + sb);
            i = j;
            j = i + k;
        }
        if ((s.length() - i) <= k && flip) {
            String tail = reverseString(s.substring(i, s.length()));
            sb.append(tail);
        } else {
            String tail = s.substring(i, s.length());
            sb.append(tail);
        }

        return sb.toString();
    }

    public static String reverseString(String s) {
        if (s == null || s.length() == 0 || s.length() == 1) {
            return s;
        }
        StringBuffer sb = new StringBuffer();
        sb.append(s);
        return sb.reverse().toString();
    }
}

package org.ict.algorithm.leetcode.array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * In a special ranking system,
 * each voter gives a rank from highest to lowest to all teams participating in the competition.
 * The ordering of teams is decided by who received the most position-one votes.
 * If two or more teams tie in the first position, we consider the second position to resolve the conflict,
 * if they tie again, we continue this process until the ties are resolved.
 * If two or more teams are still tied after considering all positions,
 * we rank them alphabetically based on their team letter.
 * You are given an array of strings votes which is the votes of all voters in the ranking systems.
 * Sort all teams according to the ranking system described above.
 * Return a string of all teams sorted by the ranking system.
 *
 * Example 1:
 * Input: votes = ["ABC","ACB","ABC","ACB","ACB"]
 * Output: "ACB"
 * Explanation:
 * Team A was ranked first place by 5 voters. No other team was voted as first place, so team A is the first team.
 * Team B was ranked second by 2 voters and ranked third by 3 voters.
 * Team C was ranked second by 3 voters and ranked third by 2 voters.
 * As most of the voters ranked C second, team C is the second team, and team B is the third.
 *
 * Example 2:
 * Input: votes = ["WXYZ","XYZW"]
 * Output: "XWYZ"
 * Explanation:
 * X is the winner due to the tie-breaking rule. X has the same votes as W for the first position,
 * but X has one vote in the second position, while W does not have any votes in the second position.
 * Example 3:
 *
 * Input: votes = ["ZMNAGUEDSJYLBOPHRQICWFXTVK"]
 * Output: "ZMNAGUEDSJYLBOPHRQICWFXTVK"
 * Explanation: Only one voter, so their votes are used for the ranking.
 * Constraints:
 * 1 <= votes.length <= 1000
 * 1 <= votes[i].length <= 26
 * votes[i].length == votes[j].length for 0 <= i, j < votes.length.
 * votes[i][j] is an English uppercase letter.
 * All characters of votes[i] are unique.
 * All the characters that occur in votes[0] also occur in votes[j] where 1 <= j < votes.length.
 * @author sniper
 * @date 14 May 2023
 * LC1366, Medium, frequency=22
 */
public class RankTeamsByVotes {

    public String rankTeamsV1(String[] votes) {
        //todo
        return null;
    }

    /**
     * Understanding the following solution
     * Time Cost 4ms
     * e.g. votes = ["ABC","ACB","ABC","ACB","ACB"]
     *
     * @param votes
     * @return
     */
    public String rankTeams(String[] votes) {
        Team[] teams = new Team[26];
        int teamSize = votes[0].length();
        for (int i = 0; i < 26; i++) {
            teams[i] = new Team((char)('A' + i), teamSize);
        }

        for (String s : votes) {
            for (int i = 0; i < teamSize; i++) {
                teams[s.charAt(i) - 'A'].rank[i]++;
            }
        }
        Arrays.sort(teams, new Comparator<Team>() {
            @Override
            public int compare(Team o1, Team o2) {
                for (int i = 0; i < o1.rank.length; i++) {
                    if (o1.rank[i] > o2.rank[i]) {
                        return -1;
                    } else if (o1.rank[i] < o2.rank[i]) {
                        return 1;
                    }
                }
                return o1.name - o2.name;
            }
        });

        StringBuilder res = new StringBuilder();
        /**
         * Notice, there are only teamSize teams, not 26 teams
         * So the following codes using teamSize instead of teams.length.
         * e.g. votes = ["ABC","ACB","ABC","ACB","ACB"]
         * There are only 3 teams: A, B, C
         */
        for (int i = 0; i < teamSize; i++) {
            res.append(teams[i].name);
        }
        return res.toString();
    }

    static class Team {
        private char name;

        private int[] rank;

        public Team(char name, int size) {
            this.name = name;
            this.rank =new int[size];
        }
    }
}

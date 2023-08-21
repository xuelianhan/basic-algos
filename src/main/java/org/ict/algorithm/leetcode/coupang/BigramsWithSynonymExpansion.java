package org.ict.algorithm.leetcode.coupang;

import java.util.*;

/**
 * Given a list of synonyms and an input sentence,
 * generate all unique bigrams with synonyms expansion.
 * Return the bigrams in sorted order.
 * ---------------------
 * Bigram is a sequence of two adjacent elements from string of words.
 * In search, we often create bigrams from texts for the purpose of indexing.
 * e.g. "the sofa is too big" can generate bigrams:
 * ["the sofa", "sofa is", "is too", "too big"].
 * However, some words are synonyms expansion to each other.
 * e.g. "big" is similar with "large". Thus, we also do
 * synonym expansion to include synonyms in the bigrams, and we would
 * generate bigrams: ["the sofa", "sofa is", "is too", "too big", "too large"]
 * Synonyms could also contain multiple words.
 * e.g. "fed up" -> "annoyed". "i am fed up with you" with synonyms expansion
 * will generate bigrams like
 * ["i am", "am fed", "fed up", "up with", "with you", "am annoyed", "annoyed with"]
 * Similarly, "i am annoyed with you" with synonyms expansion will generate the same bigrams:
 * ["i am", "am fed", "fed up", "up with", "with you", "am annoyed", "annoyed with"]
 * -----------------------
 * Conditions:
 * -- sentence only contains lower case alphabet and space.
 * -- synonyms: a list of string in the format of <synonym1>, <synonym2>.
 * Each synonym only contains lower case alphabet and space.
 * e.g.: ["big,large", "fed up,annoyed"].
 * One synonym will only show up once in synonym list.
 * And there will not be nested synonyms---if you have a synonym like "A,B,X", neither A or B can be
 * synonyms by itself.
 * -- There won't be two consecutive synonyms in the input sentence.
 * Example(python):
 * generateBigrams('the sofa is too big', ['big,large']) will return
 * ['is too', 'sofa is', 'the sofa', 'too big', 'too large']
 *
 * @author sniper
 * @date 17 Aug 2023
 */
public class BigramsWithSynonymExpansion {

    /**
     * The function takes two arguments: the input sentence and the list of synonyms.
     * The first step is to split the input sentence into words.
     * If the list of synonyms is empty, then the function simply generates all bigrams of the input sentence.
     * Otherwise, the function builds a map of synonyms.
     * The key of the map is a word, and the value of the map is a set of synonyms for that word.
     * The function then generates bigrams with synonyms expansion.
     * For each word in the input sentence, the function checks if it is a synonym.
     * If it is, then the function adds all the synonyms of that word to the bigram.
     * Finally, the function sorts the list of bigrams and returns it.
     *
     * @param sentence
     * @param synonyms
     * @return
     */
    public List<String> generateBigrams(String sentence, String[] synonyms) {
        if (null == sentence || sentence.length() == 0) {
            return new ArrayList<>();
        }
        String[] words = sentence.split("\\+s");
        List<String> res = new ArrayList<>();
        if (null == synonyms || synonyms.length == 0) {
            for (int i = 1; i < words.length; i++) {
                res.add(words[i - 1] + " " + words[i]);
            }
            Collections.sort(res);
            return res;
        }
        // Build a map of synonyms.
        Map<String, Set<String>> synonymMap = new HashMap<>();
        for (String synonym : synonyms) {
            String[] parts = synonym.split(",");
            for (String part : parts) {
                synonymMap.putIfAbsent(part, new HashSet<>());
                synonymMap.get(part).add(synonym);
            }
        }

        // Generate bigrams with synonyms expansion.
        for (int i = 1; i < words.length; i++) {
            String curWord = words[i];
            String prevWord = words[i - 1];

            // If the current word is a synonym, expand it.
            if (synonymMap.containsKey(curWord)) {
                for (String synonym : synonymMap.get(curWord)) {
                    res.add(prevWord + " " + synonym);
                }
            } else {
                res.add(prevWord + " " + curWord);
            }
        }
        Collections.sort(res);
        return res;
    }

}

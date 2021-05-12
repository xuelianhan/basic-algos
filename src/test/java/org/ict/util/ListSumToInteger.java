package org.ict.util;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * https://stackoverflow.com/questions/23110853/java8-sum-values-from-specific-field-of-the-objects-in-a-list
 * https://www.websparrow.org/java/java-8-stream-sum-of-list-of-integers
 */
public class ListSumToInteger {

    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Student s = new Student();
            s.setName("s" + i);
            s.setScore(i);
            list.add(s);
        }
        list.stream().filter(item -> item.getScore() > 0).mapToInt(item -> item.getScore()).sum();
    }

    static class Student {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        private int score;
    }

}

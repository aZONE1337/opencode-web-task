package ru.shchekalev.opencodewebtask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> answerOptions = new ArrayList<>(Arrays.asList("a", "b", "c"));
        List<String> userAnswers = new ArrayList<>(Arrays.asList("a", "c"));

        userAnswers.removeAll(answerOptions);
        System.out.println(userAnswers.toString());

        Integer test = 1;

        if (test++ == 2) {
            System.out.println(test);
        }
    }
}

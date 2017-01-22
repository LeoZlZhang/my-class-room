package com.leo;

import java.util.Arrays;

/**
 * Created by sh00514 on 2017/1/22.
 * target class to load by spring
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class MyClassLoadBySpring {

    private String[] words;

    public void print(){
        Arrays.asList(words).forEach(System.out::println);
    }

    public String[] getWords() {
        return words;
    }

    public void setWords(String[] words) {
        this.words = words;
    }
}

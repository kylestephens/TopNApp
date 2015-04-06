package com.kyle.test;

public class Main {

    public static final int NO_TO_OUTPUT = 20;

    public static void main(String[] args){
        TopN letsGetSomeNumbers = new TopN();
        letsGetSomeNumbers.getTopN("res/numbers.txt", NO_TO_OUTPUT);
    }

}

package com.kilopo;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList(
        "MARKER_START",
        "This text should",
        "appear",
        "in the",
        "result",
        "MARKER_END",
        "MARKER_START",
        "Another part",
        "of valuable",
        "data",
        "MARKER_END",
        "to be ignored",
        "MARKER_START",
        "Something that we",
        "need",
        "to keep",
        "MARKER_END",
        "MARKER_START",
        "MARKER_END",
        "to keep",
        "to keep",
        "to keep");

        CustomIterator customIterator = new CustomIterator(strings, "MARKER_START", "MARKER_END");

        while (customIterator.hasNext()) {
            System.out.println(customIterator.next());
        }
    }

}

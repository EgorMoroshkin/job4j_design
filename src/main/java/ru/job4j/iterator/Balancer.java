package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        while (source.hasNext()) {
            for (int i = 0; i < nodes.size(); i++) {
                if (source.hasNext()) {
                    nodes.get(i).add(source.next());
                }
            }
        }
    }
}

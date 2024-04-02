package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        int numberOfNodes = nodes.size();
        int index = 0;

        while (source.hasNext()) {
            if (index >= numberOfNodes) {
                index = 0;
            }
            List<Integer> currentList = nodes.get(index);
            currentList.add(source.next());
            index++;
        }

    }
}

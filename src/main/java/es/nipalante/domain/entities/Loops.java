package es.nipalante.domain.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Stream;

public class Loops {

    private Map<Integer, Integer> loopsMaps = new HashMap<>();

    public Loops(List<String> decodedCode) {

        Stack<Integer> openPositions = new Stack<>();
        Integer position = 0;
        for (String emoji : decodedCode) {
            if (emoji.equals("\uD83E\uDD1C")) {
                openPositions.add(position);
            } else if (emoji.equals("\uD83E\uDD1B")) {
                Integer init = openPositions.pop();
                loopsMaps.put(init, position);
            }
            position++;
        }
    }

    public Integer getEndPosition(Integer position) {
        return this.loopsMaps.get(position);
    }

    public Integer getStarPosition(Integer position) {
        return keys(this.loopsMaps, position).findFirst().get();
    }

    private <K, V> Stream<K> keys(Map<K, V> map, V value) {
        return map
                .entrySet()
                .stream()
                .filter(entry -> value.equals(entry.getValue()))
                .map(Map.Entry::getKey);
    }

}

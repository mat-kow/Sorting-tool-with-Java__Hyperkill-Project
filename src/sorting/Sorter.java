package sorting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sorter {
    protected final List<String> inputList;

    public Sorter(List<String> inputList) {
        this.inputList = inputList;
    }

    protected <T> String outputNatural(List<T> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("Sorted data: ");
        list.stream().sorted().forEach(l -> sb.append(l).append(" "));
        return sb.toString();
    }

    protected <T extends Comparable<T>> String outputByCount(List<T> list, int size) {
        List<ComparableCountContainer<T>> containerList = sortByCount(list);
        StringBuilder sb = new StringBuilder();
        for (var e : containerList) {
            String format = String.format(e.key() + ": %d time(s), %d%%\n", e.count(), Math.round(100.0 * e.count() / size));
            sb.append(format);
        }
        return sb.toString();
    }

    protected <K extends Comparable<K>> List<ComparableCountContainer<K>> sortByCount(List<K> list) {
        Map<K, Integer> countMap = new HashMap<>();
        for (var e : list) {
            countMap.merge(e, 1, (Integer::sum));
        }

        return countMap.entrySet().stream()
                .map((e) -> new ComparableCountContainer<K>(e.getKey(), e.getValue()))
                .sorted()
                .toList();
    }

}

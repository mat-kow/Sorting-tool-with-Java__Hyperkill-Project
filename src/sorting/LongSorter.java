package sorting;

import java.util.ArrayList;
import java.util.List;

public class LongSorter extends Sorter {

    public LongSorter(List<String> inputList) {
        super(inputList);
    }

    protected String sort(boolean naturalSort) {
        StringBuilder sb = new StringBuilder();
        List<Long> list = new ArrayList<>();
        for (var s : inputList) {
            try {
                list.add(Long.parseLong(s));
            } catch (RuntimeException e) {
                String format = String.format("\"%s\" is not a long. It will be skipped.\n", s);
                sb.append(format);
            }
        }

        int size = list.size();
        String format = String.format("Total numbers: %d.\n", size);
        sb.append(format);
        if (naturalSort) {
            sb.append(outputNatural(list));
        } else
            sb.append(outputByCount(list, size));
        return sb.toString();
    }

}

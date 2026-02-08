package sorting;

import java.util.List;

public class StringSorter extends Sorter {

    public StringSorter(List<String> inputList) {
        super(inputList);
    }

    public String sortLines(boolean naturalSort) {
        int size = inputList.size();
        String format = String.format("Total lines: %d.\n", size);
        StringBuilder sb = new StringBuilder();
        sb.append(format);
        if (naturalSort) {
            sb.append(outputNatural(inputList));
        } else
            sb.append(outputByCount(inputList, size));
        return sb.toString();
    }

    public String sortWords(boolean naturalSort) {
        int size = inputList.size();
        String format = String.format("Total words: %d.\n", size);
        StringBuilder sb = new StringBuilder();
        sb.append(format);
        if (naturalSort) {
            sb.append(outputNatural(inputList));
        } else
            sb.append(outputByCount(inputList, size));
        return sb.toString();

    }

}

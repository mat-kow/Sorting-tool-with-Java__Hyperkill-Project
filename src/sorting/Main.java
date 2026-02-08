package sorting;

import java.io.*;
import java.util.*;
import java.util.function.Supplier;

public class Main {

    private static Scanner scanner;

    public static void main(final String[] args) {
        Map<String, String> argsMap = new HashMap<>();
        argsMap.put("-dataType", "word");
        argsMap.put("-sortingType", "natural");
        argsMap.put("-inputFile", "");
        argsMap.put("-outputFile", "");
        for (int i = 0; i < args.length; i++) {
            String key = args[i];
            if (!argsMap.containsKey(key)) {
                System.out.printf("\"%s\" is not a valid parameter. It will be skipped.\n", key);
                continue;
            }
            if (i + 1 >= args.length || args[i + 1].startsWith("-")) {
                System.out.printf("no %s type defined!", key.substring(1, key.length() - 4));
                return;
            }
            i++;
            argsMap.put(key, args[i]);
        }
        boolean naturalSort = "natural".equals(argsMap.get("-sortingType"));

        String inputFile = argsMap.get("-inputFile");
        if (inputFile.isEmpty()) {
            scanner = new Scanner(System.in);
        } else {
            try {
                scanner = new Scanner(new File(inputFile));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        String result = getResult(argsMap.get("-dataType"), naturalSort);

        outputResult(argsMap.get("-outputFile"), result);
        scanner.close();
    }

    private static String getResult(String dataType, boolean naturalSort) {
        List<String> inputList;
        return switch (dataType) {
            case "long" -> {
                inputList = readStrings(scanner::next);
                LongSorter longSort = new LongSorter(inputList);
                yield longSort.sort(naturalSort);
            }
            case "line" -> {
                inputList = readStrings(scanner::nextLine);
                StringSorter stringSort = new StringSorter(inputList);
                yield stringSort.sortLines(naturalSort);
            }
            case "word" -> {
                inputList = readStrings(scanner::next);
                StringSorter stringSort = new StringSorter(inputList);
                yield stringSort.sortWords(naturalSort);
            }
            default -> throw new IllegalArgumentException();
//            default -> "Something is wrong!";
        };
    }

    private static void outputResult(String outputFile, String output) {
        if (outputFile.isEmpty()) {
            System.out.println(output);
        } else {
            try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)))){
                writer.println(output);
            } catch (IOException e) {
                scanner.close();
                throw new RuntimeException(e);
            }
        }
    }

    private static List<String> readStrings(Supplier<String> supplier) {
        List<String> list = new ArrayList<>();
        while (scanner.hasNext()) {
            list.add(supplier.get());
        }
        return list;
    }

}

//1 -2   33 4
//42
//1                    1
// 
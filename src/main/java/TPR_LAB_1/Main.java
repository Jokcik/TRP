package TPR_LAB_1;

import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

class Main {
    private static double[][] matrix;
    private static int[] delta;
    private static double[] ly;

    public static void main(String[] args) {
        matrix = new double[4][4];
        matrix[0] = new double[]{1000, 4800, 2048, 7940};
        matrix[1] = new double[]{1000, 4500, 1024, 8400};
        matrix[2] = new double[]{1050, 5500, 6144, 15629};
        matrix[3] = new double[]{1020, 5400, 2048, 7799};

        ly = new double[]{0.001306057, 0.022073193, 0.706388449, 0.270232301};
        delta = new int[]{1, 1, 1, -1};

        List<Integer> values = Arrays.asList(0, 1, 2, 3);
        Permute.permute(values, 0);
        List<List<Integer>> permutations = Permute.getLists();

        List<Entry<List<Integer>, Double>> results = new ArrayList<>();
        for (List<Integer> permutation : permutations) {
            double value = 0;
            for (int j = 0; j < permutation.size(); ++j) {
                for (int z = j + 1; z < permutation.size(); ++z) {
                    Entry<List<Integer>, List<Integer>> pair = getPair(permutation, j, z);
                    value += getValue(pair.getKey()) - getValue(pair.getValue());
                }
            }
            results.add(new SimpleEntry<>(permutation, value));
        }
//        results.sort(Comparator.comparingDouble(o -> -o.getValue()));
        for (Entry<List<Integer>, Double> result : results) {
            System.out.println(result.getKey().stream().map((v) -> Integer.toString(v + 1)).reduce(((v1, v2) -> v1 + v2)).get());
        }
//        writeArray(results);
    }

    private static double getValue(List<Integer> list) {
        double result = 0;
        for (Integer integer : list) {
            result += ly[integer];
        }

        return result;
    }

    private static Entry<List<Integer>, List<Integer>> getPair(List<Integer> list, int k, int l) {
        List<Integer> C = new ArrayList<>();
        List<Integer> H = new ArrayList<>();

        for (int i = 0; i < matrix[list.get(k)].length; ++i) {
            if (matrix[list.get(k)][i] == matrix[list.get(l)][i]) continue;

            if (matrix[list.get(k)][i] * delta[i] > matrix[list.get(l)][i] * delta[i]) {
                C.add(i);
            } else {
                H.add(i);
            }
        }

        return new SimpleEntry<>(C, H);
    }

//    private static double getWeight(List<Integer> C, List<Integer> H) {
//
//    }

    private static void writeArray(double[][] array) {
        for (double[] anArray : array) {
            for (int j = 0; j < array[0].length; ++j) {
                System.out.print(anArray[j] + " ");
            }
            System.out.println();
        }
    }
}
package TPR_LAB_1;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.swap;

public class Permute {
    private static List<List<Integer>> lists = new ArrayList<>();

    public static List<List<Integer>> getLists() { return lists; }

    public static void setLists(List<List<Integer>> lists) { Permute.lists = lists; }

    public static void permute(List<Integer> arr, int k){
        for(int i = k; i < arr.size(); i++){
            swap(arr, i, k);
            permute(arr, k+1);
            swap(arr, k, i);
        }
        if (k == arr.size() -1){
            lists.add(new ArrayList<>(arr));
        }
    }
}
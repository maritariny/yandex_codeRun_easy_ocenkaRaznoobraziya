import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        Map <Long, Long> allProduct = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            String[] s = reader.readLine().split(" ");
            long product = Long.parseLong(s[0]);
            long category = Long.parseLong(s[1]);
            allProduct.put(product, category);
        }
        Map<Long, ArrayList<Integer>> map = new HashMap<>(n);
        String[] s = reader.readLine().split(" ");
        for (int i = 0; i < s.length; i++) {
            String str = s[i];
            long product = Long.parseLong(str);
            long category = allProduct.get(product);
            ArrayList<Integer> arr = map.get(category);
            if (arr == null) {
                arr = new ArrayList<>();
            }
            arr.add(i + 1);
            map.put(category, arr);
        }
        long result = Long.MAX_VALUE;
        long count = 0;
        for (Map.Entry<Long, ArrayList<Integer>> entry : map.entrySet()) {
            ArrayList<Integer> arr = entry.getValue();
            Collections.sort(arr);
            if (arr.size() == 1) {
                count++;
            } else {
                long min = arr.get(1) - arr.get(0);
                for (int i = 2; i < arr.size(); i++) {
                    long div = arr.get(i) - arr.get(i - 1);
                    if (div < min) {
                        min = div;
                    }
                }
                result = Math.min(result, min);
            }
        }
        if (count == map.size()) {
            result = count;
        }
        System.out.println(result);
    }
}
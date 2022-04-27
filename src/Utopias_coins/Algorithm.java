package Utopias_coins;

import java.util.HashMap;

public class Algorithm {
    /**
     * Utopias coins
     * 假设某国货币只有1 7 10 22 四种面值，要求一种算法，用最少的硬币得到给定的值N
     */

    private HashMap<Integer, Integer> hashMap;
    //use a map to store previous calculated results, similar to the fibonacci
    private HashMap<Integer, Integer> pastHashMap;

    public Algorithm() {
        this.hashMap = new HashMap<>();
        this.pastHashMap = new HashMap<>();

        hashMap.put(1, 1);
        hashMap.put(7, 1);
        hashMap.put(10, 1);
        hashMap.put(22, 1);
    }

    private int findChange(int amount) {
        int tempMinChange = 0;
        int minChange = 0;

        //cannot use coins to express a negative number or 0, so return 0
        if (amount <= 0) {
            return 0;
        }

        //if amount == one of 1 7 10 22, just return 1
        if (hashMap.containsKey(amount)) {
            return 1;
        } else {
            //get minChange if the map contains N
            if (pastHashMap.containsKey(amount)) {
                return pastHashMap.get(amount);
            }

            for (Integer key : hashMap.keySet()) {
                int var = amount - key;
                if (var > 0) {
                    //get the minimum value recursively
                    tempMinChange = findChange(var) + 1;
                    if (tempMinChange < minChange || minChange == 0) {
                        minChange = tempMinChange;
                    }
                }
            }
            pastHashMap.put(amount, minChange);
        }
        return minChange;
    }

    public String findResult(int amount){
        int r = findChange(amount);
        return "N = " + amount + ", minimum number = " + r;
    }
}

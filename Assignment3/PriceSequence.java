

import java.util.*;

public class PriceSequence {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        LinkedList<Long> storage = new LinkedList<>();
        TreeMap<Long,Integer> counts = new TreeMap<>();
        Long minADJDifference = Long.MAX_VALUE;
        Long minDifference = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            long a = sc.nextLong();
            storage.add(a);
            if(counts.containsKey(a)){
                counts.put(a,counts.get(a) + 1);
            }else{
                counts.put(a,1);
            }
            if(i > 0){
                minADJDifference = Math.min(minADJDifference, Math.abs(storage.get(i) - storage.get(i - 1)));
                minDifference = findMInDifference(counts,a,minDifference);
            }
        }
        sc.nextLine();
        for (int i = 0; i < m; i++) {
                String command = sc.nextLine();
            if(command.equals("CLOSEST_ADJ_PRICE")){
                System.out.println(minADJDifference);
            }else if(command.equals("CLOSEST_PRICE")){
                System.out.println(minDifference);
            }else {
                //BUY
                long a = Long.parseLong(command.split(" ")[1]);
                long prev = storage.getLast();
                storage.add(a);
                if(counts.containsKey(a)){
                    counts.put(a,counts.get(a) + 1);
                }else{
                    counts.put(a,1);
                }
                minADJDifference = Math.min(minADJDifference, Math.abs(prev - a));
                minDifference = findMInDifference(counts,a,minDifference);
            }
        }
    }
    private static long findMInDifference(TreeMap<Long,Integer> map,long a,long minDifference) {

        if(map.get(a) > 1) return 0;

        Long higherKey = map.higherKey(a);

            if(higherKey != null){
                minDifference = Math.min(minDifference,Math.abs(higherKey - a));
            }

            Long lowerKey = map.lowerKey(a);
            if(lowerKey != null){
                minDifference = Math.min(minDifference,Math.abs(lowerKey - a));
            }

        return minDifference;
    }


}

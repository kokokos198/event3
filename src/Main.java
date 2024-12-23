import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

        private static Map<String, Map<String, Double>> conversions = new HashMap<>();
        private static Map<String, Boolean> visited = new HashMap<>();

        public static void main (String[]args){

            String input = "";
            double number1;
            double number2;
            String val1;
            String val2;
            String[] part1;
            Scanner scanner = new Scanner(System.in);
                        while(true)
                        {
                            input = scanner.nextLine();
                            if(input.isEmpty())
                            {
                                break;
                            }
                            part1 = input.split(" ");
                             number1 = Double.parseDouble(part1[0]);
                             val1 = part1[1];
                             val2 = part1[4];
                             number2 = Double.parseDouble(part1[3]);

                            if (!conversions.containsKey(val1)) {
                                conversions.put(val1, new HashMap<>());
                            }
                            conversions.get(val1).put(val2, number2 / number1);

                            if (!conversions.containsKey(val2)) {
                                conversions.put(val2, new HashMap<>());
                            }
                            conversions.get(val2).put(val1, number1 / number2);
                        }

            while (scanner.hasNext()) {
                input = scanner.nextLine();
                part1 = input.split(" ");
                number1 = Double.parseDouble(part1[0]);
                val1 = part1[1];
                val2 = part1[4];

                dfs(val1, number1, val2);
            }
        }

        private static void dfs (String current,double multiplier, String target){
            visited.put(current, true);

            if (current.equals(target)) {
                System.out.println(current + " = " + multiplier);
                return;}

            for (Map.Entry<String, Double> entry : conversions.get(current).entrySet()) {
                String nextValue = entry.getKey();
                System.out.print(nextValue);
                double factor = entry.getValue();


               if (!visited.containsKey(nextValue)) {
                    dfs(nextValue, multiplier * factor, target);
                }

            }
        }
}


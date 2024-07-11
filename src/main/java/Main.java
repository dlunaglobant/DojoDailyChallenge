import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws Exception {

        //String[][] input = {{"MTY","GDL"},{"AICM" ,"MTY"}, {"SJO","AICM"}};
        String[][] input = {{"AICM", "GDL"}, {"MTY", "SJO"}, {"NLU", "MTY"}, {"SJO", "AICM"}};
        //String[][] input = {{"AICM", "GDL"}, {"MTY", "SJO"}, {"NLU", "MXO"}, {"SJO", "AICM"}};

        ArrayList inputSourceArray = new ArrayList(Arrays.stream(input).toList());

        ArrayList orderingRouteArray = orderRoute(inputSourceArray);
        

        StringBuilder outputOrderedRoute = new StringBuilder();
        orderingRouteArray.stream().forEach(airport -> outputOrderedRoute.append(airport).append("-->"));
        System.out.println(outputOrderedRoute.replace(outputOrderedRoute.length() - 3, outputOrderedRoute.length(), ""));


    }

    public static ArrayList orderRoute(ArrayList inputSourceArray) throws Exception {

        ArrayList orderingRouteArray = new ArrayList();

        HashSet aValidatorHash = new HashSet();


        while (!inputSourceArray.isEmpty()) {
            int lastIndexInputArray = inputSourceArray.size() - 1;

            String[] routePart = (String[]) inputSourceArray.get(lastIndexInputArray);

            String originAirport = routePart[0];
            String destinationAirport = routePart[1];

            if (orderingRouteArray.isEmpty()) {
                orderingRouteArray = new ArrayList();

                addAirportToRoute(orderingRouteArray,originAirport, null);
                addAirportToRoute(orderingRouteArray,destinationAirport, null);
                removeLastProcessedRouteByIndex(inputSourceArray,lastIndexInputArray);
            } else {
                String endOfRoute = (String) orderingRouteArray.get(orderingRouteArray.size() - 1);
                String startOfRoute = (String) orderingRouteArray.get(0);

                if (originAirport.equals(endOfRoute)) {
                    addAirportToRoute(orderingRouteArray,destinationAirport, null);
                    removeLastProcessedRouteByIndex(inputSourceArray,lastIndexInputArray);

                } else if (startOfRoute.equals(destinationAirport)) {
                    addAirportToRoute(orderingRouteArray,originAirport, 0);
                    removeLastProcessedRouteByIndex(inputSourceArray,lastIndexInputArray);
                } else {
                    String[] lastRouteOnList = (String[]) inputSourceArray.get(lastIndexInputArray);
                    inputSourceArray.remove(lastIndexInputArray);
                    if(!aValidatorHash.contains(lastRouteOnList))
                        aValidatorHash.add(lastRouteOnList);
                    else
                        throw new Exception("Cannot complete the route");
                    inputSourceArray.add(0, lastRouteOnList);

                }
            }
        }
        return orderingRouteArray;
    }

    private static void removeLastProcessedRouteByIndex(ArrayList inputSourceArray, int index) {
        inputSourceArray.remove(index);
    }

    private static void addAirportToRoute(ArrayList orderingRouteArray, String airport, Integer index) {
        if(index == null)
            orderingRouteArray.add(airport);
        else
            orderingRouteArray.add(index,airport);
    }
}
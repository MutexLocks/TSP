package GA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class CalculateDistance {

    //    public static double totalDistence(List<String[]> population) {
//        double sum = 0;
//        for (String[] cities : population) {
//            sum = sum + calculateCityDistence(cities);
//        }
//        return sum;
//    }
    public CalculateDistance() {

    }

    public static double calculateCityDistance(String[] cities, Map<String, String> cityNameCoordinateMap) {
        double sum = 0;
        String[] coordinate1 = cityNameCoordinateMap.get(cities[0]).split(",");
        String[] coordinate2 = new String[2];
        for (int i = 1; i < cities.length; i++) {
            String coordinate = cityNameCoordinateMap.get(cities[i]);

            coordinate2 = coordinate.split(",");

            sum = sum + calculateDistance(Double.valueOf(coordinate1[0]), Double.valueOf(coordinate1[1]),
                    Double.valueOf(coordinate2[0]), Double.valueOf(coordinate2[1]));
            System.arraycopy(coordinate2, 0, coordinate1, 0, coordinate1.length);
        }
        //回到出发城市的距离
        coordinate1 = cityNameCoordinateMap.get(cities[0]).split(",");
        coordinate2 = cityNameCoordinateMap.get(cities[cities.length - 1]).split(",");
        sum = sum + calculateDistance(Double.valueOf(coordinate1[0]), Double.valueOf(coordinate1[1]),
                Double.valueOf(coordinate2[0]), Double.valueOf(coordinate2[1]));
        return sum;
    }

    public static double calculateDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }
}

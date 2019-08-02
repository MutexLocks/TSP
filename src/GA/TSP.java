package GA;

import View.CreateView;
import View.InfoBean;
import View.TSPView;
import com.sun.javaws.exceptions.ExitException;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class TSP {
    private final static int GENERATIONTIMES = 100;
    public static void main(String[] args) {
        new TSPView().initComponents();
    }

    public static Map<String, String> cityNameCoordinateMap = getCityInfo();
    public Object[] tsp(InfoBean infoBean) {
        String startCity = infoBean.getStartCity();
        String[] passCities = infoBean.getPassCity();
        InitializePopulation tsp = new InitializePopulation(startCity, passCities);
        List<String[]> population = tsp.createPopulation();
        //Map<String, String> cityNameCoordinateMap = getCityInfo();

        ArrayList<Double> saveDistance = new ArrayList<>();

        Object[] resultCity = new Object[2];
        String[] optimalCity = population.get(0);
        String[] secondOptmalCity = population.get(0);


        for (int count = 0; count < GENERATIONTIMES; count ++) {
            List<Evolution.CityProbability> individualProbability = Evolution.evaluateFitness(startCity, passCities
                    , cityNameCoordinateMap);
            List<String[]> selectionPopulation = Evolution.selection(individualProbability);
            //List<String[]> crossoverPopulation = GA.Evolution.crossover(selectionPopulation);
            List<String[]> crossoverPopulation = Evolution.partiallyMatchedCrossover(selectionPopulation);

            population = Evolution.mutation(crossoverPopulation);

            //进化逆转
            population = Evolution.reverseOperation(population, cityNameCoordinateMap);
            //////test
            String[] testCity = getMostShortCityDistenceOrder(population, cityNameCoordinateMap);


            double presentDistance = CalculateDistance.calculateCityDistance(testCity, cityNameCoordinateMap);
            double optimalDistance = CalculateDistance.calculateCityDistance(optimalCity, cityNameCoordinateMap);
            //double secondDistance = CalculateDistance.calculateCityDistance(secondOptmalCity, cityNameCoordinateMap);
            if (presentDistance < optimalDistance) {
               secondOptmalCity = optimalCity.clone();
               optimalCity = testCity.clone();
              //  System.arraycopy(optmalCity, 0, secondOptmalCity, 0, secondOptmalCity.length);
              //  System.arraycopy(testCity, 0, optimalCity, 0, optimalCity.length);
            }
            saveDistance.add(optimalDistance);


       //     System.out.println(CalculateDistance.calculateCityDistance(optmalCity, cityNameCoordinateMap));
        }

        resultCity[0] = optimalCity;
        resultCity[1] = secondOptmalCity;

        double dd = CalculateDistance.calculateCityDistance(optimalCity, cityNameCoordinateMap);
        System.out.println(dd);
        new CreateView().createView(saveDistance);
        return resultCity;







//        new View.CreateView().createView(saveDistance); //最优个体折线图
//        for (String[] city : population) {
//            System.out.println(GA.CalculateDistance.calculateCityDistance(city, cityNameCoordinateMap));
//        }
//        System.out.println();
//        String [] finalCityOrder = getMostShortCityDistenceOrder(population, cityNameCoordinateMap);
//        System.out.println(GA.CalculateDistance.calculateCityDistance(finalCityOrder, cityNameCoordinateMap));
//        for (String city : finalCityOrder) {
//            System.out.print(city + " ");
//        }

    }

    private static String[] getMostShortCityDistenceOrder(List<String[]> population, Map<String, String> cityNameCoordinateMap) {
        String[] mostShortCityDistenceOrder = population.get(0);
        for (String[] cityOrder : population) {
            if (CalculateDistance.calculateCityDistance(mostShortCityDistenceOrder, cityNameCoordinateMap)
                    > CalculateDistance.calculateCityDistance(cityOrder, cityNameCoordinateMap)) {
                mostShortCityDistenceOrder = cityOrder;
            }
        }
        return mostShortCityDistenceOrder;
    }

    private static Map<String, String> getCityInfo() {
        String path = "data/data.txt";
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader br = null;
        Map<String, String> cityNameCoordinateMap = new HashMap<>();
        try {
            fileInputStream = new FileInputStream(path);
            inputStreamReader = new InputStreamReader(fileInputStream, "GBK");
            br = new BufferedReader(inputStreamReader);
            String line = "";
            String[] arrs = null;
            while ((line = br.readLine()) != null) {
                arrs = line.split(",");
                cityNameCoordinateMap.put(arrs[0], arrs[1] + "," + arrs[2]);
                //   System.out.println(cityNameCoordinateMap.get(arrs[0]));
            }
            return cityNameCoordinateMap;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return cityNameCoordinateMap;
    }
}

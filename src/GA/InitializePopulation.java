package GA;

import java.util.*;

public class InitializePopulation {
    public static void main(String[] args) {
        List<String> passCities = new ArrayList<>();
//        passCities.add("北京");
//        passCities.add("天津");
//        passCities.add("厦门");
//        passCities.add("西藏");
//        GA.InitializePopulation test = new GA.InitializePopulation("西安", passCities);
//        test.printPopulation(test.createPopulation());
//        System.out.println(test.countAllPopulation(5));
    }


    private String startCity = null;
    private String[] passCities = null;
    public InitializePopulation(String startCity, String[] passCities) {
        this.startCity = startCity;
        this.passCities =  passCities;
    }
    private int countAllPopulation(int passCityNum) {
        int total = 1;
        while (passCityNum != 0) {
            total = total * passCityNum;
            passCityNum = passCityNum - 1;
        }
        return total;
    }
    private void printPopulation(List<String[]> population) {
        for (int i = 0; i < population.size(); i++) {
            for (String city : population.get(i)) {
                System.out.print(city + " ");
            }
            System.out.println();
        }
    }
    public List<String[]> createPopulation() {
        int len = passCities.length;
        int populationSize = 80;      //设置总群规模
        if (len > 20) {
            populationSize = 500;
        }
        if (len > 30) {
            populationSize = 1000;
        }

        List<String[]> population = new ArrayList<String[]>(populationSize);

        List<String> savePassCitiy = new ArrayList<>();
        String[] individual = null;
        Random random = new Random();
        int index = 0;
        for (int i = 0; i < populationSize; i++) { //控制总群大小
            individual = new String[len + 1];
            savePassCitiy.addAll(Arrays.asList(passCities));
            individual[0] = startCity;
            int count = 1;
            while (savePassCitiy.size() > 0){  //控制个体个数
                index = random.nextInt(savePassCitiy.size()); //随机选择城市
                individual[count] = savePassCitiy.get(index);
                savePassCitiy.remove(index);
                count ++;
            }
            population.add(individual);
        }
        return population;
    }
}

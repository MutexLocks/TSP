package GA;

import java.util.*;

public class Evolution {

    static class CityProbability {
        private String[] citys;
        private double probability;

        public void setCitys(String[] citys) {
            this.citys = citys;
        }

        public void setProbability(double probability) {
            this.probability = probability;
        }

        public double getProbability() {
            return probability;
        }

        public String[] getCitys() {
            return citys;
        }
    }

    public static void main(String[] args) {
        Evolution test = new Evolution();
        // test.selection();
    }

    public static List<CityProbability> evaluateFitness(String startCity, String[] passCities
            , Map<String, String> cityNameCoordinateMap)
    {
        InitializePopulation initializePopulation = new InitializePopulation(startCity, passCities);
        List<String[]> population = initializePopulation.createPopulation();
        List<CityProbability> individualProbability = new ArrayList<>();
        double sum = 0;
        //计算个体适应度之和
        for (String[] cities : population) {
            double cityDistence = CalculateDistance.calculateCityDistance(cities, cityNameCoordinateMap);
            sum = sum + 1 / cityDistence;
        }
        //计数选择概率
        for (String[] cities : population) {
            double cityDistence = CalculateDistance.calculateCityDistance(cities, cityNameCoordinateMap);
            CityProbability cityProbability = new CityProbability();
            cityProbability.setCitys(cities);
            cityProbability.setProbability((1 / cityDistence) / sum);
            individualProbability.add(cityProbability);
        }
        return individualProbability;
    }

    public static List<String[]> selection(List<CityProbability> cityProbability) {
        /*
         *probalilityMapping 存储概率的映射
         *如全部概率为 0.2, 0.3, 0.5则映射为范围为(0, 0.2) (0.2, 0.5) (0.5, 1)
         */
        List<String[]> newIndividual = new ArrayList<>();
        double[] probabilityMapping = new double[cityProbability.size()];
        double previousBound = 0;
        //计算累计概率
        for (int i = 0; i < cityProbability.size(); i++) {
            probabilityMapping[i] = cityProbability.get(i).getProbability() + previousBound;
            previousBound = previousBound + probabilityMapping[i];
        }

        //选择个体
        Random random = new Random();
        for (int i = 0; i < cityProbability.size(); i++) {

            double choice = random.nextDouble();
            int individualIndex = 0;
            for (individualIndex = 0; individualIndex < probabilityMapping.length; individualIndex++) {
                if (choice < probabilityMapping[individualIndex]) {
                    break;
                }
            }
            newIndividual.add(cityProbability.get(individualIndex).getCitys());
        }
        return newIndividual;
    }

    /*
     * example
     * @cutIndex = 2
     * city[] = {北京,天津,上海,重庆,拉萨}
     * after crossover
     * newCity[] = {北京,上海,重庆,拉萨,天津}
     *
     */
    private static final double CROSSOVERPROBABILITY = 0.1;  //杂交概率

    public static List<String[]> crossover(List<String[]> population) {
        Random random = new Random();
        int cityLen = population.get(0).length;
        List<String[]> nextGeneration = new ArrayList<>();
        for (String[] cities : population) {
            int cutIndex = random.ints(1, cityLen).findFirst().getAsInt();
            if (random.nextDouble() < CROSSOVERPROBABILITY) {
                for (int cityIndex = 1; cityIndex < cityLen; cityIndex++) {
                    String[] newCityOrder = new String[cityLen];
                    newCityOrder[0] = cities[0]; //第一个城市不交换
                    System.arraycopy(cities, cutIndex + 1, newCityOrder, 1, cityLen - cutIndex - 1);
                    System.arraycopy(cities, 1, newCityOrder, cityLen - cutIndex, cutIndex);
                    nextGeneration.add(newCityOrder);
                }
            } else {
                nextGeneration.add(cities);
            }
        }
        return nextGeneration;
    }

    /*PMX   K - V    5 - 2
    father: 9 8 4 | 5 6 7 | 1 3 2 0
    mother: 8 7 1 | 2 3 0 | 9 5 4 6

    temp:  9 8 4 | 2 3 0 | 1 3 2 0
    temp:  8 7 1 | 5 6 7 | 9 5 4 6

    child : 9 8 4 | 2 3 0 | 1 6 5 7
    child : 8 0 1 | 5 6 7 | 9 2 4 3

     */
    public static List<String[]> partiallyMatchedCrossover(List<String[]> population) {
        List<String[]> newPopulation = new ArrayList<>();
        int cityLen = population.get(0).length;
        Random random = new Random();
        int mid = population.size() / 2;
        for (int i = 0; i < mid; i++) {
            String[] fatherCities = population.get(i);
            String[] motherCities = population.get(i + mid);
            if (random.nextDouble() < CROSSOVERPROBABILITY) {
                String[] child1 = new String[cityLen];
                String[] child2 = new String[cityLen];
                int cutPoint1 = random.ints(1, cityLen).findFirst().getAsInt();  //[)
                int cutPoint2 = random.ints(cutPoint1, cityLen).findFirst().getAsInt();

                if (cutPoint1 != cutPoint2) {

                    //复制前面部分
                    for (int j = 0; j < cutPoint1; j++) {
                        child1[j] = fatherCities[j];
                        child2[j] = motherCities[j];
                    }

                    //交换中间部分
                    Map<String, String> saveChild1SawpCity = new HashMap<>();
                    Map<String, String> saveChild2SawpCity = new HashMap<>();
                    List<String> child1MiddlePart = new ArrayList<>();
                    List<String> child2MiddlePart = new ArrayList<>();

                    for (int j = cutPoint1; j < cutPoint2; j++) {
                        child1[j] = motherCities[j];
                        child1MiddlePart.add(motherCities[j]);

                        child2[j] = fatherCities[j];
                        child2MiddlePart.add(fatherCities[j]);

                        saveChild1SawpCity.put(motherCities[j], fatherCities[j]);
                        saveChild2SawpCity.put(fatherCities[j], motherCities[j]); //保存交换城市的映射
                    }
                    //复制后面部分
                    for (int j = cutPoint2; j < cityLen; j++) {
                        child1[j] = fatherCities[j];
                        child2[j] = motherCities[j];
                    }

                    //消除child重复部分
                    for (int k = 0; k < cutPoint1; k++) {
                        if (child1MiddlePart.contains(child1[k])) {
                            while (true) {
                                String value = saveChild1SawpCity.get(child1[k]);
                                if (!child1MiddlePart.contains(value)) {
                                    child1[k] = value;
                                    break;
                                }
                                child1[k] = value;
                            }
                        }

                        if (child2MiddlePart.contains(child2[k])) {
                            while (true) {
                                String value = saveChild2SawpCity.get(child2[k]);
                                if (!child2MiddlePart.contains(value)) {
                                    child2[k] = value;
                                    break;
                                }
                                child2[k] = value;
                            }
                        }
                    }
                    for (int k = cutPoint2; k < cityLen; k++) {
                        if (child1MiddlePart.contains(child1[k])) {
                            while (true) {
                                String value = saveChild1SawpCity.get(child1[k]);
                                if (!child1MiddlePart.contains(value)) {
                                    child1[k] = value;
                                    break;
                                }
                                child1[k] = value;
                            }
                        }

                        if (child2MiddlePart.contains(child2[k])) {
                            while (true) {
                                String value = saveChild2SawpCity.get(child2[k]);
                                if (!child2MiddlePart.contains(value)) {
                                    child2[k] = value;
                                    break;
                                }
                                child2[k] = value;
                            }
                        }
                    }
                    newPopulation.add(child1);
                    newPopulation.add(child2);
                } else {
                    newPopulation.add(fatherCities);
                    newPopulation.add(motherCities);
                }
            } else {
                newPopulation.add(fatherCities);
                newPopulation.add(motherCities);
            }
        }

//        for (String[] cities : newPopulation) {
//            Set<String> set = new HashSet<>();
//            for (String city : cities) {
//                set.add(city);
//            }
//            for (String s : set) {
//                System.out.print(s + " ");
//            }
//            System.out.println();
//        }


        return newPopulation;
    }

    /*
     *交换两个城市顺序模拟突变
     */
    private static final double MUTATIONPROBABILITY = 0.1; //突变率

    public static List<String[]> mutation(List<String[]> population) {
        Random random = new Random();
        int cityLen = population.get(0).length;
        List<String[]> newPopulation = new ArrayList<>();
        for (String[] cities : population) {
            if (random.nextDouble() < MUTATIONPROBABILITY) {
                int mutationPair1 = random.ints(1, cityLen).findFirst().getAsInt();
                int mutationPair2 = random.ints(1, cityLen).findFirst().getAsInt();
                String temp = cities[mutationPair1];
                cities[mutationPair1] = cities[mutationPair2];
                cities[mutationPair2] = temp;
                newPopulation.add(cities);
            }
            newPopulation.add(cities);
        }
        return newPopulation;
    }

    public static List<String[]> reverseOperation(List<String[]> population, Map<String, String> cityNameCoordinateMap) {
        Random random = new Random();
        int cityLen = population.get(0).length;
        List<String[]> newPopulation = new ArrayList<>();
        for (String[] cities : population) {
            int mutationPair1 = random.ints(1, cityLen).findFirst().getAsInt();
            int mutationPair2 = random.ints(mutationPair1, cityLen).findFirst().getAsInt();
            if (mutationPair1 != mutationPair2) {
                String[] newCitiesOrder = new String[cityLen];
                System.arraycopy(cities, 0, newCitiesOrder, 0, cityLen);
                String[] partCities = new String[mutationPair2 - mutationPair1];
                for (int i = mutationPair1; i < mutationPair2; i++) {
                    partCities[i - mutationPair1] = cities[i];
                }
                for (int i = 0; i < partCities.length; i++) {
                    newCitiesOrder[i + mutationPair1] = partCities[i];
                }

                if (CalculateDistance.calculateCityDistance(cities, cityNameCoordinateMap)
                        > CalculateDistance.calculateCityDistance(newCitiesOrder, cityNameCoordinateMap))
                {
                    newPopulation.add(newCitiesOrder);
                } else {
                    newPopulation.add(cities);
                }

            } else {
                newPopulation.add(cities);
            }
        }
        return newPopulation;
    }
}

package com.example;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public final class App {
    private App() {
    }


    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
    
        URL resource = App.class.getResource("/Задача ВС Java Сбер.csv");
        File file = new File(resource.toURI());

        Scanner scanner = new Scanner(file);

        List<City> cities = new ArrayList<>();
        List<String> str;

        while (scanner.hasNextLine()) {
            str = Arrays.asList(scanner.nextLine().split(";"));

            try {
                cities.add(new City(str.get(1), str.get(2), str.get(3), Integer.parseInt(str.get(4)), str.get(5)));
            } catch (ArrayIndexOutOfBoundsException e) {
                cities.add(new City(str.get(1), str.get(2), str.get(3), Integer.parseInt(str.get(4)), "-"));
            }
        }
        scanner.close();
        printNumberOfCities(cities);

    }

    static void printNumberOfCities(List<City> cities) {

        HashMap<String, Integer> map = new HashMap<>();
        for (City city : cities) {
            if (!map.containsKey(city.getRegion())) {
                map.put(city.getRegion(), 1);
            } else {
                map.put(city.getRegion(), map.get(city.getRegion()) + 1);
            }
        }


        for (Map.Entry<String, Integer> pair : map.entrySet()) {
            System.out.println(pair.getKey() + " - " + pair.getValue());
        }
    }
    
    static void printMaxPopulation(List<City> cities) {
        int answer = 0;
        for (int i = 1; i < cities.size(); i++) {
            if (cities.get(i).getPopulation() > cities.get(answer).getPopulation())
                answer = i;
        }
        System.out.println("[" + answer + "] = " + cities.get(answer).getPopulation());
    }

    static void sort(List<City> cities) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a sorting method:\n" +
                "1) Key = name\n" +
                "2) Key = district + name");

        int choice = scanner.nextInt();

        if (choice == 1) {
            cities.sort(new CityNameComparator());
        } else if (choice == 2) {
            cities.sort(new DistrictComparator());
        }

        for (City city : cities) {
            System.out.println(city);
        }

        scanner.close();
    }

    static void printAll(List<City> cities) {
        for (City city : cities) {
            System.out.println(city);
        }
    }

}

package com.example;

import java.util.Comparator;

public class DistrictComparator implements Comparator<City> {
    @Override
    public int compare(City o1, City o2) {
        int val = o1.getDistrict().compareTo(o2.getDistrict());
        if (val != 0) {
            return val;
        } else {
            return o1.getName().compareTo(o2.getName());
        }

    }
}
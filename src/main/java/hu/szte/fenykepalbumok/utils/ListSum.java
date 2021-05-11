package hu.szte.fenykepalbumok.utils;

import hu.szte.fenykepalbumok.model.Ertekeles;

import java.util.List;
import java.util.stream.Collectors;

public class ListSum {

    public static Double sumList(List<Ertekeles> list) {

        System.out.println("hello" + list);
        var integerList = list.stream().map(urEntity -> urEntity.getErtekeles()).collect(Collectors.toList());


        double sum = Math.round(integerList.stream()
                .collect(Collectors.averagingInt(Integer::intValue)) * 100) / 100;


        return sum;

    }
}


package hu.szte.fenykepalbumok.utils;

import hu.szte.fenykepalbumok.model.Ertekeles;

import java.util.List;
import java.util.stream.Collectors;

public class ListSum {

    public static Integer sumList(List<Ertekeles> list){

        System.out.println("hello" + list);
        var integerList = list.stream().map(urEntity -> urEntity.getErtekeles()).collect(Collectors.toList());


        Integer sum = integerList.stream()
                .collect(Collectors.summingInt(Integer::intValue));


        return sum;

    }
}


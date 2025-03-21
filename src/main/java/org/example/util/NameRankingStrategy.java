package org.example.util;

import org.example.model.SortingModel;

import java.util.List;

public class NameRankingStrategy  implements Ranking{
    private static final Ranking rankingStrategy = new NameRankingStrategy();
    public static Ranking getInstance() {
        return rankingStrategy;
    }
    private NameRankingStrategy(){

    }
    @Override
    public List<SortingModel> sort(List<SortingModel> sortingModelList, boolean reverse) {
        if(reverse){
            return sortingModelList.stream().sorted((val1,val2)->{
               String name1 = val1.getPerson().getName();
               String name2 = val2.getPerson().getName();
               return name2.compareTo(name1);
            }).toList();
        } return sortingModelList.stream().sorted((val1,val2)->{
            String name1 = val1.getPerson().getName();
            String name2 = val2.getPerson().getName();
            return name1.compareTo(name2);
        }).toList();
    }
}

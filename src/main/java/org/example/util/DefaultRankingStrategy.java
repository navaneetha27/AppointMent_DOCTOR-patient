package org.example.util;

import org.example.model.SortingModel;

import java.util.Comparator;
import java.util.List;

public class DefaultRankingStrategy  implements Ranking{
    private static Ranking rankingStrategy = new DefaultRankingStrategy();
    public static Ranking getInstance() {
        return rankingStrategy;
    }
    private DefaultRankingStrategy(){

    }

    @Override
    public List<SortingModel> sort(List<SortingModel> doctorSlot, boolean reverse) {
        if(reverse){
            return doctorSlot.stream().sorted(Comparator.comparing((SortingModel model)-> Integer.parseInt(model.getSlots().getStartTime().split(":")[0])).reversed().thenComparing((SortingModel model)-> Integer.parseInt(model.getSlots().getEndTime().split(":")[1])).reversed()).toList();
        }
        return doctorSlot.stream().sorted(Comparator.comparingInt((SortingModel a) -> Integer.parseInt(a.getSlots().getStartTime())).thenComparingInt(a -> Integer.parseInt(a.getSlots().getEndTime()))).toList();
    }
}

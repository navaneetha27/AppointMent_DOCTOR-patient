package org.example.util;

import org.example.entity.Doctor;
import org.example.model.SortingModel;

import javax.print.Doc;
import java.util.List;

public class DoctorRankBasedRanking implements Ranking{
    private static Ranking rankingStrategy = new DoctorRankBasedRanking();
    public static Ranking getInstance() {
        return rankingStrategy;
    }
    private DoctorRankBasedRanking(){

    }

    @Override
    public List<SortingModel> sort(List<SortingModel> sortingModelList, boolean reverse) {
        List<SortingModel> sortedList =  sortingModelList.stream().filter(model -> model.getPerson() instanceof Doctor).sorted((model1,model2)->{
            Doctor doctor1 = (Doctor) model1.getPerson();
            Doctor doctor2 = (Doctor) model2.getPerson();
            return (doctor1.getRating() < doctor2.getRating()) ? 0 : 1 ;
        }).toList();
        if(reverse){
            return sortedList.reversed();
        }
        return sortedList;
    }
}

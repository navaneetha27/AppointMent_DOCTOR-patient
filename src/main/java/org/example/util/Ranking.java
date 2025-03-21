package org.example.util;

import org.example.model.SortingModel;

import java.util.List;

public interface Ranking {
    List<SortingModel> sort(List<SortingModel> sortingModelList, boolean reverse);
}

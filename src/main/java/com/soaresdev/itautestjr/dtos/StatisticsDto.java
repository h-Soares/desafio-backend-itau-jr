package com.soaresdev.itautestjr.dtos;

import java.util.DoubleSummaryStatistics;

public record StatisticsDto(long count, double min, double max, double sum) {
    public StatisticsDto(DoubleSummaryStatistics statistics) {
        this(statistics.getCount(), statistics.getMin(), statistics.getMax(), statistics.getSum());
    }
}
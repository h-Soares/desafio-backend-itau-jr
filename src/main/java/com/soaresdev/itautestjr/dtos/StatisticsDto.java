package com.soaresdev.itautestjr.dtos;

import java.util.DoubleSummaryStatistics;

public record StatisticsDto(long count, double sum, double avg, double min, double max) {
    public StatisticsDto(DoubleSummaryStatistics stats) {
        this(stats.getCount(), stats.getSum(), stats.getAverage(), stats.getMin(), stats.getMax());
    }
}
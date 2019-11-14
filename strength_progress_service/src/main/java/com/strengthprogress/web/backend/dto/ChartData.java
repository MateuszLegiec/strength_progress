package com.strengthprogress.web.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ChartData implements Comparable<ChartData> {
    private LocalDate date;
    private Double max;
    private Double volume;

    @Override
    public int compareTo(ChartData c) {
        if (getDate() == null || c.getDate() == null)
            return 0;
        return getDate().compareTo(c.getDate());
    }
}

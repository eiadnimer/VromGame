package me.eiad.vromgame.core;

import lombok.Getter;

import java.util.Map;

@Getter
public class Report {

    private final Map<Integer, String> carsReport;

    public Report(Map<Integer, String> report) {
        this.carsReport = report;
    }
}

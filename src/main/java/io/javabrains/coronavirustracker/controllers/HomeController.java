package io.javabrains.coronavirustracker.controllers;

import io.javabrains.coronavirustracker.model.LocationStats;
import io.javabrains.coronavirustracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping(path = "corona/api/v1")
    public String hello(Model model){
        List<LocationStats> allStats=coronaVirusDataService.getAllStats();
        int totalReportedCases =allStats.stream().mapToInt(stat->stat.getLatestTotalCases()).sum();
        model.addAttribute("locationStats",allStats );
        model.addAttribute("totalReportedCases",totalReportedCases );
        return "hello";
    }

}

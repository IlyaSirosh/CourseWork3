package com.sirosh.app.service;

import javafx.util.Pair;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Illya on 5/27/17.
 */
public interface VisitorService {


    void add(Date date, String ip, String page);
    Map<String,Integer> getCountryRating();
    List<Pair> getPageVisitInfo();

}

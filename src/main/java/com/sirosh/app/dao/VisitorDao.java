package com.sirosh.app.dao;



import javafx.util.Pair;

import java.util.Date;
import java.util.List;

/**
 * Created by Illya on 5/25/17.
 */
public interface VisitorDao {


    List<String> getVisitorsIp();
    void add(Date date, String ip, String page);
    List<Pair> getPageVisitInfo();

}

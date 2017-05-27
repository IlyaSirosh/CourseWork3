package com.sirosh.app.service.impl;


import com.sirosh.app.dao.VisitorDao;
import com.sirosh.app.service.GeolocationService;
import com.sirosh.app.service.VisitorService;
import com.sirosh.app.service.GeolocationService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Illya on 5/27/17.
 */

@Service
public class VisitorServiceImpl implements VisitorService{

    @Autowired
    private VisitorDao visitorDao;

    @Autowired
    private GeolocationService geolocationService;

    public void add(Date date, String ip, String page) {
        visitorDao.add(date, ip, page);
    }

    public Map<String, Integer> getCountryRating() {

        List<String> ips = visitorDao.getVisitorsIp();
        Map<String,Integer> countries = new HashMap<String, Integer>();

        String country = null;

        for(String x: ips){

            country = geolocationService.getStateByIp(x);

            if(country!=null) {

                int count = countries.containsKey(country) ? countries.get(country) : 0;

                countries.put(country, count + 1);
            }

            System.out.println("country: "+country+" ip: "+x);
        }

        return countries;
    }

    public List<Pair> getPageVisitInfo() {
        return visitorDao.getPageVisitInfo();
    }
}

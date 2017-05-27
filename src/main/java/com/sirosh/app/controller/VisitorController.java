package com.sirosh.app.controller;


import com.sirosh.app.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Illya on 5/26/17.
 */
@Controller
public class VisitorController {

    @Autowired
    private VisitorService visitorService;


    @ModelAttribute("ip")
    public String populateClientIpAddress (HttpServletRequest request) {

        return request.getRemoteAddr();
    }


    @GetMapping
    @RequestMapping({"/","/main"})
    public String main(@ModelAttribute("ip") String ip){

        return "main";
    }

    @GetMapping
    @RequestMapping("/left")
    public String left(@ModelAttribute("ip") String ip){

        return "left";
    }

    @GetMapping
    @RequestMapping("/right")
    public String right(@ModelAttribute("ip") String ip){
        return "right";
    }

    @GetMapping
    @RequestMapping("/middle")
    public String middle(@ModelAttribute("ip") String ip){
        return "middle";
    }

    @GetMapping
    @RequestMapping("/stats")
    public String stats(@ModelAttribute("ip") String ip,Model model){

        model.addAttribute("countryRating",visitorService.getCountryRating());
        model.addAttribute("pageVisitInfo",visitorService.getPageVisitInfo());

        return "stats";
    }



}

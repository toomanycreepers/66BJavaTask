package com.TMC.WebFootballers.Controllers;

import com.TMC.WebFootballers.Services.FootballerService;
import com.TMC.WebFootballers.Utility.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PagesController {
    @Autowired
    private FootballerService service;
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String getIndexPage(Model model){
        model.addAttribute("countryEnum", Country.class);
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/FootballerList")
    public String getFootballerListPage(Model model){
        var footballers = service.GetAllFootballers();
        model.addAttribute("footballers",footballers);
        model.addAttribute("countryEnum", Country.class);
        return "footballerList";
    }
}

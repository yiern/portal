package com.portal.portal.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Controller
public class controller {
    
    @RequestMapping(path = "/aws", method = RequestMethod.GET)
    public String AdminPortalPage(ModelMap model)
    {

        
        return index;
    }
}
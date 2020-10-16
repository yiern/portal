package com.portal.Controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.services.stepfunctions.model.StateMachineListItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.portal.aws.StepFunction;

import org.apache.commons.logging.Log;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class My_Controller {

    StepFunction sf = new StepFunction();
    
    @RequestMapping(path = "/aws", method = RequestMethod.GET)
    public String AdminPortalPage(ModelMap model)
    {

        return "test";
    }


    @RequestMapping(value = "/awsget", method = RequestMethod.POST)
    public String getData(String jsonData , String p_name)
    {    
        sf.CreateWorkFlow(jsonData, p_name);
        return null;
    }
   
    @RequestMapping(value = "/awslist",method = RequestMethod.GET)
    public String listStateMachine(HttpServletRequest request, ModelMap model)
    {
        
        model.put("StateMachineList", sf.ListStateMachine());
        request.setAttribute("StateMachineList", sf.ListStateMachine());
        return "list";
    }
}

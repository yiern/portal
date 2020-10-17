package com.portal.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.services.stepfunctions.model.HistoryEvent;
import com.amazonaws.services.stepfunctions.model.StateMachineListItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.portal.aws.StepFunction;

import org.apache.commons.logging.Log;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;

@Controller
public class My_Controller {

    StepFunction sf = new StepFunction();
    String url = "";
    String executionARN;

    @RequestMapping(path = "/aws", method = RequestMethod.GET)
    public String AdminPortalPage(ModelMap model) {

        return "test";
    }

    @RequestMapping(value = "/awsget", method = RequestMethod.POST)
    public String getData(String jsonData, String p_name) {
        sf.CreateWorkFlow(jsonData, p_name);
        return null;
    }

    @RequestMapping(value = "/awslist", method = RequestMethod.GET)
    public String listStateMachine(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request.getRequestURI());
        response.setStatus(response.SC_OK);
        // model.put("StateMachineList", sf.ListStateMachineName());
        request.setAttribute("StateMachineList", sf.ListStateMachineName());
        request.setAttribute("StateMachineListARN", sf.ListStateMachineARN());
        request.setAttribute("StateMachineStatus", sf.getStateMachineStatus());

        return "list";
    }

    @GetMapping(value = "/deletestate/{name}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteStateMachine(@PathVariable String name, HttpServletRequest request,
            HttpServletResponse response) {

        sf.deleteStateMachine(name);
        request.setAttribute("StateMachineList", sf.ListStateMachineName());
        request.setAttribute("StateMachineListARN", sf.ListStateMachineARN());
        request.setAttribute("StateMachineStatus", sf.getStateMachineStatus());

        return "delete";
    }

    @GetMapping(value = "/seeStateMachine/{arn}")
    public String seeState(@PathVariable String arn, HttpServletRequest request) {
        request.setAttribute("ARN", arn);
        request.setAttribute("definition", sf.seeStateMachine(arn));
        return "details";
    }
    /*
     * @RequestMapping(value = "/runExecution", method = RequestMethod.POST) public
     * String runExecution(String input, String arn) { executionARN =
     * sf.runExecution(arn,input); //sf.getExecutionHistory(executionARN);
     * System.out.println(executionARN); return "execution"; }
     */

    @RequestMapping(value = "/runExecution", method = RequestMethod.GET)
    public String postExecution(HttpServletRequest request,  @RequestParam String input, @RequestParam String arn,@RequestParam String definition) {
      
        String resultARN = sf.runExecution(arn, input);
        for(int a =0;a<10000;a++)
        {
            System.out.println(a);
        }
        request.setAttribute("event", sf.getExecutionHistory(resultARN));
        request.setAttribute("definition",definition);
        return "execution";
    }
    
    
}

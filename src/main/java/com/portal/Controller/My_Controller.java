package com.portal.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.services.stepfunctions.model.HistoryEvent;
import com.google.gson.Gson;
import com.portal.aws.StepFunction;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
  

    @RequestMapping(value = "/runExecution", method = RequestMethod.GET)
    public String postExecution(HttpServletRequest request,  @RequestParam String input, @RequestParam String arn,@RequestParam String definition) {
      
        String resultARN = sf.runExecution(arn, input);
       
        for(int a =0;a<1400;a++)
        {
        
        }
       
        List<HistoryEvent> list = sf.getExecutionHistory(resultARN);
        String jsonStr = new Gson().toJson(list);

        request.setAttribute("event", jsonStr);
        request.setAttribute("definition",definition);
        request.setAttribute("arn",arn);
        return "execution";
    }

    @RequestMapping(value = "/saveCode", method = RequestMethod.POST)
    @ResponseBody
    public void saveCode( @RequestParam String input,  @RequestParam String arn)
    {
        sf.saveCode(input, arn);
    }
    
    
}

package com.portal.aws;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.stepfunctions.AWSStepFunctions;
import com.amazonaws.services.stepfunctions.AWSStepFunctionsClientBuilder;
import com.amazonaws.services.stepfunctions.model.CreateStateMachineRequest;
import com.amazonaws.services.stepfunctions.model.CreateStateMachineResult;
import com.amazonaws.services.stepfunctions.model.DeleteStateMachineRequest;
import com.amazonaws.services.stepfunctions.model.DeleteStateMachineResult;
import com.amazonaws.services.stepfunctions.model.DescribeStateMachineRequest;
import com.amazonaws.services.stepfunctions.model.DescribeStateMachineResult;
import com.amazonaws.services.stepfunctions.model.GetExecutionHistoryRequest;
import com.amazonaws.services.stepfunctions.model.GetExecutionHistoryResult;
import com.amazonaws.services.stepfunctions.model.HistoryEvent;
import com.amazonaws.services.stepfunctions.model.ListStateMachinesRequest;
import com.amazonaws.services.stepfunctions.model.ListStateMachinesResult;
import com.amazonaws.services.stepfunctions.model.StartExecutionRequest;
import com.amazonaws.services.stepfunctions.model.StartExecutionResult;
import com.amazonaws.services.stepfunctions.model.StateMachineListItem;
import com.amazonaws.services.stepfunctions.model.UpdateStateMachineRequest;
import com.amazonaws.services.stepfunctions.model.UpdateStateMachineResult;
import com.google.gson.Gson;

public class StepFunction {

    //Declare and initialize Gson object
    Gson gson = new Gson();

    //Provide AWS credentials
    public static ProfileCredentialsProvider InitCredentials() {

        ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
        try {
            credentialsProvider.getCredentials();
        } catch (Exception e) {
            throw new AmazonClientException("Cannot load the credentials from the credential profiles "
                    + "file. Please make sure that your credentials file is "
                    + "at the correct location (~/.aws/credentials), and is " + "in valid format.", e);
        }
        return credentialsProvider;
    }

    // Make StepFunction Client
    static AWSStepFunctions sfnClient = AWSStepFunctionsClientBuilder.standard().withCredentials(InitCredentials())
           .withEndpointConfiguration(
                   new AwsClientBuilder.EndpointConfiguration("states.ap-southeast-1.amazonaws.com", "ap-southeast-1"))
           .build();

   // Create State Machine
   public CreateStateMachineResult CreateWorkFlow(String json, String StateMachine_name) {

      
       CreateStateMachineResult results = sfnClient
               .createStateMachine(new CreateStateMachineRequest().withName(StateMachine_name).withDefinition(json)
                       .withRoleArn("arn:aws:iam::149081783947:role/PortalTest")

               );

       return results;

   }

    public List<String> ListStateMachineName() 
    {
       ListStateMachinesResult listStateMachinesResult = sfnClient.listStateMachines(new ListStateMachinesRequest());

        List<StateMachineListItem> stateMachines = listStateMachinesResult.getStateMachines();
        ArrayList<String> sm_name = new ArrayList<String>();
       
        if (!stateMachines.isEmpty()) {
            stateMachines.forEach(sm -> {
                sm_name.add(sm.getName());
               
                
            });

           
        }
       
        return sm_name;
    }

    public List<String> ListStateMachineARN() 
    {
       ListStateMachinesResult listStateMachinesResult = sfnClient.listStateMachines(new ListStateMachinesRequest());

        List<StateMachineListItem> stateMachines = listStateMachinesResult.getStateMachines();
        ArrayList<String> sm_ARN = new ArrayList<String>();
        
        if (!stateMachines.isEmpty()) {
            stateMachines.forEach(sm -> {
                sm_ARN.add(sm.getStateMachineArn());
                
                
            });

           
        }
       
        return sm_ARN;
    }

    public ArrayList<String> getStateMachineStatus()
    {
        ArrayList<String> status_array = new ArrayList<String>();
        List<String> ARN_name = ListStateMachineARN();
        for (int a=0; a < ARN_name.size();a++)
        {
            DescribeStateMachineResult describeStateMachineResult = sfnClient.describeStateMachine(new DescribeStateMachineRequest().withStateMachineArn(ARN_name.get(a)));
            String status = describeStateMachineResult.getStatus();
            status_array.add(status);
        }

        return status_array;
    }

	public DeleteStateMachineResult deleteStateMachine(String ARN_name) {

        DeleteStateMachineResult deleteStateMachineResult = sfnClient.deleteStateMachine(new DeleteStateMachineRequest()
                .withStateMachineArn(ARN_name));

                return deleteStateMachineResult;

	}

	public String seeStateMachine(String arn) {
        DescribeStateMachineResult describeStateMachineResult = sfnClient.describeStateMachine(new DescribeStateMachineRequest().withStateMachineArn(arn));

       String definition = describeStateMachineResult.getDefinition();
       
       return definition;
	}

	public String runExecution(String arn, String input) {
       

        StartExecutionResult startExecutionResult = sfnClient.startExecution(new StartExecutionRequest()
        .withInput(input)
        .withStateMachineArn(arn)
        );
        String executionARN = startExecutionResult.getExecutionArn();

        
		return executionARN;
	}

	public List<HistoryEvent> getExecutionHistory(String executionARN) {
        GetExecutionHistoryResult getExecutionHistoryResult = sfnClient.getExecutionHistory(new GetExecutionHistoryRequest().withExecutionArn(executionARN));
        List<HistoryEvent> events = getExecutionHistoryResult.getEvents();

      

        return events;
        
	}

	public void saveCode(String input, String arn) {
        UpdateStateMachineResult updateStateMachineResult = sfnClient.updateStateMachine(new UpdateStateMachineRequest()
        .withDefinition(input)
        .withStateMachineArn(arn));

        updateStateMachineResult.getUpdateDate();
	}
}
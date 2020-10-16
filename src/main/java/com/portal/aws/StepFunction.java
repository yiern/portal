package com.portal.aws;

import java.util.Arrays;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.partitions.model.Endpoint;
import com.amazonaws.services.stepfunctions.AWSStepFunctions;
import com.amazonaws.services.stepfunctions.AWSStepFunctionsClientBuilder;
import com.amazonaws.services.stepfunctions.model.CreateStateMachineRequest;
import com.amazonaws.services.stepfunctions.model.CreateStateMachineResult;
import com.amazonaws.services.stepfunctions.model.ListStateMachinesRequest;
import com.amazonaws.services.stepfunctions.model.ListStateMachinesResult;
import com.amazonaws.services.stepfunctions.model.StateMachineListItem;
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

       System.out.println(json);
       System.out.println(StateMachine_name);
       CreateStateMachineResult results = sfnClient
               .createStateMachine(new CreateStateMachineRequest().withName(StateMachine_name).withDefinition(json)
                       .withRoleArn("arn:aws:iam::149081783947:role/PortalTest")

               );

       return results;

   }

        public List<StateMachineListItem> ListStateMachine() {
       ListStateMachinesResult listStateMachinesResult = sfnClient.listStateMachines(new ListStateMachinesRequest());

        List<StateMachineListItem> stateMachines = listStateMachinesResult.getStateMachines();
       
        System.out.println("State machines count: " + stateMachines.size());
        if (!stateMachines.isEmpty()) {
            stateMachines.forEach(sm -> {
                
                System.out.println("\t- Name: " + sm.getName());
                System.out.println("\t- Arn: " + sm.getStateMachineArn());
                
            });

           
        }
       
        return stateMachines;
    }
}
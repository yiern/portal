package com.portal.aws;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.stepfunctions.AWSStepFunctions;
import com.amazonaws.services.stepfunctions.AWSStepFunctionsClientBuilder;
import com.amazonaws.services.stepfunctions.model.CreateStateMachineRequest;
import com.amazonaws.services.stepfunctions.model.CreateStateMachineResult;
import com.google.gson.Gson;

public class StepFunction {

    //Declare and initialize Gson object
    Gson gson = new Gson();

    //Provide AWS credentials
    public ProfileCredentialsProvider InitCredentials()
    {

        ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
        try
        {
            credentialsProvider.getCredentials();
        }
        catch (Exception e)
        {
            throw new AmazonClientException("Cannot load the credentials from the credential profiles "
                    + "file. Please make sure that your credentials file is "
                    + "at the correct location (~/.aws/credentials), and is " + "in valid format.",
                    e);
        }
    return credentialsProvider;
    }

    //Make StepFunction Client
    AWSStepFunctions sfnClient = AWSStepFunctionsClientBuilder.standard()
            .withCredentials(InitCredentials())
            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                    "states.ap-southeast-1.amazonaws.com", "ap-southeast-1"))
            .build();

    //Take Json  to convert into string


    //Create State Machine
    //TODO: replace "with definition"  with json file
    public CreateStateMachineResult CreateWorkFlow() {
        CreateStateMachineResult result = sfnClient.createStateMachine(
                new CreateStateMachineRequest().withName("Java")
                        .withDefinition("{\"Comment\":\"A simple minimal example of the States language\",\"StartAt\":\"Hello World\",\"States\":{\"Hello World\":{\"Type\":\"Pass\",\"End\":true}}}")
                        .withRoleArn("arn:aws:iam::149081783947:role/PortalTest")

        );
        return result;
    }

}

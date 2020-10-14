package com.portal.portal;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.stepfunctions.AWSStepFunctions;
import com.amazonaws.services.stepfunctions.AWSStepFunctionsClientBuilder;
import com.amazonaws.services.stepfunctions.model.CreateActivityRequest;
import com.amazonaws.services.stepfunctions.model.CreateActivityResult;
import com.amazonaws.services.stepfunctions.model.CreateStateMachineRequest;
import com.amazonaws.services.stepfunctions.model.CreateStateMachineResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PortalApplication {

        public static void main(String[] args) {

                //Declare and initialize Gson object
                Gson gson = new Gson();
               

                //Provide AWS credentials
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

                //Make StepFunction Client 
                AWSStepFunctions sfnClient = AWSStepFunctionsClientBuilder.standard()
                                .withCredentials(credentialsProvider)
                                .withEndpointConfiguration(new EndpointConfiguration(
                                                "states.ap-southeast-1.amazonaws.com", "ap-southeast-1"))
                                .build();

                //Take Json  to convert into string
                //Create State Machine 
                //TODO: replace "with definition"  with json file
                CreateStateMachineResult result = sfnClient.createStateMachine(
                                new CreateStateMachineRequest().withName("Java")
                                .withDefinition("{\"Comment\":\"A simple minimal example of the States language\",\"StartAt\":\"Hello World\",\"States\":{\"Hello World\":{\"Type\":\"Pass\",\"End\":true}}}")
                                .withRoleArn("arn:aws:iam::149081783947:role/PortalTest")

                );
                System.out.println("This is the result of create " + result);

               
        }
}

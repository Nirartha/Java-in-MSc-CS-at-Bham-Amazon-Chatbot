package com.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.helper.FileManagerHelper;
import com.helper.MySQLInsert;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class BasicInfoFlowHandler implements RequestHandler {
	
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("BasicInfoIntent"));
    }

    public Optional<Response> handle(HandlerInput input) {
    	
    	Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();

        // Get the name slot from user input.
        Slot nameslot = slots.get("Name");
        Slot levelslot = slots.get("Level");
        Slot exerciseStartslot = slots.get("ExerciseStart");
        
        String speechText = "If you still interested in exercise %s , type '%s' again. Or type '%s'.";
        String repromptText = "Please try again.";
        String suggestion =  "Also, you should know about: ";
    	
        // Insert new data in database
        if (nameslot != null && levelslot != null && exerciseStartslot != null) {
        	
        	String stu_name = nameslot.getValue();
        	String javaLevel = levelslot.getValue();
        	String exerciseStart = exerciseStartslot.getValue();
        	
        	// return by different exercise
        	if (exerciseStart.toLowerCase().equals("basic"))
	            speechText = String.format(speechText, "basic", "basic", "advanced");
            else //if (exercisenum.equals("2") || exercisenum.toLowerCase().equals("two"))
	            speechText = String.format(speechText, "advanced", "advanced", "basic");
        	
        	MySQLInsert insertNewdata = new MySQLInsert();
        	insertNewdata.insertGoJavaNewdata(stu_name, javaLevel);
        }
        
        return input.getResponseBuilder()
	                .withSimpleCard("BasicInfoIntentHandler", speechText)
	                .withSpeech(speechText)
	                .withReprompt(repromptText)
	                .build();
        
    }

}

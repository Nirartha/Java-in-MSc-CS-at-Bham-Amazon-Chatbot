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
import com.helper.MySQLSelect;
import com.helper.MySQLUpdate;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class FirstExerciseHandler implements RequestHandler {
	
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("FirstExerciseIntent"));
    }

    public Optional<Response> handle(HandlerInput input) {
    	
    	String connectQuestionTwo = "Well done! "
				   + "You could type 'advanced' for move to the advanced one "
				   + "if you are really. ";
    	
    	String suggestion =  "Also, you should know about: ";
    	
    	String connectQuestionnaire = "Brillant! "
				   + "Please type 'ok' for move to give us feedback "
				   + "if you are really.";
    	
    	Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        
        // Get the name slot from user input.
        Slot firstAnwerslot = slots.get("FirstAnswer");
        
        String speechText = connectQuestionTwo;
        String repromptText = "Please try again.";
    	
        // Insert new data in database
        if (firstAnwerslot != null) {
        	
        	String firstAnwer = firstAnwerslot.getValue();
        	
        	// 0:userID, 1:basic_score, 2:advanced_score
        	int[] arrResults = new int[3];
        	
        	// compiler the answer by compiler api
        	int basic_score = (int)(Math.random() * 50 + 1);
        	
        	// get the latest user ID
        	MySQLSelect msqlSelect = new MySQLSelect();
        	arrResults = msqlSelect.SelectLatestIDandScore();
        	
        	// update the basic socre and answer
        	MySQLUpdate msqlUpdate = new MySQLUpdate();
        	int updateResult = msqlUpdate.UpdateFirstScoreByuserID(arrResults[0], basic_score, firstAnwer);
        	
        	
        	if (arrResults[2] != 0 && updateResult == 1) {
        		speechText = "You got " + basic_score + " of 50% in the basic exercise and "
     				   + arrResults[2] + " of 50% in this exercise. "
     				   + connectQuestionnaire;
        	} else if (arrResults[2] == 0 && updateResult == 1) {
        		// pass the next introduction of the advanced exercise
        		MySQLSelect msqlSelect3 = new MySQLSelect();
            	String userJavaLevel = msqlSelect3.SelectJavaLevelByID(arrResults[0]);
            	
            	if (userJavaLevel == "junior") {
	        		speechText = connectQuestionTwo + suggestion + " The java.lang.Math.pow(double a, double b) returns the value of the first argument raised to the power of the second argument. ";
	        	} else if (userJavaLevel == "senior") {
	        		speechText = connectQuestionTwo + suggestion + " try to use Math.pow() method ";
	        	} else {
	        		// keep original suggestion
	        	}
            	
        	} else {
        		speechText = "Database update failed. Please try again.";
        	}
        	
        }
        
        return input.getResponseBuilder()
	                .withSimpleCard("FirstExerciseIntentHandler", speechText)
	                .withSpeech(speechText)
	                .withReprompt(repromptText)
	                .build();
        
    }

}

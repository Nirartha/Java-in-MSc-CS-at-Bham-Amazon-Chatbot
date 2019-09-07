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

public class SecondExerciseHandler implements RequestHandler {
	
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("SecondExerciseIntent"));
    }

    public Optional<Response> handle(HandlerInput input) {
    	
    	String connectQuestionOne = "Well done! "
				   + "You could type 'basic' for move to the basic one "
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
        Slot secondAnwerslot = slots.get("SecondAnswer");
        
        String speechText = "";
        String repromptText = "Please try again.";
    	
        // Insert new data in database
        if (secondAnwerslot != null) {
        	
        	String secondAnwer = secondAnwerslot.getValue();
        	
        	// 0:userID, 1:basic_score, 2:advanced_score
        	int[] arrResults = new int[3];
        	
        	// compiler the answer by compiler api
        	int advanced_score = (int)(Math.random() * 50 + 1);
        	
        	// get the latest user ID and score
        	MySQLSelect msqlSelect = new MySQLSelect();
        	arrResults = msqlSelect.SelectLatestIDandScore();
        	
        	// update the basic socre and answer
        	MySQLUpdate msqlUpdate = new MySQLUpdate();
        	int updateResult = msqlUpdate.UpdateSecondScoreByuserID(arrResults[0], advanced_score, secondAnwer);
        	
        	if (arrResults[1] != 0 && updateResult == 1) {
        		speechText = "You got " + arrResults[1] + " of 50% in the basic exercise and "
     				   + advanced_score + " of 50% in this exercise. "
     				   + connectQuestionnaire;
        	} else if (arrResults[1] == 0 && updateResult == 1) {
        		// pass the next introduction of the advanced exercise
        		MySQLSelect msqlSelect3 = new MySQLSelect();
            	String userJavaLevel = msqlSelect3.SelectJavaLevelByID(arrResults[0]).toLowerCase();
            	if (userJavaLevel.equals("junior")) {
	        		speechText = connectQuestionOne + suggestion + " https://stackoverflow.com/questions/35666078/fast-integer-powers-in-java ";
	        	} else if (userJavaLevel.equals("senior")) {
	        		speechText = connectQuestionOne + suggestion + " recursive method and run odd/even separately ";
	        	} else {
	        		// keep original suggestion
	        	}
        	} else {
        		speechText = "Database update failed. Please try again.";
        	}
        	
        }
        
        return input.getResponseBuilder()
	                .withSimpleCard("SecondExerciseIntentHandler", speechText)
	                .withSpeech(speechText)
	                .withReprompt(repromptText)
	                .build();
        
    }

}

package com.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.helper.MySQLInsert;
import com.helper.MySQLSelect;
import com.helper.MySQLUpdate;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class QuestionnaireIntent implements RequestHandler {
	
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("QuestionnaireIntent"));
    }

    public Optional<Response> handle(HandlerInput input) {
    	
    	Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        
        Slot questionnaireoneslot = slots.get("QuestionnaireOne");
        Slot questionnairetwoslot = slots.get("QuestionnaireTwo");
        Slot questionnairethreeslot = slots.get("QuestionnaireThree");
        Slot questionnaireopenoneslot = slots.get("QuestionnaireOpenOne");
        Slot questionnaireopentwoslot = slots.get("QuestionnaireOpenTwo");
        String speechText = "Questionnaire Part";
        String repromptText = "Please call me again";
        
        // Finish and then insert the questionnaire
        if (questionnaireoneslot != null && questionnairetwoslot != null && questionnairethreeslot != null) {
        	
        	String questionnaireone = questionnaireoneslot.getValue();
            String questionnaireotwo = questionnairetwoslot.getValue();
            String questionnaireothree = questionnairethreeslot.getValue();
            String questionnaireopenone = questionnaireopenoneslot.getValue();
            String questionnaireopentwo = questionnaireopentwoslot.getValue();
            
        	// get the latest user ID
        	MySQLSelect msqlSelect = new MySQLSelect();
        	int latestUserID = msqlSelect.SelectALatestID();
        	
        	// update the questionnaire
        	MySQLUpdate msqlUpdate = new MySQLUpdate();
        	int updateResult = msqlUpdate.UpdateQuestionnaireByuserID(
        					   latestUserID, 
        					   Integer.parseInt(questionnaireone), 
        					   Integer.parseInt(questionnaireotwo), 
        					   Integer.parseInt(questionnaireothree),
        					   questionnaireopenone,
        					   questionnaireopentwo
        					   );
        	
            if (updateResult == 1) {
            	int updatefinishResult = msqlUpdate.UpdatefinishStatusBool(latestUserID);
            	if (updatefinishResult == 1)
            		speechText = "Thanks for your survey, see you next time.";
            }
            
        }
        
        return input.getResponseBuilder()
                .withSimpleCard("QuestionnaireIntentHandler", speechText)
                .withSpeech(speechText)
                .withReprompt(repromptText)
                .build();
    }

}

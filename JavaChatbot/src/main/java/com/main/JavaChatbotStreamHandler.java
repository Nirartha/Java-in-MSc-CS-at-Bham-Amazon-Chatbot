package com.main;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import com.handlers.CancelandStopIntentHandler;
import com.handlers.FirstExerciseHandler;
import com.handlers.HelpIntentHandler;
import com.handlers.LaunchRequestHandler;
import com.handlers.QuestionnaireIntent;
import com.handlers.SecondExerciseHandler;
import com.handlers.SessionEndedRequestHandler;
import com.handlers.BasicInfoFlowHandler;
import com.handlers.TestIntent;

public class JavaChatbotStreamHandler extends SkillStreamHandler {
	
	private static Skill getSkill() {
        return Skills.standard()
                	 .addRequestHandlers(
                        new CancelandStopIntentHandler(),
                        new HelpIntentHandler(),
                        new LaunchRequestHandler(),
                        new SessionEndedRequestHandler(),
                        new QuestionnaireIntent(),
                        new BasicInfoFlowHandler(),
                        new FirstExerciseHandler(),
                        new SecondExerciseHandler(),
                        new TestIntent())
	                 .withSkillId("amzn1.ask.skill.d0d3b3ac-06ef-4003-9d41-d1c8b9d1fab4")
	                 .build();
    }
	public JavaChatbotStreamHandler() {
        super(getSkill());
    }

	
}

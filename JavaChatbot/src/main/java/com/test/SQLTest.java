/*
package com.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.helper.MySQLInsert;
import com.helper.MySQLUpdate;

public class SQLTest {
	
	@Before
	public void setup() {
		
	}
	
	@Test
	//Basic test with an all positive tree
	public void testName() {
		
		// test data
	    String stu_name = "TestTest";
	    String javaLevel = "expert";
	    String[] selectResult = new String[2];
	    
	    // insert test data
		MySQLInsert insertNewdata = new MySQLInsert();
    	insertNewdata.insertGoJavaNewdata(stu_name, javaLevel);
    	
    	// get LatestID
    	TestMySQLSelect TestSelect = new TestMySQLSelect();
    	int LatestID = TestSelect.SelectALatestID();
    	
    	// get Name and java level
    	TestMySQLSelect TestSelect1 = new TestMySQLSelect();
    	selectResult = TestSelect1.SelectNameandjavaLevelByLatestID(LatestID);
    	
    	
    	String expected = stu_name;
    	String actual = selectResult[0];
    	
		assertEquals(expected, actual);
		
	}
	
	@Test
	//Basic test with an all positive tree
	public void testJavaLevel() {
		
		// test data
	    String javaLevel = "expert";
	    String[] selectResult = new String[2];
	    
    	// get LatestID
    	TestMySQLSelect TestSelect = new TestMySQLSelect();
    	int LatestID = TestSelect.SelectALatestID();
    	
    	// get Name and java level
    	TestMySQLSelect TestSelect1 = new TestMySQLSelect();
    	selectResult = TestSelect1.SelectNameandjavaLevelByLatestID(LatestID);
    	
    	
    	String expected = javaLevel;
    	String actual = selectResult[1];
    	
		assertEquals(expected, actual);
		
	}
	
	@Test
	//Basic test with an all positive tree
	public void testUpdateFirstScoreByuserID() {
		
		// test data
		int basic_score = 40;
	    String firstAnwer = "JuniorSeniorExpert";
	    
    	// get LatestID
    	TestMySQLSelect TestSelect = new TestMySQLSelect();
    	int LatestID = TestSelect.SelectALatestID();
    	
    	// update the basic socre and answer
    	MySQLUpdate msqlUpdate = new MySQLUpdate();
    	int updateResult = msqlUpdate.UpdateFirstScoreByuserID(LatestID, basic_score, firstAnwer);
    	
    	
    	int expected = 1;
    	int actual = updateResult;
    	
		assertEquals(expected, actual);
		
	}
	
	@Test
	//Basic test with an all positive tree
	public void testUpdateSecondScoreByuserID() {
		
		// test data
		int advanced_score = 30;
	    String secondAnwer = "ExpertSeniorJunior";
	    
    	// get LatestID
    	TestMySQLSelect TestSelect = new TestMySQLSelect();
    	int LatestID = TestSelect.SelectALatestID();
    	
    	// update the basic socre and answer
    	MySQLUpdate msqlUpdate = new MySQLUpdate();
    	int updateResult = msqlUpdate.UpdateSecondScoreByuserID(LatestID, advanced_score, secondAnwer);
    	
    	
    	int expected = 1;
    	int actual = updateResult;
    	
		assertEquals(expected, actual);
		
	}

	@Test
	//Basic test with an all positive tree
	public void testUpdateQuestionnaireByuserID() {
		
		// test data
		int questionnaireOne = 4;
		int questionnaireTwo = 5;
		int questionnaireThree = 4;
		String questionnaireopenOne = "questionnaireopenOne test content";
		String questionnaireopenTwo = "questionnaireopenTwo content test";
	    
    	// get LatestID
    	TestMySQLSelect TestSelect = new TestMySQLSelect();
    	int LatestID = TestSelect.SelectALatestID();
    	
    	// update the basic socre and answer
    	MySQLUpdate msqlUpdate = new MySQLUpdate();
    	int updateResult = msqlUpdate.UpdateQuestionnaireByuserID(LatestID, 
    															  questionnaireOne, 
    															  questionnaireTwo, 
    															  questionnaireThree,
    															  questionnaireopenOne,
    															  questionnaireopenTwo);
    	
    	
    	int expected = 1;
    	int actual = updateResult;
    	
		assertEquals(expected, actual);
		
	}
	
	@Test
	//Basic test with an all positive tree
	public void testUpdatefinishStatusBool() {
		
		// get LatestID
    	TestMySQLSelect TestSelect = new TestMySQLSelect();
    	int LatestID = TestSelect.SelectALatestID();
    	
    	// update the basic socre and answer
    	MySQLUpdate msqlUpdate = new MySQLUpdate();
    	int updateResult = msqlUpdate.UpdatefinishStatusBool(LatestID);
    	
    	
    	int expected = 1;
    	int actual = updateResult;
    	
		assertEquals(expected, actual);
		
	}
	
}
*/


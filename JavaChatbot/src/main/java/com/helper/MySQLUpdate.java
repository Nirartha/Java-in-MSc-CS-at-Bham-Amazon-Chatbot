package com.helper;

import java.sql.*;

public class MySQLUpdate {
	
	// MySQL 8.0 and later version - JDBC diver and it's URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://www.db4free.net:3306/gojava?useSSL=false&serverTimezone=UTC";
    
    // user name and password
    static final String USER = "kxc855";
    static final String PASS = "qazwsx123";
 
    public int UpdatefinishStatusBool(int userID) {
        Connection conn = null;
        Statement stmt = null;
        
        int count = 0;
        
        try {
            // register JDBC Diver
            Class.forName(JDBC_DRIVER);
        
            // open connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        
            // do select
            stmt = conn.createStatement();
            String sql = "UPDATE `gojava` "
            		   + "SET `finishStatusBool` = 1 "
            		   + "WHERE `userid` = " + userID;
            
            count = stmt.executeUpdate(sql);
            
            // finished and then close these
            stmt.close();
            conn.close();
            
        } catch (SQLException se){
            // deal JDBC problem
            se.printStackTrace();
        } catch (Exception e){
            // deal Class.forName problem
            e.printStackTrace();
        } finally {
            // close resources
            try {
                if(stmt != null) stmt.close();
            } catch (SQLException se2){
            } // do nothing
            try {
                if(conn != null) conn.close();
            } catch (SQLException se){
                se.printStackTrace();
            }
        }
        return count;
    }
    
    public int UpdateQuestionnaireByuserID(int userID, int questionnaireOne, int questionnaireTwo, int questionnaireThree, String questionnaireopenone, String questionnaireopentwo) {
        Connection conn = null;
        Statement stmt = null;
        
        int count = 0;
        
        try {
            // register JDBC Diver
            Class.forName(JDBC_DRIVER);
        
            // open connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        
            // do select
            stmt = conn.createStatement();
            String sql = "UPDATE `gojava` "
            		   + "SET `questionnaireone` = " + questionnaireOne 
            		   + ", `questionnairetwo` = " + questionnaireTwo
            		   + ", `questionnairethree` = " + questionnaireThree
            		   + ", `questionnaireopenone` = '" + questionnaireopenone
            		   + "', `questionnaireopentwo` = '" + questionnaireopentwo
            		   + "' WHERE `userid` = " + userID;
            
            count = stmt.executeUpdate(sql);
            
            // finished and then close these
            stmt.close();
            conn.close();
            
        } catch (SQLException se){
            // deal JDBC problem
            se.printStackTrace();
        } catch (Exception e){
            // deal Class.forName problem
            e.printStackTrace();
        } finally {
            // close resources
            try {
                if(stmt != null) stmt.close();
            } catch (SQLException se2){
            } // do nothing
            try {
                if(conn != null) conn.close();
            } catch (SQLException se){
                se.printStackTrace();
            }
        }
        return count;
    }
    
    public int UpdateFirstScoreByuserID(int userID, int basic_score, String basic_ans) {
        Connection conn = null;
        Statement stmt = null;
        
        int count = 0;
        
        try {
            // register JDBC Diver
            Class.forName(JDBC_DRIVER);
        
            // open connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        
            // do select
            stmt = conn.createStatement();
            String sql = "UPDATE `gojava` "
            		   + "SET `basic_score` = " + basic_score
            		   + ", `basic_ans` = '" + basic_ans
            		   + "' WHERE `userid` = " + userID;
            
            count = stmt.executeUpdate(sql);
            
            // finished and then close these
            stmt.close();
            conn.close();
            
        } catch (SQLException se){
            // deal JDBC problem
            se.printStackTrace();
        } catch (Exception e){
            // deal Class.forName problem
            e.printStackTrace();
        } finally {
            // close resources
            try {
                if(stmt != null) stmt.close();
            } catch (SQLException se2){
            } // do nothing
            try {
                if(conn != null) conn.close();
            } catch (SQLException se){
                se.printStackTrace();
            }
        }
        return count;
    }

    public int UpdateSecondScoreByuserID(int userID, int advanced_score, String advanced_ans) {
        Connection conn = null;
        Statement stmt = null;
        
        int count = 0;
        
        try {
            // register JDBC Diver
            Class.forName(JDBC_DRIVER);
        
            // open connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        
            // do select
            stmt = conn.createStatement();
            String sql = "UPDATE `gojava` "
            		   + "SET `advanced_score` = " + advanced_score
            		   + ", `advanced_ans` = '" + advanced_ans
            		   + "' WHERE `userid` = " + userID;
            
            count = stmt.executeUpdate(sql);
            
            // finished and then close these
            stmt.close();
            conn.close();
            
        } catch (SQLException se){
            // deal JDBC problem
            se.printStackTrace();
        } catch (Exception e){
            // deal Class.forName problem
            e.printStackTrace();
        } finally {
            // close resources
            try {
                if(stmt != null) stmt.close();
            } catch (SQLException se2){
            } // do nothing
            try {
                if(conn != null) conn.close();
            } catch (SQLException se){
                se.printStackTrace();
            }
        }
        return count;
    }
}

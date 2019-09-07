package com.test;

import java.sql.*;

public class TestMySQLSelect {
	
	// MySQL 8.0 and earlier version - JDBC diver and it's URL
    //static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    //static final String DB_URL = "jdbc:mysql://www.db4free.net:3306/gojava";
 
    // MySQL 8.0 and later version - JDBC diver and it's URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://www.db4free.net:3306/gojava?useSSL=false&serverTimezone=UTC";
    
    // user name and password
    static final String USER = "kxc855";
    static final String PASS = "qazwsx123";
    
    public int[] SelectLatestIDandScore() {
        Connection conn = null;
        Statement stmt = null;
        
        int[] arrResults = new int[2];
        
        try {
            // register JDBC Diver
            Class.forName(JDBC_DRIVER);
        
            // open connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        
            // do select
            // SELECT `userid`, `basic_score`, `advanced_score` 
            // FROM `gojava` WHERE `finishStatusBool` = 0 ORDER BY `userid` DESC LIMIT 1
            stmt = conn.createStatement();
            String sql = "SELECT `userid`, `basic_score`, `advanced_score` "
            		   + "FROM `gojava` "
            		   + "WHERE `finishStatusBool` = 0 "
            		   + "ORDER BY `userid` DESC "
            		   + "LIMIT 1";
            ResultSet rs = stmt.executeQuery(sql);
            
            // print the result
            while(rs.next()){
                // select by
            	arrResults[0] = rs.getInt("userid");
            	arrResults[1] = rs.getInt("basic_score");
            	arrResults[2] = rs.getInt("advanced_score");
            }
            
            // finished and then close these
            rs.close();
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
        
        return arrResults;
        
    }
    
    public String[] SelectNameandjavaLevelByLatestID(int LatestID) {
        Connection conn = null;
        Statement stmt = null;
        
        String[] arrResults = new String[3];
        
        try {
            // register JDBC Diver
            Class.forName(JDBC_DRIVER);
        
            // open connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        
            // do select
            // SELECT `userid`, `basic_score`, `advanced_score` 
            // FROM `gojava` WHERE `finishStatusBool` = 0 ORDER BY `userid` DESC LIMIT 1
            stmt = conn.createStatement();
            String sql = "SELECT `stu_name`, `javaLevel` "
            		   + "FROM `gojava` "
            		   + "WHERE `userid` = " + LatestID;
            ResultSet rs = stmt.executeQuery(sql);
            
            // print the result
            while(rs.next()){
                // select by
            	arrResults[0] = rs.getString("stu_name");
            	arrResults[1] = rs.getString("javaLevel");
            }
            
            // finished and then close these
            rs.close();
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
        
        return arrResults;
        
    }
    
    
    public int SelectALatestID() {
        Connection conn = null;
        Statement stmt = null;
        
        int userid = 0;
        String stu_name = "";
        
        try {
            // register JDBC Diver
            Class.forName(JDBC_DRIVER);
        
            // open connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        
            // do select
            // SELECT `userid`, `stu_name` FROM `gojava` WHERE `finishStatusBool` = 0 ORDER BY `userid` DESC LIMIT 1
            stmt = conn.createStatement();
            String sql = "SELECT `userid`, `stu_name` "
            		   + "FROM `gojava` "
            		   + "WHERE `finishStatusBool` = 0 "
            		   + "ORDER BY `userid` DESC "
            		   + "LIMIT 1";
            ResultSet rs = stmt.executeQuery(sql);
            
            // print the result
            while(rs.next()){
                // select by
                userid = rs.getInt("userid");
				stu_name = rs.getString("stu_name");
            }
            
            // finished and then close these
            rs.close();
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
        
        return userid;
        
    }
    
    public String SelectJavaLevelByID(int userID) {
        Connection conn = null;
        Statement stmt = null;
        
        String javaLevel = "";
        
        try {
            // register JDBC Diver
            Class.forName(JDBC_DRIVER);
        
            // open connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        
            // do select
            stmt = conn.createStatement();
            String sql = "SELECT `javaLevel`"
            		   + "FROM `gojava` "
            		   + "WHERE `userid` = " + userID;
            
            ResultSet rs = stmt.executeQuery(sql);
            
            // print the result
            while(rs.next()){
                // select by
            	javaLevel = rs.getString("javaLevel");
            }
            
            // finished and then close these
            rs.close();
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
        
        return javaLevel;
        
    }
}

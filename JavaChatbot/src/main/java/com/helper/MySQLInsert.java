package com.helper;

import java.sql.*;

public class MySQLInsert {
	
	// MySQL 8.0 and later version - JDBC diver and it's URL
    String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    String DB_URL = "jdbc:mysql://www.db4free.net:3306/gojava?useSSL=false&serverTimezone=UTC";
 
 
    // user name and password
    String USER = "kxc855";
    String PASS = "qazwsx123";
    
    public void insertGoJavaNewdata(String stu_name, String javaLevel) {
    	
        Connection conn = null;
        Statement stmt = null;
        try{
            // register JDBC Diver
            Class.forName(JDBC_DRIVER);
        
            // open connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        
            // do insert
            // the mysql insert statement
            String query = " insert into gojava (stu_name, javaLevel)"
              + " values (?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, stu_name);
            preparedStmt.setString (2, javaLevel);

            // execute the preparedstatement
            preparedStmt.execute();
            
            conn.close();
            
        }catch(SQLException se){
            // deal JDBC problem
            se.printStackTrace();
        }catch(Exception e){
            // deal Class.forName problem
            e.printStackTrace();
        }finally{
            // close resources
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            } // do nothing
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }

	public void insertGoJava(String stu_name, int questionnaireone, int questionnairetwo, int questionnairethree) {
    	
        Connection conn = null;
        Statement stmt = null;
        try{
            // register JDBC Diver
            Class.forName(JDBC_DRIVER);
        
            // open connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        
            // do insert
            // the mysql insert statement
            String query = " insert into gojava (stu_name, questionnaireone, questionnairetwo, questionnairethree)"
              + " values (?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, stu_name);
            preparedStmt.setInt (2, questionnaireone);
            preparedStmt.setInt   (3, questionnairetwo);
            preparedStmt.setInt(4, questionnairethree);

            // execute the preparedstatement
            preparedStmt.execute();
            
            conn.close();
            
        }catch(SQLException se){
            // deal JDBC problem
            se.printStackTrace();
        }catch(Exception e){
            // deal Class.forName problem
            e.printStackTrace();
        }finally{
            // close resources
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            } // do nothing
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
	
}

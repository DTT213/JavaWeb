/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toandt.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Toan
 */
public class DBHelper implements Serializable{
    public static Connection makeConnection()
        throws /*ClassNotFoundException*/ NamingException, SQLException {
        //1. get current context
        Context context = new InitialContext();
        //2. lookup tomcat's context
        Context tomcat = (Context)context.lookup("java:comp/env");
        //3. lookup datasource
        DataSource ds=(DataSource)tomcat.lookup("BeanDS");
        //4. open connection
        Connection con=ds.getConnection();
        return con;
//        //1. Load driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2. Create connection string
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=JavaWeb;instanceName=SQLEXPRESS";
//        //3. Open connection
//        Connection con= DriverManager.getConnection(url,"sa","12345678");
//        return con;
    }
    
}

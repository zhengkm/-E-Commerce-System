package com.dsc.DataAggregation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hongzhang
 * 
 * How do the various regions compare by sales volume?
 */
public class showProductInfo extends HttpServlet {
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res){
        
        String product_ID = req.getParameter("id");
        
        String url = "jdbc:mysql://localhost:8889/DSC";  // DSC为mysql里面的数据库名称
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        
        res.setCharacterEncoding("UTF-8");
        res.setContentType("text/html;charset=UTF-8");
        
        

                     
        try{
                PrintWriter out = res.getWriter();
            try{
                   // Class.forName("org.gjt.mm.mysql.Driver").newInstance();
                   Class.forName("com.mysql.jdbc.Driver");
                   System.out.println("mysql working ...");
            }
            catch (Exception ex){
                   System.out.println("fail to create mysql drive" + ex.getMessage());
                   return;
            }
            
            try{
                // root为mysql用户名, ""为密码
                con = DriverManager.getConnection(url, "root", "root");
                stat = con.createStatement();
                
                
                String sqlString = "select *\n" +
                                   "from Products\n" +
                                   "where product_ID = " + product_ID ;
                rs = stat.executeQuery(sqlString);
                
                String str = "There is no this product in the database given by product_ID!";
                // create a table
                if (rs.next()) {
                str = 
"            <li style=\"display:none;\">product_ID:&nbsp;<input id=\"productID1\" type=\"text\" value=\"" + rs.getString(1) +"\" /></li>\n" +
"            <li>name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id=\"name1\" type=\"text\" value=\"" + rs.getString(2) +"\"/></li>\n" +
"            <li>price:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id=\"price1\" type=\"text\" value=\"" + rs.getString(4) +"\" /></li>\n" +
"            <li>kind:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id=\"kind1\" type=\"text\" value=\"" + rs.getString(5) +"\" /></li>\n" +
"            <li>brand:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id=\"brand1\" type=\"text\" value=\"" + rs.getString(6) +"\" /></li>\n" +
"            <li>description:   <p></p><textarea rows=\"10\" cols=\"30\" id=\"description1\" >" + rs.getString(7) + "</textarea></li>            \n" +
"            <li><button id=\"updateProduct\">update this product !</button></li>\n";
                }  
                
               
                // output to client 
                out.print(str);
          
                rs.close();
                stat.close();
                con.close();
            }
            catch (SQLException ex){
                ex.printStackTrace(out);
            }
        }
        catch (Exception ex){
                ex.printStackTrace();
        }
    }
}

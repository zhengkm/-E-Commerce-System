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
 * What are the top product categories?
 */
public class Top_ProductCategory extends HttpServlet {
    
    public void doGet(HttpServletRequest req, HttpServletResponse res){
        //String productName = req.getAttribute("productName");
        String productName = "mouse";
        
        String url = "jdbc:mysql://localhost:8889/DSC";  // DSC为mysql里面的数据库名称
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        
        res.setContentType("text/html");

        String docType = "<!DOCTYPE HTML PUBLIC \"//W3C//DTD HTML 4.0 ";
               docType += "Transitional//EN\">\n";

                     
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
                // root为mysql用户名, 123456为密码
                con = DriverManager.getConnection(url, "root", "root");
                stat = con.createStatement();
                //this sql return product_ID and its total sales 
                String sqlString = "SELECT product_ID, sum(quantity)\n" +
                                   "FROM Transactions\n" +
                                   "group by product_ID\n" +
                                   "order by sum(quantity) DESC";
                rs = stat.executeQuery(sqlString);
                
                StringBuilder str = new StringBuilder();
                while (rs.next()){
                    // rs.getString(1), rs.getString(2)为a表中的两个字段
                    String tmp = "<p>" +
                            rs.getString(1) + ", " + rs.getString(2) +  
                            "</p>\n";
                    str.append(tmp);
                }
                
                out.println(docType + 
                   "<HTML>\n" +
                   "<BODY>\n" +
                   str + 
                   "</BODY></HTML>");
          
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

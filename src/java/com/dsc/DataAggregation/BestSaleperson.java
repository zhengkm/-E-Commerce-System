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
 * Which  businesses are buying given products the most?
 */
public class BestSaleperson extends HttpServlet {
    
    public void doPost(HttpServletRequest req, HttpServletResponse res){
        
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
                // root为mysql用户名, 123456为密码
                con = DriverManager.getConnection(url, "root", "root");
                stat = con.createStatement();
                
                String sqlString = "SELECT salesperson_id, salesperson_name, salary, sales_volume \n" +
                                   "FROM DSC.Salespersons\n" +
                                   "WHERE job_title = 'salesperson'\n" +
                                   "ORDER BY sales_volume DESC";
                rs = stat.executeQuery(sqlString);
                
                
                // create a table
                String header = "<div class=\"Inventory_Record_thead\">\n" +
                                    "<ul>" +
                                        "<li class=\"len6\">salesperson_ID</li>" +
                                        "<li class=\"len6\">salesperson_name</li>" +
                                        "<li class=\"len6\">salary</li>" +
                                        "<li class=\"len6\">sales_volume</li>" +
                                    "</ul>" +
                                "</div>\n" +
                                "<div class=\"Inventory_Record_TB\">\n" +
                                    "<table cellpadding=\"0\" cellspacing=\"0\">\n";
                
                String tailer =     "</table>\n" +
                                "</div>";
                
                // salesperson_id, salesperson_name, salary, sales_volume
                int count = 2;
                StringBuilder str = new StringBuilder();
                str.append(header);
                
                while (rs.next()){
                    if (count % 2 == 0) { // even
                        str.append("<tr class=\"even\">\n");
                        
                    } else { // odd
                        str.append("<tr class=\"odd\">\n");
                    }
                    // rs.getString(1)为表中的第一个字段
                    String tmp = "<td class=\"len6\">" + rs.getString(1) + "</td>\n" +
                                 "<td class=\"len6\">" + rs.getString(2) + "</td>\n" +
                                 "<td class=\"len6\">" + rs.getString(3) + "</td>\n" +
                                 "<td class=\"len6 IR_tips\"><a>" + rs.getString(4) + "</a></td>\n" +
                                 "</tr>\n";
                    str.append(tmp);
                    count++;
                }
                str.append(tailer);
               
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

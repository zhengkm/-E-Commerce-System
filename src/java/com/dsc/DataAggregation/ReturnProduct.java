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
public class ReturnProduct extends HttpServlet {
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res){
        
        //String customer_ID = req.getParameter("id");
        
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
                // return_ID, order_number, salesperson_id, product_ID, return_quantity
                String sqlString = "select *\n" +
                                   "from Sell_Return\n" +
                                   "order by order_number" ;
                rs = stat.executeQuery(sqlString);
                
                
                // create a table
                String header = "<div class=\"Inventory_Record_thead\">\n" +
                                    "<ul>\n" +
                                        "<li class=\"len2\">return_ID</li>\n" +
                                        "<li class=\"len2\">order_number</li>\n" +
                                        "<li class=\"len2\">salesperson_id</li>\n" +
                                        "<li class=\"len2\">product_ID</li>\n" +
                                        "<li class=\"len2\">return_quantity</li>\n" +
                                    "</ul>\n" +
                                "</div>\n" +
                                "<div class=\"Inventory_Record_TB\">\n" +
                                    "<table cellpadding=\"0\" cellspacing=\"0\">\n";
                
                String tailer =     "</table>\n" +
                                "</div>\n";
                
                // return_ID, order_number, salesperson_id, product_ID, return_quantity
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
                    String tmp = "<td class=\"len2\">" + rs.getString(1) + "</td>\n" +
                                 "<td class=\"len2\">" + rs.getString(2) + "</td>\n" +
                                 "<td class=\"len2\">" + rs.getString(3) + "</td>\n" +
                                 "<td class=\"len2\">" + rs.getString(5) + "</td>\n" +
                                 "<td class=\"len2 IR_tips\"><a>" + rs.getString(4) + "</a></td>\n" +
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

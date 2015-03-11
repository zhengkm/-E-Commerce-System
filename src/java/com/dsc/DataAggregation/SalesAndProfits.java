package com.dsc.DataAggregation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hongzhang
 * 
 * What are the top product categories?
 */
public class SalesAndProfits extends HttpServlet {
    
    
    /**
     * Data object used to store sales and profits by given product_ID
     */
    private class Data {
        public final String id;
        public final String name;
        public final float sales;
        public final float profits;
        
        public Data(String _id, String _name, float _sales, float _profits) {
            id = _id;
            name = _name;
            sales = _sales;
            profits = _profits;
        }
        
        @Override
        public String toString() {
            return id + ", " + name + ", " + sales + ", " + profits;
        }
    }
    
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
//                   Class.forName("org.gjt.mm.mysql.Driver").newInstance();
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
                
                /**
                 * iterate all product and calculate its sales and profits
                 * list used to store Data object
                 * Data -> object that uses to store product_id name, sales and profits
                 */
                ArrayList<Data> list = new ArrayList<Data>();
                
                String sqlString = "SELECT product_ID\n" +
                                   "FROM DSC.Stock\n" + 
                                   "GROUP BY product_ID";
                rs = stat.executeQuery(sqlString);
                 
                while (rs.next()) {
                     String product_ID = rs.getString(1);
                     Data record = calculateSalesAndProfits(product_ID, con);
                     list.add(record);
                 }
                            
                
               // create a table
                String header = "<div class=\"Inventory_Record_thead\">\n" +
                                    "<ul>" +
                                        "<li class=\"len6\">product_ID</li>" +
                                        "<li class=\"len6\">product_name</li>" +
                                        "<li class=\"len6\">salesr</li>" +
                                        "<li class=\"len6\">profits</li>" +
                                    "</ul>" +
                                "</div>\n" +
                                "<div class=\"Inventory_Record_TB\">\n" +
                                    "<table cellpadding=\"0\" cellspacing=\"0\">\n";
                
                String tailer =     "</table>\n" +
                                "</div>";
                
                //id, name, sales, profits
                int count = 2;
                StringBuilder str = new StringBuilder();
                str.append(header);
                
                for (Data a : list) {
                    if (count % 2 == 0) { // even
                        str.append("<tr class=\"even\">\n");
                        
                    } else { // odd
                        str.append("<tr class=\"odd\">\n");
                    }
                    // rs.getString(1)为表中的第一个字段
                    String tmp = "<td class=\"len6\">" + a.id + "</td>\n" +
                                 "<td class=\"len6\">" + a.name + "</td>\n" +
                                 "<td class=\"len6\">" + a.sales + "</td>\n" +
                                 "<td class=\"len6 IR_tips\"><a>" + a.profits + "</a></td>\n" +
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
    
    private Data calculateSalesAndProfits(String product_ID, Connection con) {
        
        try {
            Statement stat = con.createStatement();
            ResultSet rs   = null;

            /**
            * this SQL return stock_amount and stock_price by given product_ID 
            * duration: all time
            */
            String sqlString = "SELECT stock_amount, stock_price\n" +
                              "FROM DSC.Stock\n" + 
                              "where product_ID = " + product_ID;
            rs = stat.executeQuery(sqlString);


            /**
            * calculate avgPurchasePrice
            */
            int amount = 0;
            float priceBeforeAvg = 0;
            while(rs.next()) {
               amount += rs.getInt(1);
               priceBeforeAvg += rs.getInt(1) * rs.getFloat(2);

               System.out.println(amount + "    " + priceBeforeAvg +"   " + rs.getInt(1) + "    " + rs.getFloat(2));
            }

            float avgPurchasePrice = priceBeforeAvg / amount;


            /**
            * calculate sales and profits
            */
            sqlString = "SELECT price, name\n" +
                        "FROM Products \n" +
                        "where product_ID = " + product_ID;
            rs = stat.executeQuery(sqlString);

            float sales = 0;
            float profits = 0;
            String name = null;
            
            if (rs.next()) { //要先next（），这样指针才会指到第一个数据
               float price = rs.getFloat(1);
               name = rs.getString(2);
               sales = amount * price;
               profits = amount * (price - avgPurchasePrice);
            }

            // keep three decimal places
            profits = (int)Math.round(1000*profits)/1000f;
            
            return new Data(product_ID, name, sales, profits);
            
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }                
    }
}

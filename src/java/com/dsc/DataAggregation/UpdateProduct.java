package com.dsc.DataAggregation;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hong zhang
 */
public class UpdateProduct extends HttpServlet {

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productid=request.getParameter("productid");
        String name=request.getParameter("name");
        String brand=request.getParameter("brand");
        String kind=request.getParameter("kind");
        String price=request.getParameter("price");
        String description=request.getParameter("description");
        
        String flag = "1";
        String sql = "UPDATE `DSC`.`Products` SET `name`='"+name+"', `price`='"+price+"', `kind`='"+kind+"', `brand`='"+brand+"', `description`='"+description+"' WHERE `product_ID`='"+productid+"';";
    
        String url = "jdbc:mysql://localhost:8889/DSC";  
        Connection con = null;
        Statement stat = null;
            try{
                   // Class.forName("org.gjt.mm.mysql.Driver").newInstance();
                   Class.forName("com.mysql.jdbc.Driver");
                   //html.append("mysql working ...");
            }
            catch (Exception ex){
                  System.out.println("fail to create mysql drive" + ex.getMessage());
                  return;
            }
            try
            {
                // root为mysql用户名, 123456为密码
                con = DriverManager.getConnection(url, "root", "root");
                stat = con.createStatement();
                stat.executeUpdate(sql);
                
                
                stat.close();
                con.close();
            }
            catch (SQLException ex){
                while (ex != null){
                        flag = "0";
                        ex.printStackTrace();
                }
            }
            
            response.getWriter().print(flag);
    }

}
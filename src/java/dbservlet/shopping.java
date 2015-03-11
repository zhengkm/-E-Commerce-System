/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbservlet;

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
 * @author zhengkaiming
 */
public class shopping extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String[] data = request.getParameterValues("data");
        StringBuilder html = new StringBuilder();
       
            try
            {
              
                html.append(GetHtml(data));
                
                
            }
            catch (Exception ex){
                while (ex != null){
                        html.append(ex.toString());
                       
                }
            }
            
            String result=html.toString();
            request.getRequestDispatcher("product.jsp").forward(request, response);
            response.getWriter().print(result);
    }

    
    
String GetHtml(String[] data)
{
    StringBuilder html = new StringBuilder();
    try
    {
        if(data.length==0)
            html.append("No result !");
        else
        {
            html.append("<table id=\"item\">");
            html.append("<tr><td>Name</td><td>InventoryAmount</td><td>Price</td></tr>");
             
                for(int i=0;i<data.length;i++){  
                    html.append("<tr>");
//                for(int j=i;j<i+3;j++)
//                {
                    html.append("<td>");
                    html.append(data[i]);
                    html.append("</td>");
//                }
                html.append("</tr>");
             }
              
 
            
            html.append("</talbe>");
        }
            
    }
    catch(Exception ex)
    {
           while (ex != null)
           {
                html.append(ex.toString());
                    
            }
    }

    
    return html.toString(); 
}


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

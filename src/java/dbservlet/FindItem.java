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
public class FindItem extends HttpServlet {

   

    

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name=request.getParameter("Name");
        String brand=request.getParameter("Brand");
        String kind=request.getParameter("Kind");
        String price=request.getParameter("Price");
        String sort= request.getParameter("sort");
        StringBuilder html = new StringBuilder();
        String sql="";
        if(price.equals("0"))
            sql="select * from products where name like '"+name+"' and kind like '"+kind+"' and brand like '"+brand+"' order by price  "+sort+"";
        if(price.equals("1"))
            sql="select * from products where price between 0 and 499 and name like '"+name+"' and kind like '"+kind+"' and brand like '"+brand+"' order by price "+sort+"";
        if(price.equals("2"))
            sql="select * from products where price between 500 and 999 and name like '"+name+"' and kind like '"+kind+"' and brand like '"+brand+"' order by price "+sort+"";        
        if(price.equals("3"))
            sql="select * from products where price between 1000 and 1499 and name like '"+name+"' and kind like '"+kind+"' and brand like '"+brand+"' order by price "+sort+"";        
        if(price.equals("4"))
            sql="select * from products where price between 1500 and 1999 and name like '"+name+"' and kind like '"+kind+"' and brand like '"+brand+"' order by price "+sort+"";
        if(price.equals("5"))
            sql="select * from products where price >= 2000 and name like '"+name+"' and kind like '"+kind+"' and brand like '"+brand+"' order by price "+sort+""; 
        
        
        String url = "jdbc:mysql://localhost:8889/DSC";  // 数据库只能小写！！！
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
            try{
                   // Class.forName("org.gjt.mm.mysql.Driver").newInstance();
                   Class.forName("com.mysql.jdbc.Driver");
                   //html.append("mysql working ...");
            }
            catch (Exception ex){
                   html.append("fail to create mysql drive");
                   html.append(ex.getMessage());
            }
            try
            {
                // root为mysql用户名, 123456为密码
                con = DriverManager.getConnection(url, "root", "root");
                stat = con.createStatement();
                rs = stat.executeQuery(sql);
                html.append(GetHtml(rs));
                rs.close();
                stat.close();
                con.close();
            }
            catch (SQLException ex){
                while (ex != null){
                        html.append(ex.toString());
                        ex = ex.getNextException();
                }
            }
            
            String result=html.toString();
            response.getWriter().print(result);
    }
String GetHtml(ResultSet rs)
{
    StringBuilder html = new StringBuilder();
    try
    {
        if(rs.next()==false)
            html.append("No result !");
        else
        {
            html.append("<table id=\"item\">");
            html.append("<tr><td>Name</td><td>Amount</td><td>Price</td><td>Kind</td><td>Brand</td><td>Description</td><td>Number</td></tr>");
            do
            {
                html.append("<tr class=\"");
                html.append(rs.getString(1));
                html.append("\">");
                for(int i=2;i<=7;i++)
                {
                    html.append("<td>");
                    html.append(rs.getString(i));
                    html.append("</td>");
                }
                html.append("<td>");
                html.append("<input class=\"pname\" value=\"1\" pattern=\"[1-" );
                html.append(rs.getString(3));
                html.append("]\" width=\"2px\"onkeyup=\"this.value=this.value.replace(/[^\\d]/g,'') \" />");
                html.append("</td>");
                html.append("<td>");
                html.append("<button class=\"purchase\" >Purchase</button>");
              //  html.append("<button  onclick=\"PurchaseItem()\">Purchase !</button>");
                html.append("</td>");
                html.append("</tr>");
                
            }while(rs.next());
            html.append("</talbe>");
        }
            
    }
    catch(SQLException ex)
    {
           while (ex != null)
           {
                html.append(ex.toString());
                ex = ex.getNextException();     
            }
    }

    
    return html.toString(); 
}


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
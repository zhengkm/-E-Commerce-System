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
import javax.servlet.http.HttpSession;

/**
 *
 * @author zhengkaiming
 */
public class Login extends HttpServlet {

  
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        StringBuilder html = new StringBuilder();
        String url = "jdbc:mysql://localhost:8889/DSC";  // 数据库只能小写！！！
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        
        String sql = "select password from StaffAccount where username='"+username+"'";
        String salespersonid="select salesperson_id from StaffAccount where username='"+username+"'";
        
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
                if(!rs.first())
                    html.append("false");
                else if(!rs.getString(1).equals(password))
                    html.append("false");
                else
                {
                    rs=stat.executeQuery(salespersonid);
                    if(rs.next()==false)
                        html.append("false");
                    else
                    {
                        String sid=rs.getString(1);
                        String posi="select job_title from salespersons where salesperson_id='"+sid+"'";
                        rs=stat.executeQuery(posi);
                        rs.next();
                        html.append(rs.getString(1));
                        session.setAttribute("username", username);
                        session.setAttribute("job_title", rs.getString(1));
                    }
                }
                    
                    
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

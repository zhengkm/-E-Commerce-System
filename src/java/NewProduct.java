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
 * @author Kehao Xu
 */
public class NewProduct extends HttpServlet {

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productid=request.getParameter("productid");
        String name=request.getParameter("name");
        String brand=request.getParameter("brand");
        String kind=request.getParameter("kind");
        String price=request.getParameter("price");
        String description=request.getParameter("description");
        
        StringBuilder html = new StringBuilder();
        String check="select * from products where product_ID='"+productid+"'";
        String sql="insert into products VALUES('"+productid+"','"+name+"',0,"+price+",'"+kind+"','"+brand+"','"+description+"')";
        
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
                rs = stat.executeQuery(check);
                int result=1;
                if(rs.next()==false)
                    result=stat.executeUpdate(sql);
                else
                    html.append("Already has this product, do not add again!");
                if(result>0)
                    html.append("successful add new product!");
                else
                    html.append("fail to add product!");
                
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

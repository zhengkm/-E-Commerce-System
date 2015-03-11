/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
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
public class Newcustomer extends HttpServlet {

    

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        StringBuilder html = new StringBuilder();
        String getid="select customer_ID from Customers_Info";
        String sql1="";
        String sql2="";
        String name=request.getParameter("name");
        String street=request.getParameter("street");
        String city=request.getParameter("city");
        String state=request.getParameter("state");
        String zipcode=request.getParameter("zipcode");
        String kind=request.getParameter("kind");
        String hasexist="select * from Customers_Info where name='"+name+"' and street='"+street+"' and city='"+city+"' and state='"+state+"' and zipcode="+zipcode+" and kind='"+kind+"'";



        
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
                rs=stat.executeQuery(hasexist);
                if(rs.next()==true)
                {
                    html.append("You have already register! you customer ID is ");
                    html.append(rs.getString(1));
                }
                else
                {
                rs=stat.executeQuery(getid);
                rs.last();
                int customer_id=Integer.valueOf(rs.getString(1))+1;
                String id = String.valueOf(customer_id);
                int longth=id.length();
                StringBuilder customerid=new StringBuilder();
                for(int i=0;i<(7-longth);i++)
                    customerid.append("0");
                customerid.append(id);
                sql1="insert into Customers_Info VALUES('"+customerid.toString()+"','"+name+"','"+street+"','"+city+"','"+state+"',"+zipcode+",'"+kind+ "')";

                if(kind.equals("b"))
                {
                    String businesscate=request.getParameter("businesscate");
                    String income=request.getParameter("income");
                    sql2="insert into Business_Customers VALUES('"+customerid.toString()+"','"+businesscate+"',"+income+")";
                 }
                else
                {
                    String marriage=request.getParameter("marriage");
                    String gender=request.getParameter("gender");
                    String age=request.getParameter("age");
                    String income=request.getParameter("income");
                    sql2="insert into Home_Customers VALUES('"+customerid.toString()+"','"+marriage+"','"+gender+"',"+age+","+income+")";
                }
                int updateresult1=stat.executeUpdate(sql1);
                int updateresult2=stat.executeUpdate(sql2);                
                if(updateresult1>0 && updateresult2>0)
                {
                    html.append("You have became our new customer! your customer ID is ");
                    html.append(customerid.toString());
                }
                else
                    html.append("Sorry, there are something wrong!");
                rs.close();
                stat.close();
                con.close();
                }
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

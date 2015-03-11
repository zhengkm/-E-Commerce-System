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
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
/**
 *
 * @author Kehao Xu
 */
public class NewInventory extends HttpServlet {

   


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        StringBuilder html = new StringBuilder();
        String productid=request.getParameter("productid");
        String salespersonid=request.getParameter("salespersonid");
        String stockamount=request.getParameter("stockamount");
        String stockprice=request.getParameter("stockprice");
        Date now=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd");
        String stocktime=dateFormat.format(now);
        
        String hasitem="select * from products where product_ID='"+productid+"'";
        String hassalesperson="select * from salespersons where salesperson_id='"+salespersonid+"'";
        String getlaststock_ID="select * from stock" ;
//                                "order by stock_ID";
        
        String url = "jdbc:mysql://localhost:8889/DSC";  // 数据库只能小写！！！
        Connection con = null;
        Statement stat = null;
        Statement stat1 = null;
        ResultSet rs = null;
        ResultSet rs2=null;
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
                stat1 = con.createStatement();
                rs = stat.executeQuery(hasitem);
                rs2=stat1.executeQuery(hassalesperson);
                if(rs.next()==false)
                {
                    html.append("Do not have this item, please add it first!");
                }
                else if(rs2.next()==false)
                {
                    html.append("Do not have this salesperson, Please enter the right id");
                }
                else
                {
                    rs=stat.executeQuery(getlaststock_ID);
                    rs.last();
                    int stock_id=Integer.valueOf(rs.getString(1))+1;
                    String id = String.valueOf(stock_id);
                    int longth=id.length();
                    StringBuilder stockid=new StringBuilder();
                    for(int i=0;i<(7-longth);i++)
                        stockid.append("0");
                    stockid.append(id);
                    String sql="insert into stock VALUES('"+stockid.toString()+"','"+productid+"','"+salespersonid+"','"+stocktime+"',"+stockamount+","+stockprice+")";
                    String add="UPDATE products SET inventory_amount=inventory_amount+"+Integer.valueOf(stockamount)+" where product_ID='"+productid+"'";
                    int updateresult1=stat.executeUpdate(sql);
                    int updateresult2=stat.executeUpdate(add);
                    if(updateresult1>0 && updateresult2>0)
                        html.append("Successful Add Inventory!");
                    else
                        html.append("Fail to add inventory!");
                }
                
                rs.close();
                rs2.close();
                stat1.close();
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

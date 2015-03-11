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
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;

import bean.Products;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
/**
 *
 * @author zhengkaiming
 */
public class Servlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
     public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    request.setCharacterEncoding("UTF-8");
   		    response.setCharacterEncoding("UTF-8");
		try {  
		
  			 update(request,response);
  			
		       
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	}
	//doGet方法
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
       doPost(request,response);
    }
    
    public Connection connect() throws ClassNotFoundException, SQLException{
    	Connection conn=null; 
	    Class.forName("com.mysql.jdbc.Driver");
		String url="jdbc:mysql://localhost:8889/DSC"; 
	        String user="root"; 
		String password="root"; 
		conn=DriverManager.getConnection(url,user,password); 
		return conn;
	}
	//关闭数据库资源
	public void close(Statement stat,Connection conn) throws SQLException{
		if(stat!=null){
	    	   stat.close();
	    }
	    if(conn!=null){
	    	   conn.close();
	    }
            
	}
        
        public void update(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
    	Connection conn=null;
    	Statement stat=null;
        ResultSet rs=null;
        ResultSet rs0=null;
        ResultSet rs1=null;
        ResultSet rs2=null;
        String data = request.getParameter("data");
        StringBuilder html = new StringBuilder();
        JSONArray datalist = null;
        JSONObject input=null;
      try {
          input = new JSONObject(data);
      } catch (JSONException ex) {
          Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
      }
      try {
           datalist = input.getJSONArray("data");
      } catch (JSONException ex) {
          Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
      }
        Date now=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd");
        String BuyDate=dateFormat.format(now);
        System.out.println(BuyDate);
      for(int i=0;i<datalist.length();i++){
          String product_ID="";
          String quantity = "";
          String customer_ID="";
          //String BuyDate="";
          String salesperson_id="";
      try {
          product_ID = (String) datalist.getJSONObject(i).get("id");
          quantity= (String) datalist.getJSONObject(i).get("amount");
          customer_ID = (String) datalist.getJSONObject(i).get("customer_ID");
          //BuyDate = (String) datalist.getJSONObject(i).get("BuyDate");
          salesperson_id = (String) datalist.getJSONObject(i).get("salesperson_id");
          if(salesperson_id.length()!=7){
              response.getWriter().print("0");
              return;
          }
           //System.out.println(salesperson_id);
      } catch (JSONException ex) {
          Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
      }

      
 
    	conn=connect();
        stat=conn.createStatement();
                 rs=stat.executeQuery("select salesperson_id,customer_ID from Salespersons,Customers_Info where salesperson_id="+salesperson_id+" and customer_ID="+customer_ID+"");
                 //System.out.print(rs.getString(salesperson_id));
                 if(!rs.first()){
                 
                  response.getWriter().print("0");
                  System.out.print(rs);
                  
                 }
                 else{
                 try{
                 int intage = Integer.parseInt(quantity);
                 rs1 =stat.executeQuery("select inventory_amount from Products where product_ID="+product_ID+"");
                 if(rs1.next()){
                 int initage = rs1.getInt("inventory_amount");                 
                 int quantityresult = initage - intage;
                 String s=String.valueOf(quantityresult);
		 stat.execute("update Products set inventory_amount="+s+" where product_ID="+product_ID+"");
                 response.getWriter().print("1");
                request.setAttribute("result", select(product_ID,""));
                 }
                 else{response.getWriter().print("0");}
                 }
                 catch (Exception e){
//                     System.out.println("fuck1");
//                     request.getRequestDispatcher("productwrong.jsp").forward(request, response);
                 }
                 
                try{
                rs1 = stat.executeQuery("select MAX(order_number) from Transactions");
                int orderNumber;
                if(rs1.next()){
                    orderNumber = 1+rs1.getInt(1);
                }
                else{
                    orderNumber=1;
                }
                
                String ran = String.valueOf(orderNumber);
                while(ran.length()<7){
                    ran = 0+ran;
                }
                stat.execute("INSERT INTO `Transactions`(`order_number`, `BuyDate`, `salesperson_id`, `customer_ID`, `product_ID`, `quantity`) VALUES ('"+ran+"','"+BuyDate+"','"+salesperson_id+"','"+customer_ID+"','"+product_ID+"','"+quantity+"')");
                 
                 }
                 catch (Exception e){
                    
                    
                     request.getRequestDispatcher("tranwrong.jsp").forward(request, response);
                 }
		
                 try{
                 int initquan = Integer.parseInt(quantity);
                 rs1 =stat.executeQuery("select * from Products where product_ID="+product_ID+"");
                 rs1.next();
                 
                 double initprice = rs1.getDouble("price");
                 
                 rs2 =stat.executeQuery("select * from Salespersons where salesperson_id="+salesperson_id+"");
                 rs2.next();
                 double salesvolume = rs2.getDouble("sales_volume");
              
                 double finalresult = salesvolume+initprice*initquan;
                 
                 stat.execute("update Salespersons set sales_volume="+finalresult+" where salesperson_id="+salesperson_id+"");
                 //request.getRequestDispatcher("update.jsp").forward(request, response);
                }
                catch (Exception e){
                    System.out.println("hello");
                     request.getRequestDispatcher("salewrong.jsp").forward(request, response);
                }}
      
//            html.append("");
//            String result=html.toString();
            //response.getWriter().print("sucess");
            //request.getRequestDispatcher("salewrong.jsp").forward(request, response);
      }
    }
        

   
}

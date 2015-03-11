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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author zhengkaiming
 */
public class returnServlet extends HttpServlet {

 
    private static final long serialVersionUID = 1L;
     public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    request.setCharacterEncoding("UTF-8");
   		    response.setCharacterEncoding("UTF-8");
		try {  
		
  			 returnProduct(request,response);
  			
		       
			} catch (ClassNotFoundException | SQLException e) {
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
        
        public void returnProduct(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
    	Connection conn=null;
    	Statement stat=null;
        ResultSet rs=null;
        
        ResultSet rs1=null;
        ResultSet rs2=null;

     
          
          String  quantity= request.getParameter("quantity");
          String order_number=request.getParameter("order_number");
          String salesperson_id=request.getParameter("salesperson_id");
          String product_ID=request.getParameter("product_ID");
//          if(salesperson_id.length()!=7){
//              response.getWriter().print("0");
//              return;
//          }
      
 
    	conn=connect();
        stat=conn.createStatement();
            try{
                rs1 = stat.executeQuery("select MAX(return_ID) from Sell_Return");
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
                
                String sql = "select quantity\n" +
                             "from Transactions\n" +
                             "where order_number = " + order_number;
                int quan = 0;
                rs1 = stat.executeQuery(sql);
                if (rs1.first()) {
                    quan = rs1.getInt(1);
                } else {
                    response.getWriter().print("0");
                    return;
                }
                
                
                String sql1 = "select sum(return_quantity)\n" +
                             "from Sell_Return\n" +
                             "where order_number = " + order_number +"\n" +
                             "and   product_ID = " + product_ID;
                int Rquan = 0;
                rs1 = stat.executeQuery(sql1);
                if (rs1.first()) {
                    Rquan = rs1.getInt(1);
                } else {
                    response.getWriter().print("0");
                    return;
                }
                
                if (quan < (Integer.parseInt(quantity) + Rquan)) {
                        response.getWriter().print("0");
                        return;
                    }
                
                
		 //stat.execute("INSERT INTO `Sell_Return`(`order_number`,  `salesperson_id`, `product_ID`, `quantity`) VALUES ('"+order_number+"','"+salesperson_id+"','"+product_ID+"','"+quantity+"')");
                 stat.execute("INSERT INTO `Sell_Return`(`return_ID`, `order_number`, `salesperson_id`, `return_quantity`, `product_ID`) VALUES ('"+ran+"','"+order_number+"','"+salesperson_id+"','"+quantity+"','"+product_ID+"')");
                 response.getWriter().print("1");
                
//                 }
//                 else{response.getWriter().print("0");}
                 }
                 catch (Exception e){
                     response.getWriter().print("0");
                     e.printStackTrace();
//                     request.getRequestDispatcher("productwrong.jsp").forward(request, response);
                 }
                 
               
		
                
                 }
      

      
    
        
}

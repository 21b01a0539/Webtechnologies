import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;*/
public class Sign extends HttpServlet{
public void doPost(HttpServletRequest req, HttpServletResponse res)throws 
IOException,ServletException{
res.setContentType("text/html");
PrintWriter out = res.getWriter();
String username = req.getParameter("username");
String password = req.getParameter("password");
try{
Class.forName("com.mysql.jdbc.Driver");
Connection con = 
DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb?useSSL=false","root","M@shrath29");
String q = "insert into user(username,password) values (?,?)";
PreparedStatement stm = con.prepareStatement(q);
stm.setString(1,username);
stm.setString(2,password);

int x = stm.executeUpdate();
System.out.println("Data Updated successfully..."+x);
if(x>0){
res.sendRedirect("index.html");
}
else{
out.println("<html>Register Not successful!</html>");
}
con.close();
}
catch(Exception e){System.out.println("opps");}
}
}


import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class Cookie1 extends HttpServlet{
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException{
        res.setContentType("text/html");
        String n = req.getParameter("usr");
        String p = req.getParameter("pwd");
        PrintWriter out = res.getWriter();
        String usr1 = getServletConfig().getInitParameter("usr1");
        String pw1 = getServletConfig().getInitParameter("pw1");
		String usr2 = getServletConfig().getInitParameter("usr2");
		String pw2 = getServletConfig().getInitParameter("pw2");
        String usr[] = {usr1,usr2};
		String pw[] = {pw1,pw2};
int flag=0;
		for(int i=0;i<2;i++){
	if(usr[i].equals(n) && pw[i].equals(p)){
	flag = 1;}
if (flag == 1){
out.println("Welcome "+n);}
else{
out.println("Invalid authentication");}
    }
}
}
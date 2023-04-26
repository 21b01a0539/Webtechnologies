import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CatalogueServlet")
public class CatalogueServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Step 1: Establish a database connection
        String url = "jdbc:mysql://localhost:3306/bookstore";
        String username = "root";
        String password = "password";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(url, username, password);

            // Step 2: Retrieve data from the database using a SELECT statement
            stmt = conn.createStatement();
            String query = "SELECT name, author, publisher, price FROM books WHERE category = 'Programming'";
            rs = stmt.executeQuery(query);

            // Step 3: Display the data in HTML format
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Catalogue</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<table width='100%'>");
            out.println("<tr>");
            out.println("<th>Book Name</th>");
            out.println("<th>Author</th>");
            out.println("<th>Publisher</th>");
            out.println("<th>Price</th>");
            out.println("</tr>");
            while (rs.next()) {
                String name = rs.getString("name");
                String author = rs.getString("author");
                String publisher = rs.getString("publisher");
                double price = rs.getDouble("price");
                out.println("<tr>");
                out.println("<td>" + name + "</td>");
                out.println("<td>" + author + "</td>");
                out.println("<td>" + publisher + "</td>");
                out.println("<td>" + price + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Step 4: Close the database connection and statement objects
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

<%@page import="java.sql.*"%>
<html>
<head>
    <title>CATALOGUE</title>
</head>
<body>
    <table width="100%" height="100%">
        <%
            // Step 1: Establish a database connection
            String url = "jdbc:mysql://localhost:3306/bookstore";
            String username = "root";
            String password = "password";
            Connection conn = DriverManager.getConnection(url, username, password);

            // Step 2: Retrieve data from the database using a SELECT statement
            Statement stmt = conn.createStatement();
            String query = "SELECT name, author, publisher, price FROM books WHERE category = 'Programming'";
            ResultSet rs = stmt.executeQuery(query);

            // Step 3: Display the data in a table row
            while (rs.next()) {
                String name = rs.getString("name");
                String author = rs.getString("author");
                String publisher = rs.getString("publisher");
                double price = rs.getDouble("price");
        %>
        <tr>
            <td> <img src="c.jpg"></td>
            <td> Name: <%= name %><br>Author: <%= author %><br>Publisher: <%= publisher %><br></td>
            <td> $ <%= price %></td>
            <td><button type="submit">ADD TO CART</td>
        </tr>
        <%
            }

            // Step 4: Close the database connection and statement objects
            rs.close();
            stmt.close();
            conn.close();
        %>
    </table>
</body>
</html>

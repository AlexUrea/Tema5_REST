import java.sql.*;

import static spark.Spark.*;


public class Shopify {

    static Customer customer = new Customer();
    static Orders order = new Orders();

    public static void main(String[] args)throws SQLException {


        get("/customer/:id", (req, res) ->{
            long id = Long.parseLong(req.params(":id"));
            String connectionUrl = "jdbc:mysql://localhost:3306/mvn_test";
            String username = "root";
            String password = "Imprimanta12345";
            Connection connection = DriverManager.getConnection(connectionUrl, username, password);

            String QUERY = "SELECT * FROM customers WHERE id = " + id;

            Statement ps = connection.createStatement();

            ResultSet rs = ps.executeQuery(QUERY);
            Customer customer = new Customer();
            while(rs.next()){
                customer.setID(rs.getInt("id"));
                customer.setUsername(rs.getString("username"));
                customer.setLast_name(rs.getString("last_name"));
                customer.setFirst_name(rs.getString("first_name"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                customer.setCity(rs.getString("city"));
                customer.setPostalCode(rs.getString("postalCode"));
                customer.setCountry(rs.getString("country"));
            }

            return customer;
        });

        put("/customer/:id", (req,res) -> {
            String sql = "select CASE WHEN count(" + req.params(":id") + " > 0 THEN true ELSE false END from Customers";
            String connectionUrl = "jdbc:mysql://localhost:3306/mvn_test";
            String username = "root";
            String password = "Imprimanta12345";
            Connection connection = DriverManager.getConnection(connectionUrl, username, password);
            PreparedStatement stc=connection.prepareStatement(sql);

            ResultSet rsk=stc.executeQuery();
            boolean cc=true;
            cc=rsk.getBoolean(1);

            if(cc){
                PreparedStatement psw = connection.prepareStatement("INSERT INTO `customers` (`username`, `last_name`, `first_name`, `phone`, `address`, `city`, `postalCode`, `country`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
                psw.setString(2, customer.getUsername());
                psw.setString(3, customer.getLast_name());
                psw.setString(4, customer.getFirst_name());
                psw.setString(5, customer.getPhone());
                psw.setString(6, customer.getAddress());
                psw.setString(7, customer.getCity());
                psw.setString(8, customer.getPostalCode());
                psw.setString(9, customer.getCountry());
                psw.execute();
                return "A customer with the id " + req.params(":id") + " has been created";
            }
            else{
                PreparedStatement psw = connection.prepareStatement("INSERT INTO `customers` (`id`, `username`, `last_name`, `first_name`, `phone`, `address`, `city`, `postalCode`, `country`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
                psw.setInt(1, customer.getAndIncrement(customer.getID()));
                psw.setString(2, customer.getUsername());
                psw.setString(3, customer.getLast_name());
                psw.setString(4, customer.getFirst_name());
                psw.setString(5, customer.getPhone());
                psw.setString(6, customer.getAddress());
                psw.setString(7, customer.getCity());
                psw.setString(8, customer.getPostalCode());
                psw.setString(9, customer.getCountry());
                psw.execute();
                return "The customer with the id " + req.params(":id") + " has been updated";
            }


        });


        delete("/customer/:id",(req,res)->{
            String connectionUrl = "jdbc:mysql://localhost:3306/mvn_test";
            String username = "root";
            String password = "Imprimanta12345";

            Connection connection = DriverManager.getConnection(connectionUrl, username, password);
            PreparedStatement psw = connection.prepareStatement("DELETE FROM customers WHERE ID = " + req.params(":id") + ";");
            psw.execute();

            return "The customer with the id " + req.params(":id") + " has been removed";
        });

        get("/customer/:id",(req,res)->{
            String connectionUrl = "jdbc:mysql://localhost:3306/mvn_test";
            String username = "root";
            String password = "Imprimanta12345";
            Connection connection = DriverManager.getConnection(connectionUrl, username, password);

            String QUERY = "SELECT * FROM customers";

            Statement ps = connection.createStatement();

            ResultSet rs = ps.executeQuery(QUERY);

            while(rs.next()){
                System.out.println(rs.getInt("id"));
                System.out.println(rs.getString("username"));
                System.out.println(rs.getString("last_name"));
                System.out.println(rs.getString("first_name"));
                System.out.println(rs.getInt("phone"));
                System.out.println(rs.getString("address"));
                System.out.println(rs.getString("city"));
                System.out.println(rs.getInt("postalCode"));
                System.out.println(rs.getString("country"));
                System.out.println("\n\n");
            }
            return "All customers accessed";
        });

        get("/order/:id", (req, res) ->{
            long id = Long.parseLong(req.params(":id"));
            String connectionUrl = "jdbc:mysql://localhost:3306/mvn_test";
            String username = "root";
            String password = "Imprimanta12345";
            Connection connection = DriverManager.getConnection(connectionUrl, username, password);

            String QUERY = "SELECT * FROM orders WHERE id = " + id;

            Statement ps = connection.createStatement();

            ResultSet rs = ps.executeQuery(QUERY);
            Orders order = new Orders();
            while(rs.next()){
                order.setID(rs.getInt("id"));
                order.setOrder_date(rs.getString("order_date"));
                order.setShipped_date(rs.getString("shipped_date"));
                order.setStatus(rs.getString("status"));
                order.setComments(rs.getString("comments"));
                order.setCustomer_id(rs.getInt("customer_id"));
            }

            return order;
        });


        put("/order/:id", (req,res) -> {
            String sql = "select CASE WHEN count(" + req.params(":id") + " > 0 THEN true ELSE false END from Orders";
            String connectionUrl = "jdbc:mysql://localhost:3306/mvn_test";
            String username = "root";
            String password = "Imprimanta12345";
            Connection connection = DriverManager.getConnection(connectionUrl, username, password);
            PreparedStatement stc=connection.prepareStatement(sql);

            ResultSet rsk=stc.executeQuery();
            boolean cc=true;
            cc=rsk.getBoolean(1);

            if(cc){
                PreparedStatement psw = connection.prepareStatement("INSERT INTO `orders` (`id`, `order_date`, `shipped_date`, `status`, `comments`, `customer_id`) VALUES (?, ?, ?, ?, ?, ?);");
                psw.setString(2, order.getOrder_date());
                psw.setString(3, order.getShipped_date());
                psw.setString(4, order.getStatus());
                psw.setString(5, order.getComments());
                psw.setInt(6, order.getCustomer_id());
                psw.execute();
                return "An order with the id " + req.params(":id") + " has been created";
            }
            else{
                PreparedStatement psw = connection.prepareStatement("INSERT INTO `orders` (`id`, `order_date`, `shipped_date`, `status`, `comments`, `customer_id`) VALUES (?, ?, ?, ?, ?, ?);");
                psw.setInt(1, order.getID());
                psw.setString(2, order.getOrder_date());
                psw.setString(3, order.getShipped_date());
                psw.setString(4, order.getStatus());
                psw.setString(5, order.getComments());
                psw.setInt(6, order.getCustomer_id());
                psw.execute();

                return "The order with the id " + req.params(":id") + " has been updated";
            }
        });

        delete("/order/:id",(req,res)->{
            String connectionUrl = "jdbc:mysql://localhost:3306/mvn_test";
            String username = "root";
            String password = "Imprimanta12345";

            Connection connection = DriverManager.getConnection(connectionUrl, username, password);
            PreparedStatement psw = connection.prepareStatement("DELETE FROM orders WHERE ID = " + req.params(":id") + ";");
            psw.execute();

            return "The order with the id " + req.params(":id") + " has been removed";
        });

        get("/customer/:id",(req,res)->{
            String connectionUrl = "jdbc:mysql://localhost:3306/mvn_test";
            String username = "root";
            String password = "Imprimanta12345";
            Connection connection = DriverManager.getConnection(connectionUrl, username, password);

            String QUERY = "SELECT * FROM customers";

            Statement ps = connection.createStatement();

            ResultSet rs = ps.executeQuery(QUERY);

            while(rs.next()){
                System.out.println(rs.getInt("id"));
                System.out.println(rs.getString("order_date"));
                System.out.println(rs.getString("shipped_date"));
                System.out.println(rs.getString("status"));
                System.out.println(rs.getString("comments"));
                System.out.println(rs.getInt("customer_id"));

            }
            return "All orders accessed";
        });

        get("/orders?customer_id = 3", (req,res) ->{
            String connectionUrl = "jdbc:mysql://localhost:3306/mvn_test";
            String username = "root";
            String password = "Imprimanta12345";
            Connection connection = DriverManager.getConnection(connectionUrl, username, password);
        })
    }



}

package dbms.project.kitchenvault_project.classes;


import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatabaseTalk {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    private Statement stmt_select;
    private final PreparedStatement stmt_add;
    private final PreparedStatement stmt_del;
    private Connection connection;

    public DatabaseTalk(Connection c) throws SQLException {

        connection = c;
        // creating for select statement
        stmt_select = connection.createStatement();
        // creating insert statement
        String insertQuery = "INSERT INTO food_inventory(product_name, type, quantity, cost, total_cost, expiry_date, stock_date) VALUES(?, ?, ?, ?, ?, ?, ?)";
        stmt_add = connection.prepareStatement(insertQuery);
        // creating delete statement
        String deleteQuery = "DELETE FROM food_inventory WHERE id = ?";
        stmt_del = connection.prepareStatement(deleteQuery);
    }

    public void addDataToDb(String name, String type, int quantity, Double price, String expiry) throws SQLException {
        stmt_add.setString(1, name);
        stmt_add.setString(2, type);
        stmt_add.setInt(3, quantity);

        String formatPrice = String.format("%.2f", price);
        stmt_add.setString(4, formatPrice);

        String formatTotalPrice = String.format("%.2f", price*quantity);
        stmt_add.setString(5, formatTotalPrice);

        stmt_add.setString(6, expiry);

        String stock_date = LocalDate.now().format(formatter);
        stmt_add.setString(7, stock_date);

        int rowsAffected = stmt_add.executeUpdate();
        connection.commit();

        System.out.println("rows affected: " + rowsAffected);
    }

    public void deleteData(String id) throws SQLException {

        stmt_del.setString(1, id);  // Set the parameter value dynamically
        int affected = stmt_del.executeUpdate();    // Execute the delete operation

        System.out.println("No. of Deleted rows: " + affected);
        connection.commit();
    }

    public void deleteByQuantity(String id, Integer quantity) throws SQLException {
        ResultSet rs = stmt_select.executeQuery("SELECT * FROM food_inventory WHERE id = '" + id + "'");
        RowData data = null;
        while(rs.next()){
            data = new RowData(
                rs.getInt("id"),
                rs.getString("product_name"),
                rs.getString("type"),
                rs.getInt("quantity"),
                rs.getString("cost"),
                rs.getString("total_cost"),
                rs.getString("expiry_date"),
                rs.getString("stock_date")
            );
        }

        if(data != null){
            stmt_del.setString(1, id);  // Set the parameter value dynamically
            stmt_del.executeUpdate();    // Execute the delete operation
        }
        if(data != null && data.getQuantity() > quantity){

            addDataToDb(data.getProduct_name(), data.getType(), (data.getQuantity() - quantity), Double.parseDouble(data.getCost()), data.getExpiry_date());
        }
        connection.commit();
    }



    public ResultSet searchDb(String id, String pr_name, String type, String expiry, String stock) throws SQLException {
        String baseQuery = "SELECT * FROM food_inventory";
        StringBuilder whereClause = new StringBuilder();

        // Check for non-null and non-empty values for each parameter
        if (id != null && !id.isEmpty()) {
            whereClause.append("id = ").append(id);
        }
        if (pr_name != null && !pr_name.isEmpty()) {
            whereClause.append((whereClause.length() > 0 ? " AND " : "")).append("product_name = '").append(pr_name).append("'");
        }
        if (type != null && !type.equals("null")) {
            whereClause.append((whereClause.length() > 0 ? " AND " : "")).append("type = '").append(type).append("'");
        }
        if (expiry != null && !expiry.equals("null")) {
            whereClause.append((whereClause.length() > 0 ? " AND " : "")).append("expiry_date = '").append(expiry).append("'");
        }
        if (stock != null && !stock.equals("null")) {
            whereClause.append((whereClause.length() > 0 ? " AND " : "")).append("stock_date = '").append(stock).append("'");
        }

        String finalQuery = baseQuery + (whereClause.length() > 0 ? " WHERE " : "") + whereClause;

        System.out.println(finalQuery);  // Debugging output

        ResultSet rs = stmt_select.executeQuery(finalQuery);

        return rs;
    }

}

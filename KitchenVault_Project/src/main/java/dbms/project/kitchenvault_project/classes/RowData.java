package dbms.project.kitchenvault_project.classes;

public class RowData {
    private String product_name, type, cost, total_cost, expiry_date, stock_date;
    private Integer id, quantity;
    // Constructor
    public RowData(Integer id, String product_name, String type, Integer quantity, String cost, String total_cost, String expiry, String stock) {
        this.id = id;
        this.product_name = product_name;
        this.type = type;
        this.quantity = quantity;
        this.cost = cost;
        this.total_cost = total_cost;
        this.expiry_date = expiry;
        this.stock_date = stock;
    }

    // Getter methods
    public Integer getId() {
        return this.id;
    }

    public String getProduct_name() {
        return this.product_name;
    }

    public String getType() {
        return this.type;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public String getCost() {
        return this.cost;
    }

    public String getTotal_cost() {
        return this.total_cost;
    }

    public String getExpiry_date() {
        return this.expiry_date;
    }

    public String getStock_date() {
        return this.stock_date;
    }
}
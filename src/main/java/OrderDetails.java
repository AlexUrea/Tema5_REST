public class OrderDetails {
    private int quantity;
    private int priceEach;
    private int ID;
    private int product_code;

    public OrderDetails(int quantity, int priceEach, int ID, int product_code) {
        this.quantity = quantity;
        this.priceEach = priceEach;
        this.ID = ID;
        this.product_code = product_code;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPriceEach() {
        return priceEach;
    }

    public void setPriceEach(int priceEach) {
        this.priceEach = priceEach;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getProduct_code() {
        return product_code;
    }

    public void setProduct_code(int product_code) {
        this.product_code = product_code;
    }
}

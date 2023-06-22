package cinema.db.tables;

public class Snack {
    private final int sellId;
    private final String type;
    private final float price;
    private final String brand;
    private final String CF;

    public Snack(int sellId, String type, float price, String brand, String CF) {
        this.sellId = sellId;
        this.type = type;
        this.price = price;
        this.brand = brand;
        this.CF = CF;
    }

    public int getSellId() {
        return sellId;
    }

    public String getType() {
        return type;
    }

    public float getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }

    public String getCF() {
        return CF;
    }
}

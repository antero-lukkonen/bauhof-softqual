package bauhof.pages;

public class CartSummary {

    private String price;
    private String vat;
    private String priceWithVat;
    private String total;

    public CartSummary(String price, String vat, String priceWithVat, String total) {
        this.price = price;
        this.vat = vat;
        this.priceWithVat = priceWithVat;
        this.total = total;
    }

    public String getPrice() {
        return this.price;
    }

    public String getVat() {
        return this.vat;
    }

    public String getPriceWithVat() {
        return this.priceWithVat;
    }

    public String getTotal() {
        return this.total;
    }
}

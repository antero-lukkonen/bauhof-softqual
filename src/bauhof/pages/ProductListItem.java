package bauhof.pages;

public class ProductListItem {

    private String price;
    private Clickable addToCartButton;
    private String name;
    private Clickable openButton;
    private String id;

    public ProductListItem(String id, String name, String price, Clickable addToCartButton, Clickable openButton) {
        this.price = price;
        this.addToCartButton = addToCartButton;
        this.name = name;
        this.openButton = openButton;
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public Clickable getAddToCartButton() {
        return this.addToCartButton;
    }

    public String getName() {
        return this.name;
    }

    public Clickable getOpenButton() {
        return this.openButton;
    }

    public String getId() {
        return this.id;
    }

    public void addToCart() {
        getAddToCartButton().click();
    }
}

package bauhof.pages;

public class CartItem {

    private String name;
    private Clickable removeButton;
    private Clickable addMoreButton;
    private int qty;

    public CartItem(String name, Clickable removeButton, Clickable addMoreButton, int qty) {
        this.name = name;
        this.removeButton = removeButton;
        this.addMoreButton = addMoreButton;
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public Clickable getRemoveButton() {
        return this.removeButton;
    }

    public Clickable getAddOneMoreButton() {
        return this.addMoreButton;
    }

    public int getQty() {
        return this.qty;
    }
}

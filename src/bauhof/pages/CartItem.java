package bauhof.pages;

public class CartItem {

    private String name;
    private Clickable removeButton;

    public CartItem(String name, Clickable removeButton) {
        this.name = name;
        this.removeButton = removeButton;
    }

    public String getName() {
        return name;
    }

    public Clickable getRemoveButton() {
        return this.removeButton;
    }
}

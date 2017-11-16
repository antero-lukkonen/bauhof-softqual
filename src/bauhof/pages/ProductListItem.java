package bauhof.pages;

public class ProductListItem {

	private String price;
	private Clickable addToCartButton;
	private String name;

	public ProductListItem(String price, Clickable addToCartButton, String name) {
		this.price = price;
		this.addToCartButton = addToCartButton;
		this.name = name;
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
}

package bauhof.pages;

public class ProductListItem {

	private String price;
	private Object addToCartButton;
	private String name;

	public ProductListItem(String price, Object addToCartButton, String name) {
		this.price = price;
		this.addToCartButton = addToCartButton;
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public Object getAddToCartButton() {		
		return this.addToCartButton;
	}

	public String getName() {		
		return this.name;
	}
}

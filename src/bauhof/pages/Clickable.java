package bauhof.pages;

public class Clickable {

	public final Runnable _click;

	public Clickable(Runnable click) {
		this._click = click;
	}
	
	public void click() {
		this._click.run();
	}
}

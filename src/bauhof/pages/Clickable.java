package bauhof.pages;

public class Clickable {

	public final Runnable click;

	public Clickable(Runnable click) {
		this.click = click;
	}

	public void click() {
		this.click.run();
	}
}

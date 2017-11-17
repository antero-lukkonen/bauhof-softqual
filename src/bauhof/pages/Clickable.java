package bauhof.pages;

public class Clickable {

    public final Runnable click;
    private String name;

    public Clickable(Runnable click, String name) {
        this.click = click;
        this.name = name;
    }

    public void click() {
        this.click.run();
    }

    public String getName() {
        return this.name;
    }
}

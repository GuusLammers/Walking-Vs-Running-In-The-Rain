package Model;

public class RainDroplet extends Rectangle2D {

    Vector2D velocity;

    public RainDroplet(double width, double height, double bottomLeftX, double bottomLeftY, double velocity) {
        super(width, height, bottomLeftX, bottomLeftY);
        this.velocity = new Vector2D(0, -1, velocity);
    }

    public void move() {
        setBottomLeftY(getBottomLeftY() + velocity.y * velocity.magnitude);
    }
}

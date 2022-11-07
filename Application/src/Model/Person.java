package Model;

import java.util.HashSet;
import java.util.Set;

public class Person extends Rectangle2D {

    Vector2D velocity;

    boolean madeItToDestination;

    Set<RainDroplet> dropletsCollidedWith;

    int numberOfRainDropsCollidedWith;

    public Person(double width, double height, double bottomLeftX, double bottomLeftY, double velocity) {
        super(width, height, bottomLeftX, bottomLeftY);
        this.velocity = new Vector2D(1, 0, velocity);
        madeItToDestination = false;
        dropletsCollidedWith = new HashSet<RainDroplet>();
        numberOfRainDropsCollidedWith = 0;
    }

    public void hitByRainDroplet(RainDroplet rainDroplet) {
        if(dropletsCollidedWith.contains(rainDroplet)) return;
        if(this.collidesWith(rainDroplet)) {
            numberOfRainDropsCollidedWith++;
            dropletsCollidedWith.add(rainDroplet);
        }
    }

    public void move() {
        setBottomLeftX(getBottomLeftX() + velocity.x * velocity.magnitude);
    }

    public void hitByRainDroplet() {
        numberOfRainDropsCollidedWith++;
    }

    public int getNumberOfRainDropsCollidedWith() {
        return numberOfRainDropsCollidedWith;
    }

    public void madeItToDestination() {
        madeItToDestination = true;
    }

    public boolean atDestination() {
        return madeItToDestination;
    }
}

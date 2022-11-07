package Model;

public class Rectangle2D {

    double width, height;

    Coordinate2D bottomLeft;

    public Rectangle2D(double width, double height, double bottomLeftX, double bottomLeftY) {
        this.width = width;
        this.height = height;
        this.bottomLeft = new Coordinate2D(bottomLeftX, bottomLeftY);
    }

    public boolean collidesWith(Rectangle2D rect) {
        Coordinate2D rectBottomLeft = rect.getBottomLeft();
        
        // below this
        if(bottomLeft.getY() > rectBottomLeft.getY() + rect.getHeight()) {
            return false;
        }
        // above this
        else if(bottomLeft.getY() + getHeight() < rectBottomLeft.getY()) {
            return false;
        }
        // left of this
        else if(bottomLeft.getX() > rectBottomLeft.getX() + rect.getWidth()) {
            return false;
        }
        // right of this
        else if(bottomLeft.getX() + getHeight() < rectBottomLeft.getX()) {
            return false;
        }
       
        return true;
    }
    

    public Coordinate2D getBottomLeft() {
        return bottomLeft;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getBottomLeftX() {
        return bottomLeft.getX();
    }

    public void setBottomLeftX(double x) {
        this.bottomLeft.setX(x);
    }

    public double getBottomLeftY() {
        return bottomLeft.getY();
    }

    public void setBottomLeftY(double y) {
        this.bottomLeft.setY(y);
    }
    
}

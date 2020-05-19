import java.util.ArrayList;
/**
 * 
 * represent a states for problem solving by search
 */
import java.util.List;

public interface Tile {
    public Tile Parent();
    public double getCost();

    public void setCost(double b);

    public void moveUp();

    public void moveDown();

    public void moveRight();

    public void moveLeft();
    public Tile copy();
    public boolean equals(Object b);
    public String toString();
}

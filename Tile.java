import java.util.ArrayList;
/**
 * 
 * represent a states for problem solving by search
 */
import java.util.List;

public interface tile {
    public tile Parent();
    public double getCost();

    public void setCost(int b);

    public void moveUp();

    public void moveDown();

    public void moveRight();

    public void moveLeft();
    public tile copy();
    public boolean equals(Object b);
    public String toString();
	public Board getBoard();
	public String getOp();
}

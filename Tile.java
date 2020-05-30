import java.util.ArrayList;
/**
 * 
 * represent a states for problem solving by search
 */
import java.util.List;

public interface tile {
	public tile getParent();
	public void setParent(tile parent);

public boolean isVisited();
	public int getCost();
	public int getTime();
	public void setCost(int b);

	public Board getBoard();
	
	public String getNumOp();
	public void setNumOp(String num_op);
	
	public tile move(int direction);

	public tile copy();
	
	public boolean equals(Object b);
	
	public String toString();

	public tile getArrangedTile();
	public void visit();

}

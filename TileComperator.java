import java.util.Comparator;
/**
 * state comperator for algo use a sort queue
 */

public class TileComperator implements Comparator<tile>{



	@Override
	public int compare(tile arg0, tile arg1) {
		// TODO Auto-generated method stub
		if(arg0.getCost()==arg1.getCost()) return 0;
		else if (arg0.getCost()>arg1.getCost()) return 1;
		else return -1;
	}
    
}
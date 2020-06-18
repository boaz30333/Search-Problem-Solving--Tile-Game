import java.util.Comparator;

/**
 * 
 * @author Boaz Sharabi
 *This class represent search algo
 */
public abstract class heuristic_search_algo extends search_algo{


	public heuristic_search_algo(tile start, tile goal, boolean withOpen, boolean withTime) {
		super(start, goal, withOpen, withTime);
		// TODO Auto-generated constructor stub
	}
	public abstract int heuristic(tile t);
	public int f(tile t) {
		return(t.getCost()+heuristic(t));
	}
	public  Comparator<tile> get_tile_comperator(){
		return new Comparator<tile>() {
			
			@Override
			public int compare(tile arg0, tile arg1) {
				if (arg0==null) return -1;
				else if (arg1==null) return 1;
				int x= (f(arg0)-f(arg1));
				if(x!=0) 
				return x;
				else return arg0.getTime()-arg1.getTime();
			}
		};
	}
}

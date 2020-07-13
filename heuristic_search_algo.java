import java.util.Comparator;

/**
 * 
 * @author Boaz Sharabi
 *This class represent informed search algo using heuristic 
 */
public abstract class heuristic_search_algo extends search_algo{


	public heuristic_search_algo(tile start, tile goal, boolean withOpen, boolean withTime) {
		super(start, goal, withOpen, withTime);
		// TODO Auto-generated constructor stub
	}
	public abstract int heuristic(tile t);
	/**
	 * 
	 * @param tile
	 * @return evaluation function:f(n)=g(n)+h(n)
	 * Meaning: f(n) is the best estimate of a lowest cost
	 * path from the initial state to a goal state that is
	 * constrained to pass through node n. This is not
	 * necessarily the best path to the goal. (from lecture slides)

	 * Note: g(n) is accurate, h(n) is estimated.
	 */
	public int f(tile t) {
		return(t.getCost()+heuristic(t));
	}
	/**
	 * 
	 * @return tile comperator to determine which state is better acording to evaluation function and created time
	 */
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

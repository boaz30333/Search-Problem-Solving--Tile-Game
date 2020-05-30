
public abstract class heuristic_search_algo extends search_algo{


	public heuristic_search_algo(tile start, tile goal, boolean withOpen, boolean withTime) {
		super(start, goal, withOpen, withTime);
		// TODO Auto-generated constructor stub
	}
	public abstract int heuristic(tile t);
	public int f(tile t) {
		return(t.getCost()+heuristic(t));
	}
}

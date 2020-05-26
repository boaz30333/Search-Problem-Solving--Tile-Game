
public class DFBnB_algo extends heuristic_search_algo {

	public DFBnB_algo(tile start, tile goal, boolean withOpen, boolean withTime) {
		super(start, goal, withOpen, withTime);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int heuristic(tile t) {
		// TODO Auto-generated method stub
		return Tile_Heuristic_Functions.Manethen_Distance(t);
	}

	@Override
	public String run() {
		// TODO Auto-generated method stub
		return null;
	}



}

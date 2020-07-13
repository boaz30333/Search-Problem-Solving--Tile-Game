import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * 
 * @author Boaz Sharabi
 *
 *	This class represent A* search algorithm 
 *	https://en.wikipedia.org/wiki/A*_search_algorithm
 */
public class A_star_algo extends heuristic_search_algo {

	//more explain on tile_comperator in heuristic_search_algo class file
	PriorityQueue<tile> PQ= new PriorityQueue<>(get_tile_comperator());  
	HashMap<String, tile> CloseList= new HashMap<>();
	public A_star_algo(tile start, tile goal, boolean withOpen, boolean withTime) {
		super(start, goal, withOpen, withTime);
	}

	@Override
	public int heuristic(tile t) {
		return Tile_Heuristic.Manethen_Distance(t);
	}

	@Override
	public void run() {
		StartTime = System.nanoTime();
		if(!checkValid(start)) {
			saveToFile();
			return;
		}


		// insert the intial state to openlist and priority queue
		openlist.put(start.getKey(),start);
		PQ.add(start);

		while(!PQ.isEmpty()) {

			// print openlist to console if needed
			if(withOpen) PrintOpenList();

			// remove the state with lowest f(n) - f(n) = h(n) + g(n) / more explain in heuristic_search_algo class file
			tile state=PQ.poll(); 
			openlist.remove(state.getKey());


			// if we find the goal state return the solution
			if(state.equals(goal)) {
				path=path(state);
				count=ColorTilePuzzle.count;
				cost =state.getCost();
				saveToFile();
				return;
			}
			// insert the state to closelist
			CloseList.put(state.getKey(), state);

			//for each allowed operator on n do: {
			for(int i=1;i<5;i++) {
				tile child= state.move(i);
				if(child!=null) {

					// insert the state to the queue(and to openlist) if it not in openlist and not in closelist 
					if(!CloseList.containsKey(child.getKey())&&!openlist.containsKey(child.getKey())) {
						PQ.add(child);
						openlist.put(child.getKey(), child);
					}
					else {
						// check if there same tile in openlist/priority queue 
						tile same=openlist.get(child.getKey());

						// if we find this "child" state has cheaper path from initial state than same state already in openlist
						// take out the state with the expensive path from openlist and insert the state with the cheap path

						if(same!=null&&same.getCost()>child.getCost()) {
							PQ.remove(same);
							openlist.remove(child.getKey());
							openlist.put(child.getKey(), child);
							PQ.add(child);
						}}
				}
			}

			//end  for each allowed operator }


		}
		// there is no solution - check how much tiles  generated and return "no path"
		saveToFile();
	}
}

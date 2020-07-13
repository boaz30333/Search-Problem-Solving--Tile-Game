import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author Boaz Sharabi
 *
 *This class represent BFS search algo
 *https://en.wikipedia.org/wiki/Breadth-first_search
 */
public class BFS_algo extends search_algo{
	HashMap<String, tile> closelist= new HashMap<>();  // make closelist
	LinkedHashMap<String, tile> frontier= openlist;   // make frontier
	Queue<tile> queue= new LinkedList<>();            // queue
	public BFS_algo(tile start, tile goal, boolean with_open, boolean with_time) {
		super(start,goal,with_open,with_time);
	}
	@Override
	public void run() {

		StartTime = System.nanoTime();  
		if(!checkValid(start)) {
			saveToFile();
			return;
		}
		// make queue and hashmap (frontier) with initial tile

		frontier.put(start.getKey(),start);    
		queue.add(start);                        
		tile state=null; 
		while(!queue.isEmpty()) {
			//			if(ColorTilePuzzle.count>8000000) System.out.println(false);
			// print openlist to console if needed
			if(withOpen) PrintOpenList();
			// state = queue.remove_front , remove state from frontier and insert to closelist
			state= queue.poll();
			frontier.remove(state.getKey());
			closelist.put(state.getKey(), state);

			//for each allowed operator on state do: {
			for(int i=1;i<5;i++) {                   
				tile child= state.move(i);				// child= legal_operator(state) or null

				// if child is legal_operator(state) and not in frontier and not in closelist
				if(child!=null&&!closelist.containsKey(child.getKey())&&!frontier.containsKey(child.getKey())) { 

					// if child tile is the goal child return child as solution
					if(child.equals(goal)) {	
						path=path(child);
						cost =child.getCost();
						saveToFile();
						return;
					}

					// if child isnt the goal tile - add tile to frontier and to queue
					frontier.put(child.getKey(), child); 
					queue.add(child);


				}                                                                  
				//end  for each allowed operator }
			}
		}
		// there is no solution - check how much tiles  generated and return "no path"
		saveToFile();
	}


}
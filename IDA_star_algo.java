import java.util.Stack;

/**
 * 
 * @author Boaz Sharabi
 *This class represent IDA* search algo
 *https://en.wikipedia.org/wiki/Iterative_deepening_A*
 */
public class IDA_star_algo extends heuristic_search_algo {
	Stack<tile> stack= new Stack<>(); // make stack
	int t;  // threshold
	int minF=0;
	public IDA_star_algo(tile start, tile goal, boolean withOpen, boolean withTime) {
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

		t= heuristic(start);   // set intial threshold
		while(t!=Integer.MAX_VALUE) {
			minF= Integer.MAX_VALUE;
			openlist.put(start.getKey(), start);
			stack.push(start);
			while(!stack.isEmpty()) {
				tile state=stack.pop();
				
				//if we have finished scanning the descendants of this state to the limit
				if(state.isOut()) {
					// print openlist to console if needed
					if(withOpen) PrintOpenList();
					
					openlist.remove(state.getKey());}
				
				else {
					// mark the state "out" and insert to stack again -  when we pop this state again from stack  
					// it means that we have finished scanning the descendants of this state to the limit
					state.out();
					stack.push(state);
					
					//for each allowed operator on state do: {
					for(int i=1;i<5;i++) {
						tile child= state.move(i);
						if(child!=null) {
							if(f(child)>t) {
								
								// find the minimum 'f' encountered greater than threshold
								
								minF= Math.min(minF,f(child));         
								continue;
							}
							// loop avoidence
							if(openlist.containsKey(child.getKey())&&(openlist.get(child.getKey()).isOut())) 
								continue;
							//if there is same state in the stack not in correct path check if this state has
							// better estimate cost to the goal 
							// yes - replace it , no - dont insert this state to the stack
							if (openlist.containsKey(child.getKey())&&!(openlist.get(child.getKey()).isOut()) ){
								if(f(child)<f(openlist.get(child.getKey()))) {
									
									// print openlist to console if needed
									if(withOpen) PrintOpenList();
									stack.remove(openlist.get(child.getKey()));
									openlist.remove(child.getKey());
								}
								// 
								else continue;
							}
							
							// if we find the goal state return the solution
							if(child.equals(goal)) {
								path=path(child);
								cost =child.getCost();
								saveToFile();
								return;

							}
							// insert child state to openlist
							openlist.put(child.getKey(), child);
							stack.push(child);
						}
					}
					//end  for each allowed operator }

				}
			}
			//update threshold to be the minimum 'f' encountered greater than threshold
			t=minF; 
			// for next iteration mark start "noOut" again
			start.markNoOut();
		}
		saveToFile();
	}
}

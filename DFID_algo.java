

/**
 * 
 * @author Boaz Sharabi
 *
 *This class represent DFID search algo
 *https://en.wikipedia.org/wiki/Iterative_deepening_depth-first_search
 */
public class DFID_algo extends search_algo{
	String result="";
	int depth=1;    
	boolean isCutoff=false;
	public DFID_algo(tile start, tile goal, boolean withOpen, boolean withTime) {
		super(start, goal, withOpen, withTime);
	}
	@Override
	public void run() {
		StartTime = System.nanoTime();
		if(!checkValid(start)) {
			saveToFile();
			return;
		}
	    // for depth 1 -> infinty
		while(true) {      
			// make openlist
			openlist.clear();	
			
			 // call to limited dfs until depth (every loop depth param icrease by 1)
			result= Limited_DFS(start,goal,depth);   
			
			// if result = path to goal
			if(!result.equals("cutoff")&&!result.equals("fail")) { 
				saveToFile();
				return;
			}
			// if result = fail (no solution) exit and return "no path"
			else if(result.equals("fail")) break;  
			
			 // if result = "cutoff" increase depth by 1 and search again			
			depth++;  
		}
		saveToFile();
	}


	private String Limited_DFS(tile state, tile goal, int limit) {
		
		// if the state is goal return "succses"	
		if(state.equals(goal)) { 
			path=path(state);								//           |
			cost =state.getCost();							//			 |
			return "sucsses";							 	//			 |  the simple base cases of the recursion
		}													//		     |	"cutoff" , find the goal , "fail"
															//		     |	
															//		     |	
		else if(limit==0)return "cutoff"; 					//		     |
		// if we reach to limit depth and still no solution found and the algorithm not failed (maybe there is a solution in deeper depth)
		
		
		// if the tile is no the goal tile and not reach to the limit yet 
		else {	
			isCutoff=false; 
			
			//  insert tile to openlist
			openlist.put(state.getKey(), state);   
          
			
			//    for each allowed operator on state
			for(int i=1; i<5; i++) {    
				
				// child= legal_operator(state)
				tile child= state.move(i);   
			
				// loop avoidence
				if(child!=null&&!openlist.containsKey(child.getKey())) {  
					result= Limited_DFS(child,goal,limit-1);         //dfs step
					
					 // no solution in correct depth bound  
					if(result.equals("cutoff")) isCutoff=true;    
					
					// if we find a solution
					else if(!result.equals("fail")) return result;
				}
			}
			// print openlist to console if needed
			if(withOpen) PrintOpenList();
			
			//the memory for state tile should be also released (there no solution in its path)
			openlist.remove(state.getKey()); 
			

			
			if(isCutoff) return "cutoff";
			
			// We found no solution and were not blocked "cutoff"
			else return "fail";
		}
	}
}



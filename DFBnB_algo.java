
import java.util.Arrays;


/**
 * 
 * @author Boaz Sharabi
 *
 *This class represent DFBnB search algo
 *
 */

import java.util.PriorityQueue;
import java.util.Stack;
public class DFBnB_algo extends heuristic_search_algo {
	Stack<tile> stack= new Stack<>();
	Stack<tile> stack_reverse= new Stack<>();
	int t=0;
	int minF=0;
	String result="no path";
	
	//more explain on tile_comperator in heuristic_search_algo class file
	PriorityQueue<tile> PQ= new PriorityQueue<>(get_tile_comperator());  
	public DFBnB_algo(tile start, tile goal, boolean withOpen, boolean withTime) {
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
		openlist.put(start.getKey(), start);
		stack.push(start);
		t= DFBnB_up_bounder();
		while(!stack.isEmpty()) {
			tile n=stack.pop();
			
			//if we have finished scanning the descendants of this state 
			if(n.isOut()) {
				
				// print openlist to console if needed
				if(withOpen) PrintOpenList();
				
				openlist.remove(n.getKey()); 

				}
			
			else { 
				
				//mark n as “out” and insert it to the stack
				n.out();
				stack.push(n);
				tile[] arr= new tile[4]; // branch factor (its 3 indeed but we count it as NULL)
				
			//	generate all child state from all allowed operators
				for(int i=1;i<5;i++) {
					tile child= n.move(i);
					if(child!=null) {  
						arr[i-1]= child;
					}
				}
				
				
				//sort the nodes in N according to their f values (increasing order)
				Arrays.sort(arr, get_tile_comperator());

				//for each node g from arr according to the order of arr{
				for(int i=0;i<arr.length;i++) {
					if(arr[i]==null)continue;
					
					tile g=arr[i];
					//if the f(child) of minimum tile bigger than the threshold ,cut this branch
					if(f(g)>=t) {
						while(i<arr.length) {
							arr[i]=null;
							i++;
						}
					}
					// if same state already exists in openlist
					else if(openlist.containsKey(g.getKey())) {
						tile like_g= openlist.get(g.getKey());
						
						//loop avoidence
						if(like_g.isOut()) {
							arr[i]=null;
						}
						
						// if the state exist in openlist but in other branch 
						// find which one has a cheaper path and enter it(the new one has cheeper path) \ leave it (the old one has cheeper path)
						else {
							if(f(like_g)<=f(g)) 
								arr[i]=null;
							else {
								// print openlist to console if needed
								if(withOpen) PrintOpenList();
								
								openlist.remove(like_g.getKey());
								stack.remove(like_g);
							}
						}
					}
					// if we find the goal state save the solution ,set treshold to be the path cost of this state

					else if(g.equals(goal)) {
						t=f(g);
						path= path(g);
//						count=ColorTilePuzzle.count;
						cost= g.getCost();	
						while(i<arr.length) {
							arr[i]=null;
							i++;
						}
					}
				}
				//end for each node g from arr according to the order of arr}
				
				//insert the array in a reverse order to the openlist and to the stack
				for(int i=3;i>=0;i--) {
					tile node= arr[i];

					if(node!=null) {     
						openlist.put(node.getKey(), node);
						stack.push(node);
					}
				}
			}
		}
		saveToFile();
	}
	/**
	 * 
	 * @return up bounder for DFBnB = min (n!,Integer.MAX_VALUE ) when n denote the non-black cell in the tile
	 */
	private int DFBnB_up_bounder() {

		int n=0;
		Board board= start.getBoard();
		for(int i=0;i<board.mat.length;i++) {
			for(int j=0;j<board.mat[0].length;j++) {
				if(board.mat[i][j]!=-1) {
					if(board.color_cell.get(board.mat[i][j])!=Color.BLACK);
					n++;
				}
			}
		}
			// n!
		if(n<=12) {
			int n_fact=1;
			for(int i=1;i<=n;i++){    
				n_fact=n_fact*i;    
			} 

			return n_fact;
		}
		else {
			return Integer.MAX_VALUE;
		}
	}
}



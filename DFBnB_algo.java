
import java.util.Arrays;

/**
 * 
 * @author Boaz Sharabi
 *
 *This class represent DFBnB search algo
 */
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;
public class DFBnB_algo extends heuristic_search_algo {
	Stack<tile> stack= new Stack<>();
	Stack<tile> stack_reverse= new Stack<>();
	int t=150;
	int minF=0;
	String result="no path";
	PriorityQueue<tile> PQ= new PriorityQueue<>(new Comparator<tile>() {
		@Override
		public int compare(tile arg0, tile arg1) {
			int x= (f(arg0)-f(arg1));
			if(x!=0) 
				return x;
			else return arg0.getTime()-arg1.getTime();
		}
	});
	public DFBnB_algo(tile start, tile goal, boolean withOpen, boolean withTime) {
		super(start, goal, withOpen, withTime);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int heuristic(tile t) {
		// TODO Auto-generated method stub
		return Tile_Heuristic.Manethen_Distance(t);
	}

	@Override
	public void run() {

		
		
		StartTime = System.nanoTime();
		openlist.put(start.toString(), start);
		stack.push(start);
		t= Integer.MAX_VALUE;
		while(!stack.isEmpty()) {
			tile n=stack.pop();
			if(n.isVisited()) openlist.remove(n.toString());
			else { //3.3
				n.visit();//3.3.1
				stack.push(n);//3.3.1
				tile[] arr= new tile[4];
				for(int i=1;i<5;i++) {
					tile child= n.move(i);
					if(child!=null) {      // 3.3.2 , 3.3.3    
						arr[i-1]= child;
					}
				}
				Arrays.sort(arr, get_tile_comperator() );

				for(int i=0;i<arr.length;i++) {
					if(arr[i]==null)continue;
					tile g=arr[i];
					if(f(g)>=t) {
						while(i<arr.length) {
							arr[i]=null;
							i++;
						}
					}
					else if(openlist.containsKey(g.toString())) {
						tile like_g= openlist.get(g.toString());
						if(like_g.isVisited()) {
							arr[i]=null;
						}
						else {
							if(f(like_g)<=f(g)) 
								arr[i]=null;
							else {
								openlist.remove(like_g.toString());
								stack.remove(like_g);
							}
						}
					}
					else if(g.equals(goal)) {
						t=f(g);
						path= path(g);
						count=ColorTile.count;
						cost= g.getCost();	
						while(i<arr.length) {
							arr[i]=null;
							i++;
						}
					}
				}
				for(int i=3;i>=0;i--) {
					tile n1= arr[i];

					if(n1!=null) {      // 3.3.2 , 3.3.3    
						openlist.put(n1.toString(), n1);
						stack.push(n1);
					}
				}
			}
		}
		count=ColorTile.count; //4
		saveToFile();
	}
}



import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Stack;

public class DFBnB_algo extends heuristic_search_algo {
	Stack<tile> stack= new Stack<>();
	Stack<tile> stack_reverse= new Stack<>();
	int t=0;
	int minF=0;
	String result="no path";
	PriorityQueue<tile> PQ= new PriorityQueue<>(new Comparator<tile>() {
		@Override
		public int compare(tile arg0, tile arg1) {
			int x= (heuristic(arg0)+arg0.getCost())-(heuristic(arg1)+arg1.getCost());
			if(x!=0) return x;
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
	public String run() {
		long startTime = System.nanoTime();
		openlist.put(start.toString(), start);
		stack.push(start);
		t= Integer.MAX_VALUE;
		while(!stack.isEmpty()) {
			tile n=stack.pop();
			if(n.isVisited()) openlist.remove(n.toString());
			else { //3.3
				n.visit();//3.1
				stack.push(n);//3.1
				for(int i=1;i<5;i++) {
					tile child= n.move(i);
					if(child!=null) {      // 3.2 , 3.3     
						PQ.add(child);
					}
				}
				Iterator<tile> iter= PQ.iterator();
				while(iter.hasNext()) { //3.4
					tile g=iter.next();
					if(f(g)>=t) PQ.clear();

					else if(openlist.containsKey(g.toString())) {
						tile like_g= openlist.get(g.toString());
						if(like_g.isVisited()) {
							PQ.poll();
						}
						else {
							if(f(like_g)<=f(g)) PQ.poll();
							else {
								openlist.remove(like_g.toString());
								stack.remove(like_g);
							}
						}
					}
					else if(g.equals(goal)) {
						t=f(g);
						result= path(g)+"\rNum : "+ColorTile.count+"\rCost: "+g.getCost()+"\rTime: "+
								String.format("%.3f",(System.nanoTime()-startTime)*Math.pow(10, -9))+" seconds";
						PQ.clear();
					}

				}
				while(!PQ.isEmpty())
					stack_reverse.push(PQ.poll());//3.5 
				while(!stack_reverse.isEmpty()){
					tile n1= stack_reverse.pop();
					openlist.put(n1.toString(), n1);
					stack.push(n1);
				}
			}
		}
		return result;
	}





}

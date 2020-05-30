import java.util.Stack;

public class IDA_star_algo extends heuristic_search_algo {
	Stack<tile> stack= new Stack<>();
	int t=0;
	int minF=0;
	public IDA_star_algo(tile start, tile goal, boolean withOpen, boolean withTime) {
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
		// TODO Auto-generated method stub
		long startTime = System.nanoTime();

		t= heuristic(start);
		while(t!=Integer.MAX_VALUE) {
			minF= Integer.MAX_VALUE;
			openlist.put(start.toString(), start);
			stack.push(start);
			while(!stack.isEmpty()) {
				tile n=stack.pop();
				if(n.isVisited()) openlist.remove(n.toString());
				else {
					n.visit();
					stack.push(n);
					for(int i=1;i<5;i++) {
						tile child= n.move(i);
						if(child!=null) {
							if((heuristic(child)+child.getCost())>t) {
								minF= Math.min(minF,(heuristic(child)+child.getCost()));
								continue;
							}
							if(openlist.containsKey(child.toString())&&(openlist.get(child.toString()).isVisited())) 
								continue;
							if (openlist.containsKey(child.toString())&&!(openlist.get(child.toString()).isVisited()) ){
								if(heuristic(child)+child.getCost()<openlist.get(child.toString()).getCost()+heuristic(openlist.get(child.toString()))) {
									stack.remove(openlist.get(child.toString()));
									openlist.remove(child.toString());
								}
								else continue;
							}
							if(child.equals(goal)) return path(child)+"\rNum : "+ColorTile.count+"\rCost: "+child.getCost()+"\rTime: "+
									String.format("%.3f",(System.nanoTime()-startTime)*Math.pow(10, -9))+" seconds";;
							openlist.put(child.toString(), child);
							stack.push(child);
						}
					}

				}
			}
			t=minF;

		}
		return "false";
	}
}

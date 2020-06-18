import java.util.Stack;

/**
 * 
 * @author Boaz Sharabi
 *This class represent IDA* search algo
 */
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
	public void run() {
		// TODO Auto-generated method stub
		StartTime = System.nanoTime();

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
								minF= Math.min(minF,f(child));
								continue;
							}
							if(openlist.containsKey(child.toString())&&(openlist.get(child.toString()).isVisited())) 
								continue;
							if (openlist.containsKey(child.toString())&&!(openlist.get(child.toString()).isVisited()) ){
								if(f(child)<f(openlist.get(child.toString()))) {
									stack.remove(openlist.get(child.toString()));
									openlist.remove(child.toString());
								}
								else continue;
							}
							if(child.equals(goal)) {
								path=path(child);
								count=ColorTile.count;
								cost =child.getCost();
								saveToFile();
								return;

							}
							openlist.put(child.toString(), child);
							stack.push(child);
						}
					}

				}
			}
			t=minF;
			start.markNoVisit();
		}
		count=ColorTile.count;
		saveToFile();
	}
}

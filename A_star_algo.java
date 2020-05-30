import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class A_star_algo extends heuristic_search_algo {
	PriorityQueue<tile> PQ= new PriorityQueue<>(new Comparator<tile>() {
		@Override
		public int compare(tile arg0, tile arg1) {
			int x= (f(arg0)-f(arg1));
			if(x!=0) return x;
			else return arg0.getTime()-arg1.getTime();
		}
	});
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
		openlist.put(start.toString(),start);
		PQ.add(start);
		while(!PQ.isEmpty()) {
			tile lower=PQ.poll();
			if(lower.equals(goal)) {
				path=path(lower);
				count=ColorTile.count;
				cost =lower.getCost();
				saveToFile();
			}
			openlist.remove(lower.toString());
			CloseList.put(lower.toString(), lower);
			for(int i=1;i<5;i++) {
				tile child= lower.move(i);
				if(child!=null) {
					tile same=openlist.get(child.toString());// for the else condition check if there same tile in openlist/priority queue 
					if(!CloseList.containsKey(child.toString())&&!openlist.containsKey(child.toString())) {
						PQ.add(child);
						openlist.put(child.toString(), child);
						if(withOpen)PrintOpenList();
					}
					else if(same!=null&&same.getCost()>child.getCost()) {
						PQ.remove(same);
						openlist.remove(child.toString());
						openlist.put(child.toString(), child);
						PQ.add(child);
					}
				}
			}
		}
		count=ColorTile.count;
		saveToFile();
	}
}

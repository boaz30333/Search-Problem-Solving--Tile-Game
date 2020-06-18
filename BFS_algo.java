import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author Boaz Sharabi
 *
 *This class represent BFS search algo
 */
public class BFS_algo extends search_algo{
	HashMap<String, tile> closelist= new HashMap<>();
	LinkedHashMap<String, tile> frontier= openlist;
	Queue<tile> b= new LinkedList<>();
	public BFS_algo(tile start, tile goal, boolean with_open, boolean with_time) {
		super(start,goal,with_open,with_time);
	}
	@Override
	public void run() {
		StartTime = System.nanoTime();
		frontier.put(start.toString(),start);
		b.add(start);
		tile n=null;
		while(!b.isEmpty()) {
			System.out.println(ColorTile.count);
			 n= b.poll();
			frontier.remove(n.toString());
			closelist.put(n.toString(), n);
			for(int i=1;i<5;i++) {
				tile child= n.move(i);
				if(child!=null&&!closelist.containsKey(child.toString())&&!frontier.containsKey(child.toString())) {
					if(child.equals(goal)) {
						path=path(child);
						count=ColorTile.count;
						cost =child.getCost();
						saveToFile();
						return;
					}
					frontier.put(child.toString(), child);
					b.add(child);
					if(withOpen)PrintOpenList();
				}
			}
		}
		count=ColorTile.count;
		saveToFile();
	}


}
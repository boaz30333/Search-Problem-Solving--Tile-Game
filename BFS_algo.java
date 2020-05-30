import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BFS_algo extends search_algo{
	HashMap<String, tile> closelist= new HashMap<>();
	LinkedHashMap<String, tile> frontier= openlist;
	public BFS_algo(tile start, tile goal, boolean with_open, boolean with_time) {
		super(start,goal,with_open,with_time);
	}
	@Override
	public void run() {
		StartTime = System.nanoTime();
		frontier.put(start.toString(),start);
		while(!frontier.isEmpty()) {
			tile n=frontier.remove(frontier.keySet().toArray()[0]);
			closelist.put(n.toString(), n);
			for(int i=1;i<5;i++) {
				tile child= n.move(i);
				if(child!=null&&!closelist.containsKey(child.toString())) {
					if(child.equals(goal)) {
						path=path(child);
						count=ColorTile.count;
						cost =child.getCost();
						saveToFile();
						return;
					}
					frontier.put(child.toString(), child);
					if(withOpen)PrintOpenList();
				}
			}
		}

		count=ColorTile.count;
		saveToFile();
	}


}
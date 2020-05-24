import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BFS_algo extends search_algo{
//	HashQueueTile cl= new HashQueueTile(); //queue for open and closed list both C and L from pseudo-code
	HashMap<String, tile> closelist= new HashMap<>();
	LinkedHashMap<String, tile> frontier= openlist;
    public BFS_algo(tile start, tile goal, boolean with_open, boolean with_time) {
		// TODO Auto-generated constructor stub
    	super(start,goal,with_open,with_time);
	}
	@Override
	public String run() {
    	long startTime = System.nanoTime();
//    	cl.add(start.toString(), start);1
//    	openlist.put(start.toString(), start);2
    	frontier.put(start.toString(),start);
    	
//    	while(cl.peek()!=null) {1
//    		tile n=cl.poll();1
    	while(!frontier.isEmpty()) {
		tile n=frontier.remove(frontier.keySet().toArray()[0]);
		
//			openlist.remove(n.toString());2
    		closelist.put(n.toString(), n);
    		for(int i=1;i<5;i++) {
    			tile child= n.move(i);
    			if(child!=null&&!closelist.containsKey(child.toString())) {
    				if(child.equals(goal))
    					return path(child)+"\n\rNum : "+ColorTile.count+"\r Cost: "+child.getCost()+"\rTime: "+(System.nanoTime()-startTime);
//    				cl.add(child.toString(), child); 1
//    				frontier.add(child);2
    				frontier.put(child.toString(), child);
//    				openlist.put(child.toString(), child);	2	
//    				PrintOpenList();
    			}
    		}
    	}
    	long endTime   = System.nanoTime(); // return no path!
    	long totalTime = endTime - startTime;
    	System.out.println(totalTime);
    		return null;
	}


	}
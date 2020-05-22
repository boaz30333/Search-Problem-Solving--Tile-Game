import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Queue;

public class BFS_algo implements search_algo {
    tile goal;
    tile start;
    String path;
    boolean withOpen;
    boolean withTime;
	public BFS_algo(tile start, tile goal, boolean withOpen, boolean withTime) {
		this.start=start;
		this.goal=goal;
		this.withOpen=withOpen;
		this.withTime=withTime;
	}


    @Override
    public tile getStart() {
        // TODO Auto-generated method stub
        return start;
    }

    @Override
    public tile getGoal() {
        // TODO Auto-generated method stub
        return goal;
    }

    @Override
    public Boolean getWithOpen() {
        // TODO Auto-generated method stub
        return withOpen;
    }

    @Override
	public String run() {
    	long startTime = System.nanoTime();
    	HashQueueTile cl= new HashQueueTile(); //queue for open and closed list both C and L from pseudo-code
    	cl.add(start.toString(), start);
    	while(cl.peek()!=null) {
    		tile n=cl.poll();
    		for(int i=1;i<5;i++) {
    			tile child= n.move(i);
    			if(child!=null&&!cl.in_hash(child)) {
    				if(child.equals(goal))
    					return path(child)+"\n\rNum : "+ColorTile.count+"\r Cost: "+child.getCost()+"\rTime: "+(System.nanoTime()-startTime);
    				cl.add(child.toString(), child);
    			}
    		}
    	}
    	long endTime   = System.nanoTime(); // return no path!
    	long totalTime = endTime - startTime;
    	System.out.println(totalTime);
    		return null;
	}


	private String path(tile child) {
		// TODO Auto-generated method stub
String path="";
	tile current= child;
		while(current.getParent()!=null&&current.getParent().getParent()!=null) {
		path=	"-"+current.getNumOp()+path;
		current=current.getParent();
		}
		path=current.getNumOp()+path;
		return path;
	}

	}
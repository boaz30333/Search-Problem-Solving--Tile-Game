import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javafx.collections.SetChangeListener;

public abstract class search_algo {
tile goal;
tile start;
boolean withOpen;
boolean withTime;
String path;
LinkedHashMap<String, tile> openlist= new LinkedHashMap();
public search_algo(tile start, tile goal, boolean withOpen, boolean withTime) {
	this.start=start;
	this.goal=goal;
	this.withOpen=withOpen;
	this.withTime=withTime;
}
protected String path(tile child) {
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
	public void PrintOpenList() {
		// TODO Auto-generated method stub
//	Iterator<tile> b=	cl.h_queue.values().iterator();
		Iterator<tile> b=openlist.values().iterator();
	tile current=null;
	int count=0;
	while(b.hasNext()) {
		current=b.next();
//		if(!current.isVisited()) {
		System.out.println(count+"\t "+current+"\n---------------------------");
		count++;
	}
	 System.out.println("\n--------------------------------------------------------------------------------------------");
	}
public abstract String run(); 

}
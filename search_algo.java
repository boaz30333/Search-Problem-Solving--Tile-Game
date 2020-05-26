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
	LinkedHashMap<String, tile> openlist= new LinkedHashMap<String, tile>();
	public search_algo(tile start, tile goal, boolean withOpen, boolean withTime) {
		this.start=start;
		this.goal=goal;
		this.withOpen=withOpen;
		this.withTime=withTime;
	}
	protected String path(tile child) {
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
		StringBuilder string = new StringBuilder();
		Iterator<tile> b=openlist.values().iterator();
		tile current=null;
		while(b.hasNext()) {
			current=b.next();
			string.append("\n"+current+"____________________");
		}
		string.append("\n------------------------------------------------------------");
		System.out.println(string);
	}
	public abstract String run(); 

}
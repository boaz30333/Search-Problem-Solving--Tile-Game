import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;


/**
 * 
 * @author Boaz Sharabi
 *
 *
 */
public abstract class search_algo {
	tile goal;
	tile start;
	boolean withOpen;
	boolean withTime;
	String path="no path";
	int count;
	int cost;
	long StartTime;
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
	public abstract void run(); 
	public void saveToFile() {

		String fileName = "output.txt";

		try 
		{
			PrintWriter pw = new PrintWriter(new File(fileName));

			StringBuilder sb = new StringBuilder();

			sb.append(path);
			sb.append("\n");
			sb.append("Num: "+count);
			if(!path.equals("no path")) {
				sb.append("\n");
				sb.append("Cost: "+cost);
			}
			if(withTime) {
				sb.append("\n");
				sb.append(String.format("%.3f",(System.nanoTime()-StartTime)*Math.pow(10, -9))+" seconds");
			}

			pw.write(sb.toString());
			pw.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			return;
		}
	}

}
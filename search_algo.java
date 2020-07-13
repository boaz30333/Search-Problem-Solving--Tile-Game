import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;


/**
 * 
 * @author Boaz Sharabi
 *
 *	this abstract class represent search algorithm using tile states 
 *all of derived class have to implement the abstract method run()
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
	/**
	 * run the algorithem
	 */
	public abstract void run(); 
	protected String path(tile child) {
		String path="";
		tile current= child;
		while(current.getParent()!=null&&current.getParent().getParent()!=null) { // restore the solution path
			path=	"-"+current.getNumOp()+path;
			current=current.getParent();
		}
		path=current.getNumOp()+path;
		return path;
	}
	/**
	 * print the open list (all state that created by algorithm until now) to console
	 */
	public void PrintOpenList() {
		StringBuilder string = new StringBuilder();
		Iterator<tile> b=openlist.values().iterator();
		tile current=null;
		while(b.hasNext()) {
			current=b.next();
			string.append(current+"\n__________________________________________");
		}
		string.append("\n------------------------------------------------------------");
		System.out.println(string);
	}
	boolean checkValid(tile t){
		Board board= t.getBoard();
		for(int i=0;i<board.mat.length;i++) {
			for(int j=0;j<board.mat[0].length;j++) {
				if(board.mat[i][j]!=-1&&board.color_cell.get(board.mat[i][j])==Color.BLACK) {
					int x=((board.mat[i][j]-1)/(board.mat[0].length));
					int y= ((board.mat[i][j]-1)%(board.mat[0].length));
					if (i!=x||j!=y)
						return false;
				}
			}
		}
		return true;

	}
	/**
	 * save the algorithm result to output file
	 */
	public void saveToFile() {
		count=ColorTilePuzzle.count;
		String fileName = "output.txt";

		try 
		{
			PrintWriter pw = new PrintWriter(new File(fileName));

			StringBuilder sb = new StringBuilder();

			sb.append(path);
			sb.append("\n");
			sb.append("Num: "+(count-1)); // decrease count by 1 because i generate goal tile at start for convenience of comparison
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
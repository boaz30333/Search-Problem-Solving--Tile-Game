
public class DFID_algo extends search_algo{
	String result="";
	int depth=1;
	boolean isCutoff=false;
	public DFID_algo(tile start, tile goal, boolean withOpen, boolean withTime) {
		super(start, goal, withOpen, withTime);
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		StartTime = System.nanoTime();
		while(depth<6) {
			openlist.clear();
			result= Limited_DFS(start,goal,depth);
			if(!result.equals("cutoff")&&!result.equals("fail")) {
				saveToFile();
				return;
			}
			else if(result.equals("fail")) break;
			depth++;
		}
		count=ColorTile.count;
		saveToFile();
	}


	private String Limited_DFS(tile state, tile goal, int limit) {
		if(state.equals(goal)) {
			path=path(state);
			count=ColorTile.count;
			cost =state.getCost();
			return path;
		}
		else if(limit==0)return "cutoff";
		else {
			openlist.put(state.toString(), state);
			isCutoff=false;
			for(int i=1; i<5; i++) {
				tile child= state.move(i);
				if(child!=null&&!openlist.containsKey(child.toString())) {
					result= Limited_DFS(child,goal,limit-1);
					if(result.equals("cutoff")) isCutoff=true;
					else if(!result.equals("fail")) return result;
				}}
			openlist.remove(state.toString());
			if(isCutoff) return "cutoff";
			else return "fail";
		}
	}
}




public class DFID_algo extends search_algo{
String result;
int depth=1;
boolean isCutoff=false;
long startTime;
	public DFID_algo(tile start, tile goal, boolean withOpen, boolean withTime) {
		super(start, goal, withOpen, withTime);
	}

	
	@Override
	public String run() {
		// TODO Auto-generated method stub
		startTime=System.nanoTime();
		while(depth<6) {
			openlist.clear();
			result= Limited_DFS(start,goal,depth);
			if(!result.equals("cutoff")&&!result.equals("fail"))return result;
			else if(result.equals("fail")) break;
			depth++;
		}
		return "no path\n"+"Num: "+ColorTile.count;

	}


	private String Limited_DFS(tile state, tile goal, int limit) {
		if(state.equals(goal)) 
			return path(state)+"\rNum : "+ColorTile.count+"\rCost: "+state.getCost()+"\rTime: "+
			String.format("%.3f",(System.nanoTime()-startTime)*Math.pow(10, -9))+" seconds";
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
	
	

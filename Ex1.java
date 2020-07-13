import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @author Boaz Sharabi
 *
 */
public class Ex1
{
	public static boolean with_time=false;
	public static boolean with_open=false;
	public static search_algo algo;
	public static tile start;
	public static tile goal;
	public static void main(String[] args) throws Exception {
		String[] info = new String[3]; // 1 = algo 2 = with time| 3 = opoen list| 4= size of board | 5= black |6=red 
		BufferedReader br = null;
		String board="";
		try
		{
			br = new BufferedReader(new FileReader("input.txt"));
			int i=0;
			while (i<3&&( info[i] = br.readLine()) != null)  // with time, with open , algorithm name initialize
			{
				i++;
			}
			while (br.ready())                         // board initialize
			{
				board+= br.readLine();
				board+=System.getProperty("line.separator");
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("could not read file");
		}
		start= InitStart(board);
		goal = start.getArrangedTile();
		with_time=initWithTime(info[1]);
		with_open=initWithOpen(info[2]);
		algo= initAlgo(info[0]);

		algo.run();
	}
/**
 * 
 * @param algorithm name
 * @return  search_algo class according to algorithm name 
 * @throws Exception if there is no algorithm in this name

 */
	public static search_algo initAlgo(String algorithm) throws Exception {
		switch(algorithm) {
		case "BFS":
			return new BFS_algo(start, goal, with_open, with_time);

		case "DFID":
			return new DFID_algo(start, goal, with_open, with_time);
		case "DFBnB":
			return new DFBnB_algo(start, goal, with_open, with_time);
		case "A*":
			return new A_star_algo(start, goal, with_open, with_time);
		case "IDA*":
			return new IDA_star_algo(start, goal, with_open, with_time);

		default:
			throw new Exception("no match algo in input file");
		}

	}
	/**
	 * 
	 * @param string "with time" - to include running time in output file,  "no time" - to exclude running time Exclude running time
	 * @return true if "with time"  , false if "no time"
	 * @throws Exception otherwise
	 */
	public static boolean initWithTime(String time) throws Exception {
		switch(time) {
		case "with time":
			return true;
		case "no time":
			return false;


		default:
			throw new Exception("no input with/no runtime");
		}


	}
	/**
	 * 
	 * @param open - String  "with open" / "no open"
	 * @return true if  "with open" , false if "no open"
	 * @throws Exception otherwise
	 */
	public static boolean initWithOpen(String open) throws Exception {
		switch(open) {
		case "with open":
			return true;
		case "no open":
			return false;
		default:
			throw new Exception("no input : with/no open list");
		}
		//

	}
	public static tile InitStart(String board) throws Exception {
		return new ColorTilePuzzle(new Board(board),0,null,"");  // build start and goal state's tile
	}



}
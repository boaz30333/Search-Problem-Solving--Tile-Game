import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.sun.org.omg.CORBA.ParDescriptionSeqHelper;

import javafx.geometry.Point2D;

public class InitParam
{
	public static void main(String[] args) throws Exception {
		search_algo algo = null;
		boolean with_time = false;
		boolean with_open = false ;
		String[] info = new String[6]; // 1 = algo 2 = with time| 3 = opoen list| 4= size of board | 5= black |6=red 
		BufferedReader br = null;
		String board="";
		try 
		{
			br = new BufferedReader(new FileReader("input.txt"));
			int i=0;
			while (i<3&&( info[i] = br.readLine()) != null)  // with time, with open , algorithm name
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
		tile start= new ColorTile(new Board(board),0,null,"");  // build start and goal state's tile
		tile goal = start.getArrangedTile();
		//-------------------------------------------------------------- line 1 choose with  time
		int i=1;
		switch(info[i]) {
		case "with time":
			with_time=true;
			break;
		case "no time":
			break;


		default:
			throw new Exception("no input with/no runtime");
		}

		//---------------------------------------------------------------line=2 choose with open
		i++;
		switch(info[i]) {
		case "with open":
			with_open=true;
			break;
		case "no open":
			break;
		default:
			throw new Exception("no input : with/no open list");
		}
		//-------------------------------------------------------------------line 0 = choose algo
		switch(info[0]) {
		case "BFS":
			algo = new BFS_algo(start, goal, with_open, with_time);
			System.out.println(algo.run());
			break;
		case "DFID":
			break;
		case "DFBnB":
			// code block
			break;
		case "A*":
			// code block
			break;
		case "IDA*":
			// code block
			break;

		default:
			throw new Exception("no match algo in input file");
		}

	}

	
	}
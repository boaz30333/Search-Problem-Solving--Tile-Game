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
		Board colorBoard =null;

		String[] info = new String[6]; // 1 = algo 2 = with time| 3 = opoen list| 4= size of board | 5= black |6=red 
		String board="";
		BufferedReader br = null;
		try 
		{
			br = new BufferedReader(new FileReader("input.txt"));
			int i=0;
			while (i<3&&( info[i] = br.readLine()) != null) 
			{
				i++;
			}
			while (br.ready()) 
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
		colorBoard= new Board(board);
		/////////////////////////////////////////cont parse line 1
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

		/////////////////////////////////////////////////i=2
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
		//////////////////////////line 0 = choose algo
		switch(info[0]) {
		case "BFS":
			break;
		case "DFID":
			// code block
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
System.out.println(colorBoard);
	}

	
	}
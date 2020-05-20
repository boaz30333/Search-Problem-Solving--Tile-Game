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
		Board board = null;

		String[] info = new String[6]; // 1 = algo 2 = with time| 3 = opoen list| 4= size of board | 5= black |6=red 
		BufferedReader br = null;
		try 
		{
			br = new BufferedReader(new FileReader("input.txt"));
			int i=0;
			while (i<6&&( info[i] = br.readLine()) != null) 
			{
				i++;
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("could not read file");
		}
		/////////////////////////////////////////cont parse
		int i=0;
		switch(info[i]) {
		case "BFS":
			// code block
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
		///////////////////////////////////////// i=1
		i++;
		switch(info[i]) {
		case "with time":
			// code block
			break;
		case "no time":
			// code block
			break;


		default:
			throw new Exception("no input with/no runtime");
		}

		/////////////////////////////////////////////////i=2
		i++;
		switch(info[i]) {
		case "with open":
			// code block
			break;
		case "no open":
			// code block
			break;


		default:
			throw new Exception("no input : with/no open list");
		}

		//////////////////////////////i=3
		i++;
		int n=Integer.parseInt(""+info[i].charAt(0));
		int m = Integer.parseInt(""+info[i].charAt(2));


		////////////////////////////////////////////////////	i=4
		ArrayList<String> BlackCells = new ArrayList<>();
		ArrayList<String> RedCells = new ArrayList<>();
		i++;
		String[] Black=info[i].substring(6).split(",");
		for(int j=0;j<Black.length;j++) {
			BlackCells.add(Black[j]);
		}
		///////////////////////////////////////////////////i=5
		i++;
		String[] Red=info[i].substring(5).split(",");
		for(int j=0;j<Red.length;j++) {
			RedCells.add(Red[j]);
		}
		//////////////////////////////////////////////////
		String[] matOrder=new String[n];
		String[] rowOrder;
		int num;
		Color_Cell.Color color;
		Point2D emptyCell = null;
		Color_Cell[][] b= new Color_Cell[n][m];
		for(int j =0;j<n;j++) {
			matOrder[j]=br.readLine();
			rowOrder= matOrder[j].split(",");
			for(int k=0; k<m;k++) {
				if(rowOrder[k].equals("_")) {
					num= -1;
					color= Color_Cell.Color.EMPTY;
					emptyCell= new Point2D(j,k);
				}
				else {
					num= Integer.parseInt(""+rowOrder[k]);
					if(BlackCells.contains(rowOrder[k])) color= Color_Cell.Color.BLACK;
					if(RedCells.contains(rowOrder[k])) color= Color_Cell.Color.RED;
					else color= Color_Cell.Color.GREEN;		 
				}
				Color_Cell cell= new Color_Cell(color,num);
				b[j][k]= cell;
			}

		}
		board=new Board(b,emptyCell);
		System.out.println(board.toString());
	}

	
	}
import java.util.ArrayList;
import java.util.Arrays;

import javafx.geometry.Point2D;
public class Board{
	Color_Cell[][] mat;
	Point2D empty_space;
	public Board(    Color_Cell[][] mat,  Point2D empty_space) {
		this.mat=mat;
		this.empty_space=empty_space;
	}

	public Board copy() {
		Color_Cell[][] newmat = new Color_Cell[mat.length][mat[0].length] ;
		for (int i=0;i<mat.length;i++) {
			for (int j=0;i<mat[0].length;j++) {
				newmat[i][j]= this.mat[i][j].copy();
			}
		}
		return new Board(newmat,new Point2D(this.empty_space.getX(),this.empty_space.getY()));
	}
	public String toString() {
		StringBuilder string = new StringBuilder();
		for (Color_Cell[] row : mat) {
			string.append("\n |");
			// Loop through all columns of current row 
			for (Color_Cell x : row) {
				string.append(x.num + "("+x.color+")");
				if(x.num<10&&x.num>-1)  string.append(" ");
				if(x.color==Color_Cell.Color.RED)  string.append("  ");
				string.append("|");
			}
		}
		return string.toString();
	}

	public Board(String board) throws Exception {
		if(board==null||board.length()<4) throw new Exception("cant build board from string");
		String[] line = board.split(System.getProperty("line.separator"));
		int i=0;
		int n=Integer.parseInt(""+line[i].charAt(0));
		int m = Integer.parseInt(""+line[i].charAt(2));

		ArrayList<String> BlackCells = new ArrayList<>();
		ArrayList<String> RedCells = new ArrayList<>();
		i++;
		String[] Black=line[i].substring(6).split(",");
		for(int j=0;j<Black.length;j++) {
			BlackCells.add(Black[j]);
		}
		///////////////////////////////////////////////////i=5
		i++;
		String[] Red=line[i].substring(5).split(",");
		for(int j=0;j<Red.length;j++) {
			RedCells.add(Red[j]);
		}
		//////////////////////////////////////////////////
		i++;
		String[] rowOrder;
		int num;
		Color_Cell.Color color;
		mat= new Color_Cell[n][m];
		for(int j =0;j<n;j++) {
			rowOrder= line[i+j].split(",");
			for(int k=0; k<m;k++) {
				if(rowOrder[k].equals("_")) {
					num= -1;
					color= Color_Cell.Color.EMPTY;
					empty_space= new Point2D(j,k);
				}
				else {
					num= Integer.parseInt(""+rowOrder[k]);
					if(BlackCells.contains(rowOrder[k])) color= Color_Cell.Color.BLACK;
					if(RedCells.contains(rowOrder[k])) color= Color_Cell.Color.RED;
					else color= Color_Cell.Color.GREEN;		 
				}

				mat[j][k]= new Color_Cell(color,num);
			}
		}
	}
}

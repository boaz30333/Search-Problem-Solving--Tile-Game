import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javafx.geometry.Point2D;
public class Board{
	int[][] mat;
	 HashMap<Integer, Color> color_cell = new HashMap<>();
	Point2D empty_space;
	private Board(int[][] mat,  Point2D empty_space, HashMap<Integer, Color> color_cell) {
		this.mat=mat;
		this.empty_space=empty_space;
		this.color_cell=color_cell;
	}
	public Board getArranged() {
		int[][] arrange_board= new int[this.mat.length][this.mat[0].length];
		for(int j =0;j<arrange_board.length;j++) {
			for(int k=0; k<arrange_board[0].length;k++) {
				if(j==arrange_board.length-1&&k==arrange_board[0].length-1) {
					arrange_board[j][k]=-1;
				}
				else {
					arrange_board[j][k]=(j)*(arrange_board[0].length)+(k+1);
				}
			}
		}
		return new Board(arrange_board,new Point2D(arrange_board.length-1,arrange_board[0].length-1),this.color_cell );
	}
	public Board copy() {
		int[][] newmat = new int[mat.length][mat[0].length] ;
		for (int i=0;i<mat.length;i++) {
			for (int j=0;j<mat[0].length;j++) {
				newmat[i][j]= mat[i][j];
			}
		}
		return new Board(newmat,new Point2D(this.empty_space.getX(),this.empty_space.getY()),this.color_cell);
	}
	public Point2D getEmpty() {
		return this.empty_space;
	}
	public void setEmpty(Point2D p) {
		this.empty_space=p;
	}
	public String toString() {
//		System.out.println(this.mat.length+"x"+this.mat[0].length);
		StringBuilder string = new StringBuilder();
		for (int[] row : mat) {
			string.append("\n |");
			// Loop through all columns of current row 
			for (int x : row) {
				string.append(x + "("+color_cell.get(x)+")");
				if(x<10&&x>-1)  string.append(" ");
				if(color_cell.get(x)==Color.RED)  string.append("  ");
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

		i++;
		String[] Black=line[i].substring(6).split(",");
		for(int j=0;j<Black.length;j++) {
			if(!Black[j].equals(""))
			color_cell.put(Integer.parseInt(Black[j]), Color.BLACK);
		}
		///////////////////////////////////////////////////i=5
		i++;
		String[] Red=line[i].substring(5).split(",");
		for(int j=0;j<Red.length;j++) {
			if(!Red[j].equals(""))
			color_cell.put(Integer.parseInt(Red[j]), Color.RED);
		}
		//////////////////////////////////////////////////
		i++;
		String[] rowOrder;
		mat= new int[n][m];
		for(int j =0;j<n;j++) {
			rowOrder= line[i+j].split(",");
			for(int k=0; k<m;k++) {
				if(rowOrder[k].equals("_")) {
					mat[j][k]= -1;
					color_cell.put(-1, Color.EMPTY);
					empty_space= new Point2D(k,j);
				}
				else {
					int num= Integer.parseInt(""+rowOrder[k]);
					mat[j][k]= num;
					if(!color_cell.containsKey(num)) 
						color_cell.put(num, Color.GREEN);
				}

			}
		}
	}
}

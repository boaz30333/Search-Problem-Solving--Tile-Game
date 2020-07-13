import java.awt.geom.Point2D;
import java.util.HashMap;


/**
 * 
 * @author Boaz Sharabi
 * This class represent a board of ColorTilePuzzele
 */
public class Board{
	int[][] mat; // 2D  int array represent the order of the board cell
	HashMap<Integer, Color> color_cell = new HashMap<>(); // key- cell number , value - the color of this cell
	Point2D blank_cell; // the empty cell 
	private Board(int[][] mat,  Point2D blank_cell, HashMap<Integer, Color> color_cell) {
		this.mat=mat;
		this.blank_cell=blank_cell;
		this.color_cell=color_cell;
	}
	/**
	 * 
	 * @return arranged board
	 * e.g.
	 * |1 |2 |3 |4 |
	 * |5 |6 |7 |8 |
	 * |9 |10|11|-1| 
	 */

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
		return new Board(arrange_board,new Point2D.Double(arrange_board.length-1,arrange_board[0].length-1),this.color_cell );
	}

	/**
	 * 
	 * @return "deep" copy of current board
	 */
	public Board copy() {
		int[][] newmat = new int[mat.length][mat[0].length] ;
		for (int i=0;i<mat.length;i++) {
			for (int j=0;j<mat[0].length;j++) {
				newmat[i][j]= mat[i][j];
			}
		}
		return new Board(newmat,new Point2D.Double(this.blank_cell.getX(),this.blank_cell.getY()),this.color_cell);
	}
	/**
	 * 
	 * @return Point2D of the empty cell in the board
	 */
	public Point2D getEmpty() {
		return this.blank_cell;
	}
	/**
	 * 
	 * @param point to set new empty cell
	 */
	public void setEmpty(Point2D p) {
		this.blank_cell=p;
	}
	/**
	 *string represent the board 
	 * |1(GREEN) |2(GREEN) |3(GREEN) |-1(EMPTY)|
	 * |5(GREEN) |6(GREEN) |7(GREEN) |4(GREEN) |
	 * |9(GREEN) |10(GREEN)|11(RED)  |8(GREEN) | 
	 */
	public String toString() {

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
	public String getKey() {
		StringBuilder string = new StringBuilder();
		for(int i=0;i<mat.length;i++) {
			for(int j=0;j<mat[0].length;j++) {
				string.append(mat[i][j]);
				string.append(' ');
			}
			string.append("\n");
		}
		return string.toString();
	}

	/**
	 * init board from string input
	 * @param board A String represent a board with 3 colors
	 * 3x4 - line 0 - size of the board
	 * Black:   line 1 - the pieces in black color
	 * Red: 11  line 2 - the pieces in red color
	 * 1,2,3,4  the arrange of the board where '-' is the empty cell
	 * 5,6,11,7
	 * 9,10,8,_
	 * @throws Exception if the input not satisfy the requirements
	 */
	public Board(String board) throws Exception {
		if(board==null||board.length()<4) throw new Exception("cant build board from string");
		String[] line = board.split(System.getProperty("line.separator"));
		int i=0;
		int n=Integer.parseInt(""+line[i].charAt(0));
		int m = Integer.parseInt(""+line[i].charAt(2));
		///////////////////////////////////////////////////////////line 1
		i++;
		String[] Black=line[i].replaceAll("\\s", "").substring(6).split(",");
		for(int j=0;j<Black.length;j++) {
			if(!Black[j].equals(""))
				color_cell.put(Integer.parseInt(Black[j]), Color.BLACK);
		}
		///////////////////////////////////////////////////line 2
		i++;
		String[] Red=line[i].replaceAll("\\s", "").substring(4).split(",");
		for(int j=0;j<Red.length;j++) {
			if(!Red[j].equals(""))
				color_cell.put(Integer.parseInt(Red[j]), Color.RED);
		}
		//////////////////////////////////////////////////line 3 - board length
		i++;
		String[] rowOrder;
		mat= new int[n][m];
		for(int j =0;j<n;j++) {
			rowOrder= line[i+j].split(",");
			for(int k=0; k<m;k++) {
				if(rowOrder[k].equals("_")) {
					mat[j][k]= -1;
					color_cell.put(-1, Color.EMPTY);
					blank_cell= new Point2D.Double(k,j);
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

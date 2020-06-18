/**
 * 
 * @author Boaz Sharabi
 *
 *This class represent heuristic functions for tile game
 */

public class Tile_Heuristic {
	/**
	 * 
	 * @param t the tile for calculate the estimate cost until the goal state
	 * @return the estimate cost until the goal state
	 */
	public static int Manethen_Distance(tile t) {
		int sum = 0;
		int right_row;
		int right_col;
		int move=1;
		Board board= t.getBoard();
		for(int i=0;i<board.mat.length;i++) {
			for(int j=0;j<board.mat[0].length;j++) {
				if(board.mat[i][j]!=-1) {
					move=1;
					if(board.color_cell.get(board.mat[i][j])==Color.RED)move+=29;
					right_row=(board.mat[i][j]-1)/(board.mat[0].length);
					right_col= (board.mat[i][j]-1)%(board.mat[0].length);
					sum+=Manethen_Distance(i,j,right_row,right_col)*move;
				}
			}
		}
		return sum;
	}
	
	/**
	 * 
	 * @param t the tile for calculate the estimate cost until the goal state
	 * @return the estimate cost until the goal state
	 */
	public static int BFS_Distance(tile t) {
		int sum = 0;
		int move=1;
		Board board= t.getBoard();
		BFSmatrix bfs = new BFSmatrix(t);
		for(int i=0;i<board.mat.length;i++) {
			for(int j=0;j<board.mat[0].length;j++) {
				if(board.mat[i][j]!=-1) {
					move=1;
					if(board.color_cell.get(board.mat[i][j])==Color.RED)move+=29;;
					int dist= bfs.minDistance(i, j);
					if(dist<0) return -1;
					sum+=dist*move;
				}
			}
		}
		return sum;
	}
	/**
	 * 
	 * @param row
	 * @param col colum
	 * @param right_row
	 * @param right_col
	 * @return Manethen Distance between (row,col) to (right row , right col)
	 */
	private static int Manethen_Distance(int row, int col, int right_row, int right_col) {
		return Math.abs(row-right_row)+Math.abs(col-right_col);
	}

}

import java.util.LinkedList;
import java.util.Queue;

//import BFSmatrix.QItem;

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
					if(dist<0) return -1;                     // no possible solution!
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

	/**
	 * 
	 * @author Boaz Sharabi
	 *
	 *
	 *
	 * based on https://www.geeksforgeeks.org/shortest-distance-two-cells-matrix-grid/
	 */
	public static class BFSmatrix {

		tile s;
		int m;
		int n;
		char[][] grid;
		QItem source;
		public BFSmatrix(tile s ) {
			// TODO Auto-generated constructor stub
			n= s.getBoard().mat.length;
			m= s.getBoard().mat[0].length;
			this.s=s;


		}
		/**
		 * 
		 * @param board
		 */
		private void initGrid(Board board) {
			grid= new char[n][m];
			// TODO Auto-generated method stub
			for(int i=0;i<board.mat.length;i++) {
				for(int j=0;j<board.mat[0].length;j++) {
					if((board.mat[i][j]!=-1)&&(i!=source.row||j!=source.col) ){
						if(board.color_cell.get(board.mat[i][j])==Color.BLACK) {
							if(i!=(board.mat[i][j]-1)/(board.mat[0].length)||j!=(board.mat[i][j]-1)%(board.mat[0].length)) {
								grid=null;
								return;
							}
							else grid[i][j]= '*';
						}
						else grid[i][j]= '0';
					}
					else if(board.mat[i][j]==-1) grid[i][j]= '0';
					else grid[i][j]= 's';
				}
			}
			grid[(board.mat[source.row][source.col]-1)/(board.mat[0].length)][(board.mat[source.row][source.col]-1)%(board.mat[0].length)]= 'd';

		}
		// QItem for current location and distance 
		// from source location 
		/**
		 * @author User
		 *
		 */
		class QItem { 

			public int row; 
			public  int col; 
			public  int dist; 
			QItem(int x, int y, int w) 
			{ 
				row=x;
				col=y;
				dist=w;

			} 
		}; 

		int minDistance(int i,int j) 
		{ 
			source= new QItem(i,j,0);
			initGrid(s.getBoard());
			if (grid==null)return -2;
			// To keep track of visited QItems. Marking 
			// blocked cells as visited. 
			Boolean[][] visited = new Boolean[n][m]; 
			for (int i1 = 0; i1 < n; i1++) { 
				for (int j1 = 0; j1 < m; j1++) 
				{ 
					if (grid[i1][j1] == '0'||grid[i1][j1] == 'd') 
						visited[i1][j1] = false; 
					else
						visited[i1][j1] = true; 

				} 
			} 

			// applying BFS on matrix cells starting from source 
			Queue<QItem> q = new LinkedList<>();; 
			q.add(source); 
			visited[source.row][source.col] = true; 
			while (!q.isEmpty()) { 
				QItem p = q.poll();

				// Destination found; 
				if (grid[p.row][p.col] == 'd') 
					return p.dist; 

				// moving up 
				if (p.row - 1 >= 0 && 
						visited[p.row - 1][p.col] == false) { 
					q.add(new QItem(p.row - 1, p.col, p.dist + 1)); 
					visited[p.row - 1][p.col] = true; 
				} 

				// moving down 
				if (p.row + 1 < n && 
						visited[p.row + 1][p.col] == false) { 
					q.add(new QItem(p.row + 1, p.col, p.dist + 1)); 
					visited[p.row + 1][p.col] = true; 
				} 

				// moving left 
				if (p.col - 1 >= 0 && 
						visited[p.row][p.col - 1] == false) { 
					q.add(new QItem(p.row, p.col - 1, p.dist + 1)); 
					visited[p.row][p.col - 1] = true; 
				} 

				// moving right 
				if (p.col + 1 < m && 
						visited[p.row][p.col + 1] == false) { 
					q.add(new QItem(p.row, p.col + 1, p.dist + 1)); 
					visited[p.row][p.col + 1] = true; 
				} 
			} 
			return -1; 
		} 
	}


}

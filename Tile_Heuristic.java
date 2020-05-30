
public class Tile_Heuristic {
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
		//		System.out.println(board);
		//		System.out.println(sum+" "+t.getCost());
		return sum;
	}
	private static int Manethen_Distance(int row, int col, int right_row, int right_col) {
		return Math.abs(row-right_row)+Math.abs(col-right_col);
	}
}

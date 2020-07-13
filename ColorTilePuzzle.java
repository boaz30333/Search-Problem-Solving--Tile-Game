import java.awt.geom.Point2D;


/**
 * 
 * @author Boaz Sharabi
 * This class represent ColorTilePuzzle state
 *
 */
public class ColorTilePuzzle implements tile {
	private Board board;
	private int cost=0; // how much cost to develop this state
	private tile parent=null;
	private String num_op;
	public boolean _isOut=false;
	private int time;
	public static int count=0; // count the number of instance created
	public ColorTilePuzzle(Board board,int cost,tile parent, String num_op) {
		this.board=board;
		this.cost=cost;
		this.parent=parent;
		this.num_op=num_op;
		this._isOut=false;
		count++;
		time=count;
	} 
	@Override
	public int getTime() {
		return time;
	}
	@Override
	public Point2D getFree(){
		return board.blank_cell;
	}
	@Override
	public Board getBoard() {
		// TODO Auto-generated method stub
		return this.board;
	}

	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return this.cost;
	}

	@Override
	public void setCost(int cost) {
		// TODO Auto-generated method stub
		this.cost=cost;
	}
	public String toString() {
		// Loop through all rows 
		return this.board.toString();
	}
	@Override
	public tile getParent() {
		return this.parent;
	}

	@Override
	public tile copy() {

		return new ColorTilePuzzle(this.board.copy(),this.cost,this.parent,this.num_op);
	}
	@Override
	public boolean equals(Object b) {
		return (b instanceof tile )&&b.toString().equals(this.toString());
	}
	@Override
	public String getNumOp() {
		// TODO Auto-generated method stub
		return this.num_op;
	}

	@Override
	public tile getArrangedTile() {
		// TODO Auto-generated method stub
		return new ColorTilePuzzle(this.board.getArranged(), 0, null, "");
	}
	@Override
	public boolean isOut() {
		// TODO Auto-generated method stub
		return _isOut;
	}

	@Override
	public void out() {
		_isOut=true;

	}

	@Override
	public tile move(int direction) {
		int x =(int) this.board.getEmpty().getX();
		int y = (int) this.board.getEmpty().getY();
		// TODO Auto-generated method stub

		if(direction==1)
		{
			// checks that the cell you want to move exists in the board
			if(x==board.mat[0].length-1||

					// checks that we able to move the cell
					this.board.color_cell.get(this.getBoard().mat[y][x+1])==Color.BLACK||

					//ensure we never apply an operator and it's inverse in succession
					this.getNumOp().contains("R"))
				return null;

			return move(x,y,x+1,y,"L");
		} 

		else if(direction==2)	
		{
			if(y==board.mat.length-1||												
					this.board.color_cell.get(this.getBoard().mat[y+1][x])==Color.BLACK|| 
					this.getNumOp().contains("D")) 										
				return null;

			return move(x,y,x,y+1,"U");
		}
		else if(direction==3)
		{
			if(x==0||this.board.color_cell.get(this.getBoard().mat[y][x-1])==Color.BLACK
					||this.getNumOp().contains("L")) 
				return null;

			return move(x,y,x-1,y,"R");
		}
		else{
			if(y==0||this.board.color_cell.get(this.getBoard().mat[y-1][x])==Color.BLACK||
					this.getNumOp().contains("U"))
				return null;

			return move(x,y,x,y-1,"D");
		}
	}
	/**
	 * 
	 * @param x_dest - of blank piece
	 * @param y_dest - of blank piece
	 * @param x_src
	 * @param y_src
	 * @param direction
	 * @return new tile state after sliding piece from src point to dest point
	 */
	public tile move(int x_dest,int y_dest,int x_src,int y_src, String direction ) {
		tile newTile=this.copy();
		newTile.getBoard().mat[y_dest][x_dest]=newTile.getBoard().mat[y_src][x_src];
		newTile.getBoard().mat[y_src][x_src]=-1;
		int cost=1;
		if(this.board.color_cell.get(newTile.getBoard().mat[y_dest][x_dest])==Color.RED) 
			cost=30;
		newTile.setCost(cost+this.getCost());
		newTile.setNumOp(newTile.getBoard().mat[y_dest][x_dest]+direction);
		newTile.getBoard().setEmpty(new Point2D.Double(x_src, y_src));
		newTile.setParent(this);
		return newTile;
	}
	@Override
	public void setNumOp(String num_op) {
		// TODO Auto-generated method stub
		this.num_op=num_op;
	}

	@Override
	public void setParent(tile parent) {
		// TODO Auto-generated method stub
		this.parent=parent;
	}
	@Override
	public void markNoOut() {
		this._isOut=false;
	}
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return this.board.getKey();
	}
}
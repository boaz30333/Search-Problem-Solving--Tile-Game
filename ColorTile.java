
import java.util.List;
import java.util.Vector;

import javafx.geometry.Point2D;

public class ColorTile implements tile {
    private Board board;
    private int cost=0; // how much cost to develop this state
	private tile parent=null;
	private String num_op;
	public boolean _isVisited=false;
	public static int count=0;
	public ColorTile(Board board,int cost,tile parent, String num_op) {
        this.board=board;
		this.cost=cost;
		this.parent=parent;
		this.num_op=num_op;
		this._isVisited=false;
		count++;
    } 

	public Point2D getFree(){
        return board.empty_space;
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

	public tile move(int x_src,int y_src,int x_dest,int y_dest, String direction ) {
		tile newTile=this.copy();
		newTile.getBoard().mat[y_src][x_src]=newTile.getBoard().mat[y_dest][x_dest];
		newTile.getBoard().mat[y_dest][x_dest]=-1;
		int cost=1;
		if(this.board.color_cell.get(newTile.getBoard().mat[y_src][x_src])==Color.RED) 
			cost=30;
		newTile.setCost(cost+this.getCost());
		newTile.setNumOp(newTile.getBoard().mat[y_src][x_src]+direction);
		newTile.getBoard().setEmpty(new Point2D(x_dest, y_dest));
		newTile.setParent(this);
		return newTile;
	}



	@Override
	public tile copy() {
		
		return new ColorTile(this.board.copy(),this.cost,this.parent,this.num_op);
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
		return new ColorTile(this.board.getArranged(), 0, null, "");
	}

	@Override
	public boolean isVisited() {
		// TODO Auto-generated method stub
		return _isVisited;
	}

	@Override
	public void visit() {
		_isVisited=true;
		
	}

	@Override
	public tile move(int direction) {
		int x =(int) this.board.getEmpty().getX();
		int y = (int) this.board.getEmpty().getY();
		// TODO Auto-generated method stub
		 if(direction==1) {
			if(x==board.mat[0].length-1||this.board.color_cell.get(this.getBoard().mat[y][x+1])==Color.BLACK||this.getNumOp().contains("R")) return null;
			return move(x,y,x+1,y,"L");
		} 
		 else if(direction==2)	{
			 if(y==board.mat.length-1||this.board.color_cell.get(this.getBoard().mat[y+1][x])==Color.BLACK||this.getNumOp().contains("D")) return null;
			return move(x,y,x,y+1,"U");
		}
		else if(direction==3) {
			if(x==0||this.board.color_cell.get(this.getBoard().mat[y][x-1])==Color.BLACK||this.getNumOp().contains("L")) return null;
			return move(x,y,x-1,y,"R");
		}
		else{
			if(y==0||this.board.color_cell.get(this.getBoard().mat[y-1][x])==Color.BLACK||this.getNumOp().contains("U")) return null;
			return move(x,y,x,y-1,"D");
		}


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



    
     
}
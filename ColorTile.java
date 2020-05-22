
import java.util.List;
import java.util.Vector;

import javafx.geometry.Point2D;

public class ColorTile implements tile {
    private Board board;
    private int cost=0; // how much cost to develop this state
	private tile parent=null;
	private String num_op;
	boolean _isVisited=false;
	public static int count=0;
	public ColorTile(Board board,int cost,tile parent, String num_op) {
        this.board=board;
		this.cost=cost;
		this.parent=parent;
		this.num_op=num_op;
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

	public tile moveUp() {
		int x =(int) this.board.getEmpty().getX();
		int y = (int) this.board.getEmpty().getY();
		if(y==0||this.board.color_cell.get(this.getBoard().mat[y-1][x])==Color.BLACK||this.getNumOp().contains("U")) return null;
		tile newTile=this.copy();
		newTile.getBoard().mat[y][x]=newTile.getBoard().mat[y-1][x];
		newTile.getBoard().mat[y-1][x]=-1;
		int cost=1;
		if(this.board.color_cell.get(newTile.getBoard().mat[y][x])==Color.RED) cost=30;
		newTile.setCost(cost+this.getCost());
		newTile.setNumOp(newTile.getBoard().mat[y][x]+"D");
		newTile.getBoard().setEmpty(new Point2D(x, y-1));
		newTile.setParent(this);
		return newTile;
	}

	public tile moveDown() {
		// TODO Auto-generated method stub
		int x =(int) this.board.getEmpty().getX();
		int y = (int) this.board.getEmpty().getY();
		if(y==board.mat.length-1||this.board.color_cell.get(this.getBoard().mat[y+1][x])==Color.BLACK||this.getNumOp().contains("D")) return null;
		tile newTile=this.copy();
		newTile.getBoard().mat[y][x]=newTile.getBoard().mat[y+1][x];
		newTile.getBoard().mat[y+1][x]=-1;
		int cost=1;
		if(this.board.color_cell.get(newTile.getBoard().mat[y][x])==Color.RED) cost=30;
		newTile.setCost(cost+this.getCost());
		newTile.setNumOp(newTile.getBoard().mat[y][x]+"U");
		newTile.getBoard().setEmpty(new Point2D(x, y+1));
		newTile.setParent(this);
		return newTile;
	}

	public tile moveRight() {
		// TODO Auto-generated method stub
		int x =(int) this.board.getEmpty().getX();
		int y = (int) this.board.getEmpty().getY();
		if(x==board.mat[0].length-1||this.board.color_cell.get(this.getBoard().mat[y][x+1])==Color.BLACK||this.getNumOp().contains("R")) return null;
		tile newTile=this.copy();
		newTile.getBoard().mat[y][x]=newTile.getBoard().mat[y][x+1];
		newTile.getBoard().mat[y][x+1]=-1;
		int cost=1;
		if(this.board.color_cell.get(newTile.getBoard().mat[y][x])==Color.RED) cost=30;
		newTile.setCost(cost+this.getCost());
		newTile.setNumOp(newTile.getBoard().mat[y][x]+"L");
		newTile.getBoard().setEmpty(new Point2D(x+1, y));
		newTile.setParent(this);
		return newTile;
	}

	public tile moveLeft() {
		// TODO Auto-generated method stub
		int x =(int) this.board.getEmpty().getX();
		int y = (int) this.board.getEmpty().getY();
		if(x==0||this.board.color_cell.get(this.getBoard().mat[y][x-1])==Color.BLACK||this.getNumOp().contains("U")) return null;
		tile newTile=this.copy();
		newTile.getBoard().mat[y][x]=newTile.getBoard().mat[y][x-1];
		newTile.getBoard().mat[y][x-1]=-1;
		int cost=1;
		if(this.board.color_cell.get(newTile.getBoard().mat[y][x])==Color.RED) cost=30;
		newTile.setCost(cost+this.getCost());
		newTile.setNumOp(newTile.getBoard().mat[y][x]+"R");
		newTile.getBoard().setEmpty(new Point2D(x-1, y));
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
		// TODO Auto-generated method stub
		if (direction==3) return moveLeft();
		else if(direction==4) return moveUp();
		else if(direction==1) return moveRight();
		else return moveDown();
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
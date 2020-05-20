
import java.util.List;
import java.util.Vector;

import javafx.geometry.Point2D;

public class ColorTile implements tile {
    private Board board;
    private int cost=0; // how much cost to develop this state
	private tile parent;
	private String op;
    public ColorTile(Board board,int cost,tile parent) {
        this.board=board;
		this.cost=cost;
		this.parent=parent;
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
	public double getCost() {
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
	public tile Parent() {
		return this.parent;
	}

	@Override
	public void moveUp() {
		
	}

	@Override
	public void moveDown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveRight() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveLeft() {
		// TODO Auto-generated method stub

	}

	@Override
	public tile copy() {
		
		return new ColorTile(this.board.copy(),this.cost,this.parent);
	}

	@Override
	public boolean equals(Object b) {
		return (b instanceof tile )&&b.toString()==this.toString();
	}

	@Override
	public String getOp() {
		// TODO Auto-generated method stub
		return this.op;
	}



    
     
}
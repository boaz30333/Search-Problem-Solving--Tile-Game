import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class color_tile implements Tile {
    protected class Empty_Space{
        private int _x;
        private int _y;
        Empty_Space(int x,int y){ this._x=x; this._y=y;}
        public int x(){ return _x;  }
        public int y(){   return _y;  }
     }

    private Empty_Space x;
    private color_cell[][] Board;
    private int cost=0; // how much cost to develop this state
	private Tile parent;

    public color_tile(color_cell[][] Board,int cost,Empty_Space x,Tile parent) {
        this.Board=Board;
        this.x=x;
		this.cost=cost;
		this.parent=parent;
    } 

    public Empty_Space getFree(){
        return x;
    }



	@Override
	public int[][] getBoard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getCost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCost(double b) {
		// TODO Auto-generated method stub
		
	}


    @Override
    public Cell getEmptyCell() {
        // TODO Auto-generated method stub
        return null;
	}
	public String toString() {
		       // Loop through all rows 
			   for (color_cell[] row : mat) {
				System.out.println("|");
			   // Loop through all columns of current row 
			   for (color_cell x : row) {
				   System.out.print(x.num + " ("+x.color+")");
				   if(x.num<10) System.out.print(" ");
				   System.out.print("|");
			   }
			}
				   
	}

	@Override
	public Tile Parent() {
		return parent;
	}

	@Override
	public void moveUp() {
		if(Empty_Space.y()>0)
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
	public Tile copy() {
		
		return new color_tile(this.mat,this.cost,this.x,this.parent);
	}

	@Override
	public boolean equals(Object b) {
		return (b instanceof Tile )&&b.toString()==this.toString();
	}



    
     
}
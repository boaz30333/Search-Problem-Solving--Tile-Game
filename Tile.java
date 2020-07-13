import java.awt.geom.Point2D;

/**
 * represent a states for problem solving by search
 */


public interface tile {
	/**
	 * 
	 * @return Parent tile - the tile state through which we reached the present state
	 */
	public tile getParent();
	/**
	 * 
	 * @param Parent tile - the tile state through which we reached the present state
	 */
	public void setParent(tile parent);
	/**
	 * Possibility of marking certain information about the state - change to true
	 */
	public void out();
	/**
	 * Possibility of marking certain information about the state - change to false
	 */
	public void markNoOut();
	/**
	 * 
	 * @return certain information about the state - true or false
	 */
	public boolean isOut();

	/**
	 * 
	 * @param cost path from the initial state to current state
	 */
	public void setCost(int cost);
	/**
	 * 
	 * @return  cost path from the initial state to current state
	 */
	public int getCost();
	/**
	 * 
	 * @return board represent the current state of the Tile game
	 */
	public Board getBoard();

	/**
	 * 
	 * @param num_op - a String represent The number and moving direction of the piece moved to reach the current state
	 */
	public void setNumOp(String num_op);
	/**
	 * 
	 * @return a String represent The number and moving direction of the piece moved to reach the current state
	 */
	public String getNumOp();

	/**
	 * 
	 * @param direction - 1= LEFT , 2= UP , 3= RIGHT , 4= DOWN
	 * @return new tile state after moving - if  moving the piece that is in the "direction" of the empty cell  is legal
	 * otherwise  null;
	 * e.g. move(1) = move the piece to the left of the empty cell to the empty empty cll

	 */
	public tile move(int direction);

	/**
	 * 
	 * @return "deep" copy of currrent tile
	 */
	public tile copy();
	/**
	 * 
	 * @param other - an object 
	 * @return true if the object represent same tile state
	 */
	public boolean equals(Object other);
	/**
	 * 
	 * @return string represent current state
	 */
	public String toString();
	/**
	 * 
	 * @return String key of this state for hashing(shorter than toString)

	 */
	public String getKey();
	/**
	 * 
	 * @return goal state tile
	 */
	public tile getArrangedTile();
	/**
	 * 
	 * @return the state number according to the creation time (1 = the first state  created)
	 */
	public int getTime();
	/**
	 * 
	 * @return  empty cell location (x,y)
	 */
	public Point2D getFree();
	

}

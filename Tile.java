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
	public void visit();
	/**
	 * Possibility of marking certain information about the state - change to false
	 */
	public void markNoVisit();
/**
 * 
 * @return certain information about the state - true or false
 */
	public boolean isVisited();
	
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
 * @return new tile state after moving the piece that is in the "direction" of the empty cell 
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

	public tile getArrangedTile();
	public int getTime();


}

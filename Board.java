import java.util.Arrays;

import javafx.geometry.Point2D;
public class Board{
    Color_Cell[][] mat;
    Point2D empty_space;
	public Board(    Color_Cell[][] mat,  Point2D empty_space) {
		this.mat=mat;
		this.empty_space=empty_space;
	}
public Board copy() {
	Color_Cell[][] newmat = new Color_Cell[mat.length][mat[0].length] ;
	   for (int i=0;i<mat.length;i++) {
	   for (int j=0;i<mat[0].length;j++) {
		   newmat[i][j]= this.mat[i][j].copy();
	   }

	   }
	return new Board(newmat,new Point2D(this.empty_space.getX(),this.empty_space.getY()));
	
}
public String toString() {
	StringBuilder string = new StringBuilder();
	   for (Color_Cell[] row : mat) {
		string.append("\n |");
	   // Loop through all columns of current row 
	   for (Color_Cell x : row) {
		   string.append(x.num + "("+x.color+")");
		   if(x.num<10&&x.num>-1)  string.append(" ");
		   if(x.color==Color_Cell.Color.RED)  string.append("  ");
		   string.append("|");
	   }
	}
	   return string.toString();
}


}

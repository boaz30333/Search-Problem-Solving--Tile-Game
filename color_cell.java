

public class Color_Cell {
    public Color color;
    public int num;
    public Color_Cell(Color color, int num) {this.color=color; this.num=num;}
    public Color_Cell copy() {
    	return new Color_Cell(this.color,this.num);
    }

public enum Color{BLACK,GREEN,RED ,EMPTY};
}
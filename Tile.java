import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Tile implements state {
    private free x;
    private double[][] mat;
    private color[][] colormat;
    private int wight=0; // how much cost to develop this state
    ArrayList<command> b= new ArrayList<command>();
    public Tile(double[][] mat,color[][] colormat,int wight,free x) {
        this.mat=mat;
        this.colormat=colormat;
        this.wight=wight;
    } 
      @Override
    public void setOperators(List<command> c) {
        // TODO Auto-generated method stub
        this.b.addAll(c);
    }

    @Override
    public List<command> getOperators() {
        // TODO Auto-generated method stub
        return b;
    }

    @Override
    public double getWight() {
        // TODO Auto-generated method stub
        return wight;
    }

    @Override
    public void setWight(double wight) {
        // TODO Auto-generated method stub
       this.wight=(int)wight;
    }
    public free getFree(){
        return x;
    }
    public class free{
       private int _x;
       private int _y;
       free(int x,int y){
           this._x=x;
           this._y=y;
       }
       public int x(){
        return _x;
       }
       public int y(){
        return _y;
       }
    }
     
}
import javafx.collections.SetChangeListener;

public interface search_algo {
public tile getStart();
public tile getGoal();
public Boolean getWithOpen();
public String run(); 
public void setParam(tile start, tile goal ,boolean withOpen,boolean withTime,String[] Solution);
    
}
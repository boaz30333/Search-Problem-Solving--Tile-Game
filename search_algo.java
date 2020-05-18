import javafx.collections.SetChangeListener;

public interface search_algo {
public state getStart();
public state getGoal();
public Boolean getWithOpen();
public String run(); 
    
}
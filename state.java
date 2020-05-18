import java.util.ArrayList;
/**
 * 
 * represent a states for problem solving by search
 */
import java.util.List;

public interface state {

    public void setOperators (List<command> b);
    public List<command> getOperators();
    public state getWight();
    public state setWight();
}

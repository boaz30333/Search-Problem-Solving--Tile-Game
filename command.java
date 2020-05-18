/**
 * represent a operator to create childs state 
 */
public interface command {
    public state execute(state src);
}
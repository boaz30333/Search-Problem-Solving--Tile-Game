/**
 * represent heuristic function to estimate the cost from src state to goal state
 */
public interface heuristic_function {
    
    double getValue(state src);
    state getGoal();
}
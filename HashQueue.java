
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class HashQueue<T> {
    Queue<T> queue = new LinkedList<>();
    HashMap<String,T> h_map = new HashMap<>();

public boolean add(T e) {
     T sucsses=h_map.put(e.toString(), e);
    return (queue.add(e)&&sucsses!=null);
}


public T peek() {
    return queue.peek();
}

public T element() {

    return queue.element();
}

public T remove() {

    return queue.remove();
}

public T poll() {

    return queue.poll();
}

public int size() {

    return queue.size();
}
public boolean in_hash(tile t){
    return h_map.containsKey(t.toString());
}

}
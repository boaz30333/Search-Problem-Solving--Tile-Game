
import java.util.Iterator;
import java.util.LinkedHashMap;

import java.util.Map.Entry;


public class HashQueueTile {
    LinkedHashMap<String,tile> h_queue = new LinkedHashMap<>();
    int noVisted =0;
/**
 * 
 * @param e the element to add
 * @return true if  success  false otherwise
 */
public boolean add(String key , tile value) {
    tile success=h_queue.put(key, value);
    return (success!=null);
}


public tile peek() {
	Iterator<Entry<String, tile>> values= h_queue.entrySet().iterator();
	tile value=null;
	while(values.hasNext()) {
		 value= values.next().getValue();
		if(!value.isVisited()) {
			return value;
		}
	}
	return null;
}



public tile poll() {
tile t= this.peek();
if(t!=null)
	t.visit();
    return t;
}
public void empty() {
	h_queue.clear();
}
public int size() {

    return h_queue.size();
}
public boolean in_hash(tile t){
    return h_queue.containsKey(t.toString());
}

}
package lc.questions.basic;

/**
 * 
 * https://leetcode.com/problems/lru-cache/
 * 
 * @author Siva Kumar Edupuganti.
 * @email sivaiitroracle9@gmail.com
 * 
 */
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class LRUCache {

	private int capacity = 10;
	private LinkedHashMap<Integer, Integer> cache = null;

	public LRUCache(int capacity) {
		this.capacity = capacity;
		cache = new LinkedHashMap<Integer, Integer>(this.capacity);
	}

	public int get(int key) {
		if (cache.get(key) == null)
			return -1;
		int value = cache.remove(key);
		cache.put(key, value);

		return value;
	}

	public void set(int key, int value) {
		
		if (cache.get(key)!=null) {
			cache.remove(key);
		} else {
			if (cache.size() >= this.capacity) {
				for (Entry<Integer, Integer> e : cache.entrySet()) {
					cache.remove(e.getKey());
					break;
				}
			}
		}
		cache.put(key, value);
	}

	public static void main(String[] args) {
		LRUCache lru = new LRUCache(2);
		
		System.out.println(lru.get(2));
		lru.set(2, 6);
		System.out.println(lru.get(1));
		lru.set(1, 5);
		lru.set(1, 2);
		System.out.println(lru.get(1));
		System.out.println(lru.get(2));
	}
	
}

package algorithms.graph;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Siva Kumar Edupuganti
 * @email sivaiitroracle9@gmail.com
 * 
 *        Disjoint sets using path compression and union by rank Supports 3
 *        operations 1) makeSet 2) union 3) findSet
 * 
 *        For m operations and total n elements time complexity is O(m*f(n))
 *        where f(n) is very slowly growing function. For most cases f(n) <= 4
 *        so effectively total time will be O(m). Proof in Coreman book.
 */
public class DisjointSet {

	Map<Long, Node> map = new HashMap<Long, Node>();

	class Node {
		long data;
		Node parent;
		int rank;
	}

	public void makeSet(long data) {
		Node node = new Node();
		node.data = data;
		node.rank = 0;
		node.parent = node;
		map.put(data, node);
	}

	public boolean union(long data1, long data2) {

		Node parent1 = findSet(map.get(data1));
		Node parent2 = findSet(map.get(data2));

		if (parent1.data == parent2.data)
			return false;

		if (parent1.rank >= parent2.rank) {
			if (parent1.rank == parent2.rank) {
				parent1.rank += 1;
			}
			parent2.parent = parent1;
		} else {
			parent1.parent = parent2;
		}
		return true;
	}

	public long findSet(long data) {
		return findSet(map.get(data)).data;
	}

	public Node findSet(Node node) {

		Node parent = node.parent;
		if (parent == node) {
			return node;
		}
		// Here path compression is being done along with recursion of getting
		// the parent.
		node.parent = findSet(parent);
		return node.parent;
	}

	  public static void main(String args[]) {
	        DisjointSet ds = new DisjointSet();
	        ds.makeSet(1);
	        ds.makeSet(2);
	        ds.makeSet(3);
	        ds.makeSet(4);
	        ds.makeSet(5);
	        ds.makeSet(6);
	        ds.makeSet(7);

	        ds.union(1, 2);
	        ds.union(2, 3);
	        ds.union(4, 5);
	        ds.union(6, 7);
	        ds.union(5, 6);
	        ds.union(3, 7);

	        System.out.println(ds.findSet(1));
	        System.out.println(ds.findSet(2));
	        System.out.println(ds.findSet(3));
	        System.out.println(ds.findSet(4));
	        System.out.println(ds.findSet(5));
	        System.out.println(ds.findSet(6));
	        System.out.println(ds.findSet(7));
	    }
	
}

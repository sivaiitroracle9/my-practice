package leetcode.company.google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class L332_ReconstructItinerary {

	public static void main(String[] args) {
		L332_ReconstructItinerary lc = new L332_ReconstructItinerary();
		String[][] tickets = { { "JFK", "KUL" }, { "JFK", "NRT" },
				{ "NRT", "JFK" }};
		for (String port : lc.findItinerary(tickets))
			System.out.print(port + " -> ");
	}

	public List<String> findItinerary(String[][] tickets) {
		Map<String, TreeMap<String, Boolean>> itinaryedgemap = new HashMap<String, TreeMap<String, Boolean>>();
		for (String[] ticket : tickets) {
			if (!itinaryedgemap.containsKey(ticket[0]))
				itinaryedgemap.put(ticket[0], new TreeMap<String, Boolean>());
			itinaryedgemap.get(ticket[0]).put(ticket[1], false);
		}

		List<String> result = new ArrayList<String>(itinaryedgemap.size() + 1);
		if (!itinaryedgemap.containsKey("JFK")
				|| itinaryedgemap.get("JFK").size() == 0)
			return result;

		String start = "JFK";
		result.add(start);
		dfs(itinaryedgemap, start, result, 0, tickets.length);
		return result;
	}

	public boolean dfs(Map<String, TreeMap<String, Boolean>> itinaryedgemap,
			String node, List<String> result, int depth, int numOfEdges) {

		if (depth == numOfEdges) {
			return true;
		}

		if (itinaryedgemap.containsKey(node))
			for (String next : itinaryedgemap.get(node).keySet()) {
				if (!itinaryedgemap.get(node).get(next)) {
					itinaryedgemap.get(node).put(next, true);
					result.add(next);
					if (dfs(itinaryedgemap, next, result, depth + 1, numOfEdges))
						return true;
					result.remove(result.size() - 1);
					itinaryedgemap.get(node).put(next, false);
				}
			}
		return false;
	}
}

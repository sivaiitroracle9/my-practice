package worksapp.SnakeGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SnakeGame_With_Teleport {

	private static int rows = 0;
	private static int cols = 0;
	private static int[][] grid = null;
	private static boolean[][] visited = null;
	private static Path visitOrder = null;

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		rows = s.nextInt(); // rows
		cols = s.nextInt(); // columns

		grid = new int[rows][cols];
		visited = new boolean[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				grid[j][i] = s.nextInt();
			}
		}

		SnakeGame_With_Teleport sg = new SnakeGame_With_Teleport();
		Path path = sg.findHighestScorePath();

		if (path != null)
			path.printPath();
		else
			System.out.println(-1);
	}

	private Path findHighestScorePath() {
		List<Path> paths = new ArrayList<SnakeGame_With_Teleport.Path>();
		for (int i = 0; i < rows; i++) {
			visitOrder = null;
			if (grid[0][i] != -1)
				paths.add(snake(0, i, visitOrder));
		}

		Collections.sort(paths, Collections.reverseOrder());
		if (paths.size() != 0)
			return paths.get(0);

		return null;
	}

	private Path snake(int x, int y, Path visitOrder) {

		if (visitOrder == null)
			visitOrder = new Path();
		Node current = new Node(x, y, grid[x][y]);
		visited[x][y] = true;
		visitOrder.addPath(current);
		int score = visitOrder.score;

		List<Path> listOfPaths = new ArrayList<Path>();
		int cnt = 0;
		if (x < rows - 1 && grid[x + 1][y] != -1 && !visited[x + 1][y]) {
			listOfPaths.add(cnt, snake(x + 1, y, visitOrder));
			cnt++;
		}

		if (y < cols - 1 && grid[x][y + 1] != -1 && !visited[x][y + 1]) {
			listOfPaths.add(cnt, copyNew(snake(x, y + 1, visitOrder)));
			cnt++;
		}

		if (y > 0 && grid[x][y - 1] != -1 && !visited[x][y - 1]) {
			listOfPaths.add(cnt, copyNew(snake(x, y - 1, visitOrder)));
			cnt++;
		}

		if (y == cols - 1 && grid[x][0] != -1 && !visited[x][0]) {
			visitOrder.score = 0;
			listOfPaths.add(cnt, copyNew(snake(x, 0, visitOrder)));
			visitOrder.score = score;
			cnt++;
		}

		if (y == 0 && grid[x][cols - 1] != -1 && !visited[x][cols - 1]) {
			visitOrder.score = 0;
			listOfPaths.add(cnt, copyNew(snake(x, cols - 1, visitOrder)));
			visitOrder.score = score;
			cnt++;
		}

		if (listOfPaths.size() == 0) {
			listOfPaths.add(copyNew(visitOrder));
		} else {
			Collections.sort(listOfPaths, Collections.reverseOrder());
		}
		visited[x][y] = false;
		visitOrder.remove(current);

		return listOfPaths.get(0);
	}

	private Path copyNew(Path orij) {
		Path newPath = new Path();
		newPath.path.addAll(orij.path);
		newPath.score = orij.score;
		newPath.pathSize = orij.pathSize;
		return newPath;
	}

	private class Path implements Comparable<Path> {

		private ArrayList<Node> path = null;
		private int pathSize = 0;
		private int score = 0;

		Path() {
			path = new ArrayList<Node>();
		}

		@Override
		public int compareTo(Path that) {
			return this.score - that.score;
		}

		public void addPath(Node node) {
			path.add(node);
			pathSize = path.size();
			this.score += node.value;
		}

		public void remove(Node node) {
			path.remove(node);
			pathSize = path.size();
			this.score -= node.value;
		}

		public void printPath() {
			for (int i = 0; i < path.size() - 1; i++) {
				Node node = path.get(i);
				System.out.print("{" + node.x + ", " + node.y + ", "
						+ node.value + "}" + " -> ");
			}
			System.out.print("{" + path.get(path.size() - 1).x + ", "
					+ path.get(path.size() - 1).y + ", "
					+ path.get(path.size() - 1).value + "}");
			System.out.println();
			System.out.println();
			Node start = path.get(path.size() - 1);
			Node end = path.get(0);
			System.out.println("Start Point = ( " + start.x + ", " + start.y
					+ ") ... Elevation = " + start.value);
			System.out.println("End Point = ( " + end.x + ", " + end.y
					+ ") ... Elevation = " + end.value);
			System.out.println("Path length = " + pathSize);
			System.out.println("Score = " + score);
		}

	}

	private class Node {
		private int x = -1;
		private int y = -1;
		private int value = -1;

		Node(int x, int y, int value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}

		public boolean equals(Node that) {
			return (this.x == that.x) && (this.y == that.y);
		}
	}
}

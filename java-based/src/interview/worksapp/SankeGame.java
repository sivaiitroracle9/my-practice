package interview.worksapp;

import java.util.ArrayList;
import java.util.Scanner;

public class SankeGame {

	private static int rows = 0;
	private static int cols = 0;
	private static int[][] grid = null;
	private static int[][] visited = null;
	private static int highScore = Integer.MIN_VALUE;
	private static Path maxPath = null;

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		rows = s.nextInt(); // rows
		cols = s.nextInt(); // columns

		grid = new int[rows][cols];
		visited = new int[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				grid[i][j] = s.nextInt();
			}
		}
		SankeGame sv2 = new SankeGame();
		Path path = sv2.new Path();
		for (int i = 0; i < rows; i++) {
			if (grid[i][0] != -1) {
				sv2.findTheMaxScore(i, 0, 0, path);
			}
		}
		if (highScore >= 0) {
			System.out.println(highScore);
		} else {
			System.out.println(-1);
		}

		// maxPath.printPath();

	}

	private void findTheMaxScore(int x, int y, int sum, Path path) {

		visited[x][y] = 1;
		int newsum = sum + grid[x][y];
		Node current = new Node(x, y, grid[x][y]);
		path.addPath(current);
		// path.printPath();

		if (x - 1 >= 0 && grid[x - 1][y] != -1 && visited[x - 1][y] == 0) {
			findTheMaxScore(x - 1, y, newsum, path);
		}

		if (x + 1 < rows && grid[x + 1][y] != -1 && visited[x + 1][y] == 0) {
			findTheMaxScore(x + 1, y, newsum, path);
		}

		if (y + 1 < cols && grid[x][y + 1] != -1 && visited[x][y + 1] == 0) {
			findTheMaxScore(x, y + 1, newsum, path);
		}

		if (x == 0 && grid[rows - 1][y] != -1 && visited[rows - 1][y] == 0) {
			findTheMaxScore(rows - 1, y, 0, path);
		}

		if (x == rows - 1 && grid[0][y] != -1 && visited[0][y] == 0) {
			findTheMaxScore(0, y, 0, path);
		}

		visited[x][y] = 0;

		if (y == cols - 1) {
			if (highScore < newsum) {
				highScore = newsum;
				maxPath = copyNew(path);
				// maxPath.printPath();
			}
		}
		path.remove(current);
	}

	private Path copyNew(Path orij) {
		Path newPath = new Path();
		newPath.path.addAll(orij.path);
		newPath.pathSize = orij.pathSize;
		return newPath;
	}

	private class Path implements Comparable<Path> {

		private ArrayList<Node> path = null;
		private int pathSize = 0;
		private int currentScore = 0;

		Path() {
			path = new ArrayList<Node>();
		}

		@Override
		public int compareTo(Path that) {
			return this.currentScore - that.currentScore;
		}

		public void addPath(Node node) {
			path.add(node);
			pathSize = path.size();
			this.currentScore += node.value;
		}

		public void remove(Node node) {
			path.remove(node);
			pathSize = path.size();
			this.currentScore -= node.value;
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

package worksapp.SnakeGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SnakeGame_No_Teleport {

	private static int rows = 0;
	private static int cols = 0;
	private static int[][] grid = null;
	private static boolean[][] visited = null;

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

		SnakeGame_No_Teleport sg = new SnakeGame_No_Teleport();
		List<Path> paths = new ArrayList<SnakeGame_No_Teleport.Path>();
		List<Node> visited = new ArrayList<SnakeGame_No_Teleport.Node>();
		for (int i = 0; i < rows; i++) {

			paths.add(sg.highestScorePath(0, i));
		}

		Collections.sort(paths, Collections.reverseOrder());	
		paths.get(0).printPath();
	}

	private Path highestScorePath(int x, int y) {

		Node current = new Node(x, y, grid[x][y]);
		visited[x][y] = true;

		List<Path> listOfPaths = new ArrayList<Path>();
		int cnt = 0;
		if (x < rows - 1 && grid[x + 1][y] != -1 && !visited[x + 1][y]) {
			listOfPaths.add(cnt, copyNew(highestScorePath(x + 1, y)));
			listOfPaths.get(cnt).addPath(current);
		}

		if (y < cols - 1 && grid[x][y + 1] != -1 && !visited[x][y + 1]) {
			listOfPaths.add(cnt, copyNew(highestScorePath(x, y + 1)));
			listOfPaths.get(cnt).addPath(current);
		}

		if (y > 0 && grid[x][y - 1] != -1 && !visited[x][y - 1]) {
			listOfPaths.add(cnt, copyNew(highestScorePath(x, y - 1)));
			listOfPaths.get(cnt).addPath(current);
		}

		if (listOfPaths.size() == 0) {
			listOfPaths.add(new Path());
			listOfPaths.get(0).addPath(current);
		} else {
			Collections.sort(listOfPaths, Collections.reverseOrder());
		}
		visited[x][y] = false;

		return listOfPaths.get(0);
	}

	private Path copyNew(Path orij) {
		Path newPath = new Path();
		for(Node node : orij.path) {
			newPath.addPath(node);
		}
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

		public void printPath() {
			for (int i = path.size() - 1; i > 0; i--) {
				Node node = path.get(i);
				System.out.print("{" + node.x + ", " + node.y + ", "
						+ node.value + "}" + " -> ");
			}
			System.out.print("{" + path.get(0).x + ", " + path.get(0).y + ", "
					+ path.get(0).value + "}");
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
			// TODO Auto-generated method stub
			return (this.x == that.x) && (this.y == that.y);
		}
	}
}

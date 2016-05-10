package leetcode.questions.basic;

public class DungeonGame {

	public static void main(String[] args) {

	}

	public int[][] calculateMinHP(int[][] dungeon) {
		int[][] health = new int[dungeon.length][dungeon[0].length];

		for (int r = dungeon.length - 1; r >= 0; r--) {
			for (int c = dungeon[0].length - 1; c >= 0; c--) {
				if(dungeon[r][c] >=0) health[r][c] = 1;
				else {
					health[r][c] = 1 - dungeon[r][c];
				}
			}
		}
	}
}

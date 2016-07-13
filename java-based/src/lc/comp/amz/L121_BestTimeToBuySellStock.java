package lc.comp.amz;
/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 * 
 * @author sivae
 *
 */
public class L121_BestTimeToBuySellStock {
	public int maxProfit(int[] prices) {

		if (prices.length == 0)
			return 0;

		int profit = 0, min = prices[0]; // buy first

		for (int i = 1; i < prices.length; i++) {
			if (min > prices[i]) {
				min = prices[i];
			} 
			
			if(prices[i] - min > profit) {
				profit = prices[i] - min;
			}

		}
		return profit;
	}
}

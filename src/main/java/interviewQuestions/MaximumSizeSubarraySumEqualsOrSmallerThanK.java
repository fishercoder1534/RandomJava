package interviewQuestions;

import java.util.HashMap;
import java.util.Map;

public class MaximumSizeSubarraySumEqualsOrSmallerThanK {
	/**VMWare Hackerrank assessment:
	 * I wrote the following solution, it passed 10/12 test cases with the other two timed out, I'm NOT sure why.*/
	static int maxLength(int[] a, int k) {

		int sum = 0, max = 0;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < a.length; i++){
			sum = sum + a[i];
			if(sum <= k && sum > max) max = i + 1;
			else {
				for(Map.Entry<Integer, Integer> entry : map.entrySet()){
					if((sum - entry.getKey()) <= k) max = Math.max(max, i - entry.getValue());
				}
			}
			if(!map.containsKey(sum)) map.put(sum, i);
			System.out.println("i = " + i + "\tmax is: " + max + "\tsum is: " + sum);
		}
		return max;

	}
}

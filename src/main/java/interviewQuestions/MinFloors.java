package interviewQuestions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MinFloors {
	static long minFloors_unfinished_solution(long[] buildings, int m) {
		Map<Long, Integer> counts = new HashMap();
		for(long h : buildings){
			if(counts.containsKey(h)) counts.put(h, counts.get(h)+1);
			else counts.put(h, 1);
			if(counts.get(h) >= m) return 0;
		}
		if(counts.containsValue(m)) return 0;
		else{
			//build a treemap, key is the frequency, value is the building height
			//then we use the one with highest frequency, find the number of units we need to build m buildings
			TreeMap<Integer, Long> treeMap = new TreeMap();
			for(long i : counts.keySet()){
				treeMap.put(counts.get(i), i);
			}
			long costs = 0;
			Map.Entry<Integer, Long> lastEntry = treeMap.pollLastEntry();
			long height = lastEntry.getValue();
			int buildingsNeeded = m - lastEntry.getKey();
			//keep finding the next building that has the highest frequency, if there's a tie, find the one that is closest to height
			while(buildingsNeeded > 0 && !treeMap.isEmpty()){
				Map.Entry<Integer, Long> entry = treeMap.pollLastEntry();
			}
			return costs;
		}
	}

	public static void main(String...args){
		long[] buildings = new long[]{4,3,1,2,3,4,2};
		Arrays.sort(buildings);
		for(long l : buildings){
			System.out.print(l + ", ");
		}
		System.out.println();
		System.out.println(Finished_solution.minFloors(buildings, 3));
	}

	static class Finished_solution {
		static long minFloors(long[] buildings, int m) {
			//sort buildings
			Arrays.sort(buildings);
			//maintain a window of size m, compute the minCost of each window, update minCost along the way as the final result
			long minCost = Long.MAX_VALUE;
			for(int i = 0; i <= buildings.length-m; i++){
				long heightToMatch = buildings[i+m-1];
				if(heightToMatch == buildings[i]) return 0;//if the last building's height equals the first one, that means the whole window if of the same size, we can directly return 0
				long thisCost = 0;
				for(int j = i+m-1; j >= i; j--){
					thisCost += heightToMatch - buildings[j];
				}
				minCost = Math.min(minCost, thisCost);
			}
			return minCost;
		}
	}
}

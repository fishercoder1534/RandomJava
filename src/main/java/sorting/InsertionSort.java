package sorting;

import java.util.Random;

public class InsertionSort {
	
	public static int[] generateRandomArray(int len) {
		int[] nums = new int[len];
		for(int i = 0; i < len; i++){
			nums[i] = (new Random()).nextInt(200);
		}
		return nums;
	}

	public int[] insertionSort(int[] nums){
		long lStartTime = System.currentTimeMillis();
		for(int i = 1; i < nums.length; i++){
			int j = i-1;
			if(nums[i] > nums[j]) continue;
			while(j >= 0 && nums[j] > nums[i]){
				swap(nums, j, i);
				j--;
				i--;
			}
		}
		long lEndTime = System.currentTimeMillis();
		System.out.println("Elapsed milliseconds: " + (lEndTime - lStartTime));
		return nums;
	}

	private void swap(int[] nums, int j, int i) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
	
	public static void main(String... args){
//		System.out.println(2 >> 4);
//		System.out.println(Integer.toBinaryString(32));
		int[] nums = generateRandomArray(10);
		InsertionSort test = new InsertionSort();
		SortUtils.print(nums);
//		test.swap(nums, 2, 0);
//		test.print(nums);
		SortUtils.print(test.insertionSortAgain(nums));
//		test.print(test.insertionSort(nums));
	}
	
	public int[] insertionSortAgain(int[] nums){
		for(int i = 1; i < nums.length; i++){
			for(int j = i-1; j >= 0; j--){
				if(nums[j+1] <= nums[j]){
					int temp = nums[j+1];
					nums[j+1] = nums[j];
					nums[j] = temp;
				}
			}
		}
		return nums;
	}
}

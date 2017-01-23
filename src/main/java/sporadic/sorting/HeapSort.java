package sporadic.sorting;

public class HeapSort {
	private static int N;

	public static void sort(int[] nums) {
		heapify(nums);// put the initial array into a heap
		for (int i = N; i > 0; i--) {
			swap(nums, 0, i);// the number at index zero is always the largest
			// in
			// the current UN-sorted array, so we always swap it with the
			// current
			// last one which is i
			N = N - 1;// then we decrement N by 1 so that in the following
			// maxheap() function we only need to work on elements
			// from index zero to N (this N is decremented by 1 each
			// time)
			maxheap(nums, 0);// this is the maintain the heap property after
			// placing its previous largest number into the
			// correct place and get ready for next round of
			// swap: take the max (root) out from the heap.
		}
	}

	//this function is used to maintain the heap, always re-position the number at index i to its correct place
	private static void maxheap(int[] nums, int i) {
		int left = 2*i;
		int right = left+1;
		int max = i;
		if(left <= N && nums[left] > nums[i]){
			max = left;
		}
		if(right <= N && nums[right] > nums[max]){
			max = right;
		}

		if(max != i){
			swap(nums, i, max);
			maxheap(nums, max);
		}
	}

	private static void heapify(int[] nums) {
		N = nums.length-1;
		for(int i = N/2; i >= 0; i--){
			maxheap(nums, i);
		}
	}

	private static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	public static void main(String...strings){
		int[] nums = new int[]{6,5,3,1,8,7,2,4};
		//		int[] nums = new int[]{1,2,3,4,5,6};
		//		int[] nums = new int[]{6,5,4,3,2,1};
//		int[] nums = new int[]{488, 667, 634, 380, 944, 594, 783, 584, 550, 665, 721, 819, 285, 344, 503, 807, 491, 623, 845, 300};
		print("BEFORE printing, nums are: ", nums);
		sort(nums);
		print("AFTER printing, nums are: ", nums);
		System.out.println();
	}

	private static void print(String msg, int[] nums) {
		System.out.println(msg);
		for(int i : nums){
			System.out.print(i + ", ");
		}
		System.out.println();
	}
}

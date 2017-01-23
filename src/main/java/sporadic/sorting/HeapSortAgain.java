package sporadic.sorting;

/**After completing understanding this algorithm, I implemented myself, so this class is called HeapSortAgain.*/
public class HeapSortAgain {
    private static int N;
    public static void sort(int[] nums){
        heapify(nums);
        for(int i = N; i > 0; i--){//i doesn't need to be equal to zero, because we don't need to swap zero-indexed number with itself
            swap(nums, i, 0);//we always swap the first element in the array which means it's at the root of the heap with the number at index i which is the largest index in the UN-sorted array
            N -= 1;//don't remember to decrement N by 1, because we only need to worry about one number fewer each time
            maxheap(nums, 0);//then we always update the heap for the number at index zero
        }
    }
    private static void heapify(int[] nums) {
        N = nums.length-1;
        for(int i = N/2; i >= 0; i--){//here we need i to be equal to zero because we need to do maxheap() on its first element as well
            maxheap(nums, i);
        }
    }
    private static void maxheap(int[] nums, int i) {
        int leftChildIndex = 2*i;
        int rightChildIndex = leftChildIndex+1;
        int max = i;
        if(leftChildIndex <= N && nums[leftChildIndex] > nums[i]){
            max = leftChildIndex;
        }
        if(rightChildIndex <= N && nums[rightChildIndex] > nums[max]){
            max = rightChildIndex;
        }
        if(i != max){
            swap(nums, i, max);
            maxheap(nums, max);
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

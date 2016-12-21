package sporadic.sorting;

public class MergeSort {
	public static void main(String...strings){
		int a[] = new int[] { 9, 3, 8, 6, 2, 1, 5, 4};
		int b[] = new int[a.length];

		System.out.println("List before sorting\n");
		for (int i = 0; i < a.length; i++) System.out.print(a[i] + " ");
		
		MergeSort test = new MergeSort();
		test.sort(a, 0, a.length-1);
		
		System.out.println("\nList after sorting\n");
		for (int i = 0; i < a.length; i++) System.out.print(a[i] + " ");
	}
	
	public void sort(int[] arr, int l, int r){
		if(l < r){
			int m = (l+r)/2;
			sort(arr, l, m);
			sort(arr, m+1, r);
			merge(arr, l, m, r);
		}
	}

	private void merge(int[] arr, int l, int m, int r) {
		//find sizes of two subarrays that are to be merged
		int size1 = m-l+1;
		int size2 = r-m;
		
		//copy the two subarrays into two temp arrays
		int[] tempL = new int[size1];
		int[] tempR = new int[size2];
		for(int i = 0; i < size1; i++){
			tempL[i] = arr[l+i];
		}
		for(int i = 0; i < size2; i++){
			tempR[i] = arr[m+i+1];
		}
		
		//now we merge the two subarrays
		
		//initial indices of the two subarrays
		int i = 0, j = 0;
		
		//initial index of the merged subarray array
		int k = l;
		
		while(i < size1 && j < size2){
			if(tempL[i] <= tempR[j]){
				arr[k] = tempL[i];
				i++;
			} else {
				arr[k] = tempR[j];
				j++;
			}
			k++;
		}
		
		//copy remaining list into arr if any
		while(i < size1){
			arr[k++] = tempL[i++];
		}
		while(j < size2){
			arr[k++] = tempR[j++];
		}
	}
}

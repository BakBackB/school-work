import java.util.Random;
public class Quicksort {
    private static int comparison = 0;
    private static int copies = 0;
    private static int swaps = 0;
    public static void quickSort(int a[], int l, int r){
    	int p = a[(l+r)/2];
        copies++;
    	int i = l, j = r;
    	while (i < j){
    		while (a[i] < p){// shift left pointer right if element is less than pivot
    			i++;
                comparison++;
    		}
    		while (a[j] > p){// shift right pointer left if element is greater than pivot
    			j--;
                comparison++;
    		}
            comparison++;
    		if (i <= j){
    			int temp = a[i];
    			a[i] = a[j];
    			a[j] = temp;
                copies += 3;
                swaps++;
    			i++;
    			j--;
    		}
            comparison++;
    	}
        comparison++;
    	if (i < r){
    		quickSort(a, i, r);// sort right half
    	}
        comparison++;
    	if (l < j){
    		quickSort(a, l, j);// sort left half
    	}
    }
    
    public static void main(String[] args) {
        int[] array = new int[10000];
        Random rand = new Random();
        for(int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(100);
        }
        quickSort(array, 0, array.length - 1);
        System.out.println("Number of operations: " + copies + "/" + comparison + "/" + swaps);
    }
}

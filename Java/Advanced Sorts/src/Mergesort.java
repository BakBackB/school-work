
import java.util.Random;

public class Mergesort {
    private static int comparison = 0;
    private static int copies = 0;
    private static int swaps = 0;

            public static void sort(int[] array, int leftIndex, int rightIndex, int n) {
                if (n <=1 || leftIndex >= rightIndex) {
                    return;
                }
                int midIndex = leftIndex + (rightIndex - leftIndex) / 2;
                sort(array, leftIndex, midIndex, midIndex + 1);
                sort(array, midIndex + 1, rightIndex, rightIndex - midIndex);
                merge(array, leftIndex, midIndex, rightIndex);
                
            }
            private static void merge(int[] array, int left, int mid, int right) {
                int leftSize = mid - left + 1;
                int rightSize = right - mid;
                int[] leftArray = new int[leftSize];
                int[] rightArray = new int[rightSize];
                for (int i = 0; i < leftSize; i++) {
                    leftArray[i] = array[left + i];
                    copies++;
                }//copy data to temp arrays
                for (int j = 0; j < rightSize; j++) {
                    rightArray[j] = array[mid + 1 + j];
                    copies++;
                }
                int i = 0, j = 0;
                int k = left;
                while (i < leftSize && j < rightSize) {
                    comparison ++;
                    if (leftArray[i] <= rightArray[j]) {
                        array[k] = leftArray[i];//corrected from rightArray to leftArray
                        i++;
                        copies++;
                    } else {
                        array[k] = rightArray[j];//corrected from leftArray to rightArray
                        j++;
                        copies++;
                    }//merge the temp arrays
                    k++;
                }
                
                while (i < leftSize) {
                    array[k] = leftArray[i];
                    i++;
                    k++;
                    copies++;
                }//copy remaining elements
                while (j < rightSize) {
                    array[k] = rightArray[j];
                    j++;
                    k++;
                    copies++;
                } 
            }

    public static int getcomparison() {
            return comparison;
    }

    public static void main(String[] args) {
        int[] array = new int[50000];
        Random  rand = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(50000); // Fill array with random integers
        }
        int n = array.length;
        // System.out.println("Unsorted array:");
        // for (int i = 0; i < n; i++) {   
        //     System.out.print(array[i] + " ");
        // } System.out.println();
        sort(array, 0, n - 1, n);
        // System.out.println("Sorted array:");
        // for (int i = 0; i < n; i++) {
        //     System.out.print(array[i] + " ");
        // } System.out.println();
        System.out.println("Number of operations: " + copies + "/" + comparison + "/" + swaps);
    }
}

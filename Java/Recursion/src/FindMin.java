public class FindMin {
    public int findmin(int[] arr, int n) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty");
        }

        if (n == 1) {
            return arr[0];
        }
        return min(arr[n - 1], findmin(arr, n - 1));
    }

    public int min(int a, int b) {
        if(a>b){
            return b;
        }
        return a;
    }
}

import java.util.Random;



public class SortingTrend {
    public static void main(String[] args) {
        int nElems_1 = 10000, nElems_2 = 15000, nElems_3 = 20000,
            nElems_4 = 25000, nElems_5 = 30000, nElems_6 = 35000,
            nElems_7 = 40000, nElems_8 = 45000, nElems_9 = 50000;
        ArrayBub bubbleSortApp = new ArrayBub(nElems_9);
        ArraySel selectSortApp = new ArraySel(nElems_9);
        ArrayIns insertSortApp = new ArrayIns(nElems_9);
        Random rand = new Random();
        for (int i = 0; i < nElems_9; i++) {
            long value = (long) (rand.nextInt(50000));
            bubbleSortApp.insert(value);
            selectSortApp.insert(value);
            insertSortApp.insert(value);
        }
        bubbleSortApp.bubbleSort();
        selectSortApp.selectionSort();
        insertSortApp.insertionSort();
        System.out.println("Number of elements: " + nElems_9);
        System.out.println("------------------------------------------------------------");
        System.out.println("Number of comparison using bubble Sort: " + bubbleSortApp.getnComparison());
        System.out.println("Number of swap using bubble Sort: " + bubbleSortApp.getSwapNumber());
        System.out.println("Number of comparison using selection Sort: " + selectSortApp.getnComparison());
        System.out.println("Number of copy using insertion Sort: " + insertSortApp.getnPass());

    }   

}

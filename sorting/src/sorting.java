public class sorting {

    private static int[] arr = {5, 4, 6, 3, 9, 8, 0, 1, 11, 7};

    private static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) min = j;
            }
            swap(i, min);
        }
    }

    private static void insertionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j-1] > arr[j]) swap(j, j-1);
                else break;
            }
        }
    }

    private static void swap(int i, int j) {
        int swp = arr[i];
        arr[i] = arr[j];
        arr[j] = swp;
    }

    public static void main(String args[]) {
        insertionSort(arr);
//        selectionSort(arr);
        for (int i : arr) System.out.print(i + " ");
    }

}

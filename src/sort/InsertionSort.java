package sort;

/**
 * @author ahscuml
 * @date 2018/7/11
 * @time 16:28
 */
public class InsertionSort {
    private InsertionSort(){}
    public static void main(String[] args) {
        int N = 20000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort("sort.InsertionSort", arr);
    }

    /**
     * 每次比较都要进行交换的排序算法
     * */
    public static void insertionSort(Comparable[] arr) {
        int n = arr.length;
        //i从1开始
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && (arr[j].compareTo(arr[j - 1])) > 0; j--) {
                swap(arr, j, j - 1);
            }
        }
    }

    /**
     * 优化插入排序算法(减少交换次数，通过增加空间复杂度减少时间复杂度)
     * 注意j的数值大小
     * @param arr 数组
     */
    public static void sort(Comparable[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            Comparable e = arr[i];
            int j = i;
            for (; j > 0 && arr[j - 1].compareTo(e) > 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = e;
        }
    }

    /**
     * 交换两个元素
     */
    public static void swap(Object[] arr, int i, int j) {
        Object swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
    }
}

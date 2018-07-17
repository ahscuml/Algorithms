package sort;

/**
 * 选择排序算法
 * 主要实现 选择排序、不同类型的选择排序(包括自定义类)、排序性能的比较
 *
 * @author ahscuml
 * @date 2018.7.11
 * @time
 */
public class SelectionSort {
    private SelectionSort() {
    }

    /**
     * 测试算法
     */
    public static void main(String[] args) {
        int N = 20000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort("sort.SelectionSort", arr);
    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int minindex = i;
            //j = i + 1
            for (int j = i + 1; j < n; j++) {
                if (arr[j].compareTo(arr[minindex]) < 0) {
                    minindex = j;
                }
            }
            swap(arr, i, minindex);
        }
    }

    /**
     * 选择排序的优化
     * 每一轮的查找都找出最大值和最小值
     */
    public static void selectionSort(Comparable[] arr) {
        int n = arr.length;
        int left = 0, right = n - 1;
        while (left < right) {
            int minindex = left, maxindex = right;
            // 在每一轮查找时, 要保证arr[minIndex] <= arr[maxIndex]
            if (arr[minindex].compareTo(arr[maxindex]) > 0) {
                swap(arr, minindex, maxindex);
            }
            //注意i的范围
            for (int i = left + 1; i < right; i++) {
                if (arr[i].compareTo(arr[minindex]) < 0) {
                    minindex = i;
                }
                if (arr[i].compareTo(arr[maxindex]) > 0) {
                    maxindex = i;
                }
            }
            swap(arr, left, minindex);
            swap(arr, right, maxindex);
            left++;
            right--;
        }
    }

    /**
     * 交换数组中的两个元素
     */
    public static void swap(Object[] arr, int i, int j) {
        Object swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
    }
}

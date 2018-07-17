package sort;

import java.util.Arrays;

/**
 * @author ahscuml
 * @date 2018/7/11
 * @time 16:28
 */
public class MergeSort {
    private MergeSort() {
    }

    /**
     * 递归使用归并排序，对arr[l...r]的范围进行排序
     */
    private static void sort(Comparable[] arr, int l, int r) {
        /*if(l>=r){
            return;
        }*/
        // 优化2：在归并到一定程度的时候使用插入排序
        int size = 15;
        if (r - l <= size) {
            insertionSort(arr, l, r);
        }
        // int mid = (r - l) / 2 + l;
        int mid = (l + r) / 2;
        sort(arr, l, mid);
        sort(arr, mid + 1, r);
        // 优化1：如果已经有序就不进行归并了。
        if (arr[mid].compareTo(arr[mid + 1]) > 0) {
            merge(arr, l, mid, r);
        }
    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        sort(arr, 0, n - 1);
    }

    /**
     * 将arr[l...mid]和arr[mid+1...r]两部分进行归并
     */
    private static void merge(Comparable[] arr, int l, int mid, int r) {
        //拷贝一个数组赋值用(牺牲空间复杂度，降低时间复杂度)
        //注意还有个+1
        Comparable[] aux = Arrays.copyOfRange(arr, l, r + 1);
        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {
            // 如果左半部分元素已经全部处理完毕
            if (i > mid) {
                // j - l很有灵性
                arr[k] = aux[j - l];
                j++;
                // 如果右半部分元素已经全部处理完毕
            } else if (j > r) {
                arr[k] = aux[i - l];
                i++;
                // 左半部分所指元素 < 右半部分所指元素
            } else if (aux[i - l].compareTo(aux[j - l]) < 0) {
                arr[k] = aux[i - l];
                i++;
                // 左半部分所指元素 >= 右半部分所指元素
            } else {
                arr[k] = arr[j - l];
                j++;
            }
        }
    }

    /**
     * 优化归并排序使用的插入排序算法
     */
    private static void insertionSort(Comparable[] arr, int l, int r) {
        for (int i = l + 1; i <= r; i++) {
            int j = i;
            Comparable e = arr[j];
            for (; j > l; j--) {
                if (e.compareTo(arr[j - 1]) < 0) {
                    arr[j] = arr[j - 1];
                } else {
                    break;
                }
            }
            arr[j] = e;
        }
    }

    /**
     * 测试用例
     */
    public static void main(String[] args) {
        int N = 20000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort("sort.MergeSort", arr);
    }
}

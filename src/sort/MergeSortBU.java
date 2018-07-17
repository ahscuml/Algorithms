package sort;

import java.util.Arrays;

/**
 * @author ahscuml
 * @date 2018/7/14
 * @time 10:51
 */
public class MergeSortBU {
    private MergeSortBU() {
    }

    /**
     * 自底向上的归并算法
     */
    public static void sort(Comparable[] arr) {

        int n = arr.length;

        // Merge Sort Bottom Up 无优化版本
//        for (int sz = 1; sz < n; sz *= 2)
//            for (int i = 0; i < n - sz; i += sz+sz)
//                // 对 arr[i...i+sz-1] 和 arr[i+sz...i+2*sz-1] 进行归并
//                merge(arr, i, i+sz-1, Math.min(i+sz+sz-1,n-1));

        // Merge Sort Bottom Up 优化
        // 对于小数组, 使用插入排序优化
        for (int i = 0; i < n; i += 16) {
            insertionSort(arr, i, Math.min(i + 15, n - 1));
        }
        for (int sz = 16; sz < n; sz += sz) {
            for (int i = 0; i < n - sz; i += sz + sz)
            // 对于arr[mid] <= arr[mid+1]的情况,不进行merge
            {
                if (arr[i + sz - 1].compareTo(arr[i + sz]) > 0) {
                    merge(arr, i, i + sz - 1, Math.min(i + sz + sz - 1, n - 1));
                }
            }
        }

    }

    /**
     * 归并操作
     */
    private static void merge(Comparable[] arr, int l, int mid, int r) {
        // 这个函数的复制是从左，到右+1
        Comparable[] aux = Arrays.copyOfRange(arr, l, r + 1);
        // 初始化左半部分起点i, 右半部分起点j
        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {
            // 如果左半部分归并完成
            if (i > mid) {
                arr[k] = aux[j - l];
                j++;
            } else if (j > r) {
                arr[k] = aux[i - l];
                i++;
            } else if (aux[i - l].compareTo(aux[j - l]) < 0) {
                arr[k] = aux[i - l];
                i++;
            } else {
                arr[k] = aux[j - l];
                j++;
            }
        }
    }

    /**
     * 优化使用的插入排序
     */
    private static void insertionSort(Comparable[] arr, int l, int r) {
        for (int i = l + 1; i <= r; i++) {
            Comparable e = arr[i];
            int j = i;
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
        SortTestHelper.testSort("sort.MergeSortBU", arr);
        return;
    }
}

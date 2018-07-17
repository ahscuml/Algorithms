package sort;

/**
 * @author ahscuml
 * @date 2018/7/15
 * @time 14:08
 */
public class QuickSort2Ways {
    public QuickSort2Ways() {
    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        sort(arr, 0, n - 1);
    }

    public static void sort(Comparable[] arr, int l, int r) {
        // 优化：小数组采用插入排序, 递归的基准情况
        int size = 16;
        if (r - l <= size - 1) {
            insertionSort(arr, l, r);
            return;
        }
        // 优化：随机选择中间值，避免过多相同元素的情况
        swap(arr, l, (int) Math.random() * (r - l + 1) + l);
        Comparable v = arr[l];
        int i = l + 1, j = r;
        while (true) {
            while (i <= r && arr[i].compareTo(v) < 0) {
                i++;
            }
            while (j >= l + 1 && arr[j].compareTo(v) > 0) {
                j--;
            }
            if (i > j) {
                break;
            }
            swap(arr, i, j);
            i++;
            j--;
        }
        swap(arr, l, j);
        // 递归调用
        sort(arr, l, j - 1);
        sort(arr, j + 1, r);
    }

    private static void swap(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void insertionSort(Comparable[] arr, int l, int r) {
        for (int i = l; i < r; i++) {
            Comparable e = arr[i];
            int j = i;
            for (; j > l; j--) {
                if (arr[j - 1].compareTo(e) > 0) {
                    arr[j] = arr[j - 1];
                } else {
                    break;
                }
            }
            arr[j] = e;
        }
    }
    /*private static void insertionSort(Comparable[] arr, int l, int r) {
        for (int i = 1; i < r - l + 1; i++) {
            int j = i;
            Comparable temp = arr[j];
            for (; j > 0; j--) {
                if (temp.compareTo(arr[j - 1]) < 0) {
                    arr[j] = arr[j - 1];
                } else {
                    break;
                }
            }
            arr[j] = temp;
        }
    }*/

    public static void main(String[] args) {
        // 三路快速排序算法也是一个O(nlogn)复杂度的算法
        // 可以在1秒之内轻松处理100万数量级的数据
        int N = 1000000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort("sort.QuickSort2Ways", arr);
        return;
    }
}

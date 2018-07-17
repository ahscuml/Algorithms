package sort;

/**
 * @author ahscuml
 * @date 2018/7/11
 * @time 16:28
 */
public class QuickSortNearlyOrded {
    private QuickSortNearlyOrded() {
    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        sort(arr, 0, n - 1);
    }

    /**
     * 快速排序算法
     */
    private static void sort(Comparable[] arr, int l, int r) {
        // 优化1：对于小数组采用插入排序
        int size = 16;
        if (r - l <= size - 1) {
            insertionSort(arr, l, r);
        }
        if(l>=r) {
            return;
        }
        int p = partition(arr, l, r);
        sort(arr, l, p - 1);
        sort(arr, p + 1, r);
    }

    /**
     * 对arr[l...r]部分进行partition操作
     * 返回p, 使得arr[l...p-1] < arr[p] ; arr[p+1...r] > arr[p]
     */
    private static int partition(Comparable[] arr, int l, int r) {
        swap(arr, l, (int) Math.random() * (r - l + 1) + l);
        Comparable v = arr[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (v.compareTo(arr[i]) > 0) {
                j++;
                swap(arr, i, j);
            }
        }
        swap(arr, l, j);
        return j;
    }

    /**
     * 交换函数
     */
    private static void swap(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 插入排序算法
     */
    private static void insertionSort(Comparable[] arr, int l, int r) {
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
    }

    /**
     * 测试用例
     */
    public static void main(String[] args) {
        int N = 20000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort("sort.QuickSortNearlyOrded", arr);
    }
}

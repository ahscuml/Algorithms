package sort;

/**
 * @author ahscuml
 * @date 2018/7/11
 * @time 16:28
 */
public class QuickSortRepetion {
    private QuickSortRepetion() {
    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        sort(arr, 0, n - 1);
    }

    /**
     * 快速排序算法
     */
    private static void sort(Comparable[] arr, int l, int r) {
        int p = partition(arr, l, r);
        if (l < r) {
            sort(arr, l, p - 1);
            sort(arr, p + 1, r);
        }
    }

    /**
     * 对arr[l...r]部分进行partition操作
     * 返回p, 使得arr[l...p-1] < arr[p] ; arr[p+1...r] > arr[p]
     */
    private static int partition(Comparable[] arr, int l, int r) {
        swap(arr, l, (int) Math.random() * (r - l + 1) + l);
        Comparable v = arr[l];
        int i = l + 1, j = r;
        while (true) {
            while (i <= r && v.compareTo(arr[i]) > 0) {
                i++;
            }
            while (j <= l + 1 && v.compareTo(arr[j]) < 0) {
                j--;
            }
            if (i > j) {
                break;
            }
            swap(arr, i, j);
            i++;
            j--;
        }
        // 和j交换的原因
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
     * 测试用例
     */
    public static void main(String[] args) {
        int N = 20000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort("sort.QuickSortRepetion", arr);
        return;
    }
}

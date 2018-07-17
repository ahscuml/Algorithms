package sort;

/**
 * @author ahscuml
 * @date 2018/7/14
 * @time 16:43
 */
public class QuickSort3Ways {
    private QuickSort3Ways() {
    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        sort(arr, 0, n - 1);
    }

    /**
     * 3路快速排序算法
     */
    private static void sort(Comparable[] arr, int l, int r) {
        // 优化1：在小分组的时候使用插入排序
        int size = 16;
        if (r - l <= size - 1) {
            insertionSort(arr, l, r);
            return;
        }
        // 优化2：随机选取元素的中间值，避免大量重复元素
        swap(arr, l, (int) Math.random() * (r - l + 1) + l);
        Comparable v = arr[l];
        // gt从r+1开始
        int lt = l, gt = r + 1, i = l + 1;
        while (i < gt) {
            if (v.compareTo(arr[i]) > 0) {
                lt++;
                swap(arr, i, lt);
                i++;
            } else if (v.compareTo(arr[i]) < 0) {
                // 交换了一个没有判断过的元素到i，所以i不用+1！
                gt--;
                swap(arr, i, gt);
            } else {
                // 两个元素相等的情况，不用进行交换
                i++;
            }
        }
        // 将第一个元素交换到=v的位置
        swap(arr, l, lt);
        lt--;
        // 递归调用
        sort(arr, l, lt);
        sort(arr, gt, r);
    }

    /**
     * 插入排序算法
     */
    private static void insertionSort(Comparable[] arr, int l, int r) {
        for (int i = 1; i < r - l + 1; i++) {
            Comparable e = arr[i];
            int j = i;
            for (; j > 0; j--) {
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
        // 三路快速排序算法也是一个O(nlogn)复杂度的算法
        // 可以在1秒之内轻松处理100万数量级的数据
        int N = 1000000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort("sort.QuickSort3Ways", arr);
    }
}

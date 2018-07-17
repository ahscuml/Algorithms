package sort;

/**
 * @author ahscuml
 * @date 2018/7/17
 * @time 10:39
 */
public class BubbleSort {
    private BubbleSort() {
    }

    /**
     * 冒泡排序算法
     */
    public static void sort(Comparable[] arr) {
        int n = arr.length;
        // 对冒泡排序的优化
        int newn;
        do {
            newn = 0;
            for (int i = 1; i < n; i++) {
                if (arr[i - 1].compareTo(arr[i]) > 0) {
                    swap(arr, i - 1, i);
                    // 可以记录最后一次的交换位置,在此之后的元素在下一轮扫描中均不考虑
                    newn = i;
                }
                n = newn;
            }
        } while (newn > 0);
    }

    /**
     * 交换函数
     */
    private static void swap(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

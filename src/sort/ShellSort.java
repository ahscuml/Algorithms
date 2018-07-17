package sort;

/**
 * @author ahscuml
 * @date 2018/7/12
 * @time 10:01
 */
public class ShellSort {
    private ShellSort() {
    }

    /**
     * 希尔排序算法
     */
    public static void sort(Comparable[] arr) {
        int n = arr.length;
        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            // 排序是从h到n
            for (int i = h; i < n; i++) {
                Comparable e = arr[i];
                int j = i;
                for (; j >= h && e.compareTo(arr[j - h]) < 0; j -= h) {
                    arr[j] = arr[j - h];
                }
                arr[j] = e;
            }
            h /= 3;
        }
    }

    /**
     * 测试用例
     */
    public static void main(String[] args) {
        int N = 1000000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort("sort.ShellSort", arr);
    }
}

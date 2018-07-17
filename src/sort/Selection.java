package sort;

/**
 * 利用快速排序方法求数组第K个元素
 *
 * @author ahscuml
 * @date 2018/7/16
 * @time 12:40
 */
public class Selection {
    private Selection() {
    }

    public static Comparable selection(Comparable[] arr, int k) {
        int n = arr.length;
        return selection(arr, 0, n - 1, k - 1);
    }

    /**
     * 快速排序方法
     */
    private static Comparable selection(Comparable[] arr, int l, int r, int k) {
        swap(arr, l, (int) Math.random() * (r - l + 1) + l);
        Comparable v = arr[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (arr[i].compareTo(v) < 0) {
                j++;
                swap(arr, i, j);
            }
        }
        swap(arr, l, j);
        // 避免函数越界
        if (l == r) {
            return arr[l];
        }
        if (j < k) {
            return selection(arr, j + 1, r, k);
        } else if (j > k) {
            return selection(arr, l, j - 1, k);
        } else {
            return arr[j];
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

    // 生成一个完全有序的数组
    public static Integer[] generateOrderedArray(int n) {

        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Integer(i);
        }

        return arr;
    }

    // 将数组arr随机化
    private static void shuffleArray(Object[] arr) {

        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int j = (int) (Math.random() * (n - i)) + i;

            Object t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
        }
    }

    /**
     * 测试函数
     */
    public static void main(String[] args) {
        // 生成一个大小为n, 包含0...n-1这n个元素的随机数组arr
        int N = 4;
        Integer[] arr = generateOrderedArray(N);
        shuffleArray(arr);

        /*// 验证Selection, 对arr数组求第i小元素, 应该为i
        for( int i = 0 ; i < N ; i ++ ){
            assert selection(arr, i) .compareTo(i) == 0;
            System.out.println("test " + i + " complete.");
        }
        System.out.println("Test selection completed.");
        System.out.println();*/

        System.out.println(selection(arr, 4));
    }
}

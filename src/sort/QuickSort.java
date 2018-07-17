package sort;

/**
 * @author ahscuml
 * @date 2018/7/11
 * @time 16:28
 */
public class QuickSort {
    private QuickSort() {
    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        sort(arr, 0, n - 1);
    }
    /**
     * 把partion拟合到排序算法里的写法
     * */
    public static void sort(Comparable[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        Comparable e = arr[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (arr[i].compareTo(e) < 0) {
                j++;
                swap(arr, j, i);
            } else {
                i++;
            }
        }
        swap(arr, l, j);
        j--;
        sort(arr, l, j);
        sort(arr, j + 2, r);
    }
    /**
     * 快速排序算法
     */
    /*private static void sort(Comparable[] arr, int l, int r) {
        if( l >= r ) {
            return;
        }
        int p = partition(arr, l, r);
        sort(arr, l, p-1);
        sort(arr, p+1, r);
    }

    *//**
     * 对arr[l...r]部分进行partition操作
     * 返回p, 使得arr[l...p-1] < arr[p] ; arr[p+1...r] > arr[p]
     *//*
    private static int partition(Comparable[] arr, int l, int r) {
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
    }*/

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
        int N = 1000000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort("sort.QuickSort", arr);
    }
}

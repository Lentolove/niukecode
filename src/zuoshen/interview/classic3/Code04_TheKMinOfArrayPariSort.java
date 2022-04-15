package zuoshen.interview.classic3;

import java.util.Arrays;

/**
 * 左神：给定一个长度为 N 的 arr，一定可以组成 N^2 个数值对
 * 例如：arr = [3,1,2] ->(1,1),(1,2),(1,3),(2,1),(2,2),(2,3),(3,3)，任意两个数都有数值对，包括自己和自己
 * 对数值对进行排序，寻找第k小的数值对
 */
public class Code04_TheKMinOfArrayPariSort {


    public static int[] kthMinPair(int[] arr, int k) {
        int n = arr.length;
        if (k > n * n) return new int[0];//n个数只有n^2个数值对
        //1.从无序数组中，找到第 k 小的数字返回, 当然我们可以直接排序，那样时间复杂度为 NLogN,利用快排可以节约这个时间
        // eg: n = 10, k = 56,每个数组可以组成10个对:[a,b,c,d,e,f,g,h,i,j]:假设排序好后的原数组
        // 那第一个数的索引的位置是 (56 - 1) / 10 = 5,即 arr[5] = f，数值对的第一个一定是 f,第56个数是 fj

        //利用快排的特性找到第k小元素。即如果排序后的那个元素，但是这个过程中并没有执行整个快排过程
        int firstNum = getMinKth(arr, (k - 1) / n);
        //检查重复的元素，比如原数组中有重复的元素，firstNum 只是数值对的第一个元素，需要统计有多少个，以便退出第二个
        int lessFirstNumSize = 0;
        int sameOfFirstNumSize = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] < firstNum) {
                lessFirstNumSize++;
            }
            if (arr[i] == firstNum) {
                sameOfFirstNumSize++;
            }
        }
        //统计出来后，我们就能退出第二个元素了,以firstNum为区间，左边比它小得元素的所有数值对组合有：lessFirstNumSize * n;
        int rest = k - (lessFirstNumSize * n);
        //第二位元素也是同样的道理，找到第 rest 小的元素
        int secondNum = getMinKth(arr, (rest - 1) / sameOfFirstNumSize);
        return new int[]{firstNum,secondNum};
    }


    /**
     * 改写快排，在无序数组arr中找到，找到如果排序的话， arr[index] 的数字是什么
     */
    public static int getMinKth(int[] arr, int index) {
        int l = 0;
        int r = arr.length - 1;
        int posit = 0;//快排基准位
        int[] range = null;
        while (l < r) {
            int position = l + (r - l) / 2;//直接取中间位置吧
            posit = arr[position];
            swap(arr, position, r);
            range = partition(arr, l, r);
            if (index < range[0]) {
                r = range[0] - 1;
            } else if (index > range[1]) {
                l = range[1] + 1;
            } else {
                return posit;
            }
        }
        return arr[l];
    }


    /**
     * 每次找基准位我们是将它放在 arr[r] 位置的
     * 快排 partition 过程，以 arr[r] 为基准位进行划分
     */
    public static int[] partition(int[] arr, int l, int r) {
        int less = l - 1;//小于p的区域
        int more = r;//大于p区域的边界
        while (l < more) { //l表示当前操作的元素，而 arr[r] 为划分值
            if (arr[l] < arr[r]) {
                //当前元素小于划分至，需要放在左边
                swap(arr, ++less, l++);
            } else if (arr[l] > arr[r]) {
                //当前元素大于划分值，要分到右边界，此时交换元素，并且l指针不能动，因为我不确定当前交换的值是否满足条件
                swap(arr, --more, l);
            } else {
                //当前元素为划分值，则只用移动l指针
                l++;
            }
        }
        //将划分值放回右边区域的边界位置: 1221 3333 545454 =>[4,7]
        swap(arr, more, r);
        return new int[]{less + 1, more};
    }


    public static void swap(int[] arr, int i, int j) {
        int a = arr[i];
        arr[i] = arr[j];
        arr[j] = a;
    }

    public static int[] kthMinPair2(int[] arr, int k) {
        int N = arr.length;
        Arrays.sort(arr);
        int fristNum = arr[(k - 1) / N];//第一维的数字
        int lessFristNumSize = 0;
        int fristNumsize = 0;//和firstNum数字相等有几个
        for (int i = 0; i < N && arr[i] <= fristNum; i++) {
            if (arr[i] < fristNum) {
                lessFristNumSize++;
            }
            if (arr[i] == fristNum){
                fristNumsize++;

            }
        }
        int rest = k - lessFristNumSize * N;
        return new int[]{fristNum, arr[(rest - 1) / fristNumsize]};
    }


    public static void main(String[] args) {
//        int[] arr = {3, 2, 4, 3, 2, 1, 1, 5, 6, 8};
        int[] im = {1, 1, 2, 2, 3, 3, 4, 5, 6, 8};
//        int[] im = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//        int[] im2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] ans = kthMinPair(im,46);
        System.out.println(Arrays.toString(ans));

        System.out.println(Arrays.toString(kthMinPair2(im,46)));

    }

}

package zuoshen.topinterview;

/**
 * Author : tsp
 * Time: 2022/3/9 10:18
 * Desc : 给定两个有序的数组，找出合并后的数组的中位数
 */
public class Problem_0004_MedianOfTwoSortedArrays {


    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int size = nums1.length + nums2.length;
        boolean even = (size & 1) == 0;//偶数
        if (nums1.length != 0 && nums2.length != 0) {
            if (even) {
                return (double) (findKthNum(nums1, nums2, size / 2) + findKthNum(nums1, nums2, size / 2 + 1)) / 2D;
            } else {
                return findKthNum(nums1, nums2, size / 2 + 1);
            }
        } else if (nums1.length != 0) {
            if (even) {
                return (double) (nums1[(size - 1) / 2] + nums1[size / 2]) / 2;
            } else {
                return nums1[size / 2];
            }
        } else if (nums2.length != 0) {
            if (even) {
                return (double) (nums2[(size - 1) / 2] + nums2[size / 2]) / 2;
            } else {
                return nums2[size / 2];
            }
        } else {
            return 0;
        }
    }

    /**
     * 查找两个排序数组的上中位数
     * 1.分三种情况讨论：
     * shor = [1,2,3,4,5,6,7,8,9,10] lenS = 10
     * long = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17] lenL = 17
     * 当 k <= 10,则只需要在 shor[0,9] long[0,9]找上中位数就可以
     * 当 lenS < k <= lenL,  10 <  k <= 17
     * 当 k > lenL, k > 17
     */
    public static int findKthNum(int[] A, int[] B, int k) {
        int[] shorts = A.length < B.length ? A : B;
        int[] longs = A.length >= B.length ? A : B;
        int l = longs.length;
        int s = shorts.length;
        //1.  k <= s,直接调用getUpMedian
        if (k <= s) {
            return getUpMedian(shorts, 0, k - 1, longs, 0, k - 1);
        }
        //2. s < k <= l .shorts = [1,2,3,4,5,6,7,8,9,10],longs = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17] k = 15
        // long 排除[1,2,3,4] + [16,17] 剩余 ：[5,6,7,8,9,10,11,12,13,14,15] 长度为 11,而 shorts 长度只有10，手动判断5,
        if (k <= l) {
            if (longs[k - s - 1] >= shorts[s - 1]) {
                return longs[k - s - 1];
            }
            return getUpMedian(shorts, 0, s - 1, longs, k - s, k - 1);
        }
        //k > lenL, k > 17，shorts = [1,2,3,4,5,6,7,8,9,10],longs = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17] k = 23
        // 保留 shorts = [6,7,8,9,10]， longs = [13,14,15,16,17]
        if (shorts[k - l - 1] >= longs[l - 1]) {//TODO 单独判断[6]  ☆☆☆
            return shorts[k - l - 1];
        }
        if (longs[k - s - 1] >= shorts[s - 1]) {//TODO 单独判断[13] ☆☆☆
            return longs[k - s - 1];
        }
        return getUpMedian(shorts, k - l, s - 1, longs, k - s, l - 1);
    }


    /**
     * 给定两个长度相等且有序的数组，找出它们合并后的上中位数
     * 上中位数 [1,2,3,4] 2 为上中位数
     */
    public static int getUpMedian(int[] A, int s1, int e1, int[] B, int s2, int e2) {
        int m1 = 0, m2 = 0;
        while (s1 < e1) {
            m1 = (s1 + e1) / 2;
            m2 = (s2 + e2) / 2;
            // A = [a,b,c,d] B = [e,f,g,h] 上中位数为第 4 个，当A[m1]  = B[m2]
            //1.当A[m1] = B[m2]  b = f 时候，那排序后一定是 [x,x,b,f,x,x,x,x]
            if (A[m1] == B[m2]) {
                return A[m1];
            }
            if ((e1 - s1 + 1) % 2 == 0) {
                //2.数组长度为偶数
                if (A[m1] > B[m2]) {
                    //A = [a,b,c,d] B = [e,f,g,h] => b > f 那么 {c,d}排除了，因为{a,e,f,b}已经占了四个比它们小了，区间不可能在后面了
                    e1 = m1;
                    //对于B来说，f < b, {e,f}都不可能了
                    s2 = m2 + 1;
                } else {//A[m1] < B[m2] 相等的情况已经判断了
                    //A = [a,b,c,d] B = [e,f,g,h] => b < f
                    //那 f 的右边都不可能是排第4了，因为比 f 小的有 {a,b,e},{g,h}要排到4以后去了，缩减区间
                    s1 = m1 + 1;
                    e2 = m2;
                }
            } else {
                //数组长度为奇数讨论,需要手动排除一些边界条件
                if (A[m1] > B[m2]) {
                    //A = [a,b,c,d,e] B = [g,h,i,j,k] 找第五大数， c > i,此时c至少排第六，手动判断i是否排第五
                    if (B[m2] >= A[m1 - 1]) {
                        return B[m2];
                    }
                    //缩减区间 A = [a,b,c,d,e,] B = [g,h,i,j,k]  c > i，A [c,d,e]排除，B[g,h,i]排除
                    e1 = m1 - 1;
                    s2 = m2 + 1;
                } else {// A[m1] < B[m2] 相等的情况已经判断了
                    //A = [a,b,c,d,e] B = [g,h,i,j,k] 找第五大数， c < i,此时 i 至少排第六，手动判断c是否排第五
                    if (A[m1] >= B[m2 - 1]) {
                        return A[m1];
                    }
                    //缩减区间 A = [a,b,c,d,e,] B = [g,h,i,j,k]  c < i，A [a,b,c]排除，B[i,j,k]排除
                    s1 = m1 + 1;
                    e2 = m2 - 1;
                }
            }
        }
        //都找到最后一个数了 s1 = e1 了，取较小的一个
        return Math.min(A[s1], B[s2]);
    }


    public static void main(String[] args) {
        int[] a = {1, 2, 5, 6};
        int[] b = {3, 4};
//        System.out.println(getUpMedian(a, 0, 3, b, 0, 3));

//        int[] a = {1, 3, 5, 9,10,13,15};
//        int[] b = {2, 4, 6, 8,17,19,21,23,24,25,26};
        System.out.println(findKthNum(a, b, 3));
        System.out.println(findKthNum(a, b, 4));

        System.out.println(findMedianSortedArrays(a, b));
    }

}

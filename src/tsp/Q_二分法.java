package tsp;

public class Q_二分法 {


    public static void main(String[] args) {
//        int[] nums = {1, 2, 3, 4, 5, 6, 7, 10, 14};
//        System.out.println(search1(nums, 15));

        int[] nums2 = {1, 2, 3, 4, 5, 6, 7, 10, 14};
//        System.out.println(searchLeft(nums2, 8));
//        System.out.println(searchRight(nums2, 8));

        zuoShenSearchLeft(nums2, 15);
        zuoShenSearchRight(nums2, 8);

    }

    /**
     * 左神：查找排序数组的某个元素的左边界
     */
    public static void zuoShenSearchLeft(int[] arr, int value) {
        int l = 0, r = arr.length - 1;
        //记录最左边的位置，及时更新
        int index = -1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (value <= arr[mid]) {
                index = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        System.out.println(index);
    }

    /**
     * 左神：查找排序数组右边界
     */
    public static void zuoShenSearchRight(int[] arr, int value) {
        int l = 0, r = arr.length - 1;
        //记录最右边的位置，及时更新
        int index = -1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (value >= arr[mid]) {
                index = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        System.out.println(index);
    }

    /***************************二分法查找局部最小值************************************/

    /**
     * 左神：查找局部最小值
     */
    public static int getLessIndex(int[] arr) {
        if (arr == null || arr.length < 1) return -1;
        //1.(0~1)是升序
        if (arr.length == 1 || arr[0] < arr[1]) return arr[0];
        //2.(n-2,n-2)是降序
        int n = arr.length;
        if (arr[n - 2] > arr[n - 1]) return arr[n - 1];
        //3.局部最小值在中间位置，此时用二分查找
        int l = 1, r = n = 2;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] > arr[mid - 1]) {
                //左边升序
                r = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                //右边降序
                l = mid + 1;
            } else {
                return mid;
            }
        }
        return l;
    }


    /**
     * 标准二分法
     * 查找对应元素的索引
     */
    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            //这个 mid 的位置是偏左的，当数组长度为奇数时候，mid为正中间，当数组长度为偶数时候，mid 是靠近左边
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 寻找元素的左边界:
     * 既然要寻找左边界，搜索范围就需要从右边开始，不断往左边收缩，
     * 也就是说即使我们找到了nums[mid] == target, 这个mid的位置也不一定就是最左侧的那个边界，
     * 我们还是要向左侧查找，所以我们在nums[mid]偏大或者nums[mid]就等于目标值的时候，继续收缩右边界，
     * <p>
     * 这里的循环条件是left < right。因为在最后 left 与 right 相邻的时候，mid和left处于相同的位置(前面说过，mid偏左)，
     * 则下一步，无论怎样，left, mid, right都将指向同一个位置，如果此时循环的条件是left <= right，则我们需要再进入一遍循环，此时，如果nums[mid] < target还好说，循环正常终止；
     * 否则，我们会令right = mid，这样并没有改变left,mid,right的位置，将进入死循环。
     */
    public static int searchLeft(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            //mid靠左
            int mid = left + ((right - left) >> 1);
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(left);
        return nums[left] == target ? left : -1;
    }

    /**
     * 寻找右边界
     * 注意：寻找左边界是从右往左收缩。
     * 左边界查找中我们让 mid 是偏左的，在查找右边界的时候，我们让 mid 靠右
     */
    public static int searchRight(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            //选择mid偏右
            int mid = left + ((right - left) >> 1) + 1;
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                //寻找第一个大于等于 target 的元素,因为我们的 mid 是偏右
                left = mid;
            }
        }
        System.out.println(left);
        return nums[right] == target ? right : -1;
    }


}

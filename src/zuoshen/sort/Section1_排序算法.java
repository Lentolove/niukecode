package zuoshen.sort;

import java.util.Arrays;

/**
 * 记录左神算法学习笔记
 */
public class Section1_排序算法 {

    public static void main(String[] args) {
        int[] arr = {3, 2, 5, 4, 1, 6, 2};
        int[] nums = {1, 3, 4, 2, 5};
//        System.out.println(smallSum(nums));
        heapSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    /************************冒泡排序***************************************/
    /**
     * 方法：冒泡排序
     * 复杂度; O(n^2)
     * 稳定性：稳定
     * 说明：遍历过程中把较大的值往后移动。
     */
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        //0 ~ N-1 -> 0 ~ N - 2
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                //左边元素大于右边，将大的交换到右边去
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }
    /************************选择排序***************************************/

    /**
     * 方法：选择排序
     * 算法复杂度：O(N^2)
     * 稳定性：不稳定
     * 不稳定例子:  (7) 2 5 9 3 4 [7] 1.  (7)排到[7]后了
     * 每次查找最小的值放入到数组中
     */
    public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        //每次选择最小的元素放在对应的位置上
        for (int i = 0; i < arr.length - 1; i++) {
            //在 i ~ n - 1位置查找最小值
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                //找到一个更小的元素，更新 minIndex
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            if (minIndex != i) swap(arr, minIndex, i);
        }
    }

    /************************插入排序***************************************/

    /**
     * 方法： 插入排序
     * 算法复杂度： O(N^2)
     * 稳定性：稳定
     * 1.每次保证 0 ~ i是已经排序好的
     * 2.每次从后往前查询插入的位置
     */
    public static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        //0~0 是有序的，想要 0~i是有序的
        for (int i = 1; i < arr.length; i++) {
            // j 是从 i - 1 开始的，就是已经排好序的最后一个位置开始的，然后和即将插入的元素相比，如果最后一个元素比
            //待插入的元素 arr[j + 1] 大,那需要将该元素往数组前移动
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    /************************归并排序***************************************/
    /**
     * 方法：归并排序
     */
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        process(arr, 0, arr.length - 1);
    }

    /**
     * 归并排序的递归过程
     * arr[L...R]范围上，变成有序的
     * 时间复杂度计算公式：master T(N) = a(N/b) + O(N^d)
     * 复杂度计算过程：子问题规模 N / 2，调用 2 次，除了递归之外的时间复杂度为 merge 的时间复杂度，为O(N)。a = 2,b = 2,d = 1满足master第一条 logb^a == d规则
     * T(N) = 2T(N/2) + O(N) => O(N*logN)
     * 时间复杂度为： O(nlogn)
     * 稳定性：稳定
     */
    public static void process(int[] arr, int l, int r) {
        if (l == r) return;
        int mid = l + ((r - l) >> 1);
        //让左边有序
        process(arr, l, mid);
        //让右边有序
        process(arr, mid + 1, r);
        //合并两个有序的数组
        merge(arr, l, mid, r);
    }

    /**
     * 合并两个有序数组
     */
    public static void merge(int[] arr, int l, int m, int r) {
        int[] temp = new int[r - l + 1];
        //定义左边数组指针 p1，右边数组指针p2，temp 数组指针p
        int p = 0, p1 = l, p2 = m + 1;
        while (p1 <= m && p2 <= r) {
            temp[p++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            temp[p++] = arr[p1++];
        }
        while (p2 <= r) {
            temp[p++] = arr[p2++];
        }
        //将temp数组赋值到arr数组中
        if (temp.length >= 0) System.arraycopy(temp, 0, arr, l, temp.length);
    }

    /********************************归并排序-小和问题*********************************************/

    /**
     * 左神：小和问题
     * 题目：在一个数组中，一个数左边比它小的数的总和，叫做小和，所有数的小和累加起来，叫做数组的小和。求数组的小和。例如[1, 3, 4, 2, 5]
     * 1左边比1小的数：没有
     * 3左边比3小的数：1
     * 4左边比4小的数：1、3
     * 2左边比2小的数为：1
     * 5左边比5小的数为：1、3、4、2
     * 所以该数组的小和为：1+1+3+1+1+3+4+2 = 16
     */
    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        return process1(arr, 0, arr.length - 1);
    }

    public static int process1(int[] arr, int l, int r) {
        //只有一个数，不存在右组，小和为0
        if (l == r) return 0;
        int mid = l + ((r - l) >> 1);
        // 左侧merge的小和+右侧merge的小和+整体左右两侧的小和
        return process1(arr, l, mid) + process1(arr, mid + 1, r) + merge1(arr, l, mid, r);
    }

    /**
     * 归并排序的merge过程，在这个过程中记录小和
     */
    public static int merge1(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int p = 0, p1 = l, p2 = m + 1;
        int ans = 0;
        while (p1 <= m && p2 <= r) {
            //当前的数是比右组小的，产生右组当前位置到右组右边界数量个小和，累加到 ans。否则 ans 加 0
            ans += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
            //归并数组,注意这里的区别，左边小拷贝左边，大于等于的选择拷贝右边
            help[p++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[p++] = arr[p1++];
        }
        while (p2 <= r) {
            help[p++] = arr[p2++];
        }
        //将temp数组赋值到arr数组中
        if (help.length >= 0) System.arraycopy(help, 0, arr, l, help.length);
        return ans;
    }


    /***************************************快排前期-荷兰国旗问题**********************************************************/
    /**
     * 左神：荷兰国旗问题
     * 题目：给定一个数组，和一个整数num。请把小于num的数放在数组的左边，等于num的放中间，
     * 大于num的放右边。要求额外空间复杂度为O(1)，时间复杂度为O(N)。[3,5,4,0,4,6,7,2]，num=4。实质是经典荷兰国旗问题。
     */
    public static void sortArray(int[] arr, int target) {
        if (arr == null || arr.length < 2) return;
        int left = 0, right = arr.length - 1, cur = 0;
        while (cur <= right) {
            if (arr[cur] < target) {
                //交换到左区域，并且cur指针和left指针都移动
                swap2(arr, left, cur);
                cur++;
                left++;
            } else if (arr[cur] > target) {
                //此时应该将元素移到右边界。右边界指针移动，cur指针也不动，因为我不知道当前移过来的元素是否满足条件，需要继续判断
                swap2(arr, cur, right);
                right--;
            } else {
                //当前元素与目标元素相等，只用移动cur指针
                cur++;
            }
        }
    }

    /*****************************快速排序*****************************************/

    /**
     * 左神快排：基于荷兰问题上进一步排序
     * 时间复杂度：O(logN)
     * 空间复杂度：O(logN)
     * 稳定性：不稳定
     */
    public static void quickSort(int[] arr) {
        quickSortHelper(arr, 0, arr.length - 1);
    }

    public static void quickSortHelper(int[] arr, int l, int r) {
        if (l < r) {
            //为了避免最坏的情况，我们随机算选择一个数作为划分值，将其放到数组的尾部,可以随机算去
//            swap2(arr,l + (int)(Math.random() * (r - l + 1)),r);
            //通常我们选中间元素就行
            int mid = l + (r - l) / 2;
            swap2(arr, mid, r);
            int[] p = partition(arr, l, r);
            quickSortHelper(arr, l, p[0] - 1);//小于区域
            quickSortHelper(arr, p[1] + 1, r);//大于区域
        }
    }

    /**
     * partition过程，这是一个处理 arr[l,r]的过程
     * 默认以 p = arr[r]做划分，分成三部分  <p  ==p  >p
     * 返回的是中间等于区域(左边界，右边界)，所以这里反回了一个长度为2的数组res
     */
    public static int[] partition(int[] arr, int l, int r) {
        int less = l - 1;   // 小于 p 区域的边界
        int more = r;       // 大于 p 区域的边界
        while (l < more) {   // l表示当前操作的元素，而 arr[r] 为划分值
            if (arr[l] < arr[r]) {
                //当前元素小于划分值，应该放在左测区域,并且移动指针，因为less是前一个区域的边界
                swap2(arr, ++less, l++);
            } else if (arr[l] > arr[r]) {
                //当前元素大于划分值，要分到右边界，此时交换元素，并且l指针不能动，因为我不确定当前交换的值是否满足条件
                swap2(arr, --more, l);
            } else {
                //当前元素为划分值，则只用移动l指针
                l++;
            }
        }
        //将划分值放回右边区域的边界位置
        swap2(arr, more, r);
        //返回中间等于区域的左右边界
        return new int[]{less + 1, more};
    }


    /***************************堆排序***************************************/

    /**
     * 左神：堆排序
     * 时间复杂度为：O(NlogN)
     * 空间复杂度：O(1)
     * 稳定性：不稳定
     * 对于用户给的所有数据，我们先让其构建成为大根堆
     * 对于0到N-1位置的数，我们依次让N-1位置的数和0位置的数（全局最大值）交换,此时全局最大值来到了数组最大位置，堆大小减一，
     * 再heapify调整成大根堆。再用N-2位置的数和调整后的0位置的数交换，相同操作。直至0位置和0位置交换。每次heapify为logN，交换调整了N次
     * <p>
     * 堆排序额为空间复杂度为O(1)，且不存在递归行为
     */
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        //1.将元素数组构建成堆事件复杂度为 0(NlogN)
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        //1.1拓展，如果直接给定一个数组，我们直接执行heapify操作构建大堆,从末尾开始看是否需要heapify
//        for (int i = arr.length - 1; i >= 0;i--){
//            heapify(arr,i,arr.length);
//        }

        //2.将堆顶元素移到末尾，堆的heapSize--；
        int heapSize = arr.length;
        swap2(arr, 0, --heapSize);
        //开始下沉操作
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap2(arr, 0, --heapSize);
        }
    }

    /**
     * 向堆中插入元素，维持大堆结构
     * arr[index]刚来的数，通过比较进行上升
     */
    public static void heapInsert(int[] arr, int index) {
        //与父节点相比，大于就上身，否则停止
        while (arr[index] > arr[(index - 1) / 2]) {
            swap2(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    /**
     * 下沉操作,将当前 index 位置下沉到合适的位置，保持堆结构稳定
     */
    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            //1.找左右孩子中较大的一个
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            //2.将孩子与父节点进行比较，父节点比孩子小，则需要下沉
            largest = arr[index] < arr[largest] ? largest : index;
            //3.如果父节点的索引没变，则说明下层已经满足大堆结构了，直接break
            if (largest == index) break;
            //4.否则父节点与子节点交换
            swap2(arr, index, largest);
            //5.父节点指针移动，移动到孩子节点，接续判断孩子节点的树是否满足大堆结构，周而复始
            index = largest;
            left = index * 2 + 1;
        }
    }


    public static void swap2(int[] arr, int i, int j) {
        int tem = arr[i];
        arr[i] = arr[j];
        arr[j] = tem;
    }


    /**
     * 交换数组中的两个元素
     * 采用异或运算可以交换两个值，但是注意传入的 i != j 才能使用
     * 原理： a = a ^ b
     * b = a ^ b = (a ^ b ^ b) = (0 ^ b) = a;
     * a = a ^ b = (a ^ b ^ a) = (0 ^ b) = b
     */
    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}

package zuoshen.sort;

public class Q1_异或运算 {

    public static void main(String[] args) {
        int[] nums = {2, 2, 1, 3, 2, 3, 2, 1, 1};
        findUnique(nums);
        swap();
        findRightOne();
    }

    /**
     * 题目一：如何不用额外变量就交换两个数
     */
    public static void swap() {
        int a = 3, b = 6;
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a);
        System.out.println(b);
    }

    /**
     * 题目二：一个数组中有一种数出现了奇数次，其他数都出现了偶数次
     * [2,2,1,3,2,3,2,1,1] 数组中存在四个2，两个3，三个1，定义一个常量等于0，
     * 分别对该数组中的数遍历一遍进行异或，最后，该变量等于多少，那么奇数的值就是多少。
     * 因为异或运算满足交换和结合律
     */
    public static void findUnique(int[] arr) {
        int ans = 0;
        for (int a : arr) {
            ans ^= a;
        }
        System.out.println(ans);
    }

    /**
     * 题目三：怎么把一个int类型的数，提取出最右侧的1来
     */
    public static void findRightOne() {
        int a = 10;
        int rightOne = a & (~a + 1);
        System.out.println(rightOne);
    }

    /**
     * 题目四：一个数组中有两种不相等的数出现了奇数次，其他数出现了偶数次，怎么找到并打印这两种数
     */
    public static void findOddNum2(int[] arr) {
        //假设两个不同的数位 ： a,b
        int eor = 0;
        for (int x : arr) {
            eor ^= x;
        }
        //得到 eor = a ^ b 的结果,eor != 0， eor必然有一个位置上是1，我们找到最右边的1的位置为 rightOne ，
        // 在二进制中，a 和 b 在当前 rightOne 位置必不相等，提取出该1
        int rightOne = eor & (~eor + 1);
        //然后以这个 rightOne 将数组进行分类，区分该位置为 1 或者不为 1 两个结合，相同的元素一定被分到同一个结合中，
        // 我们只用一这个rightOne为分界点，就能找到 onlyOne = a/b中的某个数
        //然后将 eor 与 a/b异或，就能得到另一个数字
        int onlyOne = 0;
        for (int x : arr) {
            if ((x & rightOne) == 1) onlyOne ^= x;
        }
        int a = onlyOne;
        int b = onlyOne ^ eor;
    }

}

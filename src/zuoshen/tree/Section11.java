/*
 * @Author: your name
 * @Date: 2021-12-15 14:39:45
 * @LastEditTime: 2021-12-15 15:08:33
 * @LastEditors: Please set LastEditors
 * @Description: 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 * @FilePath: \niukecode\src\zuoshen\tree\Section11.java
 */
package zuoshen.tree;

import java.util.ArrayList;
import java.util.List;

public class Section11 {

    public static void main(String[] args) {
//        List<String> subs = permutationNoRepeat("abcc");
//        for (String s : subs) {
//            System.out.print(s + ",");
//        }

//        int number = number("1233283748");
//        System.out.println(number);
//        int numse2 = dpWay("1233283748");
//        System.out.println(numse2);

        //背包问题
//        int[] weights = {3, 2, 4, 7};
//        int[] values = {5, 6, 3, 19};
//        int bag = 11;
//        System.out.println(maxValue(weights, values, bag));
//        System.out.println(dpWay(weights, values, bag));


        //N皇后问题
        System.out.println(queue(8));

    }

    /*************** 汉诺塔问题************* */

    /**
     * 左神：汉罗塔问题
     * 忘掉左中右，理解为从from移动到to，from 和to都有可能是左中右。
     * 所以定义from，to，other三个杆子。
     * 1、把1到N-1移动到other上。
     * 2、把第N层移动到to上。
     */
    public void hanoi(int n) {
        if (n > 0)
            func(n, "left", "right", "mid");
    }

    // 1~i圆盘，目标是from -> to，other 是另外一个,画图理解
    public void func(int n, String from, String to, String other) {
        // base case
        if (n == 1) {
            System.out.println("Move 1 from " + from + " to " + to);
        } else {
            // 1.将 1-n-1移动到other位置
            func(n - 1, from, other, to);
            System.out.println("Move " + n + " from " + from + " to " + to);
            func(n - 1, other, to, from);
        }
    }

    /**********************字符串子序列问题*************************** */

    /**
     * 左神：打印字符串的所有子序列
     * 输入：abcc
     * 输出：“,c,c,cc,b,bc,bc,bcc,a,ac,ac,acc,ab,abc,abc,abcc”
     * 去重：可借助HashSet去重
     */
    public static List<String> subs(String s) {
        List<String> result = new ArrayList<>();
        process1(s.toCharArray(), 0, result, "");
        return result;
    }

    /**
     * str固定，不变,index此时来到的位置, 要  or 不要,
     * 如果index来到了str中的终止位置，把沿途路径所形成的答案，放入ans中
     * 之前做出的选择，就是沿途路径path
     */
    public static void process1(char[] str, int index, List<String> ans, String path) {
        if (index == str.length) {
            ans.add(path);
            return;
        }
        //1.不需要当前字符
        String no = path;
        process1(str, index + 1, ans, no);
        //2.要当前index字符
        String yes = path + str[index];
        process1(str, index + 1, ans, yes);
    }

    /*************************字符串的全排列问题***********************************/

    /**
     * 左神：打印一个字符串的全排列，不出现重复
     */
    public static List<String> permutationNoRepeat(String str) {
        ArrayList<String> ans = new ArrayList<>();
        process2(str.toCharArray(), 0, ans);
        return ans;
    }

    /**
     * str[0..i-1]已经做好决定的
     * str[i...]都有机会来到i位置
     * i终止位置，str当前的样子，就是一种结果 -> ans
     */
    public static void process2(char[] str, int i, ArrayList<String> ans) {
        if (i == str.length) {
            ans.add(String.valueOf(str));
            return;
        }
        //添加标记为，记录那些元素在当前i位置出现过,默认都是小写
        boolean[] flag = new boolean[26];
        for (int j = i; j < str.length; j++) {
            //如果当前位置i没有出现过x字母，则才交换过来
            if (!flag[str[j] - 'a']) {
                flag[str[j] - 'a'] = true;
                //将j位置交换到i位置
                swap(str, i, j);
                //继续判断第i+1位置
                process2(str, i + 1, ans);
                //回溯，将位置换回来
                swap(str, i, j);
            }
        }
    }

    public static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /********************数字字符转化问题************************/

    /**
     * 左神：数字转字符问题
     * 方法一：递归方法
     * 描述：规定1和A对应，2和B对应，3和C对应…。那么一个数字字符比如”111”就可以转化为：”AAA”,”KA”,”AK”。
     * 给定一个只有数字字符组成的字符串str,返回有多少种转化结果
     * 思路：根据从左往右，我们划分多大，来尝试，比如111，我们尝试一个1，为”A”,剩下两个1去继续尝试。
     * 如果我们两个1尝试，就是”K”。三个1超过26字符，无法尝试。继续如此周而复始
     */
    public static int number(String str) {
        if (str == null || str.length() == 0) return 0;
        return process(str.toCharArray(), 0);
    }

    /**
     * 递归问题：前面[0..i-1]已经转换完成，是固定的，i之前的位置，如何转化已经做过决定了, 不用再关心
     * 我们只关心i之后有多少种转换结果
     */
    public static int process(char[] str, int i) {
        //base case：i已经达到字符串末尾了
        if (i == str.length) return 1;
        //1.情况一：i之前的决策导致当前位置是 0 出现，那么之前决策错误的，返回0，例如10先转换成A，2位置是0字符串无法转换
        if (str[i] == '0') return 0;
        //2.情况二：i位置是1或者2，则可以当前位置单独转换，或者跟后一位一起判断是否满足
        if (str[i] == '1') {
            int res = process(str, i + 1);//i位置单独转换
            if (i + 1 < str.length) {
                //i和i+1位置转换成一个字符
                res += process(str, i + 2);
            }
            return res;
        }
        if (str[i] == '2') {
            int res = process(str, i + 1);
            //判断i和i+1位置是否在26以内
            if (i + 1 < str.length && str[i + 1] >= '0' && str[i + 1] <= '6') {
                res += process(str, i + 2);
            }
            return res;
        }
        return process(str, i + 1);
    }

    /**
     * 以动态规划的方式解决字符串编码问题
     */
    public static int dpWay(String str) {
        if (str == null || str.length() == 0) return 0;
        char[] chars = str.toCharArray();
        int n = str.length();
        //dp[i] 表示第i-n位置的转换次数
        int[] dp = new int[n + 1];
        //从后往前
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            //1.如果当前位置是 ‘0'
            if (chars[i] == '0') {
                dp[i] = 0;
            } else if (chars[i] == '1') {
                dp[i] = dp[i + 1];
                if (i + 1 < n) {
                    dp[i] += dp[i + 2];
                }
            } else if (chars[i] == '2') {
                dp[i] = dp[i + 1];
                if (i + 1 < n && chars[i + 1] >= '0' && chars[i + 1] <= '6') {
                    dp[i] += dp[i + 2];
                }
            } else {
                dp[i] = dp[i + 1];
            }
        }
        return dp[0];
    }
    /**********************背包问题*******************************/

    /**
     * 左神：背包问题
     */
    public static int maxValue(int[] w, int[] v, int bag) {
        return process(w, v, 0, bag);
    }

    public static int process(int[] w, int[] v, int index, int cap) {
        //1.base case1:当前剩余容量cap小于0了,直接返回 -1 ,表示无效方案
        if (cap < 0) return -1;
        //2.base case2:当前货物已经选完了，index到达数组的末尾
        if (index == w.length) return 0;
        //3.选择当前位置拿或者不拿,p1表示不拿
        int p1 = process(w, v, index + 1, cap);
        //4.当前位置拿，并且判断拿了当前位置后，是否是有效方案
        int p2Next = process(w, v, index + 1, cap - w[index]);
        int p2 = -1;
        if (p2Next != -1) {
            //说明当前位置可拿，满足方案
            p2 = v[index] + p2Next;
        }
        return Math.max(p1, p2);
    }

    //动态规划方案
    public static int dpWay(int[] w, int[] v, int bag) {
        int n = w.length;
        //dp[i][j] 表示从第i到n的位置中，背包重量为j的最大价值
        int[][] dp = new int[n + 1][bag + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 1; j <= bag; j++) {
                //1.i-n位置的最大价值可以直接从i+1-n位置继承而来，继续判断当前i位置是否偷
                dp[i][j] = dp[i + 1][j];
                if (j >= w[i]) {
                    //当前位置可以装下,则从中取较大值，dp[i][j - w[i]] + v[i] 表示选择偷当前位置，
                    // 则价值就是减去当前重量的背包容量在i+1~n中能偷到的最大价值
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - w[i]] + v[i]);
                }
            }
        }
        return dp[0][bag];
    }


    /************************玩家抽取纸牌问题*************************************/

    /**
     * 左神：纸牌博弈问题
     * 题目：给定一个整形数组arr，代表数值不同的纸牌排成一条线，玩家A和玩家B依次拿走每张纸牌。规定玩家A先拿，
     * 玩家B后拿，但是每个玩家每次只能拿走最左或最右的纸牌，玩家A和玩家B都绝顶聪明。请赶回最后获胜者的分数
     */
    public static int win(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        // 先手在0~length-1和后手在0~length-1上，谁分数大就是获胜者的分数
        return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
    }

    /**
     * 先手拿，在l-r区间拿一个值，并且保证后手玩家只能拿到较小的值
     */
    public static int f(int[] arr, int l, int r) {
        if (l == r) return arr[l];
        //当前是先手，从l-r中拿一个 + 后手拿剩余的数组中能得到的最大值,双方都是绝顶聪明
        return Math.max(arr[l] + s(arr, l + 1, r), arr[r] + s(arr, l, r - 1));
    }

    /**
     * 后手函数
     */
    public static int s(int[] arr, int l, int r) {
        //base case:当前是后手，如果只剩一个元素，那只能被另一个先手拿走了
        if (l == r) return 0;
        // 当前是后手，好的被绝顶聪明的先手选走了相当于是先手的决策剩下的当前牌，留下最差的min
        return Math.min(f(arr, l + 1, r), f(arr, l, r - 1));
    }


    /***************************N皇后问题********************************************/

    //左神：n皇后问题
    public static int queue(int n) {
        if (n < 1) return 0;
        return process(n, new int[n], 0);
    }

    //record记录的是每一行摆放皇后的位置，record[1] = 2 表示在第2行的第2列摆放
    public static int process(int n, int[] record, int i) {
        //base case:已经走完最后一行了,是一种可行性方案，返回1
        if (i == n) return 1;
        //没有到终止位置，还有皇后要摆,记录有多少种摆法
        int res = 0;
        //判断当前行哪一列可以放皇后
        for (int col = 0; col < n; col++) {
            // 当前i行的皇后，放在j列，会不会和之前(0..i-1)的皇后，不共行共列共斜线，
            // 如果是，认为有效，当前可以摆在j列的位置,如果不是，认为无效
            if (isValid(record, i, col)) {
                //记录当前行防止皇后的位置，然后去下一行
                record[i] = col;
                res += process(n, record, i + 1);
            }
        }
        return res;
    }

    /**
     * record[0..i-1]你需要看，record[i...]不需要看
     * 返回i行皇后，放在了j列，是否有效
     * a行b列的皇后，和c行d列的皇后会不会冲突，coding的条件是不共行
     * 共列的话b==d，共斜线的话|a-c|==|b-d|
     */
    public static boolean isValid(int[] record, int i, int j) {
        //判断0-i行是否出现同行，同列，同斜线问题,i+1行后还没开始放，不用管
        for (int row = 0; row < i; row++) {
            //1.我们是按照行来防止的，所以只用当前j位置和之前0-i-1行是否同列冲突
            //2.是否在同斜线,性质：纵坐标的差和横坐标的差绝对值相等
            if (record[row] == j || Math.abs(row - i) == Math.abs(j - record[row])) {
                return false;
            }
        }
        return true;
    }

}

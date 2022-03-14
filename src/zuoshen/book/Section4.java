package zuoshen.book;

import java.util.Arrays;
import java.util.HashMap;

public class Section4 {

    public static void main(String[] args) {
//        int[] arr = {2, 1, 5, 3, 6, 4, 8, 9, 7};
//        System.out.println(Arrays.toString(getMaxIncrease(arr)));

//        System.out.println(getMaxSameStr("abcde", "bebcd"));
//        System.out.println(getMaxSameStr2("abcde", "bebcd"));

//        System.out.println(getMinChange("ab12cd3","bebcd",5,3,2));
//        System.out.println(getMinChange2("ab12cd3", "bebcd", 5, 3, 2));

        int[] arr3 = {400,4,200,5,3,6,2,1};
        System.out.println(getLongConsecutive(arr3));

    }




    /*********************最长递增子序列******************************/
    /**
     * 左神：最长递增子序列
     */
    public static int[] getMaxIncrease(int[] arr) {
        return getMaxLen(arr, dp1(arr));
    }

    /**
     * 获取递增dp数组，即dp[i] 表示在以i位置结束的最长递增序列的长度是多少
     * arr = [2,1,5,3,6,4,8,9,7]
     * dp  = [1,1,2,2,3,3,4,5,4]
     */
    public static int[] dp1(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                //如果0~j中元素小于i才能构成递增序列
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp;
    }

    /**
     * 根据dp数组，及元素arr数组，从dp中找出最大的元素，然后向左遍历，找到比arr[i]小的元素，并且，dp[i] = dp[j] + 1
     */
    public static int[] getMaxLen(int[] arr, int[] dp) {
        int n = arr.length;
        int maxLen = 0;
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                index = i;
            }
        }
        //构建递增子序列
        int[] ans = new int[maxLen];
        ans[--maxLen] = arr[index];
        //从右向左开始添加序列
        for (int i = index; i >= 0; i--) {
            if (arr[i] < arr[index] && dp[i] + 1 == dp[index]) {
                //满足条件
                ans[--maxLen] = arr[i];
                index = i;
            }
        }
        return ans;
    }


    public static int[] getMaxIncrease2(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        int[] end = new int[n];
        dp[0] = 1;
        end[0] = arr[0];
        int l, r, rightIndex = 0;
        for (int i = 1; i < n; i++) {
            int target = arr[i];
            //1.通过二分在有效区间end[0,rightIndex]数组中找打大于等于target的数字
            l = 0;
            r = rightIndex;
            while (l <= r) {
                int m = (l + r) / 2;
                if (target > end[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            rightIndex = Math.max(rightIndex, l);
            end[l] = arr[i];
            dp[i] = l + 1;
        }
        return end;
    }


    /***********************最长公共子串问题********************************/

    public static String getMaxSameStr(String str1, String str2) {
        if (str1.isEmpty() || str2.isEmpty()) return null;
        //1.定义dp[[i][j] 表示 str1 以 i 结尾和 str2 以 j 结尾的最长公共子序列
        int n1 = str1.length();
        int n2 = str2.length();
        int[][] dp = new int[n1][n2];
        //2.初始化第0列
        for (int i = 0; i < n1; i++) {
            if (str1.charAt(i) == str2.charAt(0)) {
                dp[i][0] = 1;
            }
        }
        //3.初始化第0行
        for (int i = 0; i < n2; i++) {
            if (str1.charAt(0) == str2.charAt(i)) {
                dp[0][i] = 1;
            }
        }
        int maxLen = 0, end = 0;
        for (int i = 1; i < n1; i++) {
            char a = str1.charAt(i);
            for (int j = 1; j < n2; j++) {
                char b = str2.charAt(j);
                if (a == b) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                if (maxLen < dp[i][j]) {
                    maxLen = dp[i][j];
                    end = i;
                }
            }
        }
        return str1.substring(end - maxLen + 1, end + 1);
    }

    /**
     * 左神：通过经典动态规划可知，当前位置只跟左上角位置有关
     * 那么只需要一个变量记录左上角的值，然后斜向遍历。
     * 从(0,col) -》 (0,col-1),(1,col+1)从有上角向左下角斜着遍历
     */
    public static String getMaxSameStr2(String str1, String str2) {
        int n1 = str1.length();
        int n2 = str2.length();
        int len = 0, end = 0, maxLen = 0;
        //定义斜线初始位置
        int row = 0;
        int col = n2 - 1;
        while (row < n1) {
            //从当前位置\遍历
            int i = row;
            int j = col;
            while (i < n1 && j < n2) {
                if (str1.charAt(i) != str2.charAt(j)) {
                    len = 0;
                } else {
                    len++;
                }
                //记录当前最大值
                if (maxLen < len) {
                    maxLen = len;
                    end = i;
                }
                //正45度斜向遍历
                i++;
                j++;
            }
            //这一斜线遍历完了，继续下一条斜线
            if (col > 0) {//斜线的列开始向左移动
                col--;
            } else {
                //列移动到最左后，行向下移动
                row++;
            }
        }
        return str1.substring(end - maxLen + 1, end + 1);
    }


    /*********************最小编辑距离********************************/
//    public static int getMinChange(String str1, String str2, int ic, int dc, int rc) {
//        int n1 = str1.length();
//        int n2 = str2.length();
//        //初始化第0行和，第0列, dp[i][j] 表示由 srt1[i-1] 编辑为 str2[j-1]的最小编辑代价
//        int[][] dp = new int[n1 + 1][n2 + 1];
//        for (int i = 0; i < n1; i++) {
//            //第0列，从上往下，都只能通过删除操作才能将str1转成str2=0
//            dp[i][0] = dc * i;
//        }
//        for (int i = 0; i < n2; i++) {
//            //第0行，从左往右，只能通过插入才能让str1的长度补成str2
//            dp[0][i] = ic * i;
//        }
//        for (int i = 1; i <= n1; i++) {
//            for (int j = 1; j <= n2; j++) {
//                //1.如果当前字符相等
//                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
//                    //当前字符相等
//                    dp[i][j] = dp[i - 1][j - 1];
//                } else {
//                    //当前字符不相等,执行一步替换操作
//                    dp[i][j] = dp[i - 1][j - 1] + rc;
//                }
//                //str1[0,i-1]可以先编辑成str2[0,j-2],然后对str2[0,j-2]插入字符str2[j-1],编辑成str2[0,j-1];
//                //dp[i][j-1] 表示str1[0,i-2]编辑成str2[0.j-2]的最小代价。
//                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + ic);
//                //
//                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + dc);
//            }
//        }
//        return dp[n1][n2];
//    }
    public static int getMinChange2(String str1, String str2, int ic, int dc, int rc) {
        int n1 = str1.length();
        int n2 = str2.length();
        //初始化第0行和，第0列, dp[i][j] 表示由 srt1[i-1] 编辑为 str2[j-1]的最小编辑代价
        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = 0; i < n1; i++) {
            //第0列，从上往下，都只能通过删除操作才能将str1转成str2=0
            dp[i][0] = dc * i;
        }
        for (int i = 0; i < n2; i++) {
            //第0行，从左往右，只能通过插入才能让str1的长度补成str2
            dp[0][i] = ic * i;
        }
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                //1.如果当前字符相等
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    //当前字符相等
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1] + rc, dp[i][j - 1] + ic), dp[i - 1][j] + dc);
                }
            }
        }
        return dp[n1][n2];
    }


    //最长递增子序列
    public static int getLongConsecutive(int[] arr) {
        int max = 1;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int a : arr) {
            if (!map.containsKey(a)) {
                map.put(a, 1);
                //看看是否存在左边 a - 1 的数字存在
                if (map.containsKey(a - 1)) {
                    max = Math.max(max,merge(map,a - 1,a));
                }
                if (map.containsKey(a + 1)) {
                    max = Math.max(max,merge(map,a,a + 1));
                }
            }
        }
        return max;
    }

    public static int merge(HashMap<Integer, Integer> map, int less, int more) {
        int left = less - map.get(less) + 1;
        int right = more + map.get(more) - 1;
        int len = right - left + 1;
        map.put(left,len);
        map.put(right,len);
        return len;
    }

}

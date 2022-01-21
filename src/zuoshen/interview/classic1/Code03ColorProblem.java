package zuoshen.interview.classic1;

/**
 * 左神：染色问题：
 * 题目：给定字符串“RGRGR”，保证左侧都为 R,右侧全是G，输出染色最小的方案: RRRGG
 * 可以全部染成 R，也可以全部染成 G，都是满足条件的
 */
public class Code03ColorProblem {

    /**
     * 最小染色问题
     */
    public static int minPaint(String s) {
        if (s == null || s.length() < 2) return 0;
        char[] str = s.toCharArray();
        int n = s.length();
        //数据预处理，统计从右往左位置i的右边有多少个R
        int[] right = new int[n];
        right[n - 1] = str[n - 1] == 'R' ? 1 : 0;
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] + (str[i] == 'R' ? 1 : 0);
        }
        //开始从左往右遍历每个位置为分界线需要的填色问题
        int res = right[0];//表示将右侧的 R 全部染成 G
        int left = 0;//表示左侧 G 的数量
        for (int i = 0; i < n - 1; i++) {//只需要到 n - 1,因为需要知道右侧 right[i+1] 位置有多少 R
            left += str[i] == 'G' ? 1 : 0;
            res = Math.min(res, left + right[i + 1]);
        }
        //特别注意，全部染成 R 的情况，这样记录左侧的 G 的个数为 left + 最后一个也为 G.
        //eg : RGRRR,当便利到倒数第一个位置的时候：left = 1,right[3] = 1,for循环中算的 res = 2，但是存在全部染成R的情况
        res = Math.min(res, left + (str[n - 1] == 'G' ? 1 : 0));
        return res;
    }

    /**
     * 优化数组 right ，可以省略，提前遍历记录一共有多少个 R
     */
    public static int minPaint2(String s) {
        if (s == null || s.length() < 2) return 0;
        char[] str = s.toCharArray();
        int n = s.length();
        int rAll = 0;
        for (int i = 0; i < n; i++) {
            rAll += str[i] == 'R' ? 1 : 0;
        }
        int ans = rAll;//全部染成G
        int left = 0;
        for (int i = 0; i < n - 1; i++) {
            left += str[i] == 'G' ? 1 : 0;
            //如果是 R 就说明 i 的右侧少一个 rAll
            rAll -= str[i] == 'R' ? 1 : 0;
            ans = Math.min(ans, left + rAll);
        }
        //注意0-n-1左边，右边无
        ans = Math.min(ans, left + (str[n - 1] == 'G' ? 1 : 0));
        return ans;
    }

    public static void main(String[] args) {
        String s = "RGRGR";
        System.out.println(minPaint(s));
        System.out.println(minPaint2(s));
    }

}

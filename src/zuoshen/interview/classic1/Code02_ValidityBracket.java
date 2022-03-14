package zuoshen.interview.classic1;

/**
 * 括号的有效配对是指：
 * 1.任何一个左括号都能找到一个与之配对的右括号
 * 2.任何一个有括号都能找到和气正确配对的左括号
 * 有效： (()) ()() ()() 等
 * 无效：(())( 等
 */
public class Code02_ValidityBracket {

    /**
     * 问题一：如何判断一个括号字符串是否有效
     * 思路：1.可以借助栈来实现，遇到 '(' 入栈，遇到 ')' 就从弹出栈顶括号于其抵消。直到整个过程完成后判断栈是否为null。
     * 2.只需要一个变量 count 来记录 '(' 的个数，遇到 '('就让 count++，遇到 ')'就让 count--。当 count 变为 - 1 时候
     * 表明 ')' 数量多于'('，则不可能有效，或者遍历完后 count 不为 0 也说明 '('括号过多，又不是有效的括号字符。
     */
    public static boolean validBracket(String s) {
        char[] str = s.toCharArray();
        int count = 0;
        for (int i = 0; i < str.length; i++) {
            count += str[i] == '(' ? 1 : -1;
            if (count < 0) return false;
        }
        return count == 0;
    }

    /**
     * 问题二：如果一个字符串是无效的括号，返回至少填几个字符能让其整体变为有效的括号。
     * 思路：1.定义连个变量 count 表示左括号的个数，need 表示需要添加的括号。
     * 2.当遇到 count = -1 时候，表明 ')' 的数量多了一个，那么此时需要补一个 '('，补再哪里不重要
     * 3.总之前面部分需要补一个 '('，于是让 need++;表示需要填充的括号数量。
     * 4.遍历完后可能存在 count的值 > 0，说明有多余的 '(' 括号，需要等量的 ')' 与之匹配。
     */
    public static int needAddBracketCount(String s) {
        char[] str = s.toCharArray();
        int count = 0;
        int need = 0;
        for (char c : str) {
            if (c == '(') {
                count++;
            } else {
                if (count == 0) {
                    //一遇到 ')' ，并且前面都已经匹配好了: ....()())，那此时补一个(
                    //等价于
                    // count--;
                    //need++;
                    //count = 0;
                    need++;
                } else {
                    count--;
                }
            }
        }
        return need + count;
    }

    /**
     * 问题三：返回一个括号字符串中，最长的括号有效子串的长度
     * 思路：1.一旦涉及到子串问题，开头的情况怎么样，结尾的情况怎么样。
     *      2.枚举每个位置结尾的答案，记录其中的最大值。动态规划来解决。
     */
    public static int getMaxLenValidBracket(String s){
        if (s == null || s.length() < 2) return 0;
        char[] str = s.toCharArray();
        int n = s.length();
        int[] dp = new int[n];
        int ans = 0;
        //pre 表示 i - dp[i-1] - 1 那个位置,即 .....pre,dp[i-1],).....
        int pre = 0;
        //1.str[0]的位置不管是啥 dp[0] 都是 0，遇到 '('也dp也都是 0;
        for (int i = 1; i < n; i++){
            //以'('结尾的有效括号字符是不存在的
            if (str[i] == ')'){
                pre = i - dp[i - 1] - 1;
                if (pre >= 0 && str[pre] == '('){
                    // 判断 pre 位置是否 为 '(' ,只有'('才能与 i 位置匹配上
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
                ans = Math.max(ans,dp[i]);
            }
        }
        return ans;
    }

    /**
     * 问题四：括号最多嵌套几层,假定给定的字符括号都是有效的
     *      1.()() 这个是 1 层。 (())() 这个就是俩层
     *      2.那就是连着统计连续出现的 '('的数量
     */
    public static int deepBracket(String s){
        char[] str = s.toCharArray();
        if (!validBracket(s)) return 0;
        int count = 0;
        int max = 0;
        for (char a : str){
            if (a == '('){
                max = Math.max(max,++count);
            }else {
                count--;
            }
        }
        return max;
    }


}

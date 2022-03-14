package zuoshen.jinjie;

import java.util.ArrayList;
import java.util.List;

/**
 * KMP算法
 */
public class Section3_KMP {

    public static void main(String[] args) {
        System.out.println(getIndexOf("abc1234x", "c123"));
    }

    /**
     * 左神：查找 m 在 s 中第一次匹配成功的位置
     */
    public static int getIndexOf(String s, String m) {
        //1.大的过滤条件
        if (s == null || m == null || m.length() < 1 || s.length() < m.length()) return -1;
        char[] str = s.toCharArray();
        char[] match = m.toCharArray();
        //设置 str 和 match 的移动指针,即当前比对到的位置
        int x = 0;
        int y = 0;
        //获取 match 字符数组的前缀和
        int[] next = getNextArray(match);
        while (x < s.length() && y < m.length()) {
            //1.如果当前两个指针位置字符相等
            if (str[x] == match[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {
                //2. - 1 表示 match 中指针已经走到其实位置了，等价于 y == 0,那直接选择str的下一个字符重新来
                x++;
            } else {
                //3. kmp 算法加速的地方，根据 next 的前缀和来优化y指针的位置，暴力方法直接从 match[0] 位置开始匹配
                //而从前缀和 a = next[y],可知 match[0...a-1] 部分的字符是和 match[y - a ,y -1]字符是相等的，这也是前缀和的定义所在
                //又因为 str[x - a,x - 1] 部分字符与 match[y - a ,y -1]字符相等，这是我们刚移动指针一步步匹配到 x,y位置才发现不相等。
                //所以 str[x - a,x - 1] 与 match [0...a-1] 字符相等，直接从  str[x] 位置与 match[a] 位置开始比较，而不是从 match[0]
                //位置开始比较
                y = next[y];
            }
        }
        //跳出 while 循环后，只有y走完了才算匹配成功
        return y == match.length ? x - y : -1;
    }


    /**
     * 获取 match 字符串的前缀和：
     * 前缀和的意思：给定字符串"abcabctx"，0位置规定为-1,1位置为0
     * 1.next[i]表示[0,i-1]的字符的最长前缀的长度：[0,i-1]中，next[4]的前缀为"abca"
     * 2.前缀为"abca",1.长度不能超过自身，前缀与后坠相等, 0-0:a 和3-3:a 所以next[4]位置最长前缀和为1
     * 3.next[5],"abcabc"最长前缀和：0-1:ab，3-4：ab，所以最长前缀和为2
     * match:  "a  b  c  a  b  c  t  x"
     * next:  [-1, 0, 0, 0, 1, 2, 3, 0]
     */
    public static int[] getNextArray(char[] ms) {
        if (ms.length == 1) return new int[]{-1};
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        //因为0,1位置规定前缀和就是-1,0,所以从 i = 2 位置开始
        int i = 2;
        //cn 表示 cn 位置的字符，是当前 和 i - 1位置比较的字符
        int cn = 0;
        while (i < ms.length) {
            if (ms[i - 1] == ms[cn]) {
                //1.当前位置字符相等，说明前缀和匹配成功
                //cn++;
                //next[i] = cn;
                //i++;
                // i= 4时候，ms[0] = ms[3] = a,此时找到i位置的前缀和为1，
                next[i++] = ++cn;
            } else if (cn > 0) {
                //2.cn 指针移动到 cn 位置的最大前缀和位置：[-1, 0, 0, 0, 1, 2, 3, 0]
                // i
                cn = next[cn];
            } else {
                //起始位置，当前前缀和为0,。
                next[i++] = 0;
            }
        }
        return next;
    }


    /*****************完全相等的子树***********************/

    /**
     * 左神：递归常规解法
     * 事件复杂复杂度：O(N*M): N,M表示对应的树的节点数
     */
    public static boolean containTree1(Node big, Node small) {
        if (small == null) return true;
        if (big == null) return false;
        //与big树的根节点是否完全相等
        if (isSameStructure(big, small)) return true;
        return containTree1(big.left, small) || containTree1(big.right, small);
    }

    /**
     * 判断两棵树结构是否完全相等
     */
    public static boolean isSameStructure(Node root1, Node root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;
        if (root1.value != root2.value) return false;
        return isSameStructure(root1.left, root2.left) && isSameStructure(root1.right, root2.right);
    }

    /**
     * 左神：KMP思路求解
     * 思路：
     * 1.通过先序遍历序列化二叉树：本题直接序列化为数组的形式，当然也可以序列化为字符串的形式。每个节点表示一个字符
     * 注意序列化为字符串的时候一定要加节点阶段符标志。
     * 2.然后通过比较两个字符数组书否有包涵关系，就可以用 KMP 算法来求解了
     */
    public static boolean containTree2(Node big, Node small) {
        if (small == null) return true;
        if (big == null) return false;
        List<String> tree1 = preSerial(big);
        List<String> tree2 = preSerial(small);
        String[] strList1 = new String[tree1.size()];
        for (int i = 0; i < tree1.size(); i++) {
            strList1[i] = tree1.get(i);
        }
        String[] strList2 = new String[tree2.size()];
        for (int i = 0; i < tree2.size(); i++) {
            strList2[i] = tree2.get(i);
        }
        return getIndexOf(strList1, strList2) != -1;
    }

    public static int getIndexOf(String[] str1, String[] str2) {
        if (str1 == null || str2 == null || str1.length < 1 || str2.length < 1) return -1;
        //定义str1 和 str2 的匹配指针
        int x = 0;
        int y = 0;
        //获取前缀和
        int[] next = getNextArray(str2);
        while (x < str1.length && y < str2.length) {
            if (isEqual(str1[x], str2[y])) {
                x++;
                y++;
            } else if (next[y] == -1) {
                //移动到首位了
                x++;
            } else {
                y = next[y];
            }
        }
        return y == str2.length ? x - y : -1;
    }

    /**
     * KMS算法中获取字符串的前缀和：
     */
    public static int[] getNextArray(String[] ms) {
        if (ms.length == 1) return new int[]{-1};
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while (i < ms.length) {
            //求前缀和
            if (isEqual(ms[i - 1], ms[cn])) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static boolean isEqual(String str1, String str2) {
        if (str1 == null && str2 == null) return true;
        if (str1 == null || str2 == null) return false;
        return str1.equals(str2);
    }

    /**
     * 先序遍历序列化二叉树
     */
    public static List<String> preSerial(Node head) {
        List<String> ans = new ArrayList<>();
        preDf(head, ans);
        return ans;
    }

    /**
     * 先序遍历：根左右
     */
    public static void preDf(Node head, List<String> ans) {
        if (head == null) {
            ans.add(null);
        } else {
            ans.add(String.valueOf(head.value));
            preDf(head.left, ans);
            preDf(head.right, ans);
        }
    }


    public static class Node {
        int value;
        Node left, right;

        public Node(int value) {
            this.value = value;
        }
    }


}

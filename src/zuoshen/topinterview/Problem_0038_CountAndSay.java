package zuoshen.topinterview;

/**
 * Author : tsp
 * Time: 2022/3/11 16:35
 * Desc :排队喊话问题：
 * 第一次喊：    "1"  (1个1)
 * 第二次喊：    "11" (2个1)
 * 第三次喊：    "21" (1个2,1个1)
 * 第四次喊：    "1211" (1个1,1个2,2个1)
 * 第五次喊：    "111221"
 */
public class Problem_0038_CountAndSay {

    /**
     * 递归解决
     */
    public static String countAndSay(int n) {
        if(n == 0) return "";
        if (n == 1) return "1";
        //前一次的结果，这一次如何读
        char[] str = countAndSay(n - 1).toCharArray();
        StringBuilder sb = new StringBuilder();
        //"1211" (1个1,1个2,2个1)
        int times = 1;
        for (int i = 1; i < str.length; i++) {
            //当前元素与之前元素相同
            if (str[i] == str[i - 1]){//不读，times++
                times++;
            }else {
                //当前元素与之前不相同，读取之前的值
                sb.append(times).append(str[i - 1]);
                times = 1;
            }
        }
        sb.append(times).append(str[str.length - 1]);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(countAndSay(5));
    }
}
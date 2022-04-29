package zuoshen.trainingcamp002.class01;

/**
 * Author : tsp
 * Time: 2022/4/26 16:36
 * Desc :题目：有A,B两只羊，有 N 堆草，规定每次只能吃 4 的次方数量堆草，既 1，4，16，64.....。
 * 判断是先手吃到最后的草堆，还是后手吃到，假定两只羊都绝顶聪明。规定：不能不吃。至少吃 1 。
 * 返回: 先手 or 后手
 */
public class Code02_EatGrass {


    /**
     * 思路：博弈问题，都是绝顶聪明。
     * 1.入口函数为先手，表示先吃。
     */
    public static String winner(int n) {
        //调用该函数的时候当前对象用于表示先手，n = 0了，则表明之前的后手把草吃完了，
        //导致我无草可吃，表示后手赢
        // 0, 1, 2, 3, 4,
        // 后 先 后  先 先
        if (n < 5) {
            return (n == 0 || n == 2) ? "后手" : "先手";
        }
        //当n>=5时候
        int base = 1;//表示当前先手决定吃的草数量
        while (base <= n) {
            //当前一共 n 份草，先手吃了 base 份， n - base 是留给后手的草
            //母过程先手，在子过程中就是后手，即及过程如果返回 后手，则表明当前的决定能赢
            if (winner(n - base).equals("后手")) {
                return "先手";
            }
            //防止 base * 4 越界
            if (base > n / 4) break;
            base *= 4;
        }
        //上面过程都不能让先手赢
        return "后手";
    }


    /**
     * 观察暴力递归：发现，n % 5 == 0 || n % 5 == 2 时候是后手赢，其他情况都是先手赢
     */
    public static String winner2(int n){
        if (n % 5 == 0 || n % 5 == 2) return "后手";
        return "先手";
    }


    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            System.out.println("             " +i + " : " + winner(i));
        }

//        for (int i = 0; i < 30; i++) {
//            if (!winner(i).equals(winner2(i))){
//                System.out.println("出错");
//            }
//        }
//        System.out.println("完成");
    }

}

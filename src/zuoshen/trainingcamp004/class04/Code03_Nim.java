package zuoshen.trainingcamp004.class04;

/**
 * Author : tsp
 * Time: 2022/3/4 15:50
 * Desc :课程十七，Nim 博弈问题：
 * 题目：给定一个数组，为非负整数，每一个值代表该位置上有几个铜板，a 和 b 玩游戏，a 先手，b 后手，轮到某个人时候，
 * 只能在一个位置上拿走任意数量的铜板，但是不能不拿，谁先把铜板拿完谁赢，假设 a 和 b 都决定聪明，请返回获胜者的名称
 */
public class Code03_Nim {

    //保证arr数组是正整数组，当前数组疑惑和不为0则a胜，否则b胜利
    //eg: [2,4,7]
    //       010
    //       100
    //       111
    //异或和: 001  a胜利，a可以在7位置上拿一个铜板，使得结果异或和为0，
    //       010
    //       100
    //       110
    //谁能让对方选择的时候异或和都为0，则谁就是胜利的，此时b面临的异或和结果为0，b无论在那个数上拿铜板都没法使得异或和结果继续为0
    public static void printWinner(int[] arr) {
        int eor = 0;
        for (int a : arr) {
            eor ^= a;
        }
        if (eor != 0){
            System.out.println("先手赢");
        }else {
            System.out.println("后手赢");
        }
    }

}

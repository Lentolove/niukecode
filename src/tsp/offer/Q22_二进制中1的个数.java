package tsp.offer;

/**
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 * 负数的补码 = 反码+1
 * -10 = 100000000....1010  int 是4字节 32bit
 *  反码 = 111111.....0101
 *  补码 = 111111.....0110
 */
public class Q22_二进制中1的个数 {

    public static int NumberOf1(int n) {
        int count = 0;
        while (n!=0){
            count++;
            n=n&(n-1);
        }
        return count;
    }

    //
    public static void main(String[] args) {
        int i = NumberOf1(-1);
        System.out.println(i);
    }
}

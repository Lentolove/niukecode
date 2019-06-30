package tsp.offer;

/**
 * 剪绳子的公式
 *  f（n）= f(i)*f(n-i)
 */

public class Q21_剪绳子 {

    public static int maxProductAfterCutting(int length) {
        if(length<2) return 0;
        if (length==2) return 1;
        if (length==3) return 2;
        int[] p = new int[length+1];
        for (int i = 0; i < 4; i++) {
            p[i]=i;
        }

        for (int i = 4; i <=length; i++) {
            int max = 0;
            /**
             * //j <=i/2  假设i = 5, f（5）= f(1)*f(4) || = f(2)*f(3) 所以后面计算f(3)*f(2)是没有意义的，只需要取到i的一半就可以了
             */
            for (int j = 1; j <=i/2 ; j++) {
                int f = p[j]*p[i-j];
                if (max<f) max = f;
                //把f(i)的最大值存起来
                p[i] = max;
            }
        }
        for (int i = 0; i <= length; i++) {
            System.out.print(p[i]+" ");
        }
        return p[length];
    }

    public static void main(String[] args) {
        int i = maxProductAfterCutting(8);
    }
}

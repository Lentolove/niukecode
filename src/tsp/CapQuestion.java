package tsp;

/**
 * author : tsp
 * Date : 2022/4/22
 * DESC:
 */
public class CapQuestion {


    public static void main(String[] args) {
        System.out.println(isPushOk(3,9,5));
    }



    public static boolean isPushOk(int a,int b,int c){
        int min = Math.min(a,b);
        int max = Math.max(a,b);
        int gcd = gcd(min,max);
        System.out.println(gcd);
        return (c % gcd) == 0;
    }

    public static int gcd(int a,int b){
        return b == 0 ? a : gcd(b, a % b);
    }

}

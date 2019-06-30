package tsp.offer;

public class Q10_斐波那契额数列 {
    public int Fibonacci(int n) {
        int[] result = {0,1};
        if(n<2) return result[n];
        int one = 0;
        int two = 1;
        int Fb = 0;
        for(int i = 2;i<=n;i++){
            Fb = one+two;
            one = two;
            two = Fb;
        }
        return Fb;
    }
}

package tsp.niuke.华为机试108题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Prod {
    int v, p, q;

    Prod(int v, int p, int q) {
        this.v = v;
        this.p = p;
        this.q = q;
    }
}

public class Q17_购物单 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] line1 = bf.readLine().split(" ");
        int n = Integer.parseInt(line1[0]);//总钱
        int m = Integer.parseInt(line1[1]);//希望购买物品个数
        Prod[] prds = new Prod[m];
        for (int i = 0; i < m; i++) {
            String[] lines = bf.readLine().split(" ");
            int v = Integer.parseInt(lines[0]);//价格
            int p = Integer.parseInt(lines[1]) * v;//价值
            int q = Integer.parseInt(lines[2]);//主次物品
            prds[i] = new Prod(v, p, q);
        }
        //动态规划
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {//m个商品
            for (int j = 1; j <= n; j++) {//钱
                if (prds[i - 1].q == 0) {//主件
                    if (prds[i - 1].v <= j) { //能装下
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - prds[i - 1].v] + prds[i - 1].p);
                    }
                } else {//附件
                    if (prds[i - 1].v + prds[prds[i - 1].q].v <= j) {//附件需要带上它的主件
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - prds[i - 1].v] + prds[i - 1].p);
                    }
                }
            }
        }
        System.out.println(dp[m][n]);
    }
}

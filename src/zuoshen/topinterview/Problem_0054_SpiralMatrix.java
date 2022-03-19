package zuoshen.topinterview;

import java.util.ArrayList;
import java.util.List;

/**
 * author : tsp
 * Date : 2022/3/17
 * DESC:给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 *
 * 示例 2：
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *
 *  提示：
 *  m == matrix.length 
 *  n == matrix[i].length 
 *  1 <= m, n <= 10 
 *  -100 <= matrix[i][j] <= 100 
 *  
 *  Related Topics 数组 矩阵 模拟 👍 1017 👎 0
 */
public class Problem_0054_SpiralMatrix {


    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return ans;
        int a1 = 0,a2 = 0,b1 = matrix.length - 1,b2 = matrix[0].length - 1;
        while (a1 <= b1 && a2 <= b2){
            printCic(matrix,a1++,a2++,b1--,b2--,ans);
        }
        return ans;
    }

    public static void printCic(int[][] m,int a1,int a2,int b1,int b2,List<Integer> ans){
        //1.当前只剩一行,因为顺时针，只能是从左往右,行 < 列
        if (a1 == b1){
            while (a2 <= b2){
                ans.add(m[a1][a2]);
                a2++;
            }
        }else if (a2 == b2){
            //2.当前只剩一列，只能是从上往下，行 > 列
            while (a1 <= b1){
                ans.add(m[a1][a2]);
                a1++;
            }
        }else {
            //打印整个圈，分四块
            int curR = a1;
            int curC = a2;
            while (curC != b2){
                ans.add(m[curR][curC++]);
            }
            while (curR != b1){
                ans.add(m[curR++][b2]);
            }
            while (b2 != a2){
                ans.add(m[b1][b2--]);
            }
            while (b1 != a1){
                ans.add(m[b1--][a2]);
            }
        }
    }


    public static void main(String[] args) {
        int[][] arr2  = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] arr  = {{1},{2},{3},{4},{5}};
        List<Integer> list = spiralOrder(arr);
        for (Integer a : list) {
            System.out.println(a);
        }
    }

}

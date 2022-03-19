package zuoshen.topinterview;

import java.util.Arrays;

/**
 * author : tsp
 * Date : 2022/3/15
 * DESC:给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。你必须在 原地 旋转图像，
 * 这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 *
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[7,4,1],[8,5,2],[9,6,3]]
 *
 * 示例 2：
 * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 *
 * 提示：
 *  n == matrix.length == matrix[i].length 
 *  1 <= n <= 20 
 *  -1000 <= matrix[i][j] <= 1000 
 *
 *  Related Topics 数组 数学 矩阵 👍 1209 👎 0
 */
public class Problem_0048_RotateImage {


    /**
     * 思路：将矩阵看做一圈一圈的形式。每一圈的旋转是不影响另一圈的内容的。
     *  [1, 2, 3, 4 ]       [#,%,*,#]
     *  [5, 6, 7, 8 ]  ==>  [*,%,*,%]
     *  [10,11,12,13]       [%,%,*,*]
     *  [14,15,16,17]       [#,*,%,#]
     *  定义左上和右下两个位置点即可确定当前圈：tR,tC,bR,bC:
     *  其中：#，%，* 表示三个类型交换圈，类型圈数量 = bC - tc = 3,如果是5*5圈，每一层内部就是四个圈
     *  同类型的符号转90度交换
     */
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int tR = 0,tC = 0;
        int dR = n - 1,dC = m - 1;
        //一共有几圈，(tR,tC) -> (tR + 1,tC + 1)  (dR,dC) -> (dR - 1,dC - 1)
        while (tR < dR){//当n为奇数的时候，会收缩到中心点，不需要处理
            process(matrix,tR++,tC++,dR--,dC--);
        }
    }


    public static void process(int[][] matrix, int tR, int tC, int dR, int dC){
        //该圈一共分为多少组
        int time = dC - tC;
        int temp = 0;
        for (int i = 0; i < time; i++) {
            temp = matrix[tR][tC + i];
            //第i组，第一个数字位置，应该找到第四个数赋值
            matrix[tR][tC + i] = matrix[dR - i][tC];
            //第四个数位置应该找到第三个数赋值
            matrix[dR - i][tC] = matrix[dR][dC - i];
            //第三个数位置应该找第二个位置赋值给它
            matrix[dR][dC - i] = matrix[tR + i][dC];
            //第二个数找第一个数，temp
            matrix[tR + i][dC] = temp;
        }
    }


    public static void main(String[] args) {
        int[][] arr  = {{1,2,3,4},{12,13,14,5},{11,15,16,6},{10,9,8,7}};
        int[][] arr2  = {{1,2,3},{4,5,6},{7,8,9}};
        process(arr2,0,0,2,2);
        for (int[] ints : arr2) {
            System.out.println(Arrays.toString(ints));
        }
    }


}

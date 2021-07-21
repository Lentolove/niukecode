package tsp.offer;

public class Q2_矩阵中的路径 {

    public static boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        boolean[] flag = new boolean[matrix.length];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (jude(matrix, i, j, rows, cols, flag,str,0 )) {
                    return true;
                }
            }
        }
        return false;
    }

    public  static boolean jude(char[] matrix, int i, int j, int rows, int clos, boolean[] flag, char[] str, int k) {
        //根据i和j的值可以计算带匹配成功的第一元素在数组中的位置
        int index = i * clos + j;
        //递归终止条件
        if (i < 0 || j < 0 || i >=rows || j >=clos || matrix[index] != str[k] || flag[index] == true) return false;
        //若k已经到达str末尾了，说明之前的都已经匹配成功了，直接返回true即可
        if (k == str.length - 1) return true;
        //要走的第一个位置为true，表示已经走过
        flag[index] = true;
        //回溯 每找到一个，就给k的值加一，找不到就还原
        if (jude(matrix, i - 1, j, rows, clos, flag, str, k + 1) ||
                jude(matrix, i + 1, j, rows, clos, flag, str, k + 1) ||
                jude(matrix, i, j - 1, rows, clos, flag, str, k + 1) ||
                jude(matrix, i, j + 1, rows, clos, flag, str, k + 1)) {
            return true;
        }
        flag[index] =false;
        return false;
    }

//    public static boolean hasPath(char[] matrix, int rows, int cols, int x, int y, char[] str, int k, boolean[] flags) {
//        int index = x * cols + y;
//        if (x < 0 || y < 0 || x >= rows || y >= cols || matrix[index] != str[k] || flags[index] == true) return false;
//        if (k == str.length - 1) return true;
//        flags[index] = true;
//        /*
//                boolean hasParh = hasPath(matrix, rows, cols, x - 1, y, str, k + 1, flags) ||
//                hasPath(matrix, rows, cols, x + 1, y, str, k + 1, flags) ||
//                hasPath(matrix, rows, cols, x, y - 1, str, k + 1, flags) ||
//                hasPath(matrix, rows, cols, x, y + 1, str, k + 1, flags);
//        if (hasParh) return true;
//        flags[x * cols + y] = false;
//         */
//
//        //上述错误的原因 找到了下一个元素 还会继续找
//        if (hasPath(matrix, rows, cols, x - 1, y, str, k + 1, flags) ||
//                hasPath(matrix, rows, cols, x + 1, y, str, k + 1, flags) ||
//                hasPath(matrix, rows, cols, x, y - 1, str, k + 1, flags) ||
//                hasPath(matrix, rows, cols, x, y + 1, str, k + 1, flags)){
//            return true;
//        }
//        flags[index] = false;
//        return false;
//    }

    public static void main(String[] args) {
        char[] matrix = {'A', 'B', 'C', 'E', 'S', 'F', 'C', 'S', 'A', 'D', 'E', 'E'};
        char[] str = {'A', 'B', 'C', 'C', 'E', 'D'};
        boolean b = hasPath(matrix, 3, 4, str);
        System.out.println(b);
    }
}

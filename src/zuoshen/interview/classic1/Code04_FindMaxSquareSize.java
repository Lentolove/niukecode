package zuoshen.interview.classic1;

/**
 * 左神：找到矩阵中边框全为 1 的最大正方形
 * 给定一个矩阵，矩阵中只有0,1两种值，返回边框全为 1 的最大正方形的边长长度。
 * 对于N*N的举证有以下结论：
 * 1.内部可有存在 O(N^4) 个矩形，选择一个位置(i,j)的可能性为 O(N^2),选择对角线顶点的可能性 O(N^2)
 * 2.内部可以存在 O(N^3) 个正方向，选择一个位置 (i,j) 的可能性为 O(N^2)，选择变成即为矩阵的边长的子集 O(N)
 */
public class Code04_FindMaxSquareSize {

    /**
     * 需要遍历每个位置点为正方形左上角，边长为 o~ n-1 为边长的所有可能性
     * 1.因为每次都要找(i,j)为正方形左上角，边长为 size是否满足条件，需要去遍历这四条边是否全为1，这个子过程的事件复杂度为O(N^4)
     * 2.大过程事件复杂度不能优化，只能去优化子过程确定正方向边长的可行性出发了。减少子过程遍历的事件复杂度
     * 3.既然每次都要遍历4调边寻找连续为1的个数是否满足边长size的要求
     * 3.1 定义一个 right[i][j] 矩阵表示右边连续为1的个数，down[i][j] 表示从上往下连续为1的个数
     * 3.2 这项 right[i+size][j]，这条边也能快速查询到，down[i][j+size] 也可以。
     * 3.3 所以我们先对 right 和 down 进行预处理
     */
    public static int getMaxSize(int[][] m) {
        int r = m.length;
        int c = m[0].length;
        int[][] right = new int[r][c];
        int[][] down = new int[r][c];
        setBordMap(m,right,down);
        int maxSize = Math.min(r,c);
        for (int size = maxSize; size >=0 ; size--){
            if (hasSizeOfBorder(size,right,down)){
                return size;
            }
        }
        return 0;
    }

    /**
     * 尝试每个可能的边长，然后从 i 位置到 c - size 位置的所有可能
     * 边长为size,(i,j)的可行为范围就是 i->(0,r - size) j -> (0,c - size)
     */
    public static boolean hasSizeOfBorder(int size, int[][] right, int[][] down) {
        for (int i = 0; i < right.length - size + 1; i++) {
            for (int j = 0; j < right[0].length - size + 1; j++) {
                if (right[i][j] >= size && right[i + size - 1][j] >= size &&
                        down[i][j] >= size && down[i][j + size - 1] >= size
                ) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 依赖关系：right 矩阵从右往左，down矩阵从下往上，所以整体是从右下角往左上角
     */
    public static void setBordMap(int[][] m, int[][] right, int[][] down) {
        int r = m.length;
        int c = m[0].length;
        if (m[r - 1][c - 1] == 1) {
            right[r - 1][c - 1] = 1;
            down[r - 1][c - 1] = 1;
        }
        //初始化right矩阵的第 c 列和 down 矩阵的第 c 列,从下往上
        for (int i = r - 2; i >= 0; i--) {
            if (m[i][c - 1] == 1) {
                right[i][c - 1] = 1;
                down[i][c - 1] = down[i + 1][c - 1] + 1;
            }
        }
        for (int i = c - 2; i >= 0; i--) {
            if (m[r - 1][i] == 1) {
                right[r - 1][i] = right[r - 1][i + 1] + 1;
                down[r - 1][i] = 1;
            }
        }
        for (int i = r - 2; i >= 0; i--) {
            for (int j = r - 2; j >= 0; j--) {
                if (m[i][j] == 1) {
                    right[i][j] = right[i][j + 1] + 1;
                    down[i][j] = down[i + 1][j] + 1;
                }
            }
        }
    }

    /**
     * 两种方式初始化，一样
     */
    public static void setBordMap1(int[][] m, int[][] right, int[][] down) {
        int r = m.length;
        int c = m[0].length;
        //初始化right的最右边一列
        for (int i = 0; i < r; i++) {
            if (m[i][c - 1] == 1) {
                right[i][c - 1] = 1;
            }
        }
        //初始化down的最低一层
        for (int i = 0; i < c; i++) {
            if (m[r - 1][i] == 1) {
                down[r - 1][i] = 1;
            }
        }
        for (int i = r - 1; i >= 0; i--) {
            for (int j = c - 1; j >= 0; j--) {
                if (i < r - 1 && j < c - 1 && m[i][j] == 1) {
                    right[i][j] = right[i][j + 1] + 1;
                    down[i][j] = down[i + 1][j] + 1;
                } else {
                    if (m[i][j] == 1) {
                        if (j < c - 1) right[i][j] = right[i][j + 1] + 1;
                        if (i < r - 1) down[i][j] = down[i + 1][j] + 1;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] m = {
                {0, 1, 1, 1, 1},
                {0, 1, 0, 0, 1},
                {0, 1, 0, 0, 1},
                {0, 1, 1, 1, 1},
                {0, 1, 0, 1, 1},
        };

        System.out.println(getMaxSize(m));

//        int[][] right1 = new int[5][5];
//        int[][] down1 = new int[5][5];
//
//        int[][] down2 = new int[5][5];
//        int[][] right2 = new int[5][5];
//
//        setBordMap(m, right1, down1);
//        setBordMap1(m, right2, down2);
//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j < 5; j++) {
//                if (right1[i][j] != right2[i][j] || down1[i][j] != down2[i][j]) {
//                    System.out.println("不一样");
//                    break;
//                }
//            }
//        }
//        System.out.println("一样");

    }
}

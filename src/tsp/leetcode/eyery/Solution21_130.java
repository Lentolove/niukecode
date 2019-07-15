package tsp.leetcode.eyery;

/**
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * 示例:
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 运行你的函数后，矩阵变为：
 * <p>
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 解释:
 * <p>
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/surrounded-regions
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * dfs 递归
 */
class Solution1 {
    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //从边缘 O 开始搜索
                boolean isEdge = i == 0 || j == 0 || i == m - 1 || j == n - 1;
                if (isEdge && board[i][j] == 'O') {
                    dfs(board, i, j, m, n);
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] board, int i, int j, int m, int n) {
        //递归的终止条件 如果是 O 继续
        if (i < 0 || j < 0 || i >= m || j >= n || board[i][j] == '#' || board[i][j] == 'X') {
            // board[i][j] = '#' 说明已经搜索过了
            return;
        }
        board[i][j] = '#';
        dfs(board, i - 1, j, m, n);//上
        dfs(board, i + 1, j, m, n);//下
        dfs(board, i, j - 1, m, n);//左
        dfs(board, i, j + 1, m, n);//右
    }
}

/**
 * dsf 非递归
 * 非递归的方式，我们需要记录每一次遍历过的位置，我们用stack来记录，
 * 因为它先进后出的特点。而位置我们定义一个内部类Pos来标记横坐标和纵坐标。
 * 注意的是，在写非递归的时候，我们每次查看stack顶，但是并不出stack，直到这个位置上
 * 下左右都搜索不到的时候出Stack。
 */
class Solution2 {
    class Pos {
        int i, j;

        Pos(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //从边缘 O 开始搜索
                boolean isEdge = i == 0 || j == 0 || i == m - 1 || j == n - 1;
                if (isEdge && board[i][j] == 'O') {
                    dfs(board, i, j, m, n);
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public void dfs(char[][] board, int i, int j, int m, int n) {
        Stack<Pos> stack = new Stack<>();
        stack.push(new Pos(i, j));
        board[i][j] = '#';
        while (!stack.isEmpty()) {
            //取出当前stack顶，不弹出
            Pos p = stack.peek();
            //上 非边界
            if (p.i - 1 >= 0 && board[p.i - 1][p.j] == 'O') {
                stack.push(new Pos(p.i - 1, p.j));
                board[p.i - 1][p.j] = '#';
                continue;
            }
            //下 非边界
            if (p.i + 1 <= m - 1 && board[p.i + 1][p.j] == 'O') {
                stack.push(new Pos(p.i + 1, p.j));
                board[p.i + 1][p.j] = '#';
                continue;
            }
            //左 非边界
            if (p.j - 1 >= 0 && board[p.i][p.j - 1] == 'O') {
                stack.push(new Pos(p.i, p.j - 1));
                board[p.i][p.j - 1] = '#';
                continue;
            }
            //右 非边界
            if (p.j + 1 <= n - 1 && board[p.i][p.j + 1] == 'O') {
                stack.push(new Pos(p.i, p.j + 1));
                board[p.i][p.j + 1] = '#';
                continue;
            }
            // 如果上下左右都搜索不到,本次搜索结束，弹出stack
            stack.pop();
        }
    }

}


/**
 * bfs 非递归
 * dfs非递归的时候我们用stack来记录状态，而bfs非递归，我们则用队列来记录状态。
 * 和dfs不同的是，dfs中搜索上下左右，只要搜索到一个满足条件，我们就顺着该方向继续搜索，
 * 所以你可以看到dfs代码中，只要满足条件，就入Stack，然后continue本次搜索，进行下一次搜索，
 * 直到搜索到没有满足条件的时候出stack。而bfs中，我们要把上下左右满足条件的都入队，所以搜索的时
 * 候就不能continue。大家可以对比下两者的代码，体会bfs和dfs的差异。
 */
class Solution3 {
    class Pos {
        int i, j;

        Pos(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //从边缘 O 开始搜索
                boolean isEdge = i == 0 || j == 0 || i == m - 1 || j == n - 1;
                if (isEdge && board[i][j] == 'O') {
                    dfs(board, i, j, m, n);
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public void dfs(char[][] board, int i, int j, int m, int n) {
        Queue<Pos> queue = new LinkedList<>();
        queue.add(new Pos(i, j));
        board[i][j] = '#';
        while (!queue.isEmpty()) {
            //取出队列的头节点
            Pos p = queue.poll();
            if (p.i - 1 >= 0 && board[p.i - 1][p.j] == 'O') {
                queue.add(new Pos(p.i - 1, p.j));
                board[p.i - 1][p.j] = '#';
            }
            //下 非边界
            if (p.i + 1 <= m - 1 && board[p.i + 1][p.j] == 'O') {
                queue.add(new Pos(p.i + 1, p.j));
                board[p.i + 1][p.j] = '#';
            }
            //左 非边界
            if (p.j - 1 >= 0 && board[p.i][p.j - 1] == 'O') {
                queue.add(new Pos(p.i, p.j - 1));
                board[p.i][p.j - 1] = '#';
            }
            //右 非边界
            if (p.j + 1 <= n - 1 && board[p.i][p.j + 1] == 'O') {
                queue.add(new Pos(p.i, p.j + 1));
                board[p.i][p.j + 1] = '#';
            }
        }
    }
}

/**
 * 查并集  图的连通性
 * 并查集这种数据结构好像大家不太常用，实际上很有用，我在实际的production code中用过并查集。
 * 并查集常用来解决连通性的问题，即将一个图中连通的部分划分出来。当我们判断图中两个点之间是否存
 * 在路径时，就可以根据判断他们是否在一个连通区域。 而这道题我们其实求解的就是和边界的O在一个连通
 * 区域的的问题。并查集的思想就是，同一个连通区域内的所有点的根节点是同一个。将每个点映射成一个数字。
 * 先假设每个点的根节点就是他们自己，然后我们以此输入连通的点对，然后将其中一个点的根节点赋成另一个节
 * 点的根节点，这样这两个点所在连通区域又相互连通了。 并查集的主要操作有：
 * <p>
 * 1：find(int m):                  这是并查集的基本操作，查找m的根节点
 * 2: isConnected(int m,int n)：    判断m，n两个点是否在一个连通区域。
 * 3.  union(int m,int n):         合并m，n两个点所在的连通区域。
 */
class Solution4 {
    class UnionFind {
        int[] parents;

        public UnionFind(int totalNodes) {
            parents = new int[totalNodes];
            for (int i = 0; i < totalNodes; i++) {
                parents[i] = i;
            }
        }

        // 合并连通区域是通过find来操作的, 即看这两个节点是不是在一个连通区域内.
        void union(int node1, int node2) {
            int root1 = find(node1);
            int root2 = find(node2);
            if (root1 != root2) {
                parents[root2] = root1;
            }
        }

        //查找 node 的根节点
        int find(int node) {
            while (parents[node] != node) {
                //当前节点的父节点 指向父节点的父节点
                //保证一个连通区域的最红的 parents 只有一个
                parents[node] = parents[parents[node]];
                node = parents[node];
            }
            return node;
        }

        boolean isConnected(int node1, int node2) {
            return find(node1) == find(node2);
        }
    }

    /**
     * 我们的思路是把所有边界上的O看做一个连通区域。遇到O就执行并查集合并操作，这样所有的O就会被分成两类
     * 1:和边界上的O在一个连通区域内的。这些O我们保留。
     * 2:不和边界上的O在一个连通区域内的。这些O就是被包围的，替换。
     * 由于并查集我们一般用一维数组来记录，方便查找parants，所以我们将二维坐标用node函数转化为一维坐标。
     */
    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;
        int m = board.length;
        int n = board[0].length;
        //用一个虚拟结点 边界上的 O 的父节点都是这个虚拟节点
        UnionFind uf = new UnionFind(m * n + 1);
        int dummyNode = m * n;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    //遇到 O 进行并查集造作的合并
                    if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                        uf.union(node(i, j, n), dummyNode);
                    } else {
                        // 和上下左右合并成一个联通区域
                        if (i > 0 && board[i - 1][j] == 'O')
                            uf.union(node(i, j, n), node(i - 1, j, n));
                        if (i < m - 1 && board[i + 1][j] == 'O')
                            uf.union(node(i, j, n), node(i + 1, j, n));
                        if (j > 0 && board[i][j - 1] == 'O')
                            uf.union(node(i, j, n), node(i, j - 1, n));
                        if (j < n - 1 && board[i][j + 1] == 'O')
                            uf.union(node(i, j, n), node(i, j + 1, n));
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (uf.isConnected(node(i,j,n),dummyNode)){
                    board[i][j]='O';
                }else {
                    board[i][j] ='X';
                }
            }

        }


    }

    int node(int i, int j, int n) {
        return i * n + j;
    }
}

public class Solution21_130 {

    public static void main(String[] args) {
        Solution4 s = new Solution4();
        char[][] a = {{'X'}};
        s.solve(a);
        System.out.println(a[0][0]);
    }
}

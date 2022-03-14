package zuoshen.interview.classic5;

import java.util.TreeMap;

/**
 * 左神：打印文件夹目录
 * 思想：前缀树
 */
public class Code01_GetFolderTree {

    /**
     * 前缀树的节点
     */
    public static class Node {
        // 上一个节点是通过哪条路，到我的
        public String path;
        // key : node下级的路   value：node在key这条路上对应的节点是什么,用treeMap是保证同级的路径按字典序排序
        public TreeMap<String, Node> nextMap;

        public Node(String path) {
            this.path = path;
            nextMap = new TreeMap<>();
        }
    }

    /**
     * 打印文件夹目录
     */
    public static void print(String[] folderPaths) {
        if (folderPaths == null || folderPaths.length == 0) return;
        //根据所有字符串，把前缀树建立好，头节点为head
        Node head = generateFolderTree(folderPaths);
        //打印前缀树
        printProcess(head, 0);
    }

    /**
     * head节点，当前在level层
     */
    public static void printProcess(Node node, int level) {
        if (level != 0) {
            //空格数为当前层的两倍，第一层根目录 2*(level - 1)
            System.out.println(getSpace(level - 1) + node.path);
        }
        //打印前缀树
        for (Node item : node.nextMap.values()) {
            printProcess(item, level + 1);
        }
    }

    public static String getSpace(int n) {
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            sb.append("    ");
            n--;
        }
        return sb.toString();
    }

    /**
     * 生成前缀树
     */
    public static Node generateFolderTree(String[] folderPaths) {
        //前缀树的根节点，系统根目录
        Node head = new Node("");
        for (String folderPath : folderPaths) {
            //对 path 的 "\" 进行切割
            String[] paths = folderPath.split("\\\\");
            Node cur = head;
            for (String path : paths) {
                if (!cur.nextMap.containsKey(path)) {
                    //不在路径上，将其挂载上去
                    cur.nextMap.put(path, new Node(path));
                }
                cur = cur.nextMap.get(path);
            }
        }
        return head;
    }

}

package tsp.offer;

public class Q15_二维数组中的查找 {
    public static boolean searchArray(int[][] array, int target) {
        if (array==null||array.length<1||array[0].length<1) return false;
        int rows = array.length;
        int clos = array[0].length;
        int x = 0,y = clos-1;
        while (x<rows&&y>=0){
            if (array[x][y]==target){
                return true;
            }else if (array[x][y] <target){
                x++;
            }else {
                y--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] a = {{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
        boolean b = searchArray(a, 16);
        System.out.println(b);
    }
}

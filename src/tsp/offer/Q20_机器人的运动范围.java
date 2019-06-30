package tsp.offer;

public class Q20_机器人的运动范围 {


    public static int movingCount(int threshold, int rows, int cols) {
        boolean[] flags = new boolean[rows*cols];
        return getCount(threshold,rows,cols,0,0,flags);
    }

    public static int getCount(int k,int rows,int cols,int x,int y ,boolean[] flag){
        if (x<0||x>=rows||y<0||y>=cols||flag[x*cols+y]||(getSum(x)+getSum(y))>k) return 0;
        flag[x*cols+y]=true;
        int sum = getCount(k,rows,cols,x-1,y,flag)+
                getCount(k,rows,cols,x+1,y,flag)+
                getCount(k,rows,cols,x,y-1,flag)+
                getCount(k,rows,cols,x,y+1,flag);
        return sum+1;
    }

    public static int getSum(int x){
        int sum = 0;
        do {
            sum +=x%10;
        }while ((x=x/10)>0);
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(movingCount(5,10,10));
    }

}

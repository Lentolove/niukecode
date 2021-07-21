package tsp.offer;

/*
采用阵地攻守的思想：
第一个数字作为第一个士兵，守阵地；times = 1；
遇到相同元素，times++;
遇到不相同元素，即为敌人，同归于尽,times--；
当遇到times为0的情况，又以新的i值作为守阵地的士兵，继续下去，到最后还留在阵地上的士兵，有可能是主元素。
再加一次循环，记录这个士兵的个数看是否大于数组一般即可。
    */
public class Q34_数组中出现次数超过一半 {
    public int MoreThanHalfNum_Solution(int [] array) {
        if (array==null||array.length<0) return 0;
        int result = array[0];
        int times =1;
        for (int i = 1; i < array.length; i++) {
            if(result==array[i]){
                times++;
            }else {
                times--;
            }
            if (times==0){
                result = array[i];
                times=1;
            }
        }
        int cout = 0;
        for (int i = 0; i <array.length ; i++) {
            if (result==array[i]) cout++;
        }
        if (cout>array.length/2) return result;
        return 0;
    }


}

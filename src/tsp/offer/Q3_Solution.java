package tsp.offer;

public class Q3_Solution {

    public boolean duplicate(int numbers[],int length,int [] duplication) {
        if (numbers == null||length<1) return false;
        for (int i = 0; i < length; i++) {
            if (numbers[i]>length) return false;
            while (numbers[i]!=i){
                if (numbers[i]==numbers[numbers[i]]){
                    duplication[0] = numbers[i];
                    return true;
                }
                int temp = numbers[numbers[i]];
                numbers[numbers[i]] = numbers[i];
                numbers[i] = temp;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] a = {2,3,1,0,2,5,3};
        int[] b = new int[7];
        Q3_Solution q = new Q3_Solution();
        q.duplicate(a,7,b);
        System.out.println(b[0]);
    }

}

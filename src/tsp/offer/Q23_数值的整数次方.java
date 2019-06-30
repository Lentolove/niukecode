package tsp.offer;

public class Q23_数值的整数次方 {



    public static double Power(double base, int exponent) {
        if (base==0&&exponent<=0){
            throw new RuntimeException("分母不能为0");
        }
        double result =1;
        if (exponent==0){
            return 1;
        }
        int abs = Math.abs(exponent);
        for (int i = 1; i <= abs; i++) {
            result = base*result;
        }
        return exponent>=0?result:1/result;
    }

//    public static double Power(double base, int exponent) {
//        double res = 1,curr = base;
//        int exp;
//        if (exponent>0){
//            exp = exponent;
//        }else if (exponent<0){
//            if (base==0){
//                throw new RuntimeException("分母不能为0");
//            }
//            exp = - exponent;
//        }else {
//            return 1;
//        }
//        //每又移动一位翻一倍
//        while (exp!=0){
//            if ((exp&1)==1){
//                res *= curr;
//            }
//            curr *=curr;
//            exp>>=1;
//        }
//        return exponent>=0?res:1/res;
//    }

    public static void main(String[] args) {
        double power = Power(2, -1);
        System.out.println(power);
    }
}

package tsp.offer;

/**
 * 将一个字符串转换成一个整数(实现Integer.valueOf(string)的功能，
 * 但是string不符合数字要求时返回0)，要求不能使用字符串转换整数的库函数。
 * 数值为0或者字符串不是一个合法的数值则返回0。
 *
 *   '0'—'9' 对应的ASCII码为48~57
 */
public class Q28_表示数值的字符串 {

    public static int StrToInt(String str) {
        if (str==null||str.length()==0) return 0;
        char[] a = str.toCharArray();
        int flag = 0; //是否为负号
        if (a[0]=='-'){
            flag = 1;
        }
        int sum = 0;
        for (int i = flag; i < a.length; i++) {
            if (a[i]=='+')
                continue;
            if (a[i]<48||a[i]>57){
                return 0;
            }
            sum =sum*10+(a[i]-48);
        }
        return flag==0?sum:(-1*sum);
    }

    public static void main(String[] args) {
        String str = "-456";
        System.out.println(StrToInt(str));
    }
}

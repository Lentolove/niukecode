package tsp.niuke2018;


public class Q20_字符串价值 {
    String str=new String("hello");
    char[]ch={'a','b'};
    public static void main(String[] args) {
        Q20_字符串价值 ex=new Q20_字符串价值();
        ex.change(ex.str,ex.ch);
        System.out.print(ex.str+" and ");
        System.out.print(ex.ch);

        Integer s = new Integer(9);
        Integer t = new Integer(9);
        Long u = new Long(9);
//        System.out.println(s==u);
        System.out.println(s==t);

        System.out.println(s.equals(t));//t
        System.out.println(s.equals(9));//t
        System.out.println(s.equals(new Integer(9)));//t

        String a = "aa";
    }
    public void change(String str,char ch[]){
        str="test ok";
        ch[0]='c';
    }


}

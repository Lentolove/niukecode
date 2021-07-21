package tsp.offer;

public class Q16_替换空格 {

    public static String replaceSpaces(StringBuffer str) {
        if (str==null|| str.length()<0) return null;
        int blank = 0;
        int old_len = str.length();
        for (int i = 0; i < old_len; i++) {
            if (str.charAt(i)==' '){
                blank++;
            }
        }
        int new_len = old_len + blank*2;
        str.setLength(new_len);
        int old_point = old_len-1;
        int new_point = new_len-1;
        while (old_point>=0&&new_point>=0){
            if (str.charAt(old_point)==' '){
                str.setCharAt(new_point,'0');
                str.setCharAt(--new_point,'2');
                str.setCharAt(--new_point,'%');
                new_point--;
            }else {
                str.setCharAt(new_point--,str.charAt(old_point));
            }
            old_point--;
        }
        return str.toString();
    }

    public static void main(String[] args) {
        StringBuffer str = new StringBuffer("We are happy");
//        System.out.println(replaceSpaces(str));
        String s = "";
        System.out.println(s.length());

    }
}

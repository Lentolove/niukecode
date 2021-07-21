package tsp.offer;

/**
 * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.
 * '表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。
 * 例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
 */
public class Q27_正则表达式匹配 {

    public static boolean match(char[] str, char[] pattern) {
        if (str==null||pattern==null) return false;
        return matchTwo(str, 0, str.length, pattern, 0, pattern.length);
    }

    public static boolean matchTwo(char[] str, int p1, int length1, char[] pattern, int p2, int length2) {
        //有效性检验：str和pattern都到了尾  则匹配成功
        if (p1 == length1 && p2 == length2) return true;
        //pattern先到尾，匹配失败
        if (p1!=length1&&p2==length2) return false;
      //之前我将判断放在这里
        // char[] str1={};
        // char[] str2={',','*'}; 测试报错 会出现数组越界
//        //模式第2个不是*，且字符串第1个跟模式第1个匹配，则都后移1位，否则直接返回false
//        if ((str[p1] == pattern[p2] || pattern[p2] == '.')&&p1!=length1) {
//            return matchTwo(str,p1+1,length1,pattern,p2+1,length2);
//        }
        //模式第2个是*，且字符串第1个跟模式第1个匹配,分3种匹配模式；如不匹配，模式后移2位
        if (p2 + 1 < length2 && pattern[p2 + 1] == '*') {
            if ((p1 != length1 && pattern[p2] == str[p1]) || (pattern[p2] == '.' && p1 != length1)) {
                return matchTwo(str, p1,length1, pattern, p2 + 2,length2)//模式后移2，视为x*匹配0个字符
                        || matchTwo(str, p1 + 1,length1,pattern, p2 + 2,length2)//视为模式匹配1个字符
                        || matchTwo(str, p1 + 1,length1, pattern, p2,length2);//*匹配1个，再匹配str中的下一个
            } else {
                return matchTwo(str, p1,length1, pattern, p2 + 2,length2);
            }
        }

        //模式第2个不是*，且字符串第1个跟模式第1个匹配，则都后移1位，否则直接返回false
        if (p1!=length1&&(str[p1] == pattern[p2] || pattern[p2] == '.')) {
            return matchTwo(str,p1+1,length1,pattern,p2+1,length2);
        }

        return false;
    }

    public static void main(String[] args) {
        char[] str ={'a','a','a'};
        char[] pattern = {'a','b','*','a','c','*','a'};

        /**
         * 牛客网错误 的例子
         */
        char[] str1={};
        char[] str2={',','*'};
        char[] str3={','};


        System.out.println(str1.length);
        boolean match = match(str1, str3);
        System.out.println(match);
    }
}


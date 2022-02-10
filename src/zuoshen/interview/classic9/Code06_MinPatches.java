package zuoshen.interview.classic9;

import java.util.Arrays;

/**
 * 左神：给定一个有序的正整数数组 arr 和一个整数 aim,如果自由选择 arr 中的数组想累加得到范围
 * 1~aim范围上的所有的树，返回arr最少还缺几个数
 * eg: arr = [1,2,3,7],aim = 15;缺14这个数，所以返回1；
 *
 */
public class Code06_MinPatches {

    public static int minPatches(int[] arr, int aim) {
        int patches = 0;//缺多少个数字
        int range = 0;//已经完成了1 ~ range的目标
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            //1.当前能否达到 arr[i] - 1的范围
            while (arr[i] - 1 > range) {// arr[i] 1 ~ arr[i]-1
                //2.range没法覆盖到，就添加一个数：range + 1，这样范围 [1,2 * range + 1];
                //eg: 之前range = 4,即能得到 [1,4]所有数，此时添加一个 5 = 4 + 1，
                // 则一定可以得到[1,9]这个区间的所有数字,[1,4] + 5 = [1,9]
                range += range + 1;
                //3.判断是否覆盖到aim目标值
                if (range >= aim) {
                    return patches;
                }
            }
            //4.前面的步骤已经确保了 range 能覆盖到 arr[i] - 1 了，此时来了 arr[i]，则范围
            range += arr[i];//能覆盖的范围变大，每次变大都要判断是否覆盖到了aim值
            if (range >= aim) {
                return patches;
            }
        }
        //5.数组中所有的值加起来都没能达到aim，此时就需要添加新的数字来达到aim了
        while (aim >= range + 1) {
            range += range + 1;
            patches++;
        }
        return patches;
    }


    public static void main(String[] args) {
        int[] test = { 1, 2, 31, 33 };
        int n = 1000;
        System.out.println(minPatches(test, n));

    }


}

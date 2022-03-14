package zuoshen.interview.classic9;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 左神：给定一个正数数组 arr，返回该数组能不能划分成4个部分，并且每个部分的累加和相等，切分位置的数字不要。
 * eg：arr = [3,2,4,1,4,9,5,10,1,2,2] 返回 true，三个切割点下标为 2,5,7
 * 切除的四个子数组为 [3,2],[1,4],[5],[1,2,2],累加和都是 5
 */
public class Code02_Split4Parts {

    /**
     * 思路：
     * 1.一个数组要能切成四部分，需要切三刀，因为切的部分数据不要，那么数组长度至少为7；
     * 2.第一刀切的位置至少是从 [1,n - 5] 范围。
     * 3.可以通过map保存前缀和及其出现的位置(不包含当前位置)，便于查询。
     * 4.假设第一刀切入的位置为 i,则前缀和为 lSum，则第二刀的位置对应的前缀和为 lSum + arr[i] + lSum
     * 在map中查找到对应的 j,如果没找到，直接返回 false，否则继续;
     * 5.找到第二道 j 位置后，第三刀对应的前缀和为： lSum + arr[i] + lSum + arr[j] + lSum
     */
    public static boolean canSplits(int[] arr) {
        if (arr == null || arr.length < 7) return false;
        //1. key 表示累加和 sum，value 表示出现的位置，不包含当前位置
        HashMap<Integer, Integer> map = new HashMap<>();
        //2.数据预处理，初始化累加和
        int sum = arr[0];
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            map.put(sum, i);
            sum += arr[i];
        }
        //3.定义第一刀左侧的累加和
        int lSum = arr[0];
        for (int s1 = 1; s1 < n - 5; s1++) {//s1是第一刀的位置
            int chekS2 = lSum * 2 + arr[s1]; //100 x 100   100*2 + x
            //4.在map中能否找到第二刀
            if (map.containsKey(chekS2)) {
                //5.找到第二刀的位置，查找第三刀
                int s2 = map.get(chekS2);
                int checkS3 = chekS2 + arr[s2] + lSum;
                if (map.containsKey(checkS3)){
                    //第三刀的位置找到了，那就看最后剩余的部分是否等于 lSum
                    int s3 = map.get(checkS3);
                    if (checkS3 + arr[s3] + lSum == sum){ //所有的总和等于sum,则找到了
                        return true;
                    }
                }
            }
            lSum += arr[s1];
        }
        return false;
    }




    public static boolean canSplits1(int[] arr) {
        if (arr == null || arr.length < 7) {
            return false;
        }
        HashSet<String> set = new HashSet<String>();
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        int leftSum = arr[0];
        for (int i = 1; i < arr.length - 1; i++) {
            set.add(leftSum + "_" + (sum - leftSum - arr[i]));
            leftSum += arr[i];
        }
        int l = 1;
        int lsum = arr[0];
        int r = arr.length - 2;
        int rsum = arr[arr.length - 1];
        while (l < r - 3) {
            if (lsum == rsum) {
                String lkey = String.valueOf(lsum * 2 + arr[l]);
                String rkey = String.valueOf(rsum * 2 + arr[r]);
                if (set.contains(lkey + "_" + rkey)) {
                    return true;
                }
                lsum += arr[l++];
            } else if (lsum < rsum) {
                lsum += arr[l++];
            } else {
                rsum += arr[r--];
            }
        }
        return false;
    }

    public static int[] generateRondomArray() {
        int[] res = new int[(int) (Math.random() * 10) + 7];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int) (Math.random() * 10) + 1;
        }
        return res;
    }

    public static void main(String[] args) {
        int testTime = 3000000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRondomArray();
            if (canSplits1(arr) ^ canSplits(arr)) {
                System.out.println("Error");
            }
        }
    }

}

package zuoshen.interview.classic4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * 左神：获取最大的薪水报酬
 * 题目：每种工作难度和报酬：规定如下：
 * class Job{
 * public int money;//该工作的报酬
 * public int hard;//该动作的难度
 * }
 * 给定一个Job类型的数组 jobArray,表示所有岗位，每个岗位都可以提供任意份的工作，选工作的标准是在不超过自身能力值的情况下，
 * 选择报酬最高的岗位
 * 给定一个 int 类型的数组 arr， 表示所有人的能力，返回int类型的数组，表示每个人按照标准选工作后所获得最高报酬
 */
public class Code01_ChooseWork {

    public static class Job {
        public int money;
        public int hard;

        public Job(int money, int hard) {
            this.money = money;
            this.hard = hard;
        }
    }

    /**
     * 1.按照难度从小到大进行排序，难度一样的，报酬从大到小排序
     * 2.理论推导过滤：
     * 2.1 难度一样，过滤掉报酬低的，只保留第一个最高的报酬，因为难度一样，我肯定选择最高的报酬的工作
     * 2.2 根据报酬进行排序，难度是升序，报酬也必须升序，因为我不可能选择难度更高，报酬却低的工作
     * 2.3 经过上述两个步骤后，难度升序，报酬升序，单调递增，用有序表
     */
    public static int[] maxSalary(Job[] jobArray, int[] ability) {
        if (jobArray == null || jobArray.length == 0 || ability == null || ability.length == 0) return new int[0];
        Arrays.sort(jobArray, new JobCompare());
        //1.用TreeMap进行过滤，收集最终的合理工作。从中选择,因为是treemap，find 时间复杂度为 logn
        TreeMap<Integer, Integer> map = new TreeMap<>();
        //2.难度相同的，只选择第一个，难度不同，报酬不能降序，所以记录前一个工作，保证 money 也是有序的
        Job pre = jobArray[0];
        map.put(pre.hard, pre.money);
        for (Job job : jobArray) {
            //难度相同的只保留第一个最大的，同时如果报酬如果呈现下降趋势直接过滤掉
            if (pre.hard != job.hard && pre.money < job.money) {
                map.put(job.hard, job.money);
            }
        }
        //3.过滤完后，开始查找 ability[i] 》= map 中的hard值的工作
        int n = ability.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            //treeMap 提供上边界
            if (map.floorKey(ability[i]) != null){
                //说明有符合条件的工作
                ans[i] = map.get(map.floorKey(ability[i]));
            }
        }
        return ans;
    }

    public static class JobCompare implements Comparator<Job> {

        @Override
        public int compare(Job o1, Job o2) {
            return o1.hard != o2.hard ? o1.hard - o2.hard : o2.money - o1.money;
        }
    }

    public static void main(String[] args) {
        Job[] jobs = new Job[]{new Job(1,2),new Job(1,3),new Job(2,2),new Job(2,4),new Job(3,5),new Job(5,3)};
        int[] ables = new int[]{1,1,2,3,4,5,6,7,8};
        int[] res = maxSalary(jobs, ables);
        System.out.println(Arrays.toString(res));
    }

}

package tsp.hot100;

import java.net.Socket;
import java.util.PriorityQueue;

public class Q312_戳气球 {

    public static void main(String[] args) {
        int[] nums = {3, 1, 5, 8};
//        int[] nums1 = {9,76,64};
        Solution solution = new Solution();
        System.out.println(solution.maxCoins(nums));
    }


    static class Solution {

        ListNode head, tail;

        //借助大堆实现升序排列。将数组转成双向链表
        public int maxCoins(int[] nums) {
            PriorityQueue<ListNode> queue = new PriorityQueue<>((a, b) -> a.val - b.val);
            buildList(nums, queue);
            int ans = 0;
            while (!queue.isEmpty()) {
                ListNode first = queue.poll();
                //判断是否是头节点或者尾节点
                if (first == head || first == tail) {
                    //查找第二小的节点
                    if (!queue.isEmpty()) {
                        ListNode second = queue.poll();
                        if (second == head || second == tail) {
                            //又是边界节点,此时查看第三个节点，如果存在的话
                            if(!queue.isEmpty()){
                                ListNode three = queue.poll();
                                ans += getAns(three);
                                queue.add(first);
                                queue.add(second);
                            }else {
                                ans += first.val * (first.left == null ? 1 : first.left.val) * (first.right == null ? 1 : first.right.val);
                                queue.add(second);
                                if (first.left == null) {
                                    head = first;
                                }
                                if (first.right == null) {
                                    tail = first;
                                }
                            }
                        } else {
                            //非边界节点，则移除 second 节点
                            ans += getAns(second);
                            queue.add(first);
                        }
                    } else {
                        ans += first.val;
                        break;
                    }
                } else {
                    ans += getAns(first);
                }
            }
            return ans;
        }

        public int getAns(ListNode node) {
            int ans = node.val * node.left.val * node.right.val;
            //断链
            ListNode right = node.right;
            right.left = node.left;
            node.left.right = right;
            node.left = null;
            node.right = null;
            return ans;
        }

        public void buildList(int[] nums, PriorityQueue<ListNode> queue) {
            head = new ListNode(nums[0]);
            queue.add(head);
            ListNode point = head;
            for (int i = 1; i < nums.length; i++) {
                ListNode item = new ListNode(nums[i]);
                point.right = item;
                item.left = point;
                queue.add(item);
                point = item;
            }
            tail = point;
        }

        static class ListNode {
            int val;
            ListNode left;
            ListNode right;

            ListNode(int val) {
                this.val = val;
            }
        }

    }

}

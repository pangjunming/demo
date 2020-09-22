package com.example.server;

/**
 * @author pangjunming
 * @Description:
 * @date 2020/7/26
 */
public class DpTest {
    public static void main(String[] args) {
        int[] aa = new int[]{10,9,2,5,3,7,101,18};
        System.out.println(DpTest.lengthOfLIS(aa));
    }

    public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int result = 1;
        for(int i = 1; i < nums.length; i++){
            int temp = 1;
            for(int j = 0; j < i;j++){
                if(nums[j] < nums[i]){
                    temp = Math.max(temp, dp[j] + 1);
                }
            }
            dp[i] = temp;
            result = Math.max(temp,result);


        }
        return result;
    }
}

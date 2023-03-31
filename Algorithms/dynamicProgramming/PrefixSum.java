package dynamicProgramming;

public class PrefixSum {
    //dp[i] = sum from index 0 to i, including i;
    // base case: dp[0] = array[0];
    // induction rule: dp[i] = dp[i-1] + array[i];
    // return dp
    public static void main(String[] args) {
        int[] array = {1,3,5,6,10,8,-1,20};
        int[] prefixsumArray = findPrefixSum(array);

        for (int i : prefixsumArray) {
            System.out.println(i);
        }

    }

    public static int[] findPrefixSum(int[] array){
        int N = array.length;
        int[] dp = new int[N];
        dp[0] = array[0];

        for(int i =1; i< N; i++){
            dp[i] = dp[i-1] + array[i];
        }
        return dp;
    }

}

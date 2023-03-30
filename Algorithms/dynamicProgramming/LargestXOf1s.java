package dynamicProgramming;

public class LargestXOf1s {
    // dp[i]: the arm length of largest X
    // dp's size = array's size
    // pre-processing:
    // build up four matrix -> leftdn, rightdn, leftup, rightup
    // dp[i][j] is the local min of (leftdn, rightdn, leftup, rightup)
    // return the global max of dp[i][j]
    public static void main(String[] args) {
        int[][] matrix = {{1,0,0,0,0,0},{0,1,0,0,0,1},{0,0,1,0,1,0},{0,0,0,1,0,0},{0,0,1,0,1,0},{0,1,0,0,0,1}};
        System.out.println(largest(matrix));
    }

    public static int largest(int[][] matrix) {
        int M = matrix.length;
        int N = matrix[0]. length;
        // corner case
//        if(matrix ==null || M == 0 || N == 0 ){
//            return 0;
//        }
        //build up four matrix -> leftdn, rightdn, leftup, rightup
        int[][] dp = new int[M][N];
        int[][] leftup = new int[M][N];
        int[][] leftdn = new int[M][N];
        int[][] rightup = new int[M][N];
        int[][] rightdn = new int[M][N];

        // leftup && rightup
        for (int i = M - 1; i >= 0; i--){
            for (int j = N - 1; j >= 0; j--){
                if (matrix[i][j] == 1){
                    leftup[i][j] = getNumber(leftup, i + 1, j + 1, M, N) + 1;
                    rightup[i][j] = getNumber(rightup, i + 1, j - 1, M, N) + 1;
                }
            }
        }

        // leftdn && rightdn
        for (int i = 0; i < M; i++){
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == 1) {
                    leftdn[i][j] = getNumber(leftdn, i - 1, j - 1, M, N) + 1;
                    rightdn[i][j] = getNumber(rightdn, i - 1, j + 1, M, N) + 1;
                }
            }
        }

        int[] localMin = new int[]{Integer.MAX_VALUE};
        int globalMax = Integer.MIN_VALUE;

        for (int i = 0; i < M; i ++){
            for (int j = 0; j< N; j++){
                localMin[0] = Math.min(leftup[i][j], rightup[i][j]);
                dp[i][j] = Math.min(localMin[0], leftdn[i][j]);
                dp[i][j] = Math.min(dp[i][j], rightdn[i][j]);
                globalMax = Math.max(globalMax, dp[i][j]);
            }
        }
        return globalMax;
    }

    private static int getNumber(int[][]matrix, int i, int j, int M, int N){
        if (i < 0 || i >= M || j < 0 || j >= N){
            return 0;
        }
        return matrix[i][j];
    }
}

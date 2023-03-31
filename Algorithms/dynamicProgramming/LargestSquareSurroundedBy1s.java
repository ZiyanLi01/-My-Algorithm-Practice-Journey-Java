package dynamicProgramming;

public class LargestSquareSurroundedBy1s {
    public static void main(String[] args) {
        int[][] matrix = {{1,1,1,1},{1,0,0,1},{1,0,0,1},{1,1,1,1}};
        System.out.println(largestSquareByOne(matrix));
    }

    public static int largestSquareByOne(int[][] matrix) {
        // corner case
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        //build up the matrix from right and up sides.
        int M = matrix.length;
        int N = matrix[0].length;

        int[][] right = new int[M][N];
        int[][] up = new int[M][N];

        for (int i = M - 1; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {
                if (matrix[i][j] == 1) {
                    if (i == M - 1 && j == N -1) {
                        up[i][j] = 1;
                        right[i][j] = 1;
                    } else if (i == M -1) {
                        right[i][j] = right[i][j+1] + 1;
                        up[i][j] = 1;
                    } else if (j == N -1) {
                        right[i][j] = 1;
                        up[i][j] = up[i + 1][j] + 1;
                    } else {
                        right[i][j] = right[i][j + 1] + 1;
                        up[i][j] = up[i + 1][j] + 1;
                    }
                }
            }
        }

//        int[] potentialLen = new int[]{Integer.MAX_VALUE};
        int maxLen = Integer.MIN_VALUE;

        //Find the largest length of the potential square
        for (int i = 0; i < M; i ++){
            for (int j = 0; j< N; j ++){
                int potentialLen = Math.min(up[i][j],right[i][j]);
                for (int k = potentialLen; k > 0; k--){
                    if (up[i][j+ k -1] >= k && right[i+ k -1][j] >=k){
                        maxLen = Math.max(maxLen, k);
                    }
                }
            }
        }
        return maxLen;
    }
}
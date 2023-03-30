package dynamicProgramming;

public class LongestCrossOf1s {
    public static void main(String[] args) {
        int[][] matrix = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,1,0,0}};
        System.out.println(largest(matrix));
    }

    public static int largest(int[][] matrix) {
        // corner case
        int M = matrix.length;
        if (M == 0){
            return 0;
        }

        int N = matrix[0].length;
        if (N == 0){
            return 0;
        }

        //build four matrixs that contains consecutive 1s in left, right, up and down direction.
        int[][] up = new int[M][N];
        int[][] dn = new int[M][N];
        int[][] left = new int[M][N];
        int[][] right = new int[M][N];

        // left and up matrix
        for (int i = 0; i< M; i++){
            for (int j = 0; j< N; j++){
                if (matrix[i][j] == 1){
                    if (i == 0 && j == 0){
                        up[i][j] = 1;
                        left[i][j] = 1;
                    } else if (i == 0){
                        up[i][j] = 1;
                        left[i][j] = left[i][j-1] + 1;
                    } else if (j == 0){
                        up[i][j] = up[i-1][j] + 1;
                        left[i][j] = 1;
                    } else {
                        up[i][j] = up[i-1][j] + 1;
                        left[i][j] = left[i][j-1] + 1;
                    }
                }
            }
        }
        // right and down matrix
        for (int i = M - 1; i >= 0; i--){
            for (int j = N - 1; j>=0; j--){
                if (matrix[i][j] == 1){
                    if (i == M - 1 && j == N - 1){
                        right[i][j] = 1;
                        dn[i][j] = 1;
                    } else if (i == M - 1){
                        right[i][j] = right [i][j + 1] + 1;
                        dn[i][j] = 1;
                    } else if (j == N - 1){
                        right[i][j] = 1;
                        dn[i][j] = dn[i + 1][j] + 1;
                    } else {
                        right[i][j] = right[i][j + 1] + 1;
                        dn[i][j] = dn[i + 1][j] + 1;
                    }
                }
            }
        }

        int globalmax = Integer.MIN_VALUE;
        int[] min = new int[]{Integer.MAX_VALUE};

        for (int i = 0; i < M; i++){
            for (int j = 0; j < N; j++){
                min[0] = Math.min(up[i][j], dn[i][j]);
                min[0] = Math.min(min[0], left[i][j]);
                min[0] = Math.min(min[0], right[i][j]);
                globalmax = Math.max(globalmax, min[0]);
            }

        }

        return globalmax;
    }

}

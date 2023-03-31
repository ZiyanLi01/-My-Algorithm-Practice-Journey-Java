package dynamicProgramming;

public class LargestSubMatrixSum {
    //
    //      j 1  0 1 1
//            0  1 3 5
//            2 -6 1 4
//     cur[0] 1  0 1 1  add 1st row
//     cur[1] 1  1 4 6  1st row + 2nd row
//     cur[2] 4 -5 5 10 add first 3 rows

    // result 4 -1 5 15 (largest subArray sum)
    public static void main(String[] args) {
        int[][] matrix = {{1,0,1,1},{0,1,3,5},{2,-6,1,4}};
        System.out.println(largest(matrix));
    }
    public static int largest(int[][]matrix){
        int N = matrix.length;
        int M = matrix[0].length;
        int result = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++){
            int[] cur = new int [M];
            for(int j = i; j< N; j++){
                add(cur, matrix[j]);
                result = Math.max(result,max(cur));
            }
        }
        return result;
    }

    private static void add (int[] cur, int[] add){
        for (int i = 0; i < cur.length; i++){
            cur[i] += add[i];
        }
    }

    private static int max(int[] cur){
        int result = cur[0];
        int tmp = cur[0];
        for(int i = 1; i < cur.length; i++){
            tmp = Math.max(tmp + cur[i], cur[i]);
            result = Math.max(result, tmp);
        }
        return result;
    }

}

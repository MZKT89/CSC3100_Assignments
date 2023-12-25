
import java.util.Scanner;
public class StarSequence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        int a = sc.nextInt();
        int b = sc.nextInt();
        long f0 = sc.nextInt();
        long f1 = sc.nextInt();
        long m = sc.nextLong();
        mod = m;
        long result = starSequence(n, f0, f1, a, b);
        System.out.println(result);
    }
public static long mod = 0;

    public static long starSequence(long n,long f0,long f1, int a,int b){
        if (n == 0) {
            return f0 % mod;
        }
        if (n == 1) {
            return f1 % mod;
        }

        long [][]transformationMatrix  = {{a,b},
                                          {1,0}};

        long [][] powTransformationMatrix = quickPowMatrix(transformationMatrix,n-1);

        long fn = powTransformationMatrix[0][0] * f1 + powTransformationMatrix[0][1] * f0;

        return fn % mod;

    }

    private static long[][] quickPowMatrix(long[][] matrix, long order) {
        long [][] resultMatrix = new long[matrix.length][matrix.length];
        //Assign identity matrix to resultMatrix first
        for (int i = 0; i < matrix.length; i++) {
            resultMatrix[i][i] = 1;
        }

        while(order != 0){
            if(order % 2 == 1){
                //R = R * A
                resultMatrix = matrixMultiplication(resultMatrix,matrix);
            }
            //A = A*A
            matrix = matrixMultiplication(matrix,matrix);
            //二进制右移
            order = order/2;
        }
        return resultMatrix;
    }

    private static long[][] matrixMultiplication(long[][] leftMatrix, long[][] rightMatrix) {

        if(leftMatrix[0].length != rightMatrix.length){
                return null;
        }
        int n = leftMatrix[0].length; // 左矩阵的列 = 右矩阵的行
        int m = leftMatrix.length; // 左矩阵的行
        int k = rightMatrix[0].length; // 右矩阵的列
        long[][] resultMatrix = new long[m][k];
        for (int i = 0; i < m; i++) {
            for (int i1 = 0; i1 < k; i1++) {
                for (int i2 = 0; i2 < n; i2++) {
                    resultMatrix[i][i1] = (resultMatrix[i][i1] + leftMatrix[i][i2]*rightMatrix[i2][i1]) % mod;
                }
            }
        }
            return resultMatrix;
    }


}
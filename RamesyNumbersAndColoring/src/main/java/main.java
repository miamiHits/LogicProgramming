
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class main {

    public static void main(String[] args) {

        int Ks ;
        int Kt ;
        int n;
        String s = args[0];
        if (!s.equals("ramsey"))
        {
            System.out.println("error with args.");
            System.exit(0);
        }
        Ks = Integer.parseInt(args[1]);
        Kt =  Integer.parseInt(args[2]);
        n =  Integer.parseInt(args[3]);
        helperClass helper = new helperClass(n, Ks, Kt);
        int[][] matrix =new int[n][n];
        resetMatrix(matrix, n);
        List<int[][]> results = new ArrayList<>();
        helper.recAllPerm(matrix,0,1,results);

        String fileName = "Y:\\downloads\\output_"+Ks+"_"+Kt+"_"+n+".txt";
        if (results.isEmpty())
            writeNoSol(fileName);


        for(int[][] m : results)
        {
            mirror(m);
            writeMatrix(fileName, m);
        }

    }

    static void resetMatrix(int[][] matrix, int n) {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
                if(i!=j)
                    matrix[i][j]=-1;
        }
    }

    static void writeNoSol(String filename)
    {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
            bw.write("NO SOLUTION");
            bw.newLine();
            bw.flush();
        }
        catch (IOException e) {

        }
        }
    static void writeMatrix(String filename, int[][] matrix) {
        try {

            BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
            bw.newLine();
            bw.write("matrix is:");
            bw.newLine();
            bw.newLine();
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if(j == matrix[i].length - 1){
                        bw.write(matrix[i][j]);
                    } else{
                        bw.write(matrix[i][j] + ",");
                    }
                }
                bw.newLine();
            }
            bw.newLine();
            bw.newLine();
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            //why does the catch need its own curly?
        }
    }

    static void mirror (int[][] mat)
    {
        for (int i=0; i< mat.length; i++)
        {
            for (int j=0; j<mat[0].length; j++)
            {
                if (mat[i][j] == 0)
                    mat [j][i] = 0;

                if (mat[j][i] == 1)
                    mat [i][j] = 1;
            }
        }
    }

}

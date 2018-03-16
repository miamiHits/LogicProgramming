import java.util.*;

public class helperClass {

    public List<int[]> KsOptions;
    public List<int[]> KtOptions;
    private int Ks;
    private int Kt;
    private int n;
    private int[] vertex;

    public helperClass(int n, int k, int t){
        this.Ks = k;
        this.Kt = t;
        this.n = n;
        this.vertex = new int[n];
        for (int i=0; i<n;i++)
        {
            this.vertex[i] = i+1;
        }

        this.KsOptions = new ArrayList<>();
        kChooseN(vertex, Ks, 0, new int[Ks], KsOptions);

        this.KtOptions = new ArrayList<>();
        kChooseN(vertex, Kt, 0, new int[Kt], KtOptions);
        
    }



    private void kChooseN(int[] arr, int len, int startPosition, int[] result, List<int[]> resArray){
        if (len == 0){
            int [] ar = new int [result.length];
            ar = result.clone();
            resArray.add(ar);
            return;
        }
        for (int i = startPosition; i <= arr.length-len; i++){
            result[result.length - len] = arr[i];
            kChooseN(arr, len-1, i+1, result, resArray);
        }
    }

    private int[][] deepCopy(int[][] original) {
        if (original == null) {
            return null;
        }
        final int[][] result = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return result;
    }

    private boolean checkForKs(int matrix[][])
    {
        for( int[] kOptions : KsOptions)
        {
            int counterGoodVertex=0;
            for(int i=0; i<kOptions.length-1;i++)
            {
                for(int j=i+1;j<kOptions.length;j++)
                    if(matrix[kOptions[i]-1][kOptions[j]-1]!=0)
                        counterGoodVertex++;
            }
            if(counterGoodVertex==0)
                return true;
        }
        return false;
    }

    private boolean CheckForKt(int matrix[][])
    {
        for( int[] tOptions : KtOptions)
        {
            int counterGoodVertex=0;
            for(int i=0; i<tOptions.length-1;i++)
            {
                for(int j=i+1;j<tOptions.length;j++)
                    if(matrix[tOptions[i]-1][tOptions[j]-1]!=1)
                        counterGoodVertex++;
            }
            if(counterGoodVertex==0)
                return true;
        }
        return false;
    }

    public void recAllPerm(int matrix [][], int i , int j, List<int[][]> results)
    {
        int nextIidx;
        int nextJidx;

        //check for K or T occurs
        if((checkForKs(matrix)) || (CheckForKt(matrix)))
            return;

        //base case. Deep copy of mat and add it to the res
        if((i == matrix.length-1) && (j == matrix[0].length))
        {
            int[][] tempRes = deepCopy(matrix);
            results.add(tempRes);
            return;
        }

        //update the next indexes
        if(j<matrix[0].length-1)
        {
            nextIidx=i;
            nextJidx=j+1;
        }
        else
        {
            nextIidx=i+1;
            nextJidx=nextIidx+1;
        }

        //recurse
        matrix[i][j]=0;
        recAllPerm(deepCopy(matrix),nextIidx,nextJidx,results);
        matrix[i][j]=1;
        recAllPerm(deepCopy(matrix),nextIidx,nextJidx,results);
    }

}
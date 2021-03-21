import java.util.Arrays;

public class Elimination {

    public Elimination() {
    }

    float[][] augment(int[][] A, int[] B){
        float[][] aug = new float[A.length][A.length+1];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                aug[i][j]=A[i][j];
            }
        }
        for (int i = 0; i < aug.length; i++) {
            aug[i][aug.length] = B[i];
        }
        return aug;
    }

    float[] betterForwardElimination(int[][] A, int[] B){
        float[][] aug = augment(A,B);
        for (int i = 0; i < aug.length - 1; i++) {
            int pivotrow = i;

            for (int j = i+1; j < A.length; j++) {
                if(Math.abs(aug[j][i]) > Math.abs(aug[pivotrow][i])){
                    pivotrow = j;
                }
            }
            for (int k = i; k < A.length+1; k++) {
                float holder = aug[i][k];
                aug[i][k] = aug[pivotrow][k];
                aug[pivotrow][k]=holder;
            }

            for (int j = i+1; j < A.length; j++) {
                if(aug[i][i]==0){
                    for (int k = 0; k < A.length; k++) {
                        if(aug[k][0]!=0){
                            float[] temp = aug[k];
                            aug[k]=aug[i];
                            aug[i]=temp;
                        }
                    }
                }
                float temp = aug[j][i]/aug[i][i];
                for (int k = i; k < aug.length+1; k++) {
                    aug[j][k] -= aug[i][k]*temp;
                }
            }
        }
        for (int i = 0; i < aug.length; i++) {
            float pivot = aug[i][i];
            for (int j = i; j < aug.length+1; j++) {
                aug[i][j]/=pivot;
            }
        }

        for (int i = aug.length-1; i >-1; i--) {
            for (int j = 1; i-j > -1 ; j++) {
                float multiplier = -aug[i-j][i];
                aug[i-j][i]= (multiplier*aug[i][i]) + aug[i-j][i];
                aug[i-j][aug[i].length-1]= (multiplier*aug[i][aug[i].length-1]) + aug[i-j][aug[i].length-1];
            }

        }

       float[] answers = new float[B.length];
        for (int i = 0; i < aug.length; i++) {
            answers[i]=aug[i][aug[i].length-1];
        }
        return answers;
    }

}

import java.util.Arrays;

public class Solution {
    //Algo 1
    public int[] solution(int N, int[] A){
        int M = A.length;
        int[]counters = new int[N];
        boolean valide = true ;

        if (verifierRange(N,1,100000) && verifierRange(M,1,100000)){
                    for (int i=0 ; i<M ; i++ ) {
                        if (!verifierRange(A[i],1,N+1)){
                           valide = false;
                           break;
                        }
                    }

        }
        else {
            valide = false;
        }

        if (valide) {
            for (int i=0 ; i<M ; i++ ){
                if (verifierRange(A[i],1,N)){
                    int val = A[i]-1;
                    counters[val] +=1;
                }
                else{
                    int max = Arrays.stream(counters).max().getAsInt();
                    for (int j=0 ; j<N ; j++ ){
                        counters[j] = max;
                    }

                }
            }


        }
        else{
            System.out.println("**N and M should be within the range [1..100,000]\n" +
                    "**each element of array A should be within the range [1..N + 1]");
        }
        return counters;
    }

    //Algo 2
    public int[] solution(int[] A, int[] B) {
            int L = A.length;
            int[]c = new int[L];
            c[0] = 1;
            c[1] = 2;
            boolean valide = true ;

            if (verifierRange(L,1,50000)){
                for (int i=0 ; i<L ; i++ ) {
                    if ((!verifierRange(A[i],1,L)) || (!verifierRange(B[i],1,30))){
                        valide = false;
                        break;
                    }
                }

            }
            else {
                valide = false;
            }

            if (valide) {
                for (int i = 2; i < L; i++) {
                    c[i] = (c[i - 1] + c[i - 2]);
                    c[i]= c[i] & ((1 << 30) - 1);
                }
                for (int i = 0; i < L; i++) {
                    A[i] = c[A[i] - 1] ;
                    A[i] = A[i] & ((1 << B[i]) - 1);
                }


            }
            else{
                System.out.println("**L should be an integer within the range [1..50,000]\n" +
                        "**each element of array A should be an integer within the range [1..L]\n" +
                        "**each element of array B should be an integer within the range [1..30]\n");
            }
            return A;
        }

    //Algo 3
    public int solution(int[] A) {
        int N = A.length;
        int somme = 0;
        int max = Integer.MIN_VALUE;
        boolean valide = true ;

        if (verifierRange(N,0,20000)){
            for (int i=0 ; i<N ; i++ ) {
                if (!verifierRange(A[i],-100,100)){
                    valide = false;
                    break;
                }
            }

        }
        else {
            valide = false;
        }

        if (valide) {
            for (int i = 0; i < N; i++) {
                int value = Math.abs(A[i]);
                somme += value;
                if (max < value) {
                    max = value;
                }
                A[i] = value;
            }
            int[] k = new int[max + 1];
            for (int value: A) {
                k[value]++;
            }
            int[] l = new int[somme + 1];
            for (int i = 1; i < l.length; i++) {

                l[i] = -1;
            }
            for (int i = 1; i < k.length; i++) {
                for (int j = 0; j < l.length; j++) {
                    if (l[j] >= 0) {
                        l[j] = k[i];
                    } else if (j-i >= 0 && l[j-i] > 0) {
                        l[j] = l[j-i] - 1;
                    }
                }
            }
            int res = somme;
            for (int i = 0; i < l.length / 2 + 1; i++) {
                if (l[i] >= 0 && res > Math.abs(somme - 2 * i)) {
                    res = Math.abs(somme - 2 * i);
                }
            }
            return res;
        }
        else{
            System.out.println("**N should be an integer within the range [0..20,000];\n" +
                               "**each element of array A should be an integer within the range [−100..100].\n");
            return -1;
        }

    }

    //fonction pour vérifier l'appartenance d'un entier à un intervalle
    private boolean verifierRange(int val, int inf, int sup){
        if (val >= inf && val <= sup){
            return true;
        }
        return false;
    }



    public static void main(String[] args){

        //Algorithme 1 exemple
        int[] A11 = {3,4,4,6,1,4,4};
        Solution solAlg1 = new Solution();
        int [] resAlg1 = solAlg1.solution(5,A11);
        System.out.println("-> Algorithme 1 exemple:");
        System.out.println(Arrays.toString(resAlg1)+"\n");

        //Algorithme 1 cas où les hypothèses ne sont pas respectées
        int[] A12 = {3,4,4,6,1,4,10};
        System.out.println("-> Algorithme 1 cas où les hypothèses ne sont pas respectées:");
        int [] res1 = solAlg1.solution(5,A12);
        System.out.println(Arrays.toString(res1)+"\n");

        //Algorithme 2 exemple
        Solution solAlg2 = new Solution();
        int[] A21 = {4,4,5,5,1};
        int[] B21 = {3,2,4,3,1};
        int[] resAlg2 = solAlg2.solution(A21,B21);
        System.out.println("-> Algorithme 2 exemple:");
        System.out.println(Arrays.toString(resAlg2)+"\n");

        //Algorithme 2 cas où les hypothèses ne sont pas respectées
        int[] A22 = {4,4,5,5,7};
        int[] B22 = {3,2,4,3,1};
        System.out.println("-> Algorithme 2 cas où les hypothèses ne sont pas respectées:");
        int[] res2 = solAlg2.solution(A22,B22);
        System.out.println(Arrays.toString(res2)+"\n");

        //Algorithme 3 exemple
        int[] A31 = {1,5,2,-2};
        Solution solAlg3 = new Solution();
        int resAlg3 = solAlg3.solution(A31);
        System.out.println("-> Algorithme 3 exemple:");
        System.out.println(resAlg3+"\n");

        //Algorithme 3 cas où les hypothèses ne sont pas respectées
        int[] A32 = {1,5,150,-2};
        System.out.println("-> Algorithme 3 cas où les hypothèses ne sont pas respectées:");
        int res3 = solAlg3.solution(A32);
        System.out.println(res3 +"\n");



    }
}


import java.util.*;

class Solution {
    public static int[] kWeakestRows(int[][] mat, int k) {
        int r[] = new int[k];
        int cp[][] = new int[mat.length][2];
        int l = mat[0].length;

        for (int i = 0; i < mat.length; i++) {
            cp[i][0] = i;
            for (int j = 0; j < l; j++) {
                cp[i][1] += mat[i][j];
            }
        }

        Arrays.sort(cp, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);

        for (int j = 0; j < k; j++) {
            r[j] = cp[j][0];
            System.out.println(cp[j]);
        }

        return r;
    }

    public static void main(String[] args){
        int[] r = kWeakestRows(new int[][] {{1,1,0,0,0}, {1,1,1,1,0}, {1,0,0,0,0}, {1,1,0,0,0}, {1,1,1,1,1}}, 3);

        for (int i = 0; i < r.length; i++) {
            System.out.println(r[i]+",");
        }

    }

}
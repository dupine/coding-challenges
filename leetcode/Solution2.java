// https://support.hcltechsw.com/csm?id=kb_article&sysparm_article=KB0024706#:~:text=In%20Java%2C%20you%20can%20access,long%20total_mem%20%3D%20rt.
/* 
long startTime = System.nanoTime();
.....your program....
long endTime   = System.nanoTime();
long totalTime = endTime - startTime;
System.out.println(totalTime);
 */

import java.util.*;

class Solution2 {

    public static int countPalindromicSubsequence(String s) {

        char[] str = s.toCharArray();
        int l = 0, r, res = 0;
        boolean[][] letters = new boolean[26][27];
        int mid = str.length-1;

        while(l<mid){
            r = str.length-1;
            
            while(!letters[str[l]-97][0] && r-(l+1)!=0){
                if(str[l] == str[r]){
                    letters[str[l]-97][0] = true;
                    while (r>l+1) {
                        r--;
                        if(!letters[str[l]-97][str[r]-96]){
                            letters[str[l]-97][str[r]-96] = true;
                            res++;
                        }
                    }
                }
                r--;
            }
            l++;
        }

        return res;
    }

    public static void main(String[] args){ 
        System.out.println(countPalindromicSubsequence("tlpjzdmtwderpkpmgoyrcxttiheassztncqvnfjeyxxp"));
    }
}
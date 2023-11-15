import java.util.*;

class Solution {
    public static String sortVowels(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;  
        char temp = 0;  
        int last = 0;
        for(int i=0; i < n; i++){  
            for(int j=1; j < (n-i); j++,last++){
                if(isVowelBySwitch(arr[j]) && isVowelBySwitch(arr[last])){
                    if(arr[last] > arr[j]){
                        temp = arr[last];
                        arr[last] = arr[j];
                        arr[j] = temp;
                    }
                }
                else if(isVowelBySwitch(arr[j])) j++;
                else last--;
            }
            last = 0;
        }
        String res = "";
        for (char c : arr) res+=c;
        return res;
    }  

    static boolean isVowelBySwitch(char c) {
        switch (c) {
            case 'a':            
            case 'e':           
            case 'i':           
            case 'o':            
            case 'u':            
            case 'A':
            case 'E':            
            case 'I':           
            case 'O':            
            case 'U':
                return true;
            default:
                return false;
        }
    }

    public static void main(String[] args){ 
        System.out.println(sortVowels("lEetcOde"));
    }
}
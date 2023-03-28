class palindrome {
    private static boolean palindrome(int n){
        int sum = 0, temp = n;
        while(n > 0){
            sum = (sum * 10) + n % 10;
            n = n / 10;
        }
        if(temp == sum) return true;
        else return false;
    }
}

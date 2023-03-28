class primeNumber{
    private static boolean isPrime(int n){
        for (int i = 0; i < n/2; i++) {
            if(n % 2 == 0){
                return false;
            }
        }
        return true;
    }
}
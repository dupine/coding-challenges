class fibonacci{
    public static int fibonacci(int n) {
        // base cases:
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        // recursive case:
        return fibonacci(n-1) + fibonacci(n-2);
    }
}
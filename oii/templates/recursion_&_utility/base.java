class base{
    public static int recursiveFunction(int parameter) {
        // base case:
        if (parameter == 0) {
            return 0;
        }
        // recursive case:
        int result = recursiveFunction(parameter);
        return result;
    }
}
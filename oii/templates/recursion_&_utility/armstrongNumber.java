class armstrongNumber {

    // la somma delle k cifre di n elevate alla k tornano n stesso(Eg. 153 1^3 + 5^3 + 3^3 = 1 + 125 + 27 = 153);
    private static boolean armstrongNumber(int n){
        int sum = 0;
        int temp = n;
        int numDigits = String.valueOf(n).length();
        while (temp > 0) {
            int digit = temp % 10;
            sum += Math.pow(digit, numDigits);
            temp /= 10;
        }
        return sum == n;
    }
}

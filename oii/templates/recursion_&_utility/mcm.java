long mcd(long a, long b) {
    while (a != b) {
        if (a > b) {
            a = a - b;
        }
        else {
            b = b - a;
        }
    }
    return a;
}

long mcm(long a, long b) {
    return (a*b)/mcd(a,b);
}
package academy.tochkavhoda.introduction;

public class ZeroSteps1 {

    public int sum(int x, int y) {
        return x + y;
    }

    public int mul(int x, int y) {
        return x * y;
    }

    public int div(int x, int y) {
        return x / y;
    }

    public int mod(int x, int y) {
        return x % y;
    }

    public boolean isEqual(int x, int y) {
        return x == y;
    }

    public boolean isGreater(int x, int y) {
        return x > y;
    }

    public double calculateTriangleSquare(int side1, int side2) {
        return 0.5 * side1 * side2;
    }

    public double calculateTrianglePerimeter(int side1, int side2) {
        return side1 + side2 + Math.sqrt(side1 * side1 + side2 * side2);
    }

    public int reverseNumber(int number) {
        int d1 = number / 100;
        int d2 = (number / 10) % 10;
        int d3 = number % 10;
        return d3 * 100 + d2 * 10 + d1;
    }

    public long calculate15Degree(int number) {
        long num = number;
        long n2 = num * num;
        long n3 = n2 * num;
        long n5 = n3 * n2;
        long n10 = n5 * n5;
        return n10 * n5;
    }

    public boolean isInsideRect(int xLeft, int yTop, int xRight, int yBottom, int x, int y) {
        return x >= xLeft && x <= xRight && y >= yTop && y <= yBottom;
    }

    public double sqrtAbs(int number) {
        return Math.sqrt(Math.abs(number));
    }

    public boolean isTriangleExist(int side1, int side2, int side3) {
        return (side1 + side2 > side3) && (side1 + side3 > side2) && (side2 + side3 > side1);
    }

    public int getDigitsProduction(int number) {
        int d1 = number % 10;
        int d2 = (number / 10) % 10;
        int d3 = (number / 100) % 10;
        int d4 = number / 1000;
        return d1 * d2 * d3 * d4;
    }

    public boolean isCircleInsideSquare(int radius, int side) {
        return radius * 2 < side;
    }

    public char getCharByCode(short code) {
        return (char) code;
    }

    public short getCodeByChar(char character) {
        return (short) character;
    }

    public char getCharByCodeAndOffset(char ch, short offset) {
        return (char) (ch + offset);
    }

    public boolean isGreaterOrEqual(char ch1, char ch2) {
        return ch1 >= ch2;
    }

    public char getAverageChar(char ch1, char ch2) {
        return (char) ((ch1 + ch2) / 2);
    }
}
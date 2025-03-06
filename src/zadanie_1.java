import java.math.BigInteger;

public class zadanie_1 {

    private static boolean isInteger(String s) {
        return s.matches("-?\\d+"); // regex check
    }

    public static int overflowValidatedMultiplyInt(int a, int b) {
        if (a > 0 && b > 0 && a > Integer.MAX_VALUE / b) {;
            return Integer.MAX_VALUE; // Overflow signal
        } else if (a > 0 && b < 0 && b < Integer.MIN_VALUE / a) {
            return Integer.MAX_VALUE;
        } else if (a < 0 && b > 0 && a < Integer.MIN_VALUE / b) {
            return Integer.MAX_VALUE;
        } else if (a < 0 && b < 0 && a < Integer.MAX_VALUE / b) {
            return Integer.MAX_VALUE;
        }
        return a * b;
    }

    public static boolean intOverflow(int sum, int added_value) {
        if (added_value > 0 && sum > Integer.MAX_VALUE - added_value) {
            return true;
        } else if (added_value < 0 && sum < Integer.MIN_VALUE - added_value) {
            return true;
        }
        return false;
    }

    public static int primeNumberCalculatorInt(int n) {
        int sum = -1;
        int factorial = 1;

        String errorMessage = "Overflow detected (int)";
        for (int j = 3; j <= n; j++) {
            if (j > 3) {
                factorial = overflowValidatedMultiplyInt(factorial, j - 2);
                if (factorial == Integer.MAX_VALUE) {
                    System.out.println(errorMessage);
                    return sum;
                }
            }

            int floorDivision = factorial / j;
            int multipliedDivision = overflowValidatedMultiplyInt(j, floorDivision);

            if (multipliedDivision == Integer.MAX_VALUE) {
                System.out.println(errorMessage);
                return sum;
            }

            int partialSum = factorial - multipliedDivision;
            if (intOverflow(sum, partialSum)) {
                System.out.println(errorMessage);
                return sum;
            }

            sum += partialSum;
        }
        return sum;
    }


    public static long overflowValidatedMultiplyLong(long a, long b) {
        if (a > 0 && b > 0 && a > Long.MAX_VALUE / b) {
            return Long.MAX_VALUE;
        } else if (a > 0 && b < 0 && b < Long.MIN_VALUE / a) {
            return Long.MAX_VALUE;
        } else if (a < 0 && b > 0 && a < Long.MIN_VALUE / b) {
            return Long.MAX_VALUE;
        } else if (a < 0 && b < 0 && a < Long.MAX_VALUE / b) {
            return Long.MAX_VALUE;
        }
        return a * b;
    }

    public static boolean longOverflow(long sum, long addedValue) {
        if (addedValue > 0 && sum > Long.MAX_VALUE - addedValue) {
            return true;
        } else if (addedValue < 0 && sum < Long.MIN_VALUE - addedValue) {
            return true;
        }
        return false;
    }

    public static long primeNumberCalculatorLong(long n) {
        long sum = -1;
        long factorial = 1;

        String errorMessage = "Overflow detected (long)";
        for (long j = 3; j <= n; j++) {
            if (j > 3) {
                factorial = overflowValidatedMultiplyLong(factorial, j - 2);
                if (factorial == Long.MAX_VALUE) {
                    System.out.println(errorMessage);
                    return sum;
                }
            }

            long floorDivision = factorial / j;
            long multipliedDivision = overflowValidatedMultiplyLong(j, floorDivision);

            if (multipliedDivision == Long.MAX_VALUE) {
                System.out.println(errorMessage);
                return sum;
            }

            long partialSum = factorial - multipliedDivision;
            if (longOverflow(sum, partialSum)) {
                System.out.println(errorMessage);
                return sum;
            }

            sum += partialSum;
        }
        return sum;
    }
    public static BigInteger primeNumberCalculatorBigInteger(BigInteger n) {
        BigInteger sum = new BigInteger("-1");
        BigInteger factorial = BigInteger.ONE;

        for (BigInteger j = new BigInteger("3"); j.compareTo(n) <= 0; j = j.add(BigInteger.ONE)) {
            if (j.compareTo(BigInteger.valueOf(3)) > 0) {
                factorial = factorial.multiply(j.subtract(BigInteger.TWO));
            }

            BigInteger floorDivision = factorial.divide(j);
            BigInteger multipliedDivision = j.multiply(floorDivision);

            BigInteger partialSum = factorial.subtract(multipliedDivision);
            sum = sum.add(partialSum);
        }
        return sum;
    }


    public static void main(String[] args) {
        int n = 0;
        if (args.length == 0) {
            System.out.println("Missing argument n:");
        } else {
            if (args.length == 1) {
                String arg = args[0];
                if (isInteger(arg)) {
                    n = Integer.parseInt(arg);
                    if (n > 3) {
                        System.out.println("Result Int: " + primeNumberCalculatorInt(n));
                        System.out.println("Result Long: " + primeNumberCalculatorLong(n));
                        System.out.println("Result BigInteger: " + primeNumberCalculatorBigInteger(BigInteger.valueOf(n)));
                    }
                    else if(n == -1){
                        System.out.println("Result Int: " + primeNumberCalculatorInt(Integer.MAX_VALUE));
                        System.out.println("Result Long: " + primeNumberCalculatorLong(Long.MAX_VALUE));
                    }
                    else {
                        System.out.println("Invalid value of n");
                    }
                } else {
                    System.out.println("Invalid integer format");
                }
            } else {
                System.out.println("Too many arguments");
            }
        }
    }
}

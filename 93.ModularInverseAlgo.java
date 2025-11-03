/* ----------------------------------------------------------------------------  */
/*   ( The Authentic JS/JAVA CodeBuff )
 ___ _                      _              _ 
 | _ ) |_  __ _ _ _ __ _ __| |_ __ ____ _ (_)
 | _ \ ' \/ _` | '_/ _` / _` \ V  V / _` || |
 |___/_||_\__,_|_| \__,_\__,_|\_/\_/\__,_|/ |
                                        |__/ 
 */
/* --------------------------------------------------------------------------   */
/*    Youtube: https://youtube.com/@code-with-Bharadwaj                        */
/*    Github : https://github.com/Manu577228                                  */
/*    Portfolio : https://manu-bharadwaj-portfolio.vercel.app/portfolio      */
/* -----------------------------------------------------------------------  */

import java.io.*;
import java.util.*;

public class ModularInverse {

    // Extended Euclidean Algorithm:
    // Returns gcd(a, b) and finds coefficients x, y such that a*x + b*y = gcd(a, b)
    static long[] extendedGCD(long a, long b) {
        // base case: when b == 0, gcd = a and coefficients are (1, 0)
        if (b == 0) return new long[]{a, 1, 0};

        // recursive call
        long[] res = extendedGCD(b, a % b);

        // compute x, y using results from recursive call
        long g = res[0];       // gcd
        long x1 = res[1];      // coefficient for b
        long y1 = res[2];      // coefficient for a % b

        long x = y1;
        long y = x1 - (a / b) * y1;

        return new long[]{g, x, y};
    }

    // Modular Inverse using Extended Euclidean Algorithm (works for all modulus)
    static Long modInverseEEA(long a, long m) {
        long[] res = extendedGCD(a, m);
        long g = res[0];
        long x = res[1];

        // inverse exists only if gcd(a, m) == 1
        if (g != 1) return null;

        // x might be negative — normalize it under modulo m
        return (x % m + m) % m;
    }

    // Modular Inverse using Fermat's Little Theorem (only valid when m is prime)
    static long modInverseFermat(long a, long m) {
        return modPow(a, m - 2, m); // a^(m-2) mod m
    }

    // Fast Modular Exponentiation
    static long modPow(long base, long exp, long mod) {
        long result = 1;
        base %= mod;

        while (exp > 0) {
            if ((exp & 1) == 1)
                result = (result * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }

    // Simple primality check for demonstration
    static boolean isPrime(long n) {
        if (n < 2) return false;
        if (n % 2 == 0) return n == 2;
        for (long i = 3; i * i <= n; i += 2)
            if (n % i == 0)
                return false;
        return true;
    }

    // Demo function
    public static void main(String[] args) throws Exception {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        long[][] examples = {
                {3, 11},  // Expected inverse = 4
                {10, 17}, // Expected inverse = 12
                {6, 9}    // gcd != 1, no inverse
        };

        for (long[] e : examples) {
            long a = e[0], m = e[1];
            out.write("Example: a = " + a + ", m = " + m + "\n");

            Long inv1 = modInverseEEA(a, m);
            if (inv1 == null) {
                out.write("  EEA: No modular inverse (gcd != 1)\n");
            } else {
                out.write("  EEA: modular inverse = " + inv1 + "  (check: " + (a * inv1 % m) + " )\n");
            }

            if (isPrime(m)) {
                long inv2 = modInverseFermat(a, m);
                out.write("  Fermat: modular inverse = " + inv2 + "  (check: " + (a * inv2 % m) + " )\n");
            } else {
                out.write("  Fermat: skipped (modulus not prime)\n");
            }
            out.write("\n");
        }

        out.flush();
    }
}

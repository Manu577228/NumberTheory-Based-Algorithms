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
/*    Portfolio : https://manu-bharadwaj-portfolio.vercel.app/portfolio       */
/* -----------------------------------------------------------------------  */

import java.io.*;
import java.util.*;

public class Main {

    // Function to perform modular exponentiation
    static long power(long a, long d, long n) {
        long res = 1;                // Start with result = 1
        a = a % n;                   // Take mod n to keep numbers small

        while (d > 0) {              // While there are bits left in d
            if ((d & 1) == 1) {      // If the current bit is 1
                res = (res * a) % n; // Multiply and take mod n
            }
            d >>= 1;                 // Right shift d (divide by 2)
            a = (a * a) % n;         // Square a and mod n
        }
        return res;                  // Return final modular exponentiation result
    }

    // Miller-Rabin test for a given base 'a'
    static boolean millerTest(long d, long n) {
        long a = 2 + (long)(Math.random() * (n - 4)); // Pick random base in [2, n-2]
        long x = power(a, d, n);                      // Compute a^d % n

        if (x == 1 || x == n - 1)                     // If x is 1 or n−1, probably prime
            return true;

        while (d != n - 1) {                          // Repeat squaring process
            x = (x * x) % n;
            d *= 2;                                   // Double d

            if (x == 1)                               // If x becomes 1, composite
                return false;
            if (x == n - 1)                           // If x becomes n−1, probably prime
                return true;
        }
        return false;                                 // Otherwise composite
    }

    // Main Miller-Rabin primality test function
    static boolean isPrime(long n, int k) {            // k = number of iterations
        if (n <= 1 || n == 4) return false;            // Handle base cases
        if (n <= 3) return true;                       // 2 and 3 are prime

        long d = n - 1;
        while (d % 2 == 0) d /= 2;                     // Factor n-1 as 2^r * d

        for (int i = 0; i < k; i++)                    // Repeat k times
            if (!millerTest(d, n))                     // If any test fails, composite
                return false;
        return true;                                   // Probably prime if all pass
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        long n = 37; // Number to check
        int k = 5;   // Number of iterations

        if (isPrime(n, k))
            pw.println(n + " is probably prime.");
        else
            pw.println(n + " is composite.");

        pw.flush();
    }
}

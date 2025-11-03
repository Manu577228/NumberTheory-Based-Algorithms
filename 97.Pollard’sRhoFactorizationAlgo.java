/* ----------------------------------------------------------------------------
   ( The Authentic JS/JAVA CodeBuff )
    ___ _                      _              _ 
    | _ ) |_  __ _ _ _ __ _ __| |_ __ ____ _ (_)
    | _ \ ' \/ _` | '_/ _` / _` \ V  V / _` || |
    |___/_||_\__,_|_| \__,_\__,_|\_/\_/\__,_|/ |
                                           |__/ 
   ----------------------------------------------------------------------------
   Youtube: https://youtube.com/@code-with-Bharadwaj
   Github : https://github.com/Manu577228
   Portfolio : https://manu-bharadwaj-portfolio.vercel.app/portfolio
   -------------------------------------------------------------------------- */

import java.io.*;
import java.util.*;

public class Main {

    // Function to compute GCD using Euclidean Algorithm
    static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    // Pollard's Rho Algorithm
    static long pollardRho(long n) {
        // If number is even, return 2 as factor
        if (n % 2 == 0)
            return 2;

        Random rand = new Random();
        long c = rand.nextInt((int) (n - 1)) + 1; // Choose random c (1 < c < n)
        long x = rand.nextInt((int) (n - 1));     // Random initial x
        long y = x;
        long d = 1;

        // f(x) = (x*x + c) % n repeated until factor found
        while (d == 1) {
            x = (mulmod(x, x, n) + c) % n;        // Move x one step
            y = (mulmod(y, y, n) + c) % n;        // Move y two steps (Floyd's cycle)
            y = (mulmod(y, y, n) + c) % n;

            d = gcd(Math.abs(x - y), n);          // Compute gcd(|x - y|, n)

            if (d == n)                           // Retry if failed
                return pollardRho(n);
        }

        return d;  // Found non-trivial factor
    }

    // Function to perform modular multiplication safely
    static long mulmod(long a, long b, long mod) {
        long res = 0;
        a %= mod;
        while (b > 0) {
            if ((b & 1) == 1)
                res = (res + a) % mod;
            a = (2 * a) % mod;
            b >>= 1;
        }
        return res % mod;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        long n = 8051; // Example number (8051 = 97 * 83)
        long factor = pollardRho(n);

        out.println("A non-trivial factor of " + n + " is: " + factor);
        out.flush();
    }
}

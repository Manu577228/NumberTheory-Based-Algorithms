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
/*    Portfolio : https://manu-bharadwaj-portfolio.vercel.app/portfolio        */
/* -----------------------------------------------------------------------  */

import java.io.*;

public class ModularExponentiation {

    // Function to compute (base^exponent) % mod efficiently
    static long modularPow(long base, long exponent, long mod) {

        // Step 1: Initialize result = 1
        long result = 1;

        // Step 2: Reduce base modulo mod to avoid large numbers
        base = base % mod;

        // Step 3: Loop till exponent becomes zero
        while (exponent > 0) {

            // Step 4: If current bit (LSB) of exponent is 1, multiply result with base
            if ((exponent & 1) == 1) {
                result = (result * base) % mod;
            }

            // Step 5: Square the base for the next bit and take mod again
            base = (base * base) % mod;

            // Step 6: Right shift exponent by 1 (divide by 2)
            exponent >>= 1;
        }

        // Step 7: Return final result
        return result;
    }

    public static void main(String[] args) throws IOException {

        // Example 1: Small numbers (easy to verify manually)
        long a1 = 3, e1 = 13, m1 = 7;
        System.out.println("Example 1: compute 3^13 mod 7");
        System.out.println("modularPow -> " + modularPow(a1, e1, m1));
        System.out.println("Math.pow(3,13)%7 -> " + (long)(Math.pow(a1, e1) % m1));
        System.out.println();

        // Example 2: Large exponent (demonstrates efficiency)
        long a2 = 2, e2 = 1000, m2 = 1000000007;
        System.out.println("Example 2: compute 2^1000 mod 1000000007");
        System.out.println("modularPow -> " + modularPow(a2, e2, m2));

        // Java’s BigInteger equivalent (for verification)
        java.math.BigInteger bigResult = java.math.BigInteger.valueOf(a2)
                .modPow(java.math.BigInteger.valueOf(e2), java.math.BigInteger.valueOf(m2));
        System.out.println("BigInteger.modPow -> " + bigResult);
    }
}

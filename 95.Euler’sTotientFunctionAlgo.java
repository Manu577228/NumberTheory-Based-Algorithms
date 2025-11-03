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
/* -----------------------------------------------------------------------  */

import java.io.*;

public class EulerTotient {

    // Function to calculate Euler's Totient Function φ(n)
    static int eulerTotient(int n) {
        int result = n; // Start with n
        int p = 2;      // Smallest prime number
        while (p * p <= n) { // Check for prime factors up to sqrt(n)
            if (n % p == 0) { // If p divides n, it's a prime factor
                while (n % p == 0) { // Remove all occurrences of this prime
                    n /= p;
                }
                result -= result / p; // Apply formula: result *= (1 - 1/p)
            }
            p++; // Move to next potential prime
        }
        if (n > 1) { // If any prime factor > sqrt(n) remains
            result -= result / n; // Apply formula for last prime factor
        }
        return result; // Return the totient value
    }

    public static void main(String[] args) throws IOException {
        int n = 10; // Example number
        System.out.println("φ(" + n + ") = " + eulerTotient(n));
    }
}

/* ----------------------------------------------------------------------------  
   ( The Authentic JS/JAVA CodeBuff )
 ___ _                      _              _ 
 | _ ) |_  __ _ _ _ __ _ __| |_ __ ____ _ (_)
 | _ \ ' \/ _` | '_/ _` / _` \ V  V / _` || |
 |___/_||_\__,_|_| \__,_\__,_|\_/\_/\__,_|/ |
                                        |__/ 
   ----------------------------------------------------------------------------
   Youtube : https://youtube.com/@code-with-Bharadwaj
   Github  : https://github.com/Manu577228
   Portfolio : https://manu-bharadwaj-portfolio.vercel.app/portfolio
   ----------------------------------------------------------------------------
*/

import java.io.*;
import java.util.*;

public class Main {

    // Function to find modular inverse using Extended Euclidean Algorithm
    static long modInverse(long a, long m) {
        long m0 = m;
        long x0 = 0, x1 = 1;
        if (m == 1) return 0;

        // Run Extended Euclidean Algorithm
        while (a > 1) {
            long q = a / m;
            long temp = m;
            m = a % m;
            a = temp;

            temp = x0;
            x0 = x1 - q * x0;
            x1 = temp;
        }

        // Ensure x1 is positive
        if (x1 < 0)
            x1 += m0;

        return x1;
    }

    // Function to apply Chinese Remainder Theorem
    static long chineseRemainder(long[] num, long[] rem) {
        // Step 1: Compute product of all moduli
        long prod = 1;
        for (long n : num)
            prod *= n;

        long result = 0;

        // Step 2: Apply formula for each congruence
        for (int i = 0; i < num.length; i++) {
            long pp = prod / num[i]; // partial product
            long inv = modInverse(pp, num[i]); // modular inverse
            result += rem[i] * inv * pp; // add term to result
        }

        // Step 3: Return the unique solution modulo total product
        return result % prod;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        // Example input arrays
        long[] num = {3, 4, 5};  // moduli (pairwise coprime)
        long[] rem = {2, 3, 1};  // remainders

        // Step 4: Compute final answer
        long x = chineseRemainder(num, rem);

        pw.println("The solution x is: " + x);
        pw.flush();
    }
}

/* ----------------------------------------------------------------------------  
/*   ( The Authentic JS/JAVA CodeBuff )
 ___ _                      _              _ 
 | _ ) |_  __ _ _ _ __ _ __| |_ __ ____ _ (_)
 | _ \ ' \/ _` | '_/ _` / _` \ V  V / _` || |
 |___/_||_\__,_|_| \__,_\__,_|\_/\_/\__,_|/ |
                                        |__/ 
/* --------------------------------------------------------------------------   */
/*    Youtube: https://youtube.com/@code-with-Bharadwaj                        */
/*    Github : https://github.com/Manu577228                                  */
/*    Portfolio : https://manu-bharadwaj-portfolio.vercel.app/portfolio       */
/* -----------------------------------------------------------------------  */

import java.io.*;
import java.util.*;

public class SieveOfEratosthenesAlgo {
    public static void main(String[] args) throws IOException {

        // Using BufferedReader for fast input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        // Prompt user to enter upper limit
        out.print("Enter the limit to find all prime numbers up to n: ");
        out.flush(); // Flush to ensure prompt appears before input
        int n = Integer.parseInt(br.readLine());

        // Initialize boolean array to track primality
        // true -> prime, false -> not prime
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true); // Initially assume all numbers are prime

        // 0 and 1 are not prime numbers
        isPrime[0] = false;
        isPrime[1] = false;

        // Start checking from first prime number 2
        int p = 2;
        while (p * p <= n) {
            if (isPrime[p]) {
                // Mark all multiples of p (starting from p*p) as not prime
                for (int i = p * p; i <= n; i += p) {
                    isPrime[i] = false;
                }
            }
            p++;
        }

        // Collect all prime numbers in a list
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) primes.add(i);
        }

        // Output the list of prime numbers
        out.println("Prime numbers up to " + n + " are: " + primes);

        out.close();
    }
}

/*
------------------------------------------------------------
🧠 Algorithm Explanation — Sieve of Eratosthenes
------------------------------------------------------------

1️⃣ Start with all numbers from 2 to n and assume they are prime.
2️⃣ Begin from p = 2 (the first prime number).
3️⃣ Mark all multiples of p (p*p, p*p+p, p*p+2p, ...) as not prime.
4️⃣ Move to the next number which is still marked as prime.
5️⃣ Repeat steps 3–4 until p*p > n.
6️⃣ The remaining unmarked numbers are primes.

------------------------------------------------------------
⚙️ Time Complexity: O(n log log n)
💾 Space Complexity: O(n)
------------------------------------------------------------

✅ One of the most efficient algorithms to generate primes 
up to a given limit — widely used in number theory, 
cryptography, and competitive programming.
------------------------------------------------------------
*/

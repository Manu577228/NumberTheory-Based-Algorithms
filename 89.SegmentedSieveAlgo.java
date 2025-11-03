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

import java.util.*;
import java.io.*;

public class SegmentedSieve {

    // Step 1: Simple Sieve to find primes up to sqrt(R)
    static List<Integer> simpleSieve(int limit) {
        boolean[] sieve = new boolean[limit + 1];
        Arrays.fill(sieve, true);
        sieve[0] = sieve[1] = false;

        for (int i = 2; i * i <= limit; i++) {
            if (sieve[i]) {
                for (int j = i * i; j <= limit; j += i) {
                    sieve[j] = false;
                }
            }
        }

        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= limit; i++) {
            if (sieve[i]) primes.add(i);
        }
        return primes;
    }

    // Step 2: Segmented Sieve to find primes in range [L, R]
    static List<Integer> segmentedSieve(long L, long R) {
        int limit = (int) Math.sqrt(R) + 1;
        List<Integer> primes = simpleSieve(limit);

        boolean[] isPrime = new boolean[(int) (R - L + 1)];
        Arrays.fill(isPrime, true);

        for (int p : primes) {
            long start = Math.max((long) p * p, ((L + p - 1) / p) * (long) p);
            for (long j = start; j <= R; j += p) {
                isPrime[(int) (j - L)] = false;
            }
        }

        if (L == 1) isPrime[0] = false;

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < isPrime.length; i++) {
            if (isPrime[i]) result.add((int) (L + i));
        }

        return result;
    }

    public static void main(String[] args) {
        long L = 10;
        long R = 50;
        List<Integer> primesInRange = segmentedSieve(L, R);

        System.out.println("Prime numbers between " + L + " and " + R + ":");
        System.out.println(primesInRange);
    }
}

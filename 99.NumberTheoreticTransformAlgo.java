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

public class Main {

    static final int MOD = 998244353;   // Prime modulus (k*2^n + 1)
    static final int ROOT = 3;          // Primitive root of MOD

    // -------------------- Fast Exponentiation -------------------- //
    static long power(long a, long b, int mod) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) res = res * a % mod;  // If exponent is odd
            a = a * a % mod;                        // Square base
            b >>= 1;                                // Divide exponent by 2
        }
        return res;
    }

    // -------------------- Number Theoretic Transform -------------------- //
    static void ntt(long[] a, boolean invert) {
        int n = a.length;

        // Bit-reversal permutation
        for (int i = 1, j = 0; i < n; i++) {
            int bit = n >> 1;
            for (; (j & bit) != 0; bit >>= 1)
                j ^= bit;
            j ^= bit;
            if (i < j) {
                long temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }

        // Cooley–Tukey iterative NTT
        for (int len = 2; len <= n; len <<= 1) {
            long wlen = power(ROOT, (MOD - 1) / len, MOD);
            if (invert) wlen = power(wlen, MOD - 2, MOD); // Inverse root

            for (int i = 0; i < n; i += len) {
                long w = 1;
                for (int j = 0; j < len / 2; j++) {
                    long u = a[i + j];
                    long v = a[i + j + len / 2] * w % MOD;
                    a[i + j] = (u + v) % MOD;
                    a[i + j + len / 2] = (u - v + MOD) % MOD;
                    w = w * wlen % MOD;
                }
            }
        }

        // Scaling for inverse NTT
        if (invert) {
            long invN = power(n, MOD - 2, MOD);
            for (int i = 0; i < n; i++) a[i] = a[i] * invN % MOD;
        }
    }

    // -------------------- Polynomial Multiplication -------------------- //
    static long[] multiply(long[] a, long[] b) {
        int n = 1;
        while (n < a.length + b.length) n <<= 1;

        long[] f = new long[n];
        long[] g = new long[n];

        // Copy input coefficients
        for (int i = 0; i < a.length; i++) f[i] = a[i];
        for (int i = 0; i < b.length; i++) g[i] = b[i];

        ntt(f, false);  // Forward NTT
        ntt(g, false);  // Forward NTT

        for (int i = 0; i < n; i++) f[i] = f[i] * g[i] % MOD;  // Point-wise multiplication

        ntt(f, true);   // Inverse NTT to get final coefficients
        return f;
    }

    // -------------------- Main Function -------------------- //
    public static void main(String[] args) throws IOException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        long[] A = {1, 2, 3};   // Polynomial 1: 1 + 2x + 3x²
        long[] B = {4, 5, 6};   // Polynomial 2: 4 + 5x + 6x²

        long[] res = multiply(A, B);

        out.write("Resultant Polynomial Coefficients: ");
        for (long v : res) out.write(v + " ");
        out.newLine();

        out.flush();
    }
}

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

public class Main {

    // Recursive FFT function
    static Complex[] fft(Complex[] a) {
        int n = a.length;
        if (n == 1) return new Complex[]{a[0]}; // Base case

        // Split array into even and odd indexed elements
        Complex[] even = new Complex[n / 2];
        Complex[] odd = new Complex[n / 2];
        for (int i = 0; i < n / 2; i++) {
            even[i] = a[2 * i];
            odd[i] = a[2 * i + 1];
        }

        // Recursively compute FFT for both halves
        Complex[] fftEven = fft(even);
        Complex[] fftOdd = fft(odd);

        // Result array
        Complex[] res = new Complex[n];
        for (int k = 0; k < n / 2; k++) {
            // Compute twiddle factor: e^(-2πik/n)
            double angle = -2 * Math.PI * k / n;
            Complex w = new Complex(Math.cos(angle), Math.sin(angle));

            // Combine even and odd parts
            Complex t = w.multiply(fftOdd[k]);
            res[k] = fftEven[k].add(t);
            res[k + n / 2] = fftEven[k].subtract(t);
        }

        return res;
    }

    // Complex number class
    static class Complex {
        double re, im;
        Complex(double re, double im) {
            this.re = re;
            this.im = im;
        }

        Complex add(Complex b) {
            return new Complex(this.re + b.re, this.im + b.im);
        }

        Complex subtract(Complex b) {
            return new Complex(this.re - b.re, this.im - b.im);
        }

        Complex multiply(Complex b) {
            double real = this.re * b.re - this.im * b.im;
            double imag = this.re * b.im + this.im * b.re;
            return new Complex(real, imag);
        }

        public String toString() {
            if (Math.abs(im) < 1e-10) return String.format("(%.2f+0j)", re);
            if (Math.abs(re) < 1e-10) return String.format("(0+%.2fj)", im);
            if (im < 0) return String.format("(%.2f%.2fj)", re, im);
            return String.format("(%.2f+%.2fj)", re, im);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        // Example input
        Complex[] a = {
            new Complex(0, 0),
            new Complex(1, 0),
            new Complex(2, 0),
            new Complex(3, 0)
        };

        // Compute FFT
        Complex[] result = fft(a);

        pw.println("FFT Output:");
        for (Complex val : result) pw.println(val);
        pw.flush();
    }
}

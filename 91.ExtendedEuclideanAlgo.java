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

public class Main {
    
    // Function to implement Extended Euclidean Algorithm
    static int extendedGCD(int a, int b, int[] coeff) {
        // coeff[0] = x, coeff[1] = y
        if (b == 0) {
            coeff[0] = 1; // x = 1
            coeff[1] = 0; // y = 0
            return a;     // gcd is a
        }

        int[] temp = new int[2];
        int g = extendedGCD(b, a % b, temp);
        // temp[0] = x1, temp[1] = y1 such that b*x1 + (a%b)*y1 = g

        coeff[0] = temp[1];
        coeff[1] = temp[0] - (a / b) * temp[1];
        // update x and y using recursive results

        return g;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int a = 240;
        int b = 46;

        int[] coeff = new int[2];
        int g = extendedGCD(a, b, coeff);

        pw.println("gcd(" + a + ", " + b + ") = " + g);
        pw.println("Coefficients: x = " + coeff[0] + ", y = " + coeff[1]);
        pw.println("Check: " + a + "*" + coeff[0] + " + " + b + "*" + coeff[1] + " = " + (a * coeff[0] + b * coeff[1]));

        pw.flush();
    }
}

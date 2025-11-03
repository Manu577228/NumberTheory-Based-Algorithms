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
    // Step 1: Define a function to calculate GCD using Euclidean Algorithm
    static int gcd(int a, int b) {
        while (b != 0) {        // Step 2: Continue until remainder (b) becomes zero
            int temp = b;       // Step 3: Store 'b' temporarily
            b = a % b;          // Step 4: Replace 'b' with the remainder
            a = temp;           // Step 5: Replace 'a' with the old value of 'b'
        }
        return a;               // Step 6: When b = 0, 'a' contains the GCD
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        // Step 7: Initialize two numbers
        int num1 = 48;
        int num2 = 18;

        // Step 8: Call the gcd() function
        int result = gcd(num1, num2);

        // Step 9: Print the output
        out.println("GCD of " + num1 + " and " + num2 + " is: " + result);
        out.flush();
    }
}

# The Euclidean Algorithm is a method to find the Greatest Common Divisor (GCD) of two integers. 
# It works by repeatedly replacing the larger number with the remainder when the 
# larger number is divided by the 
# smaller one — until the remainder becomes zero.

# Explanation

# The idea behind the Euclidean Algorithm is that:

# The GCD of two numbers a and b (where a > b) is the same as the GCD of b and a % b.

# This is because if a number divides both a and b, it must also divide their difference (or remainder).
# By repeatedly applying this rule, we reduce the problem until one number 
# becomes zero — the other number then is the GCD.

def gcd(a, b):
    while b != 0:
        a, b = b, a % b
    return a

num1 = 48
num2 = 18

result = gcd(num1, num2)
print("GCD of", num1, "and", num2, "is:", result)


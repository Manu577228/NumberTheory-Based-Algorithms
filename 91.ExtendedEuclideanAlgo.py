# The Extended Euclidean Algorithm computes the greatest common divisor (GCD) 
# of two integers a and b and also finds integers x and y such that 
# a*x + b*y = gcd(a,b) (Bezout coefficients).
# It's an extension of the standard Euclidean algorithm that returns 
# the coefficients for the linear combination.

# Explanation

# The classic Euclidean algorithm repeatedly replaces (a, b) with (b, a % b) until b becomes 0; 
# the last non-zero a is the gcd.

# The extended version keeps track of how to express each remainder as a 
# linear combination of the original a and b. 
# By propagating those coefficients through the steps we obtain integers 
# x and y satisfying a*x + b*y = gcd(a,b).

# This is especially useful for computing modular inverses: if gcd(a, m) == 1, 
# then x returned by extended Euclid is the modular inverse of a modulo m (possibly after 
# reducing x modulo m).

def extended_gcd(a, b):

    if b == 0:
        return a, 1, 0
    
    g, x1, y1 = extended_gcd(b, a % b)

    x = y1 
    y = x1 - (a // b) * y1

    return g, x, y

A = 240
B = 46
g, x, y = extended_gcd(A, B)
print("gcd({}, {}) = {}".format(A, B, g))
print("Coefficients: x = {}, y = {}".format(x, y))
print("Check: {}*{} + {}*{} = {}".format(A, x, B, y, A*x + B*y))



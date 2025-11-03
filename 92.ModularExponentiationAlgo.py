# Modular exponentiation efficiently computes a^b mod m without ever forming the huge number a^b. 
# It uses binary exponentiation (exponentiation by squaring) to reduce the number of multiplications to
# O(log b).

# Explanation

# The key idea: write the exponent in binary and repeatedly 
# square the base while consuming bits of the exponent.

# Keep a running result initialized to 1.

# Reduce base = base % mod at the start to avoid large numbers.

# For each bit of exponent (from least significant to most):

# If the bit is 1, multiply result = (result * base) % mod.

# Square base = (base * base) % mod.

# Shift exponent right by one bit.

# This uses only O(log b) multiplications and always keeps intermediate values 
# below mod*mod, so it's fast and memory-efficient. Many languages (Python pow(a,b,mod)) 
# implement the same idea natively.

def modular_pow(base, exponent, mod):
    result = 1
    base = base % mod
    while exponent > 0:
        if((exponent & 1) == 1):
            result = (result * base) % mod
        base = (base * base) % mod
        exponent >>= 1

    return result

# Example 1: small numbers (for easy manual verification)
a1, e1, m1 = 3, 13, 7
print("Example 1: compute 3^13 mod 7")
print("modular_pow ->", modular_pow(a1, e1, m1))
# verify with python builtin pow (3 arguments uses fast modular exponentiation internally)
print("builtin pow  ->", pow(a1, e1, m1))

# Example 2: large exponent (shows efficiency and avoids huge intermediate numbers)
a2, e2, m2 = 2, 1000, 1000000007
print("Example 2: compute 2^1000 mod 1000000007")
print("modular_pow ->", modular_pow(a2, e2, m2))
print("builtin pow  ->", pow(a2, e2, m2))


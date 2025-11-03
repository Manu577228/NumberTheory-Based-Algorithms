# A modular inverse of a modulo m is a number x such that (a * x) % m == 1. 
# It exists iff gcd(a, m) == 1 (i.e., a and m are coprime). 
# For prime m we can compute it quickly with exponentiation using 
# Fermat’s little theorem.

# Explanation (clear, step-by-step)

# The modular inverse x satisfies a * x ≡ 1 (mod m).

# If gcd(a, m) != 1, no inverse exists because a shares a common factor with m and 
# you cannot get 1 as a linear combination.

# Two common algorithms:

# Extended Euclidean Algorithm (EEA) — finds integers x, y such that 
# a*x + m*y = gcd(a,m). If gcd == 1, x (mod m) is the inverse. Works for any m.

# Fermat’s Little Theorem — if m is prime, inverse = a^(m-2) mod m (fast with modular exponentiation).

# EEA is general and safe for non-prime modulus; Fermat's method is faster when modulus is prime.

def extended_gcd(a, b):
    if b == 0:
        return (a, 1, 0)
    g, x1, y1 = extended_gcd(b, a % b)
    x = y1
    y = x1 - (a // b) * y1
    return (g, x, y)

def modinv_eea(a, m):
    g, x, _ = extended_gcd(a, m)
    if g != 1:
        return None
    return x % m

def modinv_fermat(a, m):
    return pow(a, m - 2, m)

# Demo examples and printing output
def demo():
    examples = [
        (3, 11),   # coprime, prime modulus: inverse exists (should be 4)
        (10, 17),  # coprime, prime modulus: inverse exists (should be 12)
        (6, 9)     # gcd != 1, inverse does not exist
    ]

    for a, m in examples:
        print(f"Example: a = {a}, m = {m}")
        inv1 = modinv_eea(a, m)
        if inv1 is None:
            print("  EEA: No modular inverse (gcd != 1)")
        else:
            print(f"  EEA: modular inverse = {inv1}  (check: {(a * inv1) % m} )")
        # use Fermat only when modulus is prime — here we demonstrate when it's safe
        if is_probably_prime(m):
            inv2 = modinv_fermat(a, m)
            print(f"  Fermat: modular inverse = {inv2}  (check: {(a * inv2) % m} )")
        else:
            print("  Fermat: skipped (modulus not prime)")
        print()

# Simple (deterministic for small m) primality check for demo purposes
def is_probably_prime(n):
    if n < 2:
        return False
    if n % 2 == 0:
        return n == 2
    r = 3
    while r * r <= n:
        if n % r == 0:
            return False
        r += 2
    return True

# Run demo
if __name__ == "__main__":
    demo()

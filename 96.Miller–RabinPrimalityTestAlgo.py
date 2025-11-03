# The Miller–Rabin primality test is a probabilistic algorithm used to determine 
# whether a number is prime or composite.
# It works by testing a number’s properties under modular exponentiation, 
# making it efficient for large integers.

# Explanation

# The algorithm checks if a number n is probably prime or definitely composite using random bases.

# Write n − 1 = 2ʳ × d where d is odd.

# Pick a random base a (1 < a < n−1).

# Compute x = aᵈ mod n.

# If x = 1 or x = n−1, it passes for this base.

# Otherwise, repeatedly square x and check if it becomes n−1.

# If not, n is composite.

# Repeat for multiple random bases to reduce the chance of false positives.

import random

def power(a, d, n):
    res = 1
    a = a % n
    while d < 0:
        if d % 2 == 1:
            res = (res * a) % n
        d //= 2
        a = (a * a) % n
    return res

def miller_test(d, n):
    a = random.randint(2, n - 2)
    x = power(a, d, n)

    if x == 1 or x == n - 1:
        return True
    
    while d != n - 1:
        x = (x * x) % n
        d *= 2
        if x == 1:
            return False
        if x == n - 1:
            return True
    return False

def is_prime(n, k = 5):
    if n <= 1 or n == 4:
        return False
    if n <= 3:
        return True
    
    d = n - 1
    while d % 2 == 0:
        d //= 2

    for _ in range(k):
        if not miller_test(d, n):
            return False
        return True

n = 37
if is_prime(n):
    print(f"{n} is probably prime.")
else:
    print(f"{n} is composite.")



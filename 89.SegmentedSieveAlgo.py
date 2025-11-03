# The Segmented Sieve Algorithm is an optimized version of the Sieve of Eratosthenes 
# used to find all prime numbers in a given range [L, R], especially when R is very large. 
# It divides the range into smaller segments and uses precomputed small primes 
# to mark non-primes efficiently.

# Explanation:

# The Sieve of Eratosthenes efficiently finds all primes up to √R.
# The Segmented Sieve then uses these small primes to mark multiples in each segment [L, R].

# This approach avoids storing an unnecessarily large array when R is big (e.g., up to 10¹²).

# Steps:

# Find all primes up to √R using the normal sieve.

# Create a boolean array for the segment [L, R].

# For each small prime p, mark its multiples in [L, R] as non-prime.

# Remaining unmarked numbers in [L, R] are prime.

import math

def simple_sieve(limit):
    sieve = [True] * (limit + 1)
    sieve[0] = sieve[1] = False
    for num in range(2, int(math.sqrt(limit)) + 1):
        if sieve[num]:
            for multiple in range(num * num, limit + 1, num):
                sieve[multiple] = False

    return [i for i in range(2, limit + 1) if sieve[i]]

def segmented_sieve(L, R):
    limit = int(math.sqrt(R)) + 1
    primes = simple_sieve(limit)

    is_prime = [True] * (R - L + 1)

    for p in primes:
        start = max(p * p, (L + p - 1) // p * p)
        for j in range(start, R + 1, p):
            is_prime[j - L] = False

    if L == 1:
        is_prime[0] = False

    primes_in_range = [L + i for i in range(R - L + 1) if is_prime[i]]
    return primes_in_range

L = 10
R = 50
result = segmented_sieve(L, R)

print(f"Prime numbers between {L} and {R}:")
print(result)






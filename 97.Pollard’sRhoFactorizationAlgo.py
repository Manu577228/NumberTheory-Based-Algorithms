# Pollard’s Rho is a probabilistic algorithm used to find a non-trivial factor of a
# composite number efficiently.
# It works using a pseudo-random function and Floyd’s cycle
# detection to identify repeating values that reveal factors.

# Explanation

# The algorithm relies on the idea that, for a composite number n, if two numbers x and y become congruent modulo a factor of n, we can find that factor using the greatest common divisor (GCD).

# We generate a sequence using a function like:

# f(x) = (x² + c) % n

# and track two sequences — one moves one step at a time (x),
# and the other moves two steps (y = f(f(y))).
# If they meet, we compute gcd(|x - y|, n) — this often reveals a factor of n.

import random

def gcd(a, b):
    if b == 0:
        return a
    return gcd(b, a % b)


def pollard_rho(n):
    if n % 2 == 0:
        return 2

    c = random.randint(1, n - 1)

    x = random.randint(0, n - 1)
    y = x

    d = 1

    while d == 1:
        x = (x * x + c) % n
        y = (y * y + c) % n
        y = (y * y + c) % n

        d = gcd(abs(x - y), n)

        if d == n:
            return pollard_rho(n)

    return d


n = 8051
factor = pollard_rho(n)
print("A non-trivial factor of", n, "is:", factor)

# The Chinese Remainder Theorem is a number theory algorithm that provides a unique 
# solution to a set of modular equations when the moduli are pairwise coprime.
# In simple terms, it helps find an integer that satisfies several modular constraints simultaneously.

# 🔹 Explanation 

# Suppose we have the following system of congruences:

# x ≡ a1 (mod m1)
# x ≡ a2 (mod m2)
# x ≡ a3 (mod m3)

# If m1, m2, and m3 are pairwise coprime, then there exists a unique solution modulo M, where

# M = m1 × m2 × m3

# To find that solution, we use the formula:

# x = (a1 × M1 × y1 + a2 × M2 × y2 + a3 × M3 × y3) mod M

# Where:

# M1 = M / m1

# M2 = M / m2

# M3 = M / m3

# yi is the modular inverse of Mi modulo mi

# This method efficiently combines multiple modular equations into a single unique solution.

def mod_inverse(a, m):
    m0 = m
    x0, x1 = 0, 1

    while a > 1:
        q = a // m
        a, m = m, a % m
        x0, x1 = x1 - q * x0, x0
    if x1 < 0:
        x1 += m0
    return x1

def chinese_remainder(nums, rems):
    prod = 1
    for n in nums:
        prod *= n

    result = 0

    for n_i, a_i in zip(nums, rems):
        p = prod // n_i
        result += a_i * mod_inverse(p, n_i) * p

    return result % prod

nums = [3, 4, 5]
rems = [2, 3, 1]

x = chinese_remainder(nums, rems)

print("The solution x is:", x)




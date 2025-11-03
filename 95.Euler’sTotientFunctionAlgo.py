# Euler’s Totient Function, denoted as φ(n), counts how many integers from 1 to n 
# are coprime to n (i.e., numbers whose greatest common divisor with n is 1).
# In simple words — it tells how many numbers less than or equal to n share 
# no common factors with it other than 1.

# Explanation

# The key property of Euler’s Totient Function is:

# If

# n = p₁^a₁ × p₂^a₂ × ... × pₖ^aₖ


# (where p₁, p₂, ..., pₖ are distinct prime factors of n),
# then

# φ(n) = n × (1 - 1/p₁) × (1 - 1/p₂) × ... × (1 - 1/pₖ)


# For example,
# if n = 10 = 2 × 5,
# then
# φ(10) = 10 × (1 - 1/2) × (1 - 1/5) = 10 × (1/2) × (4/5) = 4

# So there are 4 numbers (1, 3, 7, 9) that are coprime with 10.

def euler_totient(n):
    result = n
    p = 2
    while p * p <= n:
        if n % p == 0:
            while n % p == 0:
                n //= p
            result -= result // p
        p += 1
    if n > 1:
        result -= result // n
    return result

n = 10
print("φ(", n, ") =", euler_totient(n))

            
    
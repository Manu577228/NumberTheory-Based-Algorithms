# The Sieve of Eratosthenes is an efficient algorithm to find all 
# prime numbers up to a given limit n.
# It works by iteratively marking the multiples of each prime number 
# starting from 2, the first prime.

# Explanation

# Instead of checking each number for primality one by one (which is slow), 
# the Sieve of Eratosthenes eliminates multiples of every found prime number.
# You begin with a list of numbers from 2 to n, then repeatedly mark all 
# multiples of 2, 3, 5, etc., as not prime.
# The numbers left unmarked at the end are the prime numbers.

# The time complexity of this algorithm is O(n log log n), which makes 
# it highly efficient for large values of n.

n = int(input("Enter the limit to find all prime numbers up to n: "))

is_prime = [True] * (n + 1)
is_prime[0] = is_prime[1] = False

p = 2
while p * p <= n:
    if is_prime[p]:
        for i in range(p * p, n + 1, p):
            is_prime[i] = False
    p += 1

primes = []
for i in range(2, n + 1):
    if is_prime[i]:
        primes.append(i)

print("Prime numbers upto", n, "are:", primes)


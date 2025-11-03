# The Number Theoretic Transform (NTT) is the modular 
# arithmetic version of the Fast Fourier Transform (FFT).
# It’s used to perform polynomial multiplication efficiently under modular 
# arithmetic, avoiding floating-point errors.

# Detailed Explanation

# The NTT works similarly to FFT but uses a primitive root of 
# unity modulo a prime number instead of complex roots of unity.
# It allows us to:

# Multiply large polynomials efficiently (O(n log n) instead of O(n²))

# Work entirely with integers under a prime modulus p = k * 2^n + 1

# For example, modulus p = 998244353 and primitive root 
# g = 3 are often used in competitive programming.

MOD = 998244353
ROOT = 3

def power(a, b, mod):
    res = 1
    while b > 0:
        if b & 1:
            res = res * a % mod
        a = a * a % mod
        b >>= 1
    return res

def ntt(a, invert):
    n = len(a)
    j = 0
    for i in range(1, n):
        bit = n >> 1
        while j & bit:
            j ^= bit
            bit >>= 1
        j ^= bit
        if i < j:
            a[i], a[j] = a[j], a[i]

    len_ = 2
    while len_ <= n:
        wlen = power(ROOT, (MOD - 1) // len_, MOD)
        if invert:
            wlen = power(wlen, MOD - 2, MOD)
        for i in range(0, n, len_):
            w = 1
            for j in range(i, i + len_ // 2):
                u = a[j]
                v = a[j + len_ // 2] * w % MOD
                a[j] = (u + v) % MOD
                a[j + len_ // 2] = (u - v + MOD) % MOD
                w = w * wlen % MOD
        len_ <<= 1

    if invert:
        inv_n = power(n, MOD - 2, MOD)
        for i in range(n):
            a[i] = a[i] * inv_n % MOD

def multiply(poly1, poly2):
    n = 1
    while n < len(poly1) + len(poly2):
        n <<= 1
    f = poly1 + [0] * (n - len(poly1))
    g = poly2 + [0] * (n - len(poly2))

    ntt(f, False)
    ntt(g, False)
    for i in range(n):
        f[i] = f[i] * g[i] % MOD
    ntt(f, True)
    return f

A = [1, 2, 3]
B = [4, 5, 6]

res = multiply(A, B)
print("Resultant Polynomial Coefficients:", res)





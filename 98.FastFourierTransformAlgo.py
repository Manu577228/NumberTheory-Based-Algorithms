# The Fast Fourier Transform (FFT) is an efficient algorithm to compute the 
# Discrete Fourier Transform (DFT) and its inverse.
# It converts a signal from its time domain to the frequency domain, 
# allowing us to analyze frequency components quickly.

# Explanation

# Imagine you have a sound wave (or any signal) represented as a list of sample points.
# The FFT helps us find how much of each frequency is present in that signal — 
# like separating a musical chord into individual notes.

# The naive DFT algorithm has a time complexity of O(N²), but FFT 
# reduces it to O(N log N) by exploiting symmetry and periodicity in complex roots of unity.

# The most commonly used FFT method is the Cooley–Tukey algorithm, 
# which recursively breaks down the problem into smaller subproblems.

import cmath

def fft(a):
    n = len(a)

    if n == 1:
        return a
    
    even = fft(a[0::2])
    odd = fft(a[1::2])

    res = [0] * n

    for k in range(n // 2):
        t = cmath.exp(-2j * cmath.pi * k / n) * odd[k]
        res[k] = even[k] + t
        res[k + n // 2] = even[k] - t

    return res

a = [0, 1, 2, 3]

result = fft(a)

print("FFT Output:")
for val in result:
    print(val)
package org.ict.algorithm.acwing.basicalgos;

/**
 * Find a of b squared to p modulo the value.
 * Input format
 * Three integers a,b,p are separated by spaces on the same line.
 * Output format
 * Output an integer that represents the value of a^b mod p.
 * Data range
 * 0≤a,b≤10^9
 * 1≤p≤10^9
 * @author sniper
 * @date 29 Mar, 2023
 * ACWing89
 */
public class PowerAndModulo {

    /**
     * a=126348976
     * b=982638476
     * p=938420413
     * expected 701649771
     * @param args
     */
    public static void main(String[] args) {
        long a = 126348976;
        long b = 982638476;
        long p = 938420413;

        PowerAndModulo instance = new PowerAndModulo();
        long res = instance.power(a, b, p);
        System.out.println(res);
        System.out.println(Long.MAX_VALUE);
        System.out.println(a * a);
    }


    public long powerV2(long a, long b, long p) {
        long ans = 1 % p;
        while (b > 0) {
            if ((b & 1) > 0) {
                ans = (ans * a) % p;
            }
            a = (a * a) % p;
            b >>= 1;
        }
        return ans;
    }

    /**
     * (A * B) mod C = (A mod C * B mod C) mod C
     * @see <a href="https://www.khanacademy.org/computing/computer-science/cryptography/modarithmetic/a/modular-multiplication"></a>
     * @param a
     * @param b
     * @param p
     * @return
     */
    public long powerV1(long a, long b, long p) {
        long ans = 1 % p;
        for (; b > 0; b >>= 1) {
            if ((b & 1) > 0) {
                ans = ((ans % p) * (a % p)) % p;
            }
            a = ((a % p) * (a % p)) % p;
        }
        return ans;
    }

    public long power(long a, long b, long p) {
        long ans = 1 % p;
        for (; b > 0; b >>= 1) {
            if ((b & 1) == 1) {
                ans = (ans * a) % p;
            }
            a = (a * a) % p;
        }
        return ans;
    }
}

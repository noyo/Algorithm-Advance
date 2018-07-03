package chap3;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/30 19:25
 * @see chap3
 */
public class Uva1428_LA4329 {

    static class BIT {

        long C[];

        public BIT(int n) {
            C = new long[n];
        }

        public int sum(int x) {
            int s = 0;
            while (x > 0) {
                s += C[x];
                x -= x & -x;
            }
            return s;
        }

        public void add(int x, int val) {
            while (x < C.length) {
                C[x] += val;
                x += x & -x;
            }
        }

        public void clear() {
            Arrays.fill(C, 0);
        }
    }

    private static void solve(Scanner sc) {

        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            BIT bit = new BIT(1000001);
            int a[] = new int[n];
            int c[] = new int[n];
            int d[] = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
                bit.add(a[i], 1);
                c[i] = bit.sum(a[i]) - 1;
            }

            bit.clear();
            for (int i = n - 1; i >= 0; i--) {
                bit.add(a[i], 1);
                d[i] = bit.sum(a[i]) - 1;
            }

            long sum = 0;
            for (int i = 1; i < n - 1; i++) {
                sum += c[i] * (n - i - 1 - d[i]);
                sum += d[i] * (i - c[i]);
            }
            System.out.println(sum);
        }
    }

    public static void main (String args[]) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/in.txt"));
        System.setOut(new PrintStream("src/out.txt"));

        solve(new Scanner(System.in));
    }
}

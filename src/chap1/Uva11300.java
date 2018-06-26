package chap1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/21 21:14
 * @see chap1
 */
public class Uva11300 {

    public static void main(String args[]) throws FileNotFoundException {

        System.setIn(new FileInputStream("src/in.txt"));
        System.setOut(new PrintStream("src/out.txt"));

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            int n = sc.nextInt();
            if (n == 0) {
                System.out.println(0);
                continue;
            }
            long a[] = new long[n + 1];
            long C[] = new long[n];
            long sum = 0;

            for (int i = 1; i <= n; i++) {
                a[i] = sc.nextLong();
                sum += a[i];
            }

            long average = sum / n;
            for (int i = 1; i < n; i++) {
                C[i] = C[i - 1] + a[i] - average;
            }
            Arrays.sort(C);

            int mid = n / 2;
            sum = 0;
            for (int i = 0; i < n; i++) {
                sum += Math.abs(C[i] - C[mid]);
            }

            System.out.println(sum);
        }
    }
}

package chap1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
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
            long a[] = new long[n];
            long sum = 0;

            for (int i = 0; i < n; i++) {
                a[i] = sc.nextLong();
                sum += a[i];
            }

            long average = sum / n;
            long result = 0;
            for (int i = 0; i < n; i++) {
                int d = 1;
                long val = a[i] - average;
                if (val <= 0) {
                    continue;
                }
                a[i] = average;
                while (val > 0) {
                    int pre = (i + n - d) % n;
                    int rear = (i + 1) % n;
                    if (a[pre] < average) {
                        if (val >= average - a[pre]) {
                            val -= average - a[pre];
                            result += d * (average - a[pre]);
                            a[pre] = average;
                        } else {
                            a[pre] += val;
                            result += d * (val);
                            val = 0;
                        }
                    }

                    if (a[rear] < average) {
                        if (val >= average - a[rear]) {
                            val -= average - a[rear];
                            result += d * (average - a[rear]);
                            a[rear] = average;
                        } else {
                            a[rear] += val;
                            result += d * (val);
                            val = 0;
                        }
                    }
                    d++;
                }
            }
            System.out.println(result);
        }
    }
}

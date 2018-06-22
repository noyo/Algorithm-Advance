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
 * 2018/6/21 15:56
 * @see chap1
 */
public class Uva11292 {

    public static void main(String args[]) throws FileNotFoundException {

        System.setIn(new FileInputStream("src/in.txt"));
        System.setOut(new PrintStream("src/out.txt"));

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            if ((n | m)== 0) break;
            int a[] = new int[n];
            int b[] = new int[m];

            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }
            for (int i = 0; i < m; i++) {
                b[i] = sc.nextInt();
            }

            Arrays.sort(a);
            Arrays.sort(b);

            int cur = 0;
            int cost = 0;
            for (int i = 0; i < m; i++) {
                if (b[i] >= a[cur]) {
                    cost += b[i];
                    cur++;
                    if (cur == n) {
                        break;
                    }
                }
            }
            if (cur < n) {
                System.out.println("Loowater is doomed!");
            } else {
                System.out.println(cost);
            }
        }
    }
}

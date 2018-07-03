package chap1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/26 16:12
 * @see chap1
 */
public class Uva1030 {

    private static void solve(Scanner sc) {
        while (sc.hasNext()) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            int total = n * n * n;
            boolean empty[][][] = new boolean[n][n][n];

            for (int i = 0; i < n; i++) {

            }

            System.out.format("Maximum weight: %d gram(s)", total);
        }
    }

    public static void main(String args[]) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/in.txt"));
        System.setOut(new PrintStream("src/out.txt"));

        Scanner sc = new Scanner(System.in);
    }
}

package chap1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/27 15:39
 * @see chap1
 */
public class Uva11384 {

    private static int cnt(int n) {
        if (n <= 1) {
            return n;
        }
        return 1 + cnt(n / 2);
    }

    private static void solve(Scanner sc) {
        while (sc.hasNext()) {
            int n = sc.nextInt();
            System.out.println(cnt(n));
        }
    }

    public static void main(String args[]) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/in.txt"));
        System.setOut(new PrintStream("src/out.txt"));

        solve(new Scanner(System.in));
    }
}

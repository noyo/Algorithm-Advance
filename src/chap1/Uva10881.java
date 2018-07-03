package chap1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 * <p>
 * Description:
 * <p>
 * Package: chap1
 * author: Chris
 * date: 2018/6/26 10:46
 */
public class Uva10881 {

    private static class Ant {
        int p;
        int dir;
        int index;

        Ant(int p, int dir, int index) {
            this.dir = dir;
            this.p = p;
            this.index = index;
        }

        @Override
        public String toString() {
            return p + " " + dir + " " + index;
        }
    }

    private static void solve(Scanner sc) {
        int T = sc.nextInt();
        for (int kase = 1; kase <= T; kase++) {
            int L = sc.nextInt();
            int t = sc.nextInt();
            int n = sc.nextInt();
            Ant[] ants = new Ant[n];

            for (int i = 0; i < n; i++) {
                ants[i] = new Ant(sc.nextInt(), "R".equals(sc.next()) ? 1 : -1, i);
            }
            Arrays.sort(ants, Comparator.comparingInt(o -> o.p));
            int order[] = new int[n];
            for (int i = 0; i < n; i++) {
                order[i] = ants[i].index;
            }

            for (int i = 0; i < n; i++) {
                ants[i].p = ants[i].p + ants[i].dir * t;
            }
            Arrays.sort(ants, (o1, o2) -> {
                if (o1.p == o2.p) {
                    o1.dir = o2.dir = 0;
                }
                return o1.p - o2.p;
            });

            int a[] = new int[n];
            int d[] = new int[n];
            for (int i = 0; i < n; i++) {
                int index = order[i];
                a[index] = ants[i].p;
                d[index] = ants[i].dir;
            }

            if (kase > 1) {
                System.out.println();
            }
            System.out.format("Case #%d:\n", kase);
            for (int i = 0; i < n; i++) {
                if (a[i] < 0 || a[i] > L) {
                    System.out.format("%s\n", "Fell off");
                } else {
                    System.out.format("%d %s\n", a[i], d[i] == 0 ? "Turning" : d[i] > 0 ? "R" : "L");
                }
            }
        }
    }

    public static void main(String args[]) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/in.txt"));
        System.setOut(new PrintStream("src/out.txt"));

        Scanner sc = new Scanner(System.in);
        solve(sc);
    }
}

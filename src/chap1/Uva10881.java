package chap1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

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

    private static void solve(Scanner sc) {
        int T = sc.nextInt();
        for (int kase = 1; kase <= T; kase++) {
            int L = sc.nextInt();
            int t = sc.nextInt();
            int n = sc.nextInt();

            int a[] = new int[n + 1];
            int d[] = new int[n + 1];
            Map<Integer, Set<Integer>> cnt = new HashMap<>(L + 1);
            boolean dir[][] = new boolean[n + 1][3];
            boolean right[] = new boolean[n + 1];
            for (int i = 0; i <= L; i++) {
                cnt.put(i, new HashSet<>());
            }
            for (int i = 1; i <= n; i++) {
                a[i] = sc.nextInt();
                cnt.get(a[i]).add(i);
                d[i] = "R".equals(sc.next()) ? 1 : -1;
                dir[a[i]][d[i] + 1] = true;
            }

            boolean turn[] = new boolean[n + 1];
            for (int i = 1; i <= t; i++) {
                Arrays.fill(turn, false);
                for (int j = 1; j <= n; j++) {
                    if (d[j] == 0) {
                        continue;
                    }
                    cnt.get(a[j]).remove(j);
                    dir[a[j]][d[j] + 1] = false;
                    if (a[j] == 0 && d[j] < 0 || a[j] == L && d[j] > 0) {
                        d[j] = 0;
                    } else {
                        if (dir[a[j] + d[j]][1 - d[j]]) {
                            d[j] *= -1;
                        } else {
                            a[j] += d[j];
                            cnt.get(a[j]).add(j);
                        }
                        dir[a[j]][d[j] + 1] = true;
                    }
                }
                for (int j = 1; j < L; j++) {
                    if (cnt.get(j).size() > 1) {
                        for (int key : cnt.get(j)) {
                            d[key] *= -1;
                            turn[key] = true;
                        }
                    }
                }
            }

            System.out.format("Case #%d:\n", kase);
            for (int i = 1; i <= n; i++) {
                System.out.print(a[i]);
                System.out.format(" %s\n", turn[i] ? "Turning"
                        : d[i] == 0 ? "Fell off" : d[i] > 0 ?"R" : "L");
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

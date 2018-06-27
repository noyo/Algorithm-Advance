package chap1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 * <p>
 * Description:
 * <p>
 * Package: chap1
 * author: Chris
 * date: 2018/6/27 9:11
 */
public class Uva11464 {

    private final static int INF = Integer.MAX_VALUE;

    private static int dir[][] = new int[][] {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    private static boolean legal(int r, int c, int n) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }

    private static boolean isEven(int matrix[][], int r, int c) {
        int val = 0;
        for (int i = 0; i < 4; i++) {
            int r1 = r + dir[i][0];
            int c1 = c + dir[i][1];
            if (legal(r1, c1, matrix.length)) {
                val += matrix[r1][c1];
            }
        }
        return val % 2 == 0;
    }

    private static int pushDown(int matrix[][], int cnt, int r) {
        if (r == matrix.length) {
            return cnt;
        }

        for (int j = 0; j < matrix.length; j++) {
            boolean even = isEven(matrix, r, j);
            if (!even) {
                if (matrix[r + 1][j] == 1) {
                    return INF;
                }
                matrix[r + 1][j] = 1;
                cnt++;
            }
        }
        return pushDown(matrix, cnt, r + 1);
    }

    private static void solve(Scanner sc) {
        int T = sc.nextInt();
        for (int kase = 1; kase <= T; kase++) {
            int n = sc.nextInt();
            int matrix[][] = new int[n][n];
            int cnt = n;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = sc.nextInt();
                    if (i == 0) {
                        cnt -= matrix[i][j];
                    }
                }
            }
            if (n <= 1) {
                System.out.format("Case %d: %d\n", kase, 0);
            }

            int q = 1 << cnt;
            int min = INF;
            for (int i = 0; i < q; i++) {
                int cur = i;
                cnt = 0;
                int[][] tmp = new int[n][n];
                for (int k = 0; k < n; k++) {
                    tmp[k] = matrix[k].clone();
                }
                for (int j = 0; j < n; j++) {
                    if (tmp[0][j] == -1) {
                        continue;
                    }
                    if (cur % 2 == 1) {
                        tmp[0][j] = 1;
                        cnt++;
                    }
                    cur >>= 1;
                }
                min = Math.min(min, pushDown(tmp, cnt, 0));
            }
            System.out.format("Case %d: %d\n", kase, min == INF ? -1 : min);
        }
    }

    public static void main(String args[]) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/in.txt"));
        System.setOut(new PrintStream("src/out.txt"));

        solve(new Scanner(System.in));
    }
}

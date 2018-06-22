package chap5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/22 9:58
 * @see chap5
 */
public class Uva11624 {

    private static int R, C;
    private static int rJ, cJ;
    private static List<Point> fp;
    private static int matrix[][];

    private static class Point {
        int r;
        int c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return r + ", " + c;
        }
    }

    private static int dir[][] = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    private static boolean isLegal(int r, int c, int R, int C) {
        return r >= 1 && r <= R && c >= 1 && c <= C;
    }

    private static boolean isBorder(int r, int c, int R, int C) {
        return r == 1 || r == R || c == 1 || c == C;
    }

    private static int getout() {

        if (isBorder(rJ, cJ, R, C)) {
            return 1;
        }

        Queue<Point> queue = new LinkedList<>();
        Queue<Point> fire = new LinkedList<>();
        queue.offer(new Point(rJ, cJ));
        for (Point p : fp) {
            fire.offer(p);
        }
        int cnt = 1;
        int lastN = 1;
        int n = 0;
        int lastFN = fp.size();
        int fN = 0;

        while (!queue.isEmpty()) {

            n = 0;
            fN = 0;

            for (int i = 0; i < lastFN; i++) {
                Point point = fire.poll();
                for (int j = 0; j < 4; j++) {
                    int r = point.r + dir[j][0];
                    int c = point.c + dir[j][1];
                    if (!isLegal(r, c, R, C) || matrix[r][c] <= cnt) {
                        continue;
                    }
                    fire.offer(new Point(r, c));
                    matrix[r][c] = matrix[point.r][point.c] + 1;
                    fN++;
                }
            }

            for (int i = 0; i < lastN; i++) {
                Point point = queue.poll();
                for (int j = 0; j < 4; j++) {
                    int r = point.r + dir[j][0];
                    int c = point.c + dir[j][1];
                    if (matrix[r][c] <= cnt) {
                        continue;
                    }
                    if (isBorder(r, c, R, C)) {
                        return cnt + 1;
                    }
                    matrix[r][c] = -1;
                    queue.offer(new Point(r, c));
                    n++;
                }
            }
            lastN = n;
            lastFN = fN;
            cnt++;
        }

        return -1;
    }

    private static void input(Scanner sc) {
        R = sc.nextInt();
        C = sc.nextInt();
        matrix = new int[R + 1][C + 1];
         fp = new ArrayList<>();

        for (int i = 1; i <= R; i++) {
            Arrays.fill(matrix[i], 2000);
            String s = sc.next();
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '#') {
                    matrix[i][j + 1] = -1;
                } else if (s.charAt(j) == 'F') {
                    fp.add(new Point(i, s.indexOf('F') + 1));
                    matrix[i][s.indexOf('F') + 1] = 0;
                } else if (s.charAt(j) == 'J') {
                    rJ = i;
                    cJ = s.indexOf('J') + 1;
                }
            }
        }
    }

    private static void solve(Scanner sc) {
        int T = sc.nextInt();
        while (T-- > 0) {
            input(sc);

            int cnt = getout();
            if (cnt < 0) {
                System.out.println("IMPOSSIBLE");
            } else {
                System.out.println(cnt);
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

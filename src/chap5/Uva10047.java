package chap5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Copyright © 2018 Chris. All rights reserved.
 * <p>
 * Description:
 * <p>
 * Package: chap5
 * author: Chris
 * date: 2018/6/22 14:40
 */
public class Uva10047 {

    private static final int INF = 1000;
    private static int R, C;
    private static int rS, cS;
    private static int rT, cT;
    private static int matrix[][];
    private static boolean vst[][][][];

    private static class Point {
        int r;
        int c;
        int cnt;
        int dir;
        int reverse = 0;

        Point() {
        }

        Point(int r, int c, int dir, int cnt) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return r + " " + c + " " + dir + " " + cnt + " " + reverse;
        }
    }

    private static int dir[][] = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    private static boolean isLegal(int r, int c) {
        return r >= 1 && r <= R && c >= 1 && c <= C && matrix[r][c] != INF;
    }

    private static int roll() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(rS, cS, 1, 0));
        vst[rS][cS][0][1] = true;
        int n;
        int lastN = 1;

        while (!queue.isEmpty()) {
            n = 0;

            for (int i = 0; i < lastN; i++) {
                Point point = queue.poll();
                if (point.r == rT && point.c == cT && point.cnt % 5 == 0) {
                    return point.cnt + point.reverse;
                }
                int curR = point.r;
                int curC = point.c;
                for (int j = -1; j < 2; j += 2) {
                    int dir = (point.dir + 4 + j) % 4;
                    if (!vst[curR][curC][point.cnt % 5][dir]) {
                        Point p = new Point(curR, curC, dir, point.cnt);
                        vst[curR][curC][point.cnt % 5][dir] = true;
                        p.reverse += point.reverse + 1;
                        queue.offer(p);
                        n++;
                    }
                }

                int r = dir[point.dir][0] + curR;
                int c = dir[point.dir][1] + curC;
                if (isLegal(r, c)) {
                    Point p = new Point(r, c, point.dir, point.cnt + 1);
                    p.reverse = point.reverse;
                    queue.offer(p);

                    vst[r][c][p.cnt % 5][p.dir] = true;
                    n++;
                }
            }

            lastN = n;
        }
        return -1;
    }

    private static void solve(Scanner sc) {
        int kase = 1;
        while (sc.hasNext()) {
            R = sc.nextInt();
            C = sc.nextInt();
            if ((R | C) == 0) {
                break;
            }
            if (kase > 1) {
                System.out.println();
            }
            matrix = new int[R + 1][C + 1];
            vst = new boolean[R + 1][C + 1][5][4];
            for (int i = 1; i <= R; i++) {
                String s = sc.next();
                for (int j = 1; j <= C; j++) {
                    switch (s.charAt(j - 1)) {
                        case '#':
                            matrix[i][j] = INF;
                            break;
                        case 'S':
                            rS = i;
                            cS = j;
                            break;
                        case 'T':
                            rT = i;
                            cT = j;
                            break;
                        default:
                            break;
                    }
                }
            }

            int cnt = roll();
            System.out.format("Case #%d\n", kase++);
            if (cnt < 0) {
                System.out.println("destination not reachable");
            } else {
                System.out.format("minimum time = %d sec\n", cnt);
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

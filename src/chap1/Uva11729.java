package chap1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/21 19:43
 * @see chap1
 */
public class Uva11729 {

    private static class Job {
        int b;
        int j;

        Job(int b, int j) {
            this.b = b;
            this.j = j;
        }

        @Override
        public String toString() {
            return b + ", " + j;
        }
    }

    public static void main(String args[]) throws FileNotFoundException {

        System.setIn(new FileInputStream("src/in.txt"));
        System.setOut(new PrintStream("src/out.txt"));

        Scanner sc = new Scanner(System.in);
        int kase = 1;

        while (sc.hasNext()) {
            int n = sc.nextInt();
            if (n == 0) break;

            List<Job> jobs = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                jobs.add(new Job(sc.nextInt(), sc.nextInt()));
            }

            jobs.sort(Comparator.comparingInt(o -> -o.j));
            int endJ = 0;
            int startB = 0;
            for (int i = 0; i < n; i++) {
                startB += jobs.get(i).b;
                endJ = Math.max(endJ, startB + jobs.get(i).j);
            }
            System.out.format("Case %d: %d\n", kase++, endJ);
        }
    }
}

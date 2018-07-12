package chap2;

import java.io.*;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/7/12 20:22
 * @see chap2
 */
public class Uva11538 {

    private static BufferedReader br;
    private static StreamTokenizer st;
    private static PrintWriter pw;

    private static void solve() throws IOException {
        while (true) {
            long n = nextLong();
            long m = nextLong();
            if ((n | m) == 0) {
                break;
            }

            if (n > m) {
                n ^= m;
                m ^= n;
                n ^= m;
            }
            long ans = m * n;
            ans = ans * (m + n - 2) + 2 * n * (n - 1) * (3 * m - n - 1) / 3;
            pw.println(ans);
        }
    }

    public static void main(String args[]) throws IOException {
        boolean oj = System.getProperty("ONLINE_JUDGE") != null;
        if (!oj) {
            System.setIn(new FileInputStream("src/in.txt"));
            System.setOut(new PrintStream("src/out.txt"));
        }
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StreamTokenizer(br);
        pw = new PrintWriter(new OutputStreamWriter(System.out));
        st.ordinaryChar('\''); //指定单引号、双引号和注释符号是普通字符
        st.ordinaryChar('\"');
        st.ordinaryChar('/');

        long t = System.currentTimeMillis();
        solve();
        if (!oj) {
            pw.println("[" + (System.currentTimeMillis() - t) + "ms]");
        }
        pw.flush();
    }

    private static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    private static long nextLong() throws IOException {
        st.nextToken();
        return (long) st.nval;
    }

    private static double nextDouble() throws IOException {
        st.nextToken();
        return st.nval;
    }

    private static String[] nextSS(String reg) throws IOException {
        return br.readLine().split(reg);
    }

    private static String nextLine() throws IOException {
        return br.readLine();
    }
}

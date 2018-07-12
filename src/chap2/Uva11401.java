package chap2;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.io.*;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/7/12 21:12
 * @see chap2
 */
public class Uva11401 {

    private static BufferedReader br;
    private static StreamTokenizer st;
    private static PrintWriter pw;

    private static void solve() throws IOException {

        int N = 1000001;
        long f[] = new long[N];
        for (int i = 4; i < N; i++) {
            long r = i / 2 - 1;
            long val1 = r > 1 ? (r + 1) * r / 2 : 1;
            if (i % 2 == 1) {
                val1 <<= 1;
            } else {
                val1 = (val1 - r) * 2 + r;
            }
            f[i] = f[i - 1] + val1;
        }
//        for (int i = 4; i < N; i++) {
//            f[i] = f[i - 1] + ((i - 1) * (i - 2) / 2 - (i - 1) / 2) / 2;
//            pw.print(f[i] - f[i - 1] + " ");
//        }

        while (true) {
            int n = nextInt();
            if (n < 3) {
                break;
            }

            pw.println(f[n]);
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

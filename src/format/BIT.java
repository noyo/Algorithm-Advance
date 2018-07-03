package format;

import java.util.Arrays;

/**
 * Copyright © 2018 Chris. All rights reserved.
 *
 * @author Chris
 * 2018/6/30 19:27
 * @see format
 */
public class BIT {

    long C[];

    public BIT(int n) {
        C = new long[n];
    }

    public int sum(int x) {
        int s = 0;
        while (x > 0) {
            s += C[x];
            x -= x & -x;
        }
        return s;
    }

    public void add(int x, int val) {
        while (x < C.length) {
            C[x] += val;
            x += x & -x;
        }
    }

    public void clear() {
        Arrays.fill(C, 0);
    }
}

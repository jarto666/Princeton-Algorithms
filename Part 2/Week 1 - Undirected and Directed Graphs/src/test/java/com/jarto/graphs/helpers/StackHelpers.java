package com.jarto.graphs.helpers;

import java.util.Stack;

public class StackHelpers {
    public static int[] toArray(Stack<Integer> st) {
        var res = new int[st.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = st.pop();
        }

        return res;
    }
}

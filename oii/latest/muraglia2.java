import java.util.*;

public class muraglia2 {
    Vector<Integer> ST;
    int dimensione;
    int elementi;
    int counter = 0;

    //cout ST
    public static <T> String toString(Vector<T> v) {
        int c = -1;
        int limit = 1;
        String o = "\n";
        for (T i : v) {
            if (c == limit) {
                limit *= 2;
                c = 0;
                o += "\n";
            }
            o += i + " ";
            c++;
        }
        return o;
    }

    static class Node {
        int index;
        Pair<Integer, Integer> range;

        int get_middle() {
            return (this.range.first + this.range.second) / 2;
        }
    }

    public static String toString(Node v) {
        return v.index + " {" + v.range.first + ", " + v.range.second + "}";
    }

    int left_limit(int h, int x) {
        Stack<Node> S = new Stack<>();
        S.push(new Node(1, new Pair<>(0, dimensione - 1)));

        Node curr;

        while (!S.empty()) {
            curr = S.pop();

            if (curr.index >= 2 * dimensione) continue;

            if (ST.get(curr.index) <= h) {
                continue;
            } else if (x <= curr.range.first) {
                continue;
            } else if (curr.range.second <= x) {
                while (curr.range.first != curr.range.second) {
                    int middle = curr.get_middle();
                    if (ST.get(2 * curr.index + 1) > h) {
                        curr = new Node(2 * curr.index + 1, new Pair<>(middle + 1, curr.range.second));
                    } else {
                        curr = new Node(2 * curr.index, new Pair<>(curr.range.first, middle));
                    }
                }
                if (ST.get(curr.index) > h) {
                    return curr.range.first;
                }
            }
            int middle = curr.get_middle();
            S.push(new Node(2 * curr.index, new Pair<>(curr.range.first, middle)));
            S.push(new Node(2 * curr.index + 1, new Pair<>(middle + 1, curr.range.second)));
        }
        return 0;
    }

    int right_limit(int h, int x) {
        Stack<Node> S = new Stack<>();
        S.push(new Node(1, new Pair<>(0, dimensione - 1)));

        Node curr;

        while (!S.empty()) {
            curr = S.pop();

            if (curr.index >= 2 * dimensione) continue;

            if (ST.get(curr.index) <= h) {
                continue;
            } else if (curr.range.second <= x) {
                continue;
            } else if (x <= curr.range.first) {
                while (curr.range.first != curr.range.second) {
                    int middle = curr.get_middle();
                    if (ST.get(2 * curr.index) > h) {
                        curr = new Node(2 * curr.index, new Pair<>(curr.range.first, middle));
                    } else {
                        curr = new Node(2 * curr.index + 1, new Pair<>(middle + 1, curr.range.second));
                    }
                }
                if (ST.get(curr.index) > h) {
                    return curr.range.first;
                }
            } else {
                int middle = curr.get_middle();
                S.push(new Node(2 * curr.index + 1, new Pair<>(middle + 1, curr.range.second)));
                S.push(new Node(2 * curr.index, new Pair<>(curr.range.first, middle)));
            }
        }
        return elementi - 1;
    }

    Pair<Integer, Integer> chiedi(int x) {
        counter++;
        int rx = dimensione + x;
        int h = ST.get(rx);

        return new Pair<>(left_limit(h, x), right_limit(h, x));
    }

    void cambia(Node n, int x, int h) {
        if (n.range.first == n.range.second) {
            ST.set(n.index, h);
        } else {
            int middle = n.get_middle();
            if (x <= middle) {
                cambia(new Node(n.index * 2, new Pair<>(n.range.first, middle)), x, h);
            } else {
                cambia(new Node(n.index * 2 + 1, new Pair<>(middle + 1, n.range.second)), x, h);
            }

            ST.set(n.index, Math.max(ST.get(2 * n.index), ST.get(2 * n.index + 1)));
        }
    }

    void cambia(int x, int h) {
        int pos = dimensione + x;

        cambia(new Node(1, new Pair<>(0, dimensione - 1)), x, h);

        return;
    }

    void inizializza(int N, Vector<Integer> H) {
        elementi = H.size();
        dimensione = 2 << (int) Math.ceil(Math.log(elementi) / Math.log(2)) - 1;
        ST = new Vector<>(2 * dimensione);
        for (int i = 0; i < 2 * dimensione; i++) {
            ST.add(Integer.MIN_VALUE);
        }
        for (int i = 0; i < elementi; i++) {
            ST.set(dimensione + i, H.get(i));
        }

        int i = (dimensione + elementi) / 2;
        do {
            ST.set(i, Math.max(ST.get(2 * i), ST.get(2 * i + 1)));
            i--;
        } while (i > 0);

        return;
    }
}
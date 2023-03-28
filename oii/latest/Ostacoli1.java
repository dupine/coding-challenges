import java.util.*;
import java.io.*;
import java.lang.*;

public class Ostacoli1 {
    
    static int memo[][];

    static class Ostacolo {
        int pos, diff, points, time;

        Ostacolo(int pos, int diff, int points, int time) {
            this.pos = pos;
            this.diff = diff;
            this.points = points;
            this.time = time;
        }
    }

    public static int solve(int N, int L, int D, Ostacolo[] ostacoli) {
        memo = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(memo[i], -1);
        }
        int ans = 0;
        for (int i = N - 1; i >= 0; i--) {
            ans = Math.max(ans, rec(i, i, N, L, D, ostacoli));
        }
        return ans;
    }

    public static int rec(int start, int end, int N, int L, int D, Ostacolo[] ostacoli) {
        if (end == N) {
            return 0;
        }
        if (memo[start][end] != -1) {
            return memo[start][end];
        }
        int ans = 0;
        for (int i = end; i < N; i++) {
            if (ostacoli[i].time > D) {
                break;
            }
            if (ostacoli[i].pos - ostacoli[start].pos > ostacoli[start].time - ostacoli[i].time) {
                continue;
            }
            int timeDiff = ostacoli[start].time - ostacoli[i].time;
            int posDiff = ostacoli[start].pos - ostacoli[i].pos;
            int score = ostacoli[i].points;
            ans = Math.max(ans, score + rec(i, end + 1, N, L, D - timeDiff, ostacoli));
        }
        memo[start][end] = ans;
        return ans;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            br.readLine();
            String[] line1 = br.readLine().split(" ");
            int N = Integer.parseInt(line1[0]);
            int L = Integer.parseInt(line1[1]);
            int D = Integer.parseInt(line1[2]);
            String[] line2 = br.readLine().split(" ");
        String[] line3 = br.readLine().split(" ");
        Ostacolo[] ostacoli = new Ostacolo[N];
        for (int i = 0; i < N; i++) {
            int pos = Integer.parseInt(line2[i]);
            int diff = Integer.parseInt(line3[i]);
            int points = 0;
            int time = 0;
            ostacoli[i] = new Ostacolo(pos, diff, points, time);
        }
        Arrays.sort(ostacoli, new Comparator<Ostacolo>() {
            public int compare(Ostacolo a, Ostacolo b) {
                return a.pos - b.pos;
            }
        });
        for (int i = 0; i < N; i++) {
            int pos = ostacoli[i].pos;
            int diff = ostacoli[i].diff;
            int points = i + 1;
            int time = (int) Math.ceil((double) diff / L);
            ostacoli[i] = new Ostacolo(pos, diff, points, time);
        }
        int ans = solve(N, L, D, ostacoli);
        pw.println(ans);
    }
    pw.close();
    }
}
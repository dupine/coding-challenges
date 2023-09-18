import java.io.*;
import java.util.Scanner;

public class grader {

	// Declaring variables
	int R;
	int[] risultato1;
	int[] risultato2;

	muraglia mur; // user-defined

	void leggi_eventi(int M, Scanner scn) {
		int[] pair = new int[2];
		for (int i = 0; i < M; i++) {
			String tipo = scn.next();

			if (tipo.equals("Q")) {
				int x = scn.nextInt();
				long t = System.nanoTime();
				mur.chiedi(x, pair);
				System.out.println("chiedi: "+ (System.nanoTime()-t));
				risultato1[R] = pair[0];
				risultato2[R] = pair[1];
				R++;
			} else {
				int x = scn.nextInt();
				int h = scn.nextInt();
				long t = System.nanoTime();
				mur.cambia(x, h);
				System.out.println("cambia: "+ (System.nanoTime()-t));
			}
		}
	}


	public static void main(String[] args) throws Exception {
		grader grad = new grader();
		grad.mainLoop();
	}

	void mainLoop() throws Exception{
		InputStream fin = new FileInputStream("input.txt");
        OutputStream fout = new FileOutputStream("output.txt");
        Scanner scn = new Scanner(fin);
        PrintStream prnt = new PrintStream(fout);

		// Reading input
		int N = scn.nextInt();
		int M = scn.nextInt();

		int[] H = new int[N];
		risultato1 = new int[M];
		risultato2 = new int[M];

		for (int i = 0; i < N; i++) {
			H[i] = scn.nextInt();
		}

		mur = new muraglia();
		
		// Calling functions
		mur.inizializza(N, H);
		leggi_eventi(M, scn);

		// Writing output
		for (int i = 0; i < R; i++) {
			prnt.print(risultato1[i]);
			prnt.print(' ');
			prnt.println(risultato2[i]);
		}
        fout.flush();
        scn.close();
        fin.close();
        prnt.close();
	}
}

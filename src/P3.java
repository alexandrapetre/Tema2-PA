//PROIECTAREA ALGORITMILOR - PETRE ALEXANDRA ELENA 325CB

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class P3 {
	public static final int NMAX = 1000006; // 10^6
	public static int n;
	public static int m;
	public static int t;
	public static PrintWriter writer;

	public static void main(String[] args) throws FileNotFoundException {
		File inFile = new File("p3.in"); //fisier intrare
		writer = new PrintWriter("p3.out"); // fisier de iesire
		Scanner sc = null;
		
		try {
			sc = new Scanner(inFile);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String line = sc.nextLine();
		String[] parts = line.split(" ");
		n = Integer.parseInt(parts[0]);
		m = Integer.parseInt(parts[1]);
		t = Integer.parseInt(parts[2]);
		
		writer.print(0);
		writer.close();
	}
}

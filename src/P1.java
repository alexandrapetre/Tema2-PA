//PROIECTAREA ALGORITMILOR - PETRE ALEXANDRA ELENA  325 CB

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class P1 {
	public static final int NMAX = 1000006; // 10^6
	public static int n;
	public static ArrayList<Pair> perechi;
	public static PrintWriter writer;
	public static ArrayList<Pair> adj;
	
	public static void main(String[] args) throws FileNotFoundException {
		File inFile = new File("p1.in"); //fisier intrare
		writer = new PrintWriter("p1.out"); // fisier de iesire
		Scanner sc = null;
		
		perechi = new ArrayList<>();
			
		try {
			sc = new Scanner(inFile);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}

		String line = sc.nextLine();
		String[] parts = line.split(" ");
		n = Integer.parseInt(parts[0]);
		line = sc.nextLine();
		parts = line.split(" ");
		Pair pr;
		
		for(int i = 0; i < n; i++) {
			pr = new Pair(Integer.parseInt(parts[i]), i+1);
			perechi.add(pr);
		}
		
		Compare obj = new Compare();
		obj.compare(perechi);
		
		adj = new ArrayList<Pair>();
		
		boolean value;
		value = getResult();
		if(value == true) {
			writer.println(n-1);
			for(int i = 0; i < adj.size(); i++) {
				writer.println(adj.get(i).getX() + " " + adj.get(i).getY());
			}
		}else {
			writer.println(-1);
		}
		
		writer.close();
		
	}
	
	
	public static boolean getResult() {
		
		int min = 0;
		int source = 1;
		int dif = 0;
		Pair pr;
		
		for(int i = 1; i < n; i++) {
			dif = (perechi.get(i).getX() - min);
			if(dif > 1 || perechi.get(1).getX() == 0) {
				return false;
			}else {
				if(dif == 1 && min != 0) {
					source  = perechi.get(i-1).getY();
					pr = new Pair(source, perechi.get(i).getY());
					adj.add(pr);
				}else {
					pr = new Pair(source, perechi.get(i).getY());
					adj.add(pr);
				}
				min = perechi.get(i).getX();	
			}
		}
		return true;
	}

}

class Pair{
	int x;
	int y;
	
	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Pair() {
		x = 0;
		y = 0;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}


class Compare { 
    public void compare(ArrayList<Pair> arr) { 
       Collections.sort(arr, new Comparator<Pair>() { 
        	public int compare(Pair p1, Pair p2){ 
                return p1.x - p2.x; 
            } 
        });
    }
}

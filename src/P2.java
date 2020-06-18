//TEMA PROIECTAREA ALGORITMILOR - PETRE ALEXANDRA 325CB

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class P2 {
	public static final int NMAX = 1000006; // 10^6
	public static int n;
	public static int m;
	public static int k;
	public static int[][] matrix;
	public static PrintWriter writer;

	public static void main(String[] args) throws FileNotFoundException {
		
		File inFile = new File("p2.in"); //fisier intrare
		writer = new PrintWriter("p2.out"); // fisier de iesire
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
		k = Integer.parseInt(parts[2]);
		matrix = new int[n+1][m+1];
		
		for(int i = 0; i < n; i++) {
			line = sc.nextLine();
			parts = line.split(" ");
			for(int j = 0; j < m; j++) {
				matrix[i][j] = Integer.parseInt(parts[j]);
			}
		}
		
		Element everyElem;
		int results;
		int max = 0;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				everyElem = new Element(matrix[i][j], i ,j);
				results = bfs(matrix, everyElem);
				if(max < results) {
					max = results;
				}
			}
		}
		writer.print(max);
		writer.close();
	}
	
	public static int bfs(int[][] matrix, Element start) {
		
		boolean[][] visited = new boolean[n+1][m+1];
		Queue<Element> q = new LinkedList<>();
		Queue<Element> result = new LinkedList<>();
		q.add(start);
		int posX, posY;
		posX = start.getX();
		posY =start.getY();
		
		visited[posX][posY] = true;
		int min = matrix[posX][posY];
		int max = matrix[posX][posY];
		Element el1,el2,el3,el4;
		
		while (!q.isEmpty()){
			
			   Element u =  q.poll();
			   
			   boolean inainte = visited[u.posX][u.posY];
			   visited[u.posX][u.posY] = true; //marchez ca vizitat
			   posX = u.getX();
			   posY = u.getY();
			   int  ok = 0;
			   
			   if(min <= u.value && max >= u.value && inainte != true) {
				   ok = 1;;
				   result.add(u);
			   }
			   else if(u.value < min && (max - u.value) <= k && inainte != true) {
				   min = u.value;
				   ok = 1;
				   result.add(u);
			   }
			   else if( u.value > max && (u.value - min) <= k && inainte != true) {
				   max = u.value;
				   ok = 1;
				   result.add(u);
			   }else if( u.value == min && u.value == max) {
				   ok =1;
			   }
			   
			    if(ok == 1) {

				   if(posX == 0 && posY == 0) { //colt sus stanga
					   
					   el1 = new Element(matrix[posX][posY+1],posX, (posY+1));
					   				   
					   if(visited[el1.posX][el1.posY] == false && ((el1.value <= max && el1.value >= min)
							   || (el1.value < min && max - el1.value <= k ) || 
							   (el1.value > max && el1.value - min <= k))){
						  q.add(el1);
					   }
					   if(n > 1) {
						   el2 =  new Element(matrix[posX+1][posY],posX+1, (posY));
					   
						   if(visited[el2.posX][el2.posY] == false && ((el2.value <= max && el2.value >= min)
								   || (el2.value < min && max - el2.value <= k ) || 
								   (el2.value > max && el2.value - min <= k))) {
							   q.add(el2);
						   } 
					   }
				   }
				   
				   else if(posX == 0 && posY == (m-1)) {//colt sus dreapta
					   
					   el1 = new Element(matrix[posX][posY-1],posX, (posY-1));
					  
					  
					   if(visited[el1.posX][el1.posY] == false && ((el1.value <= max && el1.value >= min)
							   || (el1.value < min && max - el1.value <= k ) || 
							   (el1.value > max && el1.value - min <= k))){
						  q.add(el1);
					   }
					   
					   if(n > 1) {
						   el2 =  new Element(matrix[posX+1][posY],posX+1, (posY));
					   
						   if(visited[el2.posX][el2.posY] == false && ((el2.value <= max && el2.value >= min)
								   || (el2.value < min && max - el2.value <= k ) || 
								   (el2.value > max && el2.value - min <= k))) {
							   q.add(el2);
						   }
					   }
				   }
				   else if(posX == (n-1) && posY == 0) {//colt jos stanga
					   
					   el1 = new Element(matrix[posX][posY+1],posX, (posY+1));
					   
					   if(visited[el1.posX][el1.posY] == false && ((el1.value <= max && el1.value >= min)
							   || (el1.value < min && max - el1.value <= k ) || 
							   (el1.value > max && el1.value - min <= k))){
						  q.add(el1);
					   }
					   
					   if(n > 1) {
						   el2 =  new Element(matrix[posX-1][posY],posX-1, (posY));
						   if(visited[el2.posX][el2.posY] == false && ((el2.value <= max && el2.value >= min)
								   || (el2.value < min && max - el2.value <= k ) || 
								   (el2.value > max && el2.value - min <= k))) {
							   q.add(el2);
						   } 
					   }
				   }
				   else if(posX == (n-1) && posY == (m-1)) {//colt jos dreapta
					   
					   el1 =  new Element(matrix[posX][posY-1],posX, (posY-1));
					   
					   if(visited[el1.posX][el1.posY] == false && ((el1.value <= max && el1.value >= min)
							   || (el1.value < min && max - el1.value <= k ) || 
							   (el1.value > max && el1.value - min <= k))){
						  q.add(el1);
					   }
					   
					   
					   if(n > 1) {
						   el2 =  new Element(matrix[posX-1][posY],posX-1, (posY));
						   if(visited[el2.posX][el2.posY] == false && ((el2.value <= max && el2.value >= min)
								   || (el2.value < min && max - el2.value <= k ) || 
								   (el2.value > max && el2.value - min <= k))) {
							   q.add(el2);
						   } 
					   }
					   
				   }
				   else if(posX == 0 && (posY != 0 && posY != (m-1))) {//bordura sus 
					   
					   el1 = new Element(matrix[posX][posY-1],posX, (posY-1));
					   el2 =  new Element(matrix[posX][posY+1],posX, (posY+1));
					   
					   
					   if(visited[el1.posX][el1.posY] == false && ((el1.value <= max && el1.value >= min)
							   || (el1.value < min && max - el1.value <= k ) || 
							   (el1.value > max && el1.value - min <= k))){
						  q.add(el1);
					   }
					   
					   if(visited[el2.posX][el2.posY] == false && ((el2.value <= max && el2.value >= min)
							   || (el2.value < min && max - el2.value <= k ) || 
							   (el2.value > max && el2.value - min <= k))) {
						   q.add(el2);
					   }
					   
					   if(n > 1) {
						   el3 = new Element(matrix[posX+1][posY], posX+1, posY);
						   if(visited[el3.posX][el3.posY] == false && ((el3.value <= max && el3.value >= min)
								   || (el3.value < min && max - el3.value <= k ) || 
								   (el3.value > max && el3.value - min <= k))) {
							   q.add(el3);
						   }
					  }
				   }
				   else if((posX != 0 && posX != (n-1)) && posY == 0) {//bordura stanga
					   
					   el1 = new Element(matrix[posX+1][posY],posX+1, (posY)); 
					   el2 =  new Element(matrix[posX-1][posY],posX-1, (posY));
					   el3 = new Element(matrix[posX][posY+1], posX, posY+1);
					   
					   if(visited[el1.posX][el1.posY] == false && ((el1.value <= max && el1.value >= min)
							   || (el1.value < min && max - el1.value <= k ) || 
							   (el1.value > max && el1.value - min <= k))){
						  q.add(el1);
					   }
					   
					   if(visited[el2.posX][el2.posY] == false && ((el2.value <= max && el2.value >= min)
							   || (el2.value < min && max - el2.value <= k ) || 
							   (el2.value > max && el2.value - min <= k))) {
						   q.add(el2);
					   }
					   
					   if(visited[el3.posX][el3.posY] == false && ((el3.value <= max && el3.value >= min)
							   || (el3.value < min && max - el3.value <= k ) || 
							   (el3.value > max && el3.value - min <= k))) {
						   q.add(el3);
					   }
				   }  
				   else if((posX != 0 && posX != (n-1)) && posY == (m-1)) {//bordura dreapta 
					   
					   el1 = new Element(matrix[posX-1][posY],posX-1, (posY));
					   el2 =  new Element(matrix[posX+1][posY],posX+1, (posY));
					   el3 = new Element(matrix[posX][posY-1], posX, posY-1);
					   
					   if(visited[el1.posX][el1.posY] == false && ((el1.value <= max && el1.value >= min)
							   || (el1.value < min && max - el1.value <= k ) || 
							   (el1.value > max && el1.value - min <= k))){
						  q.add(el1);
					   }
					   
					   if(visited[el2.posX][el2.posY] == false && ((el2.value <= max && el2.value >= min)
							   || (el2.value < min && max - el2.value <= k ) || 
							   (el2.value > max && el2.value - min <= k))) {
						   q.add(el2);
					   }
					   
					   if(visited[el3.posX][el3.posY] == false && ((el3.value <= max && el3.value >= min)
							   || (el3.value < min && max - el3.value <= k ) || 
							   (el3.value > max && el3.value - min <= k))) {
						   q.add(el3);
					   }
					   
				   }
				   else if(posX == (n-1) && (posY != 0 && posY != (m-1))) {//bordura jos
					   
					   el1 = new Element(matrix[posX][posY-1],posX, (posY-1));
					   el2 =  new Element(matrix[posX][posY+1],posX, (posY+1));
					   
					   
					   if(visited[el1.posX][el1.posY] == false && ((el1.value <= max && el1.value >= min)
							   || (el1.value < min && max - el1.value <= k ) || 
							   (el1.value > max && el1.value - min <= k))){
						  q.add(el1);
					   }
					   
					   if(visited[el2.posX][el2.posY] == false && ((el2.value <= max && el2.value >= min)
							   || (el2.value < min && max - el2.value <= k ) || 
							   (el2.value > max && el2.value - min <= k))) {
						   q.add(el2);
					   }
					   
					   if(n > 1) {
						   el3 = new Element(matrix[posX-1][posY], posX-1, posY);
						   if(visited[el3.posX][el3.posY] == false && ((el3.value <= max && el3.value >= min)
								   || (el3.value < min && max - el3.value <= k ) || 
								   (el3.value > max && el3.value - min <= k))) {
							   q.add(el3);
						   }
					   }
				   }
				   else if((posX != 0 && posX != (n-1)) && (posY != 0 && posY != (m-1))) {// mijloc
					   
					   el1 = new Element(matrix[posX][posY-1],posX, (posY-1));
					   el2 =  new Element(matrix[posX][posY+1],posX, (posY+1));
					   el3 = new Element(matrix[posX+1][posY], posX+1, posY);
					   el4 = new Element(matrix[posX-1][posY], posX-1,posY);
					   
					   if(visited[el1.posX][el1.posY] == false && ((el1.value <= max && el1.value >= min)
							   || (el1.value < min && (max - el1.value) <= k ) || 
							   (el1.value > max && (el1.value - min) <= k))){
						  q.add(el1);
					   }
					   
					   if(visited[el2.posX][el2.posY] == false && ((el2.value <= max && el2.value >= min)
							   || (el2.value < min && max - el2.value <= k ) || 
							   (el2.value > max && el2.value - min <= k))) {
						   q.add(el2);
					   }
					   
					   if(visited[el3.posX][el3.posY] == false && ((el3.value <= max && el3.value >= min)
							   || (el3.value < min && max - el3.value <= k ) || 
							   (el3.value > max && el3.value - min <= k))) {
						   q.add(el3);
					   }
					   
					   if(visited[el4.posX][el4.posY] == false && ((el4.value <= max && el4.value >= min)
							   || (el4.value < min && max - el4.value <= k ) || 
							   (el4.value > max && el4.value - min <= k))){
						  q.add(el4);
					   }
				   }
			    } 
			}
		
		return result.size()+1;
	}	
}

class Element {
	int value;
	int posX;
	int posY;
	
	public Element(int value, int posX, int posY) {
		this.value = value;
		this.posX = posX;
		this.posY = posY;
	}
	
	public int getX() {
		return posX;
	}
	
	public int getY() {
		return posY;
	}
	
	public int getValue() {
		return value;
	}
}
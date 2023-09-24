package epd_evaluable_iii;

import java.util.*;
public class Cities implements Comparable<Object>{
	int city;
	double distance;
	
	public Cities(int city, double distance) {
		this.city=city;
		this.distance =distance;
	}
	
	public Cities() {
		city=0;
		distance=0;
	}
	
//Ofrece la matriz de distancias ordenada por filas, pero por cada distancia sabemos cuál es su ciudad asociada
public Cities[][] sortCitiesByDistance(double matrizdist[][]){
	
	
	Cities[][] ciudades = makeMatrix(matrizdist);
	ciudades = sortCitiesByRows(ciudades);
	return ciudades;
	
	
	
}

private Cities[][] sortCitiesByRows(Cities[][] ciudades) {
	for(int i=0;i<ciudades.length  ;i++) 
		Arrays.sort(ciudades[i]);
	return ciudades;
}

private Cities[][] makeMatrix(double[][] matrizdist) {
	Cities[][] ciudades = new Cities[matrizdist.length][matrizdist.length];
	for(int i=0;i< matrizdist.length ;i++) {
		
		for(int j=0;j<matrizdist[i].length  ;j++) 
			ciudades[i][j] = new Cities(j, matrizdist[i][j]);
		
	}
	return ciudades;
}

@Override
public int compareTo(Object arg0) {
	Cities c = (Cities) arg0;
	int i= (int) this.distance;
	int j= (int) c.distance;
	return  i-j;
}

public String toString() {
	String s= city+" "+distance;
return s;	
}
/*
public void print(Cities ciud[][]) {
	for(int i=0;i<ciud.length;i++) {
		for(int j = 0; j< ciud[i].length;j++) {
			System.out.print(ciud[i][j].distance+" ");
		}
		System.out.println();
		
	}
}
*/
}

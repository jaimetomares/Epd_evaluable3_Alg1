package epd_evaluable_iii;

import java.io.*;

public class LecturaDeDatos {
//http://chuwiki.chuidiang.org/index.php?title=Lectura_y_Escritura_de_Ficheros_en_Java
	//esta pa la bibliografia
public void matrizatxt (double matriz[][],String nombrearchivo) {
	File f = null;//new File(nombrearchivo); 
	FileWriter fw = null;// new FileWriter(f);

	try {
		f= new File(nombrearchivo);
		fw=new FileWriter(f);
		
		for(int i=0;i<matriz.length  ;i++) {
			for(int j=0;j<matriz[i].length  ;j++) {
				fw.write(matriz[i][j]+" ");
			}
			fw.write("\n");
		}
		fw.close();
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		
	}
	
	
}
	//Recibe el nombre de un fichero de datos y genera una matriz de distancias
public double[][] obtenerMatrizDistancias(String nombreArch) {
		      File archivo = null;
		      FileReader fr = null;
		      BufferedReader br = null;
		      boolean tomardatos=false;
		      double  matrizCoord[][] = null;
		      try {
		         // Apertura del fichero y creacion de BufferedReader para poder
		         // hacer una lectura comoda (disponer del metodo readLine()).
		         archivo = new File (nombreArch);
		         fr = new FileReader (archivo);
		         br = new BufferedReader(fr);

		         // Lectura del fichero
		         String linea;
		         int i =0;
		         while((linea=br.readLine())!=null) {
		        	 
		        	  if(linea.contains("DIMENSION:")){
		        	   String s[] = linea.split(" ");
		        	   
		        	   matrizCoord = new double[Integer.parseInt(s[1])][2];
		        	   break;
		        	  }
		           }
		         
		      
		         while((linea=br.readLine())!=null) { 
		           if(linea.equals("EOF"))
		        	   break;
		           if(tomardatos) {
		        	   linea=linea.trim();
		        	   String data[]=linea.split(" ");
		        	   try {		        		   
		        		   matrizCoord[i][0] = Double.valueOf(data[1]);
		        		   matrizCoord[i][1] = Double.valueOf(data[2]);
		        	   }catch(Exception e) {		 
			        	   matrizCoord[i][0] = (double)Integer.valueOf(data[0]);
			        	   matrizCoord[i][1] = (double)Integer.valueOf(data[0]);
		        	   }
		        	   i++;
		           }
		           if(linea.equals("NODE_COORD_SECTION")){
		        	   tomardatos = true;
		           }    
		         }
		      
		      }catch(Exception e){
		         e.printStackTrace();
		         
		      }finally{
		         try{                    
		            if( null != fr )   
		               fr.close();     
		                        
		         }catch (Exception e2){ 
		            e2.printStackTrace();
		         }
		      }
		      
		      
		      return coordToDist(matrizCoord);
		      
		   }

public double[][] obtenerMatrizCoordenadas(String nombreArch) {
    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;
    boolean tomardatos=false;
    double  matrizCoord[][] = null;
    try {
       // Apertura del fichero y creacion de BufferedReader para poder
       // hacer una lectura comoda (disponer del metodo readLine()).
       archivo = new File (nombreArch);
       fr = new FileReader (archivo);
       br = new BufferedReader(fr);

       // Lectura del fichero
       String linea;
       int i =0;
       while((linea=br.readLine())!=null) {
      	 
      	  if(linea.contains("DIMENSION:")){
      	   String s[] = linea.split(" ");
      	   
      	   matrizCoord = new double[Integer.parseInt(s[1])][2];
      	   break;
      	  }
         }
       
    
       while((linea=br.readLine())!=null) { 
         if(linea.equals("EOF"))
      	   break;
         if(tomardatos) {
      	   linea=linea.trim();
      	   String data[]=linea.split(" ");
      	   try {		        		   
      		   matrizCoord[i][0] = Double.valueOf(data[1]);
      		   matrizCoord[i][1] = Double.valueOf(data[2]);
      	   }catch(Exception e) {		 
	        	   matrizCoord[i][0] = (double)Integer.valueOf(data[0]);
	        	   matrizCoord[i][1] = (double)Integer.valueOf(data[0]);
      	   }
      	   i++;
         }
         if(linea.equals("NODE_COORD_SECTION")){
      	   tomardatos = true;
         }    
       }
    
    }catch(Exception e){
       e.printStackTrace();
       
    }finally{
       try{                    
          if( null != fr )   
             fr.close();     
                      
       }catch (Exception e2){ 
          e2.printStackTrace();
       }
    }
    
    return matrizCoord;
    
 }


//Recibe una matriz con los datos de las coordenadas de los archivos tsp
//Devuelve la distancia entre todo ese conjunto de puntos
public double[][] coordToDist(double[][] matrizCoord) {
	double[][] matrizDist =new double[matrizCoord.length][matrizCoord.length];
	for(int i=0; i<matrizDist.length;i++) {
		for(int j=0; j<matrizDist[i].length;j++) {
			matrizDist[i][j] = euclideanDist(matrizCoord[i][0],matrizCoord[i][1],matrizCoord[j][0],matrizCoord[j][1]);
		}
	}
	return matrizDist;
}
//Calcula la distancia euclidea entre dos puntos
private double euclideanDist(double p1x, double p1y, double p2x, double p2y) {
	double x = p1x-p2x;
	double y = p1y-p2y; 
	return Math.hypot(x,y);
}

}



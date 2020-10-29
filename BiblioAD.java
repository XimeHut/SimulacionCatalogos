import java.io.PrintWriter; 
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import javax.swing.ImageIcon;

import java.util.StringTokenizer;

public class BiblioAD //tiene que ser public por que se va utitilizar en otros lados
{
	private PrintWriter archivoOut;
	private BufferedReader archivoIn;
	
	private ImageIcon  arregloImages[];//solo esta apuntando a null
	private int numeroLibros; //este siempre se iniciliza con 0 
	
	public String capturar (String datos)
	{
		String resultado="";
		
		try
		{
				//1.- Abrir el arcvhivo para guardar los datos
				
				archivoOut = new PrintWriter(new FileWriter("Libros.txt",true)); //el true me ayuda que no se sobre escriba la linea
				
				
				//2.- Almacenar o escribir los datos en el archivo
				
				archivoOut.println(datos); 
				
				//3.-Cerrar el archivo y enviar el resultado
				archivoOut.close(); // esto es importante por que pone una marca de fin de archivo
				
				resultado = "Captura correcta de los datos";	
		}
		catch(IOException ioe)
		{
			resultado = "Error: "+ioe;
			System.out.println("Error: "+ioe);
		}
		
		return resultado;
	}
		
		public String consultarLibros()
	
		{
			String datos="";
			
			try{
				//1.- Abir el archivo para leer los datos
				
				archivoIn = new BufferedReader(new FileReader("Libros.txt"));
				
				//2.- Procesar los datos
				numeroLibros= 0;
				while(archivoIn.ready()) //lo posiciona al principio , metodo que entrega un valor true o false cuando tiene datos que leer
				{                   // el ready se sale hasta que llega a la marca de fin de archivo
					datos = datos + archivoIn.readLine() + "\n"; //readLine lee el sting hasta encontrar la marca de fin de linea \n para hacer el salto de linea
					numeroLibros++;
				}
				
				//3.-Cerrar el archivo
				archivoIn.close();
				//mando los datos a crearArregloImages
				crearArregloImages(datos);
					
				}
			catch (FileNotFoundException fnfe)
			{
			
			datos= "Error: " +fnfe;
			System.out.println("Error: "+fnfe);
		    }
		    
		    catch(IOException ioe)
		    	
		    {
		    	
			    datos = "Error: "+ioe;
				System.out.println("Error: "+ioe);
		    }
				
		
			
			return datos;
			
		}
		
		public String consultarEditorial(String edit)
		{
			
				String datos = "";
				String strLibro="";
				StringTokenizer st;
				String titulo, autor, editorial;
				
				boolean encontrado=false;
				
				try 
				{
					
				
				//1.-Abrir el archivo para leer
				archivoIn = new BufferedReader ( new FileReader("Libros.txt"));
				
				
				
				//2.-Buscar libros de la editorial indicada
				numeroLibros=0;
				while(archivoIn.ready()) //el ready es un tipo boolenao que te avisi cuando hay una marca de fin de archivo
					{
					 strLibro = archivoIn.readLine();
					 st = new StringTokenizer(strLibro,"_"); // el _ es el delimitador , lo que toma por default es el espacio en blanco
					 titulo = st.nextToken();
					 autor = st.nextToken();
					 editorial = st.nextToken();
					 
					 if (editorial.equals(edit))
					 {
					 	datos= datos + strLibro + "\n";
					 	encontrado=true;
					 	
					 	numeroLibros++;
					 }
					 
					}
				
				//3.- Cerrar el archivo
				archivoIn.close();
				if (!encontrado)
					datos = "NOT_FOUND";
				else //es else para que no cree el arreglo con datos vacios por que truena
				crearArregloImages(datos); //
			
			}
			catch (FileNotFoundException fnfe)
			{
					datos= "NOT_FOUND";
					System.out.println("Error: "+fnfe);
			}
			 catch(IOException ioe)
		    	
		    {
		    	
			    datos = "NOT_FOUND";
				System.out.println("Error: "+ioe);
		    }
				
			return datos;
		}
		
		public String consultarTitulo(String tit)
		{
			
			
				String datos = "";
				String strLibro="";
				StringTokenizer st;
				String titulo, autor, editorial;
				
				boolean encontrado=false;
				
				try 
				{
					
				
				//1.-Abrir el archivo para leer
				archivoIn = new BufferedReader ( new FileReader("Libros.txt"));
				
				
				
				//2.-Buscar libros de la editorial indicada
					numeroLibros=0;
				while(archivoIn.ready()) //el ready es un tipo boolenao que te avisi cuando hay una marca de fin de archivo
					{
					 strLibro = archivoIn.readLine();
					 st = new StringTokenizer(strLibro,"_"); // el _ es el delimitador , lo que toma por default es el espacio en blanco
					 titulo = st.nextToken();
					 autor = st.nextToken();
					 editorial = st.nextToken();
					 
					 if (titulo.equals(tit))
					 {
					 	datos= datos + strLibro + "\n"; //el diagonal n es el delimitador 
					 	encontrado=true;
					 	numeroLibros++;
					 }
					 
					}
				
				//3.- Cerrar el archivo
				archivoIn.close();
				if (!encontrado)
					datos = "NOT_FOUND";
				else //es else para que no cree el arreglo con datos vacios por que truena
				crearArregloImages(datos); //
			
			}
			catch (FileNotFoundException fnfe)
			{
					datos="NOT_FOUND";
					System.out.println("Error: "+fnfe);
			}
			 catch(IOException ioe)
		    	
		    {
		    	
			    datos = "NOT_FOUND"+ioe;
				System.out.println("Error: "+ioe);
		    }
				
			return datos;
			
			
		}
		
		public ImageIcon[]  obtenerImages()
		{
			return arregloImages;
		}
		
		private void crearArregloImages(String datos) //es private por que solo va ser llamado en los metdos del ad 
		{
			StringTokenizer st , st2;
			String strImagen ,strLibro, titulo,autor,editorial;
			int i=0;
		     //1.Crear el arreglo de images
		     arregloImages = new ImageIcon[numeroLibros];
		     
		     //2.-Obtener las imagenes jpg a partir del String Datos
		     
		     st = new StringTokenizer (datos,"\n"); //le estoy quitando el delimitador
		     
		     while(st.hasMoreTokens()) //miesntras tengas token , ponerle st2 para quitar los guiones
		     {
		     	strLibro= st.nextToken(); //ahora va tokenizar linea por linea
		     	
		     	st2 = new StringTokenizer(strLibro,"_");
		     	titulo = st2.nextToken();
		     	autor = st2.nextToken();
		     	editorial = st2.nextToken();
		     	
		     	strImagen = "images/"+titulo+".jpg";
		     	
		     arregloImages[i] = new ImageIcon(getClass().getResource(strImagen));
		    System.out.println(strImagen); //para ver que me esta entregando
			System.out.println(arregloImages[i]);
		     		
		     		i++;
		     }
			
		}
		
		public ImageIcon[] getImage()
		{
			
			String strImagen = "images/1.jpg"; //cargale algo a la direccion 
			arregloImages = new ImageIcon[1]; //creo que el arreglo para el numero de libros
			arregloImages[0] = new ImageIcon(getClass().getResource(strImagen));//esa imagen cargala al arreglo
			
			System.out.println(strImagen);
			System.out.println(arregloImages[0]);
			
			return arregloImages;
		}
	
	
	}

import java.io.*;
import java.util.StringTokenizer;
import java.util.Date;

public class AutoAD {

	private BufferedReader archivoIn;
	private PrintWriter archivoOut;
	
	private String arregloStrings[];
	
	private AutoDP arregloObjetosDP[];// es de objetos , el contendio del arreglo va ser direcciones de la calse dp 
	private int posicion;

	
	
	private int noClientes; //este será el contador 
	
	//el ojetivo de un constructor es inicializar variables de instancia 
	
	public AutoAD() //contructor 
	{
	String datos="";
    	
    	try{
    		
    	//1. Abrir el archivo
    	archivoIn = new BufferedReader(new FileReader("Autos.txt"));
    	
    	//2. Procesoar datos
    	
    	while(archivoIn.ready())
    	{
    		archivoIn.readLine();
    		noClientes++;
    		
    	}
    	
    	//3.Cerrar el archivo
    	
    	archivoIn.close();
    	System.out.println("Strings en el archivo= "+noClientes);
    	
    	}
    		catch(FileNotFoundException ioe)
    	{
    	
    	System.out.println("ERROR: "+ioe);
    		
    	}
    	catch(IOException ioe)
    	{
    		System.out.println("ERROR:"+ioe);
    		
    	}
    
		datos=datosArchArreObj();
		
	}
	
    public String capturar (String datos) {
    	
    	String resultado="";
    
    	
    	try
    	{
    	//1. Abrir el archivo
    	
    	archivoOut = new PrintWriter(new FileWriter("Autos.txt",true));
    	
    	
    	//2. Escribir o almacener los datos en el archivo
    	archivoOut.println(datos);
    	
    	//3.Cerrar archivo
    	
    	archivoOut.close();
    	noClientes ++;
    	
    	resultado ="Datos capturados: " +datos;
    	}
    	catch(IOException ioe)
    	{
    		resultado = "Erroe: "+ioe;
    		
    	}
    	
    	
    	return resultado;
    }
    
    public String crear (String datos , String archivo) {
    	
    	String resultado="";
    	
    	try
    	{
    	//1. Abrir el archivo
    	
    	archivoOut = new PrintWriter(new FileWriter(archivo,true));
    	
    	
    	//2. Escribir o almacener los datos en el archivo
    	archivoOut.println(datos);
    	
    	//3.Cerrar archivo
    	
    	archivoOut.close();
    	noClientes ++;
    	
    	resultado ="Datos capturados: " +datos;
    	}
    	catch(IOException ioe)
    	{
    		resultado = "Erroe: "+ioe;
    		
    	}
    	return resultado;
    }
    
    public String consultarAutos()

    {
    	String datos="";
    	
    	try{
    		
    	//1. Abrir el archivo
    	archivoIn = new BufferedReader(new FileReader("Autos.txt"));
    	
    	//2. Procesoar datos
    	while(archivoIn.ready())
    		datos = datos + archivoIn.readLine()+"\n";
    		 
    	
    	
    	//3.Cerrar el archivo
    	
    	archivoIn.close();
    	
    	}
    		catch(FileNotFoundException ioe)
    	{
    		datos = "Erroe: "+ioe;
    		
    	}
    	catch(IOException ioe)
    	{
    		datos = "Erroe: "+ioe;
    		
    	}
    	
    	return datos;
    	
   
    }   
    	
    
    public String datosArchArreObj(){
    	
    
    	String respuesta="";
    	int i =0;
    	String datos ="";
    	
    	//1. Crear el arreglo
      arregloObjetosDP = new AutoDP[noClientes]; // el noClientes me lo da el contructor , definiendo el arreglo de la longitud especificia 
    	
    	//2.Abrir el archivo
    	
    		try{
    		
    	//2. Abrir el archivo
    	archivoIn = new BufferedReader(new FileReader("Autos.txt"));
    	
      	//3.Leer cada String y ponerlo en la casilla correspondiente del arreglo de objetos DP
    	
    	while(archivoIn.ready())
    	{
    	
      		datos= archivoIn.readLine(); //del archivo a un arreglo
      		arregloObjetosDP[i]= new AutoDP(datos); // creo el obejto dp y le mando los datos leido
    		i++;
    		//asinar el string a la casilla del arreglo
    		
    	}
    		
    	//4.Cerrar el archivo
    	
    	archivoIn.close();
    	respuesta = "Datos almacenados en el Arreglo";
    	
    	
    	}
    		catch(FileNotFoundException ioe)
    	{
    		respuesta= "Erroe: "+ioe;
    		
    	}
    	catch(IOException ioe)
    	{
    		respuesta = "Erroe: "+ioe;
    		
    	}
    	
    	return respuesta;
    } 
    	
    public String consultarArchArreObj()
    {
    	String datos = "";
    	int i =0;
    	
    	if (arregloObjetosDP == null)
    		datos = "Arreglo vacio...";
    			
    		else {
    			while(i<arregloObjetosDP.length)
    				
    			{
    				datos = datos + arregloObjetosDP[i] + "\n"; //concatenar el contenido del arreglo
    				i++;
    				
    			}
    		}
    		
    		return datos;
    			
    }
    
    
    public String consultarMarcaObj(String marc){
    	
    		
    	String datos = "";
    	int i =0;
    	boolean encontrado= false;
    	String respuesta = "";
    	if (arregloObjetosDP == null)
    		datos = "Arreglo OBJ vacio...";
    			
    		else {
    			while(i<arregloObjetosDP.length)
    				
    			{
    			if (marc.equals(arregloObjetosDP[i].getMarca()))	 { //con el accessors 
    			
    			encontrado = true;
    			datos = datos + arregloObjetosDP[i] + "\n"; //concatenar el contenido del arreglo
    			}	
    				i++;
    				
    			}
    		}
    		
    	  			
    	if(encontrado == true)
    	{
    		respuesta=datos;
    	}
    	else
    	{
    			respuesta="La marca " +marc+" no fue encontrada";
    	}
    	

     	return respuesta;
    }
    
    
    public String  consultarClaveObj(String clue){
    	
    		
    	String datos = "";
    	int i =0;
    	boolean encontrado= false;
    	String respuesta = "";
    	if (arregloObjetosDP == null)
    		datos = "ARREGLO_VACIO";
    			
    		else {
    			while(i<arregloObjetosDP.length&&encontrado==false)
    				
    			{
    			if (clue.equals(arregloObjetosDP[i].getClave()))	 { //con el accessors 
    			
    			encontrado = true;
    			datos = datos + arregloObjetosDP[i] + "\n"; //concatenar el contenido del arreglo
    			posicion = i;
    			}	
    				i++;
    				
    			}
    		}
    		
    	  			
    	if(encontrado == true)
    	{
    		respuesta=datos;
    	}
    	else
    	{
    		respuesta="NOT_FOUND";
    	}
    	

     	return respuesta;
    }
    

	public String datosArchivo(){
		
		String resultado = "";
		int i =0;
		
		if (arregloObjetosDP == null){
    	resultado = "Arreglo vacio...";
    	}
		else{
		
		
			try
			{
			//1. Abrir el archivo
			
			archivoOut = new PrintWriter(new FileWriter("Auto.txt")); //no le pongo el true para que sobre escriba
			
			
			//2. Escribir o almacener los datos en el archivo
			while(i<arregloObjetosDP.length){
			
			archivoOut.println(arregloObjetosDP[i]);
			i++;
			}
			//3.Cerrar archivo
			
			archivoOut.close();
		
			
			resultado ="Datos capturados correctamente:";
			}
			catch(IOException ioe)
			{
				resultado = "Erroe: "+ioe;
				
			}
		}
    	return resultado;
	
    	
    
	}
	
  
    public String cotizarAuto(int plazo){
		
	String cotiza = "COTIZACION AUTOMOVIL: \n";
	String strInteres="";
	float precioTotal=0, mensualidad=0,interes=0;

			
		
		if (plazo == 12)
		{
			interes = (float)(0.10);
			precioTotal = arregloObjetosDP[posicion].getPrecio() * interes + arregloObjetosDP[posicion].getPrecio();
			mensualidad = precioTotal/plazo;
			strInteres = "10 %";
			System.out.println(arregloObjetosDP[posicion].getPrecio());
		
		}
		
			if (plazo == 24)
		{
			interes = (float)(0.15);
			precioTotal = arregloObjetosDP[posicion].getPrecio() * interes + arregloObjetosDP[posicion].getPrecio();
			mensualidad = precioTotal/plazo;
			strInteres = "15 %";
		
		}
			if (plazo == 48)
		{
			interes = (float)(0.20);
			precioTotal = arregloObjetosDP[posicion].getPrecio() * interes + arregloObjetosDP[posicion].getPrecio();
			mensualidad = precioTotal/plazo;
			strInteres = "20 %";
		
		}
		
		
		  cotiza=cotiza+"Clave Auto: "+(arregloObjetosDP[posicion].getClave())+"\nTipo: "+(arregloObjetosDP[posicion].getTipo())+"\nPrecio Contado: "+(arregloObjetosDP[posicion].getPrecio())+"\nPlazo"+plazo+" Meses\nIntereses: "+strInteres+"\nPrecio con interes "+precioTotal+"\nMensualidad: "+mensualidad;
     
     		
			
			
			return cotiza;
	}
	
    public String venta(int plazo)
  	{
  	 
  	 	String resultado;
  	 
  	 	String datosVenta ="";
  	    String strInteres="";
  	    String datosCaptura ="";
  	   
  	  
	    float precioTotal=0, mensualidad=0,interes=0;
			
		
			if (plazo == 12)
		{
			interes = (float)(0.10);
			precioTotal = arregloObjetosDP[posicion].getPrecio() * interes + arregloObjetosDP[posicion].getPrecio();
			mensualidad = precioTotal/plazo;
			strInteres = "10 %";
		//	System.out.println(arregloObjetosDP[posicion].getPrecio());
		
		}
		
			if (plazo == 24)
		{
			interes = (float)(0.15);
			precioTotal = arregloObjetosDP[posicion].getPrecio() * interes + arregloObjetosDP[posicion].getPrecio();
			mensualidad = precioTotal/plazo;
			strInteres = "15 %";
		
		}
			if (plazo == 48)
		{
			interes = (float)(0.20);
			precioTotal = arregloObjetosDP[posicion].getPrecio() * interes + arregloObjetosDP[posicion].getPrecio();
			mensualidad = precioTotal/plazo;
			strInteres = "20 %";
		
		}
		
  	 	datosVenta= "Clave:"+arregloObjetosDP[posicion].getClave()+"\nMarca:"+arregloObjetosDP[posicion].getMarca()+"\nTipo:"+arregloObjetosDP[posicion].getTipo()+"\nPrecio Contado:"+arregloObjetosDP[posicion].getPrecio()+"\nPlazo: "+plazo+"\nInteres: "+strInteres+"\nPrecio Total: "+precioTotal+"\nMensualidad: "+mensualidad;
  		datosCaptura=arregloObjetosDP[posicion].getClave()+"_"+arregloObjetosDP[posicion].getMarca()+"_"+arregloObjetosDP[posicion].getTipo()+"_"+arregloObjetosDP[posicion].getPrecio()+"_"+plazo+"_"+strInteres+"_"+precioTotal+"_"+mensualidad;
  		resultado = datosVenta;
  		crear (datosCaptura,"Ventas.txt");
  		return resultado;
  	}
  	
  	public String consultarVentas(){
  		
  		String datos="";
    	
    	try{
    		
    	//1. Abrir el archivo
    	archivoIn = new BufferedReader(new FileReader("Ventas.txt"));
    	
    	//2. Procesoar datos
    	while(archivoIn.ready())
    		datos = datos + archivoIn.readLine()+"\n";
    		
    	
    	
    	//3.Cerrar el archivo
    	
    	archivoIn.close();
    	
    	}
    		catch(FileNotFoundException ioe)
    	{
    		datos = "Erroe: "+ioe;
    		
    	}
    	catch(IOException ioe)
    	{
    		datos = "Erroe: "+ioe;
    		
    	}
    	
    	return datos;
  		
  	}
  	
}

import java.util.StringTokenizer;

public class AutoDP{
	
	//Atributos: Varibales de Instancia o de Clase
	
	private String clave,marca , tipo , fecha , hora;
	private int precio;
	
	
	//Contructores
	
	public AutoDP()		
	{
		clave = "";
		marca = "";
		tipo = "";
		precio = 0;
		fecha = "";
		hora = "";
	}
	
	public AutoDP(String datos)
	{
		StringTokenizer st = new StringTokenizer(datos,"_");
		
		clave = st.nextToken();
		marca = st.nextToken();
		tipo = st.nextToken();
		precio = Integer.parseInt(st.nextToken());
		fecha = st.nextToken();
		hora = st.nextToken();
		
	}
	
	
	//Método: Accesors(geter's) y Mutators (seter's)
	
	public String getClave()  //me entrega un valor string , tiene que empezar con mayuscula
	{ 
		return this.clave; //el this es para decirle que es de esa clase 
	}
	
		public String getMarca()  //me entrega un valor string , tiene que empezar con mayuscula
	{ 
		return this.marca;
	}
	
		public String getTipo()  
	{ 
		return this.tipo;
		
	}
	
		public int  getPrecio()   //Me va regresar un entero 
	{ 
		return this.precio;
	}
	
		public String getFecha()  
	{ 
		return this.fecha;
		
	}
	
	  public String getHora()  
	{ 
		return this.hora;
		
	}
	
	
	//Mutatros 
	
	public void setClave(String clue) // no entrega nada pero va recibir parámetros 
	{
		this.clave = clue;
		//varibale de instancia  a cta 
		//con el this sabe que estoy haciendo nombre al atributo y sin el es el parametro
	}
	
	public void setMarca(String brand)
	{
		this.marca = brand;
	}
	
	public void setTipo(String tcta)
			
	{
			this.tipo = tcta;
	}
	
	public void setPrecio(int price)
	{
		this.precio = price;
	}
	
		public void setFecha(String date)
	{
		this.fecha = date;
	}
	
		public void setHora(String hour)
	{
		this.hora = hour;
	}
	
	
	
	
	
	public String toString() //es un metodo definido en java para un obejto , para que muestre los datos y no las direcciones
	{
		return this.clave+"_"+this.marca+"_"+this.tipo+"_"+this.precio+"_"+this.fecha+"_"+this.hora;
	}
}
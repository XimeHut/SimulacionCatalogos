import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JList;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import java.util.StringTokenizer;

public class AutosGUI extends JFrame implements ActionListener
{
    private JTextField tfClave, tfMarca, tfTipo,tfPrecio;
    private JButton    bCapturar, bConsultar, bConsultarClave,bConsultarMarca ,bCotizar,bSalir;
    private JTextArea  taDatos;
    private JPanel panel1, panel2;
    private JPanel panelGestionAutos , panelImages , panelPrincipal;
    private JTextArea taImages;
    
    private ImageIcon  arregloImages[]; //cerra un arreglo de immages icon 
    private JList listaImages;
    
    
    private Autos autosad = new Autos ();
    
    public AutosGUI()
    {
        super("Catalogo de Autos");
        
        // 1. Crear objetos de los atributos
        tfClave    = new JTextField();
        tfMarca     = new JTextField();
        tfTipo       = new JTextField();
        tfPrecio     = new JTextField();
        bCapturar   = new JButton("Capturar datos");
        bConsultar  = new JButton("Consultar datos");
        bConsultarClave = new JButton("Consultar Clave");
        bConsultarMarca = new JButton ("Consultar Marca");
        bCotizar=      new JButton ("Cotizar Auto");
        bSalir      = new JButton("Exit");
        taDatos     = new JTextArea(10,30);
        panel1      = new JPanel();
        panel2      = new JPanel();
        
        panelGestionAutos = new JPanel();
        panelImages = new JPanel();
        panelPrincipal = new JPanel();
        
        // 1.2 Adicionar addActionListener a los JButtons
        bCapturar.addActionListener(this);
        bConsultar.addActionListener(this);
        bConsultarClave.addActionListener(this);
        bConsultarMarca.addActionListener(this);
        bCotizar.addActionListener(this);
        bSalir.addActionListener(this);
        
        // 2. Definir el Layout de los JPanels
        panel1.setLayout(new GridLayout(8,3));
        panel2.setLayout(new FlowLayout());
        
        panelGestionAutos.setLayout(new GridLayout(1,1)); //renglon columna , como una matriz
        panelImages.setLayout(new GridLayout(1,1));
        panelPrincipal.setLayout(new BorderLayout(3,3));//todo el el centro con norte sur este oeste
        
        // 3. Colocar los objetos en los JPanels
        panel1.add(new JLabel("CLAVE: "));
        panel1.add(tfClave);
        panel1.add(new JLabel("MARCA: "));
        panel1.add(tfMarca);
        panel1.add(new JLabel("TIPO: "));
        panel1.add(tfTipo);
        panel1.add(new JLabel("PRECIO:"));
        panel1.add(tfPrecio);
        panel1.add(bCapturar);
        panel1.add(bConsultar);
        panel1.add(bConsultarClave);
        panel1.add(bConsultarMarca);
        panel1.add(bCotizar);
        panel1.add(bSalir);
        
        panel2.add(panel1);
        panel2.add(new JScrollPane(taDatos));
        
        panelGestionAutos.add(panel2);
        panelImages.add(new JScrollPane(taImages)); //aqui esta en panle de las imagenes
        
        panelPrincipal.add(panelGestionAutos,BorderLayout.WEST);
        panelPrincipal.add(panelImages,BorderLayout.CENTER);
        
        // 4. Adicionar los JPanels al JFrame
        add(panelPrincipal);
        
        // 5. Visualizar el JFrame
        setSize(800,400);
        setVisible(true);
    }
    
    private String obtenerDatos()//es privado por que solo lo va utilizar el GUI
    {
    	String datos ="";
    	
    	String clave=tfClave.getText();
    	String marca=tfMarca.getText();
    	String tipo=tfTipo.getText();
    	String precio=tfPrecio.getText();
    	
    	if (clave.equals("") || marca.isEmpty() || tipo.isEmpty() || precio.isEmpty()) //is empty me entrega un valor true o false , para checar si el usuario si anoto algo , comillas comillas pegado es importante
    		datos = "Vacio";                                             // es o por que pede que al menos uno este vacio
    		
    	else 
    		
    	{
    		try{
    		int n = Integer.parseInt(precio);
    		datos= clave+"_"+marca+"_"+tipo+"_"+precio;	
    		}
    		catch(NumberFormatException nfe)
    		{
    			datos = "NO_NUMERICO";
    		}
    		
    	}
    		
    
    	
    	return datos;
    }
    
    private void desplegarDatos(String datos)
    {
    	StringTokenizer st = new StringTokenizer(datos,"_");
    	
    	tfClave.setText(st.nextToken());
    	tfMarca.setText(st.nextToken());
    	tfTipo.setText(st.nextToken());
    	tfPrecio.setText(st.nextToken().trim());
    	
    }
     
    
    public void actionPerformed(ActionEvent e)
    {
        String datos, resultado;
        
        if(e.getSource() == bCapturar)
        	
        {
        	//1.- Obtener los datos de los JTextFields
        	
        	datos = obtenerDatos();
        	
        	//2.-Capturar los datos a traves del objeto BiblioAD
        	if(datos.equals("Vacio"))
        		resultado = "Algun campo esta vacio..."; // PARA QUE EL USUARIO SEPA QUE ALGUN DATO FALTA
        		else 
        			if(datos.equals("NO_NUMERICO"))
        				taDatos.setText("Precio debe ser numerico");
        				else
        				{
        						resultado = autosad.capturar(datos);
					        	//3.-Desplegar el resultado de la transaccion
					        	taDatos.setText(resultado);
        				}
        		
        	
        	
            //taDatos.setText("Capturar Datos...");
        }
        
        if(e.getSource() == bConsultar)
        	
        {
            //1. Obtener los autos en un String
        	datos = autosad.consultar();
        	
            taDatos.setText(datos);
            
             //2. Obtener el arreglo de ImagesIcons en arregloImages
            
            arregloImages = autosad.obtenerImages();
            
            //3.-Crear el Jlist del arreglo de imagenes
            listaImages = new JList(arregloImages);
            
            //4.-Desplegar el Jlist de imagen en el panelImages
            panelImages.setVisible(false);
            panelImages.removeAll(); //si el obejto tenia algo , esto quita esto del panel
            panelImages.add(new JScrollPane(listaImages));
            panelImages.setVisible(true);
        }
        
        if(e.getSource() == bConsultarClave)
        {
            String auto = tfClave.getText();
            datos = autosad.consultarClave(auto);
            
            if(datos.equals("NOT_FOUND"))
            	{
            	taDatos.setText("No se localizo la clave: "+auto);
            		panelImages.setVisible(false);
					panelImages.removeAll(); //si el obejto tenia algo , esto quita esto del panel
					taImages    = new JTextArea("Books Images",10,10);
					panelImages.add(new JScrollPane(taImages));
					panelImages.setVisible(true);
            }
            else{
             taDatos.setText(datos);
            desplegarDatos(datos);
            arregloImages = autosad.obtenerImages();
					
					//-Crear el Jlist del arreglo de imagenes
					listaImages = new JList(arregloImages);
					
					//.-Desplegar el Jlist de imagen en el panelImages
					panelImages.setVisible(false);
					panelImages.removeAll(); //si el obejto tenia algo , esto quita esto del panel
					panelImages.add(new JScrollPane(listaImages));
					panelImages.setVisible(true);
            }
          
            
        }
        
          if(e.getSource() == bConsultarMarca)
        {
            String mar = tfMarca.getText();
            datos = autosad.consultarMarca(mar);
               //Checar los datos que llefan del AD
                 if(datos.equals("NOT_FOUND"))
            	taDatos.setText("No se localizo el Auto:"+mar);
            //3. Obtener el arreglo de ImagesIcons en arregloImages
            else{
                   	taDatos.setText(datos);
					arregloImages = autosad.obtenerImages();
					
					//4.-Crear el Jlist del arreglo de imagenes
					listaImages = new JList(arregloImages);
					
					//5.-Desplegar el Jlist de imagen en el panelImages
					panelImages.setVisible(false);
					panelImages.removeAll(); //si el obejto tenia algo , esto quita esto del panel
					panelImages.add(new JScrollPane(listaImages));
					panelImages.setVisible(true);
		   }
            
        }
        
        
        
           if(e.getSource() == bCotizar)
        {
        	datos = obtenerDatos();
        	
        	if(datos.equals("Vacio"))
        	{
        		taDatos.setText("Algun campo esta vacio...");
        	}
        	else 
        		if(datos.equals("NO_NUMERICO"))
        		{
        			taDatos.setText("Precio debe ser numerico....");
        		}
        		
        		else
        		{
        	
        	
        try{
        	
        	
          String  strPlazo= JOptionPane.showInputDialog("Ingresa el numero de mese del plazo (12/24/48)");
          int plazo = Integer.parseInt(strPlazo);
          
          String cotizacion = autosad.cotizarAuto(datos,plazo);
          taDatos.setText(cotizacion);
          }
          
          catch (NumberFormatException nfe)
          {
          	taDatos.setText("El plazo de pago debe ser numerico");
          }
          
          	}
          
          	
          
        }
        
        
        if(e.getSource() == bSalir)
        {
            System.exit(0);
        }
    }
    
    
    public static void main(String args[])
    {
        new AutosGUI();
    }
}

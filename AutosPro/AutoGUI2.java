import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.FlowLayout;

import java.util.Date;
import java.util.StringTokenizer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AutoGUI2 extends JFrame implements ActionListener
{
    private JTextField tfClave, tfMarca, tfTipo, tfPrecio;
    private JButton    bCapturar, bConsultar, bSalir;
    private JPanel     panel1, panel2;
    private JTextArea  taDatos;
    private JButton bDatosArchArreglo , bConsultarArreglo;
    private JButton bDatosArchArregloObj , bConsultarArregloObj;
    private JButton bConsultarArregloMarca , bConsultarArregloObjMarca;
    private JButton bConsultarArregloClave, bConsultarArregloObjClave;
    private JButton bVenta , bCancelar;
    private JButton bArregloObjArch;
    private JButton bCotizar,bConsultarVentas ;
   
   
    
    
    private AutoAD auto = new AutoAD();
    
    public AutoGUI2()
    {
        super("Admin de Autos");
        
        // 1. Crear los objetos de los atributos
        tfClave = new JTextField();
        tfMarca = new JTextField();
        tfTipo   = new JTextField();
        tfPrecio  = new JTextField();
        bCapturar = new JButton("Capturar datos (Archivo)");
        bConsultar = new JButton("Consultar Autos (Archivo)");
        
       
       // bDatosArchArregloObj = new JButton("Datos Archivo --> Arreglo Obj");
        bConsultarArregloObj = new JButton ("Consultar Datos(Arreglo Objetos)");
        bConsultarArregloObjMarca = new JButton ("Consultar Marca ( Arreglo Objetos)");
        bConsultarArregloObjClave = new JButton ("Consultar Clave (Arreglo Objetos)");
        bCotizar = new JButton("Cotizar (Arreglo Objetos)");
        bVenta     = new JButton("Venta del Auto(Arreglo Objetos)");
        bConsultarVentas = new JButton("Consultar Ventas");    
        bCancelar = new JButton ("Cancelar Transaccion(Arreglo Objetos)");
      
      
        
        
        bSalir = new JButton("Exit");
        panel1 = new JPanel();
        panel2 = new JPanel();
        taDatos = new JTextArea(11,30);
        
        // Adicionar addActionListener a lo JButtons
        bCapturar.addActionListener(this);
        bConsultar.addActionListener(this);
        bSalir.addActionListener(this);
        
       
         bConsultarArregloObj.addActionListener(this);
         
      
         bConsultarArregloObjMarca.addActionListener(this);
         
      
        bConsultarArregloObjClave.addActionListener(this);
     
   
        bVenta.addActionListener(this);
        
      
        
        bConsultarVentas.addActionListener(this);
        bCotizar.addActionListener(this);
      	bCancelar.addActionListener(this);
        
        bCotizar.setEnabled(false);
        bCancelar.setEnabled(false);
        bVenta.setEnabled(false);
        
           
         	      
        // 2. Definir los Layouts de los JPanels
        panel1.setLayout(new GridLayout(9,2)); //11 renglones con dos columnas , en realidad esto es un arreglo bidemensional , lo tengo que referenciar a una matriz de textfields
        panel2.setLayout(new FlowLayout());
        
        // 3. Colocar los objetos de los atributos en los JPanels correspondientes
        panel1.add(new JLabel("CLAVE:"));
        panel1.add(tfClave);
        panel1.add(new JLabel("MARCA: "));
        panel1.add(tfMarca);
        panel1.add(new JLabel("TIPO:"));
        panel1.add(tfTipo);
        panel1.add(new JLabel("PRECIO:"));
        panel1.add(tfPrecio);
        panel1.add(bCapturar);
        panel1.add(bConsultar);
        panel1.add(bConsultarArregloObjMarca);
        panel1.add(bConsultarArregloObj);
        panel1.add(bConsultarArregloObjClave);	
        //panel1.add(bDatosArchArregloObj);
        panel1.add(bCotizar);
        panel1.add(bCancelar);
        panel1.add(bVenta);
        panel1.add(bSalir);
        panel1.add(bConsultarVentas);
       
        
        panel2.add(panel1);
        panel2.add(new JScrollPane(taDatos));
        
        // 4. Adicionar el panel2 al JFrame y hacerlo visible
        add(panel2);
        setSize(600,600);
        setVisible(true);
    }
    
    private String obtenerDatos()
    {
        String datos;
        
        String clave  = tfClave.getText();
        String marca = tfMarca.getText();
        String tipo   = tfTipo.getText();
        String precio = tfPrecio.getText();
        
        String strfechaApertura = "";
        String srthoraApertura = "";
        
        if(clave.equals("") || marca.isEmpty() || tipo.equals("") || precio.isEmpty())
            datos = "VACIO";
        else
        {
            try
            {
                int n = Integer.parseInt(precio); // validacion de que es numerico
                datos = clave+"_"+marca+"_"+tipo+"_"+precio;
                Date fechaCapturaAuto;
    			fechaCapturaAuto = new Date();
    			strfechaApertura = strfechaApertura.format("%tF",fechaCapturaAuto);
    			srthoraApertura = strfechaApertura.format("%tT",fechaCapturaAuto);
    			datos = datos+"_"+strfechaApertura+"_"+srthoraApertura;
                
                
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
        tfPrecio.setText(st.nextToken());
    }
 private void activarBotones()
    {
        bCotizar.setEnabled(true);
        bVenta.setEnabled(true);
        bCancelar.setEnabled(true);
        
        bCapturar.setEnabled(false);
        bConsultar.setEnabled(false);
        bConsultarArregloObjClave.setEnabled(false);
        bConsultarArregloObjMarca.setEnabled(false);
        bConsultarArregloObj.setEnabled(false);
    }
    
    private void inactivarBotones()
    {
        bCotizar.setEnabled(false);
        bVenta.setEnabled(false);
        bCancelar.setEnabled(false);
        
        bCapturar.setEnabled(true);
        bConsultar.setEnabled(true);
        bConsultarArregloObjClave.setEnabled(true);
        bConsultarArregloObjMarca.setEnabled(true);
        bConsultarArregloObj.setEnabled(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        String datos, respuesta, resultado;
        
        if(e.getSource() == bCapturar)
        {
            // 1. Obtener datos
            datos = obtenerDatos();
            
            // 2. Checar datos: datos no vacios y saldo numerico, y realizar la captura de datos
            if(datos.equals("VACIO"))
                respuesta = "Algun campo esta vacio...";
            else
                if(datos.equals("NO_NUMERICO"))
                    respuesta = "Saldo debe ser numerico...";
                else
                	
                    respuesta = auto.capturar(datos);
               
            
            // 3. Desplegar esultado de transaccion
            taDatos.setText(respuesta);
        }
        
        if(e.getSource() == bConsultar)
        {
            // 1. Realizar consulta de clientes
            datos = auto.consultarAutos();
           // datos="Consultar datos del archivo";
            // 2. Desplegar datos
            taDatos.setText(datos);
        }
        
    
          if(e.getSource() == bConsultarArregloObjClave)
        {
        	 // 1. Leer clave del tfClave
        	String clue= tfClave.getText();
        	
        	 // 2.Consultar la clave en AutosAD
  
        	datos = auto.consultarClaveObj(clue);
           // 3. Despleagr la respuesta
            //taDatos.setText(datos);
            if(datos.equals("ARREGLO_VACIO"))
                taDatos.setText("Arreglo de Objetos vacio...");
            else
                if(datos.equals("NOT_FOUND"))
                    taDatos.setText("Clave de Auto no localizada...");
                else
                {
                    taDatos.setText(datos);
                    desplegarDatos(datos);
                    activarBotones();
        	
        	}
        }
        
              if(e.getSource() == bConsultarArregloObjMarca)
        {
        	 // 1. Leer marca del tfMarca
            String marc= tfMarca.getText();
            // 2.Consultar la marca en AutosAD
            datos = auto.consultarMarcaObj(marc);
            // 3. Despleagr la respuesta
            taDatos.setText(datos);
        	
        	
        }
        
    
      if(e.getSource() == bConsultarArregloObj)
        {
        	datos = auto.consultarArchArreObj();
        	taDatos.setText(datos);
        	
        }
        
       	  if(e.getSource() == bCotizar)
        {
          try
            {
                // 2. Pedir plazo de la cotizacion al usuario
                String strPlazo = JOptionPane.showInputDialog("No. de Meses de la Cotizacion [12, 24, 48]");
                int  plazo = Integer.parseInt(strPlazo);
                
                // 3. Cotizar automovil desde AutosAD
                String cotizacion = auto.cotizarAuto(plazo);
               
                
                // 4. Desplegar resultado de la captura
                taDatos.setText(cotizacion);
            }
            catch(NumberFormatException nfe)
            {
                taDatos.setText("El Plazo de pago debe ser numerico...");
            }  
        }
        
    
        if(e.getSource() == bVenta)
        {
            
            
                // 1. Pedir plazo de la cotizacion al usuario
                String strPlazo = JOptionPane.showInputDialog("No. de Meses de la Cotizacion [12, 24, 48]");
                int  plazo = Integer.parseInt(strPlazo);
                
                // 2. Venta automovil desde AutosAD
                resultado = auto.venta(plazo);
               
                // 3. Desplegar resultado de la captura
                taDatos.setText(resultado);
                
                inactivarBotones();
            
        }
        
        
           if(e.getSource() == bCancelar)
        {
            inactivarBotones();
        }
        
             if(e.getSource() == bConsultarVentas)
        {
            // 1. ejecutar el metodo do consultar de AutosAD
            datos = auto.consultarVentas();
            
            // 2. Despleagr la respuesta
            taDatos.setText(datos);
        }
        
        
        if(e.getSource() == bSalir)
        {
            System.exit(0);
        }
    }
    
    public static void main(String args[])
    {
        new AutoGUI2();
    }
}




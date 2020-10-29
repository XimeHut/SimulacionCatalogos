import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JList;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LibroGUI2 extends JFrame implements ActionListener
{
    private JTextField tfTitulo, tfAutor, tfEditorial;
    private JButton    bCapturar, bConsultar, bConsultarEditorial,bConsultarTitulo ,bSalir;
    private JTextArea  taDatos;
    private JPanel panel1, panel2;
    private JPanel panelGestionLibros , panelImages , panelPrincipal;
    private JTextArea taImages;
    
    private ImageIcon  arregloImages[]; //cerra un arreglo de immages icon 
    private JList listaImages;
    
    private BiblioAD biblioad = new BiblioAD ();
    
    public LibroGUI2()
    {
        super("Biblioteca Tec");
        
        // 1. Crear objetos de los atributos
        tfTitulo    = new JTextField();
        tfAutor     = new JTextField();
        tfEditorial = new JTextField();
        bCapturar   = new JButton("Capturar datos");
        bConsultar  = new JButton("Consultar Libros");
        bConsultarEditorial = new JButton("Consultar Editorial");
        bConsultarTitulo = new JButton ("Consultar Titulo");
        bSalir      = new JButton("Exit");
        taDatos     = new JTextArea(10,30);
        taImages    = new JTextArea("Books Images",10,10);
        panel1      = new JPanel();
        panel2      = new JPanel();
        
        panelGestionLibros = new JPanel();
        panelImages = new JPanel();
        panelPrincipal = new JPanel();
        
        // 1.2 Adicionar addActionListener a los JButtons
        bCapturar.addActionListener(this);
        bConsultar.addActionListener(this);
        bConsultarEditorial.addActionListener(this);
        bConsultarTitulo.addActionListener(this);
        bSalir.addActionListener(this);
        
        // 2. Definir el Layout de los JPanels
        panel1.setLayout(new GridLayout(6,2));
        panel2.setLayout(new FlowLayout());
        
        panelGestionLibros.setLayout(new GridLayout(1,1)); //renglon columna , como una matriz
        panelImages.setLayout(new GridLayout(1,1));
        panelPrincipal.setLayout(new BorderLayout(3,3));//todo el el centro con norte sur este oeste
        
        // 3. Colocar los objetos en los JPanels
        panel1.add(new JLabel("TITULO: "));
        panel1.add(tfTitulo);
        panel1.add(new JLabel("AUTOR: "));
        panel1.add(tfAutor);
        panel1.add(new JLabel("EDITORIAL: "));
        panel1.add(tfEditorial);
        panel1.add(bCapturar);
        panel1.add(bConsultar);
        panel1.add(bConsultarEditorial);
        panel1.add(bConsultarTitulo);
        panel1.add(bSalir);
        
        panel2.add(panel1);
        panel2.add(new JScrollPane(taDatos));
        
        panelGestionLibros.add(panel2);
        panelImages.add(new JScrollPane(taImages)); //aqui esta en panle de las imagenes
        
        panelPrincipal.add(panelGestionLibros,BorderLayout.WEST);
        panelPrincipal.add(panelImages,BorderLayout.CENTER);
        
        // 4. Adicionar los JPanels al JFrame
        add(panelPrincipal);
        
        // 5. Visualizar el JFrame
        setSize(100,300);
        setVisible(true);
    }
    
    private String obtenerDatos()//es privado por que solo lo va utilizar el GUI
    {
    	String datos ="";
    	
    	String titulo=tfTitulo.getText();
    	String autor=tfAutor.getText();
    	String editorial=tfEditorial.getText();
    	
    	if (titulo.equals("") || autor.isEmpty() || editorial.isEmpty()) //is empty me entrega un valor true o false , para checar si el usuario si anoto algo , comillas comillas pegado es importante
    		datos = "Vacio";                                             // es o por que pede que al menos uno este vacio
    		
    	else 
    		
    	datos= titulo+"_"+autor+"_"+editorial;
    	
    	return datos;
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
        			resultado = biblioad.capturar(datos);
        	
        	
        	//3.-Desplegar el resultado de la transaccion
        	taDatos.setText(resultado);
        	
        	
            //taDatos.setText("Capturar Datos...");
        }
        
        if(e.getSource() == bConsultar)
        	
        {
        	//1. Obtener los libros en un String
        	datos = biblioad.consultarLibros();
            taDatos.setText(datos);
            
            //2. Obtener el arreglo de ImagesIcons en arregloImages
            
            arregloImages = biblioad.obtenerImages();
            
            //3.-Crear el Jlist del arreglo de imagenes
            listaImages = new JList(arregloImages);
            
            //4.-Desplegar el Jlist de imagen en el panelImages
            panelImages.setVisible(false);
            panelImages.removeAll(); //si el obejto tenia algo , esto quita esto del panel
            panelImages.add(new JScrollPane(listaImages));
            panelImages.setVisible(true);
        }
        
        if(e.getSource() == bConsultarEditorial)
        {
        	//1. Obtener la Editorial en un String
            String edit = tfEditorial.getText();
            
            //2.-Obtener los libros de la Editorial en un String
            datos = biblioad.consultarEditorial(edit);
        
            //Checar los datos que llegan del AD
            if(datos.equals("NOT_FOUND")){
            		taDatos.setText("No se localizo la Editorial:"+edit);
            		panelImages.setVisible(false);
					panelImages.removeAll(); //si el obejto tenia algo , esto quita esto del panel
					taImages    = new JTextArea("Books Images",10,10);
					panelImages.add(new JScrollPane(taImages));
					panelImages.setVisible(true);
            }
            
            	
            //3. Obtener el arreglo de ImagesIcons en arregloImages
            else{
                   	taDatos.setText(datos);
					arregloImages = biblioad.obtenerImages();
					
					//4.-Crear el Jlist del arreglo de imagenes
					listaImages = new JList(arregloImages);
					
					//5.-Desplegar el Jlist de imagen en el panelImages
					panelImages.setVisible(false);
					panelImages.removeAll(); //si el obejto tenia algo , esto quita esto del panel
					panelImages.add(new JScrollPane(listaImages));
					panelImages.setVisible(true);
		   }
        }
        
          if(e.getSource() == bConsultarTitulo)
        {
        	//1. Obtener el Titulo en un String
        	
            String tit = tfTitulo.getText();
            
            //2. Obtener el libro del titulo
            datos = biblioad.consultarTitulo(tit);
           
            
            //Checar los datos que llefan del AD
                 if(datos.equals("NOT_FOUND"))
            	taDatos.setText("No se localizo el Libro:"+tit);
            //3. Obtener el arreglo de ImagesIcons en arregloImages
            else{
                   	taDatos.setText(datos);
					arregloImages = biblioad.obtenerImages();
					
					//4.-Crear el Jlist del arreglo de imagenes
					listaImages = new JList(arregloImages);
					
					//5.-Desplegar el Jlist de imagen en el panelImages
					panelImages.setVisible(false);
					panelImages.removeAll(); //si el obejto tenia algo , esto quita esto del panel
					panelImages.add(new JScrollPane(listaImages));
					panelImages.setVisible(true);
		   }
        }
        
         /*  if(e.getSource() == bConsultarTitulo)
        {
            arregloImages = biblioad.getImage();
            
            listaImages = new JList(arregloImages); //Jlist del arreglo de imagenes 
            
            panelImages.setVisible(false);
            panelImages.removeAll(); //si el obejto tenia algo , esto quita esto del panel
            panelImages.add(new JScrollPane(listaImages));
            panelImages.setVisible(true);
        }*/
        
        if(e.getSource() == bSalir)
        {
            System.exit(0);
        }
    }
    
    public static void main(String args[])
    {
        new LibroGUI2();
    }
}

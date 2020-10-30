from tkinter import * #el * importa todo lo que vaya ocupar, en tkinter esta todas las que vamos a ocupar

from AutosAD import AutosAD


class AutosGUI:
    frame = Tk()
    autosad = AutosAD()

    
    
    def __init__(self):
                       
        
        #1. Definir los atributos y crear los objetos de tales atributos 
        self.frame.title("AUTOS")
        self.frame.geometry("700x400")  #set size(400,400)

        #Labels
        self.lbClave= Label(self.frame, text="ClAVE:   ")
        self.lbMarca = Label (self.frame, text="MARCA: ")
        self.lbTipo = Label (self.frame, text="TIPO:")
        self.lbPrecio= Label (self.frame, text="PRECIO:    ")

        #JTextFields

        self.tfClave = Entry(self.frame, width=20)
        self.tfMarca = Entry(self.frame, width=20)
        self.tfTipo = Entry(self.frame, width=20)
        self.tfPrecio = Entry(self.frame, width=20)

       #Buttons
        self.bCapturar = Button(self.frame, text="Capturar Datos", command=self.bCapturarEvent)#el command es el action Listener 
        self.bConsultar = Button (self.frame, text="Consultar Datos", command=self.bConsultarEvent)
        self.bConsultarClave = Button(self.frame, text="Consultar Clave", command=self.bConsultarClave)
        self.bConsultarMarca = Button(self.frame, text="Consultar Marca", command=self.bConsultarMarca)

       #JTextArea   

        self.taDatos = Text(self.frame, width=60, height=10)

       #2. Colocar los atributos en un Layout

        self.lbClave.grid(row=0, column=0)
        self.tfClave.grid(row=0, column=1)

        self.lbMarca.grid(row=1, column=0)
        self.tfMarca.grid(row=1, column=1)

       
        self.lbTipo.grid(row=2, column=0)
        self.tfTipo.grid(row=2, column=1)

        self.lbPrecio.grid(row=3, column=0)
        self.tfPrecio.grid(row=3, column=1)

        
        self.bCapturar.grid(row=4, column=0)
        self.bConsultar.grid(row=4, column=1)

        self.bConsultarClave.grid(row=5, column=0)
        self.bConsultarMarca.grid(row=5, column=1)

        self.taDatos.grid(row=6, column=0)


       #3. Hacer visible el frame

        self.frame.mainloop()           #setVisble(true)

    def obtenerDatos(self):

        clave = self.tfClave.get()
        marca  = self.tfMarca.get()
        tipo   = self.tfTipo.get()
        precio = self.tfPrecio.get()

        if (clave == "" or marca == "" or tipo == "" or precio == ""):
            datos = "VACIO"
        else:
            try:
                nprecio = float(precio)#conversion, si no logra hacer esta conversion es que no es numerico
                datos = clave+"_"+marca+"_"+tipo+"_"+precio
            except:
                datos = "NO_NUMERICO"
  
        return datos
    
    def desplegar(self,datos):
        st = datos.split("_")#separar los datos

        self.tfClave.insert(END,st[0])#inserto en el text field el split 0 que es el primero
        self.tfMarca.insert(END,st[1])
        self.tfTipo.insert(END,st[2])

        stPrecio = st[3].split("\n")    #el strinf que tiene la casilla 3 que es el saldo y el \n , le quito el \n del final
        self.tfPrecio.insert(END,stPrecio[0])



    def limpiarCampos(self): #para que te borre lo que estaba en el textField y poder buscar otro , que no se sobreescriba

        self.tfClave.delete("0",END)
        self.tfMarca.delete("0",END)
        self.tfTipo.delete("0",END)
        self.tfPrecio.delete("0",END)
                            

                             

    def bCapturarEvent(self): #tiene que ser a la misma altura que el constructor

        datos = self.obtenerDatos() #el self es para definir los datos, mandamos a llamar a un metodo dentro del gui

        self.taDatos.delete("1.0",END)

        if(datos == "VACIO"):
            self.taDatos.insert(END,"Algun campo esta vacio....")
        else:
            if(datos == "NO_NUMERICO"):
                resultado= "Precio debe ser numerico..."
                self.taDatos.insert(END,resultado)
            else:
                resultado = self.autosad.check(datos)
                self.taDatos.insert(END,resultado) #el insert es el settext

    def bConsultarEvent(self):

        datos = self.autosad.consultarCliente()
        self.taDatos.delete("1.0",END)
        self.taDatos.insert(END,datos)

    def bConsultarClave(self):
        
        clave =   self.tfClave.get()   
        datos = self.autosad.consultarClave(clave)
        self.taDatos.delete("1.0",END)
        
        if(datos=="NOT_FOUND"):
            self.taDatos.insert(END,"No se localizo la clave"+clave)
        else:
            self.taDatos.insert(END,datos)
            self.limpiarCampos()
            self.desplegar(datos)
        

    def bConsultarMarca(self):

        marca =   self.tfMarca.get()   
        datos = self.autosad.consultarMarca(marca)
        self.taDatos.delete("1.0",END)
        
        if(datos=="NOT_FOUND"):
            self.taDatos.insert(END,"No se localizo ningun coche de la marca"+marca)
        else:
            self.taDatos.insert(END,datos)
           

     
      
           
            
            
        
        
            

# main()
auto = AutosGUI()

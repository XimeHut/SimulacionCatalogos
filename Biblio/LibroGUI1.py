from tkinter import *

from BiblioAD import BiblioAD

class LibroGUI1:
    frame = Tk()
    bilbioad = BiblioAD()

    def __init__(this):

        # 1. Definir los atributos y crear los objetos de tales atributos
        this.frame.title("Biblio-Tec: Gestion de Libros")
        this.frame.geometry("500x400")  # setSize(400,400);
        
        # Labels
        this.lbTitulo = Label(this.frame, text="TITULO:     ")
        this.lbAutor = Label(this.frame, text="AUTOR:     ")
        this.lbEditorial = Label(this.frame, text="EDITORIAL:     ")

        # JTextFields
        this.tfTitulo = Entry(this.frame, width=20)
        this.tfAutor = Entry(this.frame, width=20)
        this.tfEditorial = Entry(this.frame, width=20)

        # JButtons
        this.bCapturar = Button(this.frame, text="Capturar Datos", command=this.bCapturarEvent)
        this.bConsultar = Button(this.frame, text="Consultar Libros", command=this.bConsultarEvent)
        this.bConsultarTitulo = Button(this.frame, text="Consultar Titulo" , command=this.bConsultarTituloEvent)
        this.bConsultarAutor = Button(this.frame, text="Consultar Autor", command=this.bConsultarAutorEvent)
        this.bConsultarEditorial = Button(this.frame, text="Consultar Editorial", command=this.bConsultarEditorialEvent)

        # JTextArea
        this.taDatos = Text(this.frame, width=40, height=10)

        # 2. Colocar los atributos en un Layout
        this.lbTitulo.grid(row=0, column=0)
        this.tfTitulo.grid(row=0, column=1)
        this.lbAutor.grid(row=1, column=0)
        this.tfAutor.grid(row=1, column=1)
        this.lbEditorial.grid(row=2, column=0)
        this.tfEditorial.grid(row=2, column=1)
        
        this.bCapturar.grid(row=3, column=0)
        this.bConsultar.grid(row=3, column=1)
        this.bConsultarTitulo.grid(row=4, column=0)
        this.bConsultarAutor.grid(row=4, column=1)
        this.bConsultarEditorial.grid(row=5, column=0)

        this.taDatos.grid(row=6, column=0)
        
        # 3. Hacer visible el frame
        this.frame.mainloop()           # setVisible(true);

    def obtenerDatos(this):
        titulo = this.tfTitulo.get()
        autor = this.tfAutor.get()
        editorial = this.tfEditorial.get()

        if(titulo == "" or autor == "" or editorial == ""):
            datos = "VACIO"
        else:
            datos = titulo+"_"+autor+"_"+editorial

        return datos
        
    
    def bCapturarEvent(this):
        datos = this.obtenerDatos()
        this.taDatos.delete("1.0",END)
        if(datos == "VACIO"):
            this.taDatos.insert(END,"Algun campo esta vacio...")
        else:
            resultado = this.bilbioad.capturar(datos)
            this.taDatos.insert(END,resultado)

    def bConsultarEvent(this):
        this.taDatos.delete("1.0",END)
        resultado = this.bilbioad.consultaGeneral()
        if(resultado == "ERROR"):
            resultado = "El archivo no existe, favor de capturar algun dato"
            
        this.taDatos.insert(END,resultado)

    def bConsultarTituloEvent(this):
        tit = this.tfTitulo.get()
        this.taDatos.delete("1.0",END)
        resultado = this.bilbioad.consultarTitulo(tit)
        if(resultado == "ERROR"):
            resultado = "El titulo "+tit+" no fue encontrado"
        
        this.taDatos.insert(END,resultado)

    def bConsultarAutorEvent(this):
        aut = this.tfAutor.get()
        this.taDatos.delete("1.0",END)
        resultado = this.bilbioad.consultarAutor(aut)
        if(resultado == "ERROR"):
            resultado = "El autor "+aut+" no fue encontrado"
         
        this.taDatos.insert(END,resultado)

    def bConsultarEditorialEvent(this):
        edit = this.tfEditorial.get()
        this.taDatos.delete("1.0",END)
        resultado = this.bilbioad.consultarEditorial(edit)
        if(resultado == "ERROR"):
            resultado = "La editorial "+edit+" no fue encontrada"
     
        this.taDatos.insert(END,resultado)

# main()
libro = LibroGUI1()

class BiblioAD:
    def capturar(this,datos):
        # 1. Abrir el archivo
        archivo = open("Libros.txt","a")

        # 2. Escribir, guardar o almacenar los datos en el archivo
        archivo.write(datos+"\n")

        # 3. Cerrar el archivo
        archivo.close()
        
        return "Datos a capturar: "+datos

    def consultaGeneral(this):
        datos=""
        libro=""
        
        try:
            # 1. Abrir el archivo
            archivo = open("Libros.txt","r")

            # 2. Procesar los datos del archivo
            libro = archivo.readline()
            while(libro != ""):
                datos = datos+libro
                libro = archivo.readline()

            # 3. Cerrar el archivo
            archivo.close()

            datos = "CONSULTA GENERAL:\n"+datos
        except:
            datos="ERROR"
        return datos

    def consultarEditorial(this, edit):
        datos=""
        libro=""
        encontrado=False
        
        
        try:
            # 1. Abrir el archivo
            archivo = open("Libros.txt","r")

            # 2. Procesar los datos del archivo
            edit = edit+"\n"
            libro = archivo.readline()
            
            while(libro != ""):
                st = libro.split(".")
                second = st[2]
                #edit = edit+"\n"

                if(edit == st[2]):
                    datos = datos + libro
                    encontrado = True

                libro = archivo.readline()

            # 3. Cerrar el archivo
            archivo.close()

            if(not encontrado):
                datos = "ERROR"

            
        except:
            datos="ERROR ABRIENDO EL ARCHIVO"
        return datos

    def consultarTitulo(this, tit):
        datos=""
        libro=""
        encontrado=False
        
        try:
            # 1. Abrir el archivo
            archivo = open("Libros.txt","r")

            # 2. Procesar los datos del archivo
            libro = archivo.readline()
            
            while(libro != ""):
                st = libro.split("_")

                if(tit == st[0]):
                    datos = datos + libro
                    encontrado = True

                libro = archivo.readline()

            # 3. Cerrar el archivo
            archivo.close()

            if(not encontrado):
                datos = "ERROR"
  
            
        except:
            datos="ERROR ABRIENDO EL ARCHIVO"
        return datos

    def consultarAutor(this, aut):
        datos=""
        libro=""
        encontrado=False
        
        try:
            # 1. Abrir el archivo
            archivo = open("Libros.txt","r")

            # 2. Procesar los datos del archivo
            libro = archivo.readline()
            
            while(libro != ""):
                st = libro.split("_")

                if(aut == st[1]):
                    datos = datos + libro
                    encontrado = True

                libro = archivo.readline()

            # 3. Cerrar el archivo
            archivo.close()

            if(not encontrado):
                datos = "ERROR"
      
            
        except:
            datos="ERROR ABRIENDO EL ARCHIVO"
        return datos

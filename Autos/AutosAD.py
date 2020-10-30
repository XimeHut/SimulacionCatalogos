class AutosAD:

    def check(self, datos):
        
        st = datos.split("_")
        clave = st[0]

        respuesta = self.consultarClave(clave)

        if (respuesta == "NOT_FOUND"):
            res = self.capturar(datos)
        else:
            res = "Ya existe el numero de Clave:"+clave

        return res

    
    def capturar(self,datos):
        
        # 1. Abrir el archivo
        archivo = open("Autos.txt","a")

        # 2. Escribir, guardar o almacenar los datos en el archivo
        archivo.write(datos+"\n")

        # 3. Cerrar el archivo
        archivo.close()
        
        return "Datos a capturar: "+datos

    def consultarCliente(self):
        datos=""
        cliente=""
        
        try:
            # 1. Abrir el archivo
            archivo = open("Autos.txt","r")

            # 2. Procesar los datos del archivo
            cliente = archivo.readline()
            while(cliente != ""):
                datos = datos+cliente
                cliente = archivo.readline()

            # 3. Cerrar el archivo
            archivo.close()

            datos = "CONSULTA GENERAL:\n"+datos
        except:
            datos="ERROR"
            
        return datos


    def consultarClave(self, clave):
        datos=""
        cliente=""
        encontrado=False
        
        try:
            # 1. Abrir el archivo
            archivo = open("Autos.txt","r")

            # 2. Procesar los datos del archivo
            cliente = archivo.readline()
            
            while(cliente != ""):
                st = cliente.split("_")

                if(clave == st[0]):
                    datos = datos + cliente
                    encontrado = True

                cliente = archivo.readline()

            # 3. Cerrar el archivo
            archivo.close()

            if(not encontrado):
                datos = "NOT_FOUND"
         
        except:
            
            datos="ERROR_ClAVE"
            
        return datos

    def consultarMarca(self, marca):
        datos=""
        cliente=""
        encontrado=False
        
        try:
            # 1. Abrir el archivo
            archivo = open("Autos.txt","r")

            # 2. Procesar los datos del archivo
            cliente = archivo.readline()
            
            while(cliente != ""):
                st = cliente.split("_")

                if(marca == st[1]):
                    datos = datos + cliente
                    encontrado = True

                cliente = archivo.readline()

            # 3. Cerrar el archivo
            archivo.close()

            if(not encontrado):
                datos = "NOT_FOUND"
         
        except:
            
            datos="ERROR_MARCA"
            
        return datos





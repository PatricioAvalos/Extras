@Override 
   public void leerPersonas() {
       
        try {
            
            FileReader f = new FileReader("personas.txt");
            BufferedReader  br = new BufferedReader(f);
            
            String linea;
            
            while((linea=br.readLine())!=null){
                String[] campos = linea.split("\\\\");
   
                String Rut = campos[0];
                String Nombre = campos[1];
                String Apellido = campos[2];
                String Correo = campos[3];
                String Estudio = campos[4];
                int Dato1 = Integer.parseInt(campos[5]);
                int Dato2 = Integer.parseInt(campos[6]);   
                
                int n = Correo.indexOf("@");
                String Alias =  Correo.substring(0,n);
                
                if(Estudio.equals("alumno")){
                   Alumno alumno = new Alumno(Rut, Nombre, Apellido, Correo, Alias, Dato1, Dato2);
                        StdOut.println(Rut+Nombre+Apellido+Correo+Alias+Dato1+Dato2);
                        alumno.setAlias(Alias);
                        listaPersonas.insertarOrdenado(alumno);
                }
                if(Estudio.equals("profesor")){
                   Profesor profesor = new Profesor(Rut, Nombre, Apellido, Correo, Alias, Dato1, Dato2);
                        StdOut.println(Rut+Nombre+Apellido+Correo+Alias+Dato1+Dato2);
                        profesor.setAlias(Alias);
                        listaPersonas.insertarOrdenado(profesor);  
                }
                           
            }

            
        } catch (IOException ex) {
            System.out.println("No se pudo leer el archivo personas.txt");
        }       
   
   }  

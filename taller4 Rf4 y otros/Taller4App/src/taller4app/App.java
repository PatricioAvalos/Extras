/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller4app;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import ucn.ArchivoEntrada;
import ucn.Registro;
import ucn.StdIn;
import ucn.StdOut;


public class App implements IApp {
 
    
    private ListaInscripciones listaInscripciones;
    private ListaMensajes listaMensajes;
    private ListaAsignaturas listaAsignaturas;
    private ListaPersonas listaPersonas;
    

    public App() {
        
        listaPersonas = new ListaPersonas();
        listaInscripciones = new ListaInscripciones();
        listaAsignaturas = new ListaAsignaturas();
        
    }

    // Método de lectura de los archivos .txt
    @Override        
    public void leerInscripciones() {
             
        try {
            ArchivoEntrada in = new ArchivoEntrada("inscripciones.txt");
            while(!in.isEndFile()){
                Registro reg = in.getRegistro();
                
                String Alias = reg.getString();
                String CodigoA = reg.getString();
                
                Inscripcion i = new Inscripcion(Alias,CodigoA);
                
                listaInscripciones.ingresar(i);               
                }
     
        } catch (IOException ex) {
            System.out.println("No se pudo leer el archivo");
        }

    }
    
   @Override 
   public void leerAsignaturas() {
       
            try {
            
            FileReader f = new FileReader("asignaturas.txt");
            BufferedReader  br = new BufferedReader(f);
            
            String linea;
            
            while((linea=br.readLine())!=null){
                String[] campos = linea.split(".");
                
                String Aux = campos[0].trim();
                int Cod = Integer.parseInt(Aux);
                String Nombre = campos[1].trim();
                int CantPersona = Integer.parseInt(campos[2].trim());
                
                Asignatura a = new Asignatura(Cod,Nombre,CantPersona);

                listaAsignaturas.add(a);     
                
            }
            
        } catch (IOException ex) {
            System.out.println("No se pudo leer el archivo");
        }
   } 
   
   
   @Override 
   public void leerPersonas() {
       
        try {
            
            FileReader f = new FileReader("creditos.txt");
            BufferedReader  br = new BufferedReader(f);
            
            String linea;
            
            while((linea=br.readLine())!=null){
                String[] campos = linea.split(";");
   
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
                    
                }
                if(Estudio.equals("profesor")){
                    
                }
                           
            }

            
        } catch (IOException ex) {
            System.out.println("No se pudo leer el archivo");
        }       
   
   }    
   
   
   // Errores de Registro 
   @Override 
   public void RF1() {
       
       // verificar tipo errores(rut) y mostrarlos
       try
        {   String filename = "ErroresDeRegistro.txt";
            PrintWriter pw = new PrintWriter(filename);
            pw.close();
            FileWriter fw = new FileWriter(filename,true); 
            fw.write("-------------------------------------------------------------------------------");
            fw.write("                             Errores de Registro                               ");
            fw.write("-------------------------------------------------------------------------------");
            fw.write("\r\n Categoria        Campo no Valido      valor campo       error encontrado");
            fw.write("\r\n -------------------------------------------------------------------------------");
            fw.write("\r\n"+"\\"+"\\");
            fw.write("\r\n"+"\\"+"\\");
            pw.close();
            fw.close();
            }
            catch(IOException ioe)
            {System.err.println("IOException: " + ioe.getMessage());
            }
   }  
   
   // Despliegue datos de una asignatura
   @Override 
   public void RF2() {
       
       StdOut.println("Ingrese código de la asignatura para mostrar sus datos: ");
       StdOut.println("---------------------");
       int cod = StdIn.readInt();
       
       NodoInscripcion actual = listaInscripciones.getFirst();
       while(actual != null){ // poner algo como getnext != first
           actual.getNext();
           
           if(actual.getInscripcion().getCodigoAsig().equals(cod)){
               
               String alias = actual.getInscripcion().getAlias();
               NodoPersona personaAsig = listaPersonas.getFirst();
               
               while(personaAsig != null){
                   personaAsig.getNext();
                   
                    if(personaAsig.persona.getAlias().equals(alias)){
                      
                       StdOut.println(" Nombre: " + personaAsig.persona.getNombre());
                       StdOut.println(" Apellido: " + personaAsig.persona.getApellido());
                       StdOut.println(" Rut: " + personaAsig.persona.getRut());
                       StdOut.println(" Correo: " + personaAsig.persona.getCorreo());
                       StdOut.println(" Alias: " + personaAsig.persona.getAlias());
                       StdOut.println(" Nota: ");

                      
                       if(personaAsig.persona instanceof Alumno){
                           Alumno alum = (Alumno)personaAsig.persona;
                           
                          StdOut.println(" Cantidad Mensajes enviados: " +alum.getCantMsjEnviadosProfe());
                          StdOut.println(" Cantidad Asignaturas: " + alum.getCantAsignaturas());                   
                       }
                      
                       else{
                           Profesor profe = (Profesor)personaAsig.persona;
                          
                          StdOut.println(" Cantidad Mensajes enviados: " + profe.getCantMsjEnviados());
                          StdOut.println(" Cantidad Mensajes recibidos: " + profe.getCantMsjRecibidos()); 
                     }
                  
                    }
                    StdOut.println("----------------------");

               }
           }       
       }
   } 
   
   // Enviar un mensaje
   @Override 
   public void RF3() {
       
      StdOut.println("-----ENVIAR UN MENSAJE----");
      StdOut.println("Ingrese un alias valido: ");
      String alias = StdIn.readString();
   
      StdOut.println("Ingrese el código de la asignatura del destinatario: ");
      String CodDestino = StdIn.readString();
            
      Iterator ite = listaAsignaturas.getIterator();
      StdOut.println("Seleccione Destinatario:");
      while(ite.hasNext()){
          Asignatura a = (Asignatura) ite.next();
          
      
         //a.getListaPersonas().buscar(codigpersona);
      }
   
   }    
   
   //Registrar una nueva persona en una asignatura.
   @Override 
   public void RF4() {
       // ingresar a la lista de personas y tambien al .txt de inscripcion y persona
       StdOut.println("-----Ingresar Persona----");
       StdOut.println("----------------------------");
       StdOut.println("-----Ingresar Asignatura----");
       StdOut.println("Ingrese la asignatura : ");
       String codasig = StdIn.readString();
       if(codasig.matches("[0-9]+") && codasig.length() > 2){
       
       StdOut.println("-----Ingresar Persona----");
       
       
       StdOut.println("Ingrese un Rut con - : ");
       String rut = StdIn.readString();
       
       //validar rut
       boolean validacion = false;
       try {
        rut =  rut.toUpperCase();
        rut = rut.replace(".", "");
        rut = rut.replace("-", "");
        int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));
        char dv = rut.charAt(rut.length() - 1);
        int m = 0, s = 1;
        for (; rutAux != 0; rutAux /= 10) {
        s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
        }
        
        if (dv == (char) (s != 0 ? s + 47 : 75)) {
        validacion = true;
        }
 
        } catch (java.lang.NumberFormatException e) {
        } catch (Exception e) {
        }

       if (validacion == true){
       StdOut.println("Ingrese un Nombre valido: ");
       String nombre = StdIn.readString();
       
       //solo ingresar letras
       if(nombre.matches(".*[a-zA-Z]+.*[a-zA-Z]")){
       StdOut.println("Ingrese un Apellido valido: ");
       String apellido = StdIn.readString();
       
       //solo ingresar letras
       if(apellido.matches(".*[a-zA-Z]+.*[a-zA-Z]"))
          {
       
       StdOut.println("Ingrese un Correo valido: ");
       String correo = StdIn.readString();
       //verificador correo
       if(correo.matches("[-\\w\\.]+@\\w+\\.\\w+")){
           
       
       StdOut.println("Ingrese 1 si es profesor o 2 si es Alumno: ");
       String tipo = StdIn.readString();
       if(tipo.equals("1")){
          tipo = "profesor"; 
          int cantm = 0; int cantasig = 0;
          Alumno al = new Alumno(rut, nombre, apellido, correo, tipo, cantm, cantasig);
          String alias = correo;
          String[] parts = alias.split("@");
          alias = parts[0].trim();
          
          Inscripcion ins = new Inscripcion(alias,codasig);
          listaPersonas.ingresaAlumno(rut, nombre, apellido, correo, tipo, cantm, cantasig);
          listaInscripciones.ingresar(ins); //conseguir parte izq @
          
          try
            {   String filename = "personas.txt";
                FileWriter fw = new FileWriter(filename,true); //the true will append the new data
                fw.write("\r\n"+rut+"\\"+nombre+"\\"+
                        apellido+"\\"+correo+"\\"+tipo+"\\"+cantm+"\\"+cantasig);//appends the string to the file
                String filename2 = "inscripciones.txt";
                FileWriter fw2 = new FileWriter(filename2,true); //the true will append the new data
                fw2.write("\r\n"+alias+","+codasig);//appends the string to the file
                fw.close();
                fw2.close();
            }
            catch(IOException ioe)
            {System.err.println("IOException: " + ioe.getMessage());
            }
       
        } 
          
       if(tipo.equals("2")){
          tipo = "profesor"; 
          int cantenv = 0; int cantrec = 0;
          Profesor prof = new Profesor(rut, nombre, apellido, correo, tipo, cantrec, cantrec);
          String alias = correo;
          String[] parts = alias.split("@");
          alias = parts[0].trim();
          Inscripcion ins = new Inscripcion(alias,codasig);
          listaPersonas.ingresaProfesor(rut, nombre, apellido, correo, tipo, cantrec, cantrec);
          listaInscripciones.ingresar(ins);
       
          try
            {   String filename = "personas.txt";
                FileWriter fw = new FileWriter(filename,true); //the true will append the new data
                fw.write("\r\n"+rut+"\\"+nombre+"\\"+
                        apellido+"\\"+correo+"\\"+tipo+"\\"+cantenv+"\\"+cantrec);//appends the string to the file
                String filename2 = "inscripciones.txt";
                FileWriter fw2 = new FileWriter(filename2,true); //the true will append the new data
                fw2.write("\r\n"+alias+","+codasig);//appends the string to the file
                fw.close();
                fw2.close();
            }
            catch(IOException ioe)
            {System.err.println("IOException: " + ioe.getMessage());
            }
          
         }else{StdOut.println("tipo no valido");
        try{ String filename = "ErroresDeRegistro.txt";
              FileWriter fw = new FileWriter(filename,true);
              fw.write("\r\n"+"Ingreso Persona"+" Tipo "+
                      tipo+"  tipo no valido (1 = Alumno // 2 = Profesor)");
              fw.close();}
              catch(IOException ioe)
              {System.err.println("IOException: " + ioe.getMessage());}
          }
          
          
          }else{StdOut.println("Correo no valido");
        try{ String filename = "ErroresDeRegistro.txt";
                FileWriter fw = new FileWriter(filename,true);
                fw.write("\r\n"+"Ingreso Persona"+" Correo "+
                        correo+"  correo no valido");
                fw.close();}
                catch(IOException ioe)
                {System.err.println("IOException: " + ioe.getMessage());}}//para el correo
          }else{StdOut.println("Apellido no valido");
        try{ String filename = "ErroresDeRegistro.txt";
               FileWriter fw = new FileWriter(filename,true);
               fw.write("\r\n"+"Ingreso Persona"+" Apellido "+
                       apellido+"  Apellido no valido");
               fw.close();}
               catch(IOException ioe)
               {System.err.println("IOException: " + ioe.getMessage());}}//para el apellido
          }else{StdOut.println("Nombre no valido");
        try{ String filename = "ErroresDeRegistro.txt";
              FileWriter fw = new FileWriter(filename,true);
              fw.write("\r\n"+"Ingreso Persona"+" Nombre "+
                      nombre+"  Nombre no valido");
              fw.close();}
              catch(IOException ioe)
              {System.err.println("IOException: " + ioe.getMessage());}}//para el nombre
       }else{StdOut.println("Rut no valido");
        try{ String filename = "ErroresDeRegistro.txt";
             FileWriter fw = new FileWriter(filename,true);
             fw.write("\r\n"+"Ingreso Persona"+" Rut "+
                     rut+"  Rut no valido");
             fw.close();}
             catch(IOException ioe)
             {System.err.println("IOException: " + ioe.getMessage());}}// para el rut
   
       }else{StdOut.println("Codigo Asignatura no valida");
       try{ String filename = "ErroresDeRegistro.txt";
            FileWriter fw = new FileWriter(filename,true);
            fw.write("\r\n"+"Ingreso Persona"+" Codigo Asignatura "+
                    codasig+"  Codigo Asignatura no valido");
            fw.close();}
            catch(IOException ioe)
            {System.err.println("IOException: " + ioe.getMessage());}
          }// para la asignatura
   
   } //fin RF4
   
   // Elimina una persona de una asignatura
   @Override 
   public void RF5() {
       // pedir alias y eliminar la inscripcion
       StdOut.println("-----Eliminar Persona----");
       StdOut.println("----------------------------");
       StdOut.println("-----Ingresar Asignatura----");
       StdOut.println("Ingrese la asignatura : ");
       String codasig = StdIn.readString();       
       StdOut.println("-----Ingresar Persona----");
       StdOut.println("Ingrese el alias de la persona a eliminar: ");
       String alias = StdIn.readString();
       listaPersonas.eliminar(alias);
       
   } 
   
   // Accede al registro de mensajes de una persona
   @Override 
   public void RF6() {
       // buscar mensajes segun emisor y receptor
      // pedir alias y eliminar la inscripcion
       StdOut.println("----------------------Registro de mensajes----------------------");
       StdOut.println("----------------------------------------------------------------");
       String codasig = StdIn.readString();
       StdOut.println("Inrese un alias: ");
       String alias = StdIn.readString();
       StdOut.println("----------------------------------------------------------------");
       StdOut.println("Lista de mensajes enviados y recibidos por: "+alias);
       StdOut.println("----------------------------------------------------------------");
       StdOut.println("----------------------------------------------------------------");
       StdOut.println("----------------------------------------------------------------");
      
       
       
   }    
    
}
 
    



    
    
    


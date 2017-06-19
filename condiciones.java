		//verificador guion
       rut.contains("-");
       System.out.println( rut.contains("-"));
       //verificador digito verificador
       boolean x = true;
       for(int i = 0;i<=9;i++){
           String dig = Integer.toString(i);
           
           if(rut.endsWith(dig)==true){
               x = true;
           }
           if(rut.endsWith("k") == true ){
           x = true;}
           else{x = false;}
        }
       System.out.println( x );
       
       StdOut.println("Ingrese un Nombre valido: ");
       String nombre = StdIn.readString();
       
       //solo ingresar letras
       if(nombre.matches(".*[a-zA-Z]+.*[a-zA-Z]"))
          {StdOut.print("nombre valido");}
       else{StdOut.print("nombre no valido");}
       
       StdOut.println("Ingrese un Apellido valido: ");
       String apellido = StdIn.readString();
       //solo ingresar letras
       if(nombre.matches(".*[a-zA-Z]+.*[a-zA-Z]"))
          {StdOut.print("apellido valido");}
       else{StdOut.print("apellidoe no valido");}
       
       StdOut.println("Ingrese un Correo valido: ");
       String correo = StdIn.readString();
       //verificador correo
       if(correo.matches("[-\\w\\.]+@\\w+\\.\\w+")){
           StdOut.print("email valido");}
       else{StdOut.print("email no valido");}


//alumno
public double CalcularNota(int CantAsignaturas, int CantMsjEnviadosProfe){
        int X = (CantMsjEnviadosProfe)/(CantAsignaturas);
        double nota = 0.0;
        if(X<5){
        return 2.5;}
        if(X>10){
        return 7.0;}
        else{return 5.0;}
    }

//profesor
public double CalcularNota(int CantMsjRecibidos, int CantMsjEnviados){
        float X = ((CantMsjEnviados)/(CantMsjRecibidos))*100;
        if(X<33){
        return 2.5;}
        if(X>66){
        return 7.0;}
        else{return 5.0;}  
    }

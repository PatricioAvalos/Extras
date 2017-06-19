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
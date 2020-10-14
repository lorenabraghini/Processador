abstract class Registrador {

    //superclasse de todos os registradores. possui o getter e o setter do valor que está armazenado nele
    
    protected String value;
    public int entrada = 0;
    public int saida = 0;

    public void setValue(String value){
        
        this.value = value;
    }
    
    public String getValue(){
        
        return value;
    } 
}

class PC extends Registrador {
    
    public PC() {

        setValue("0000000000000001");
    }
}
    
class MAR extends Registrador {
      
    public MAR() {
        
        setValue("0000000000000000");
    }
}
  
class MBR extends Registrador {
    
    
    public MBR(){
        
        setValue("0000000000000000");     
    } 
}

class IR extends Registrador {
      
    public IR(){
        
        setValue("0000000000000000");
    }
}
  
class X extends Registrador {
      
    public X(){
        
        setValue("0000000000000000");
    }
}
  
class ULA extends Registrador {
      
    public ULA(){
        
        setValue("0000000000000000");
    }

    //ao colocar um valor na ula, ele é automaticamente enviado para o ac
    @Override
    public void setValue(String value) {
    
        super.setValue(value);
        Processador.ac.setValue(value);
    }

    //métodos das operações realizadas pela ula
    
    public void add(String a, String b) {

        int c = Integer.parseInt(a ,2);
        int d = Integer.parseInt(b ,2);

        setValue(Integer.toBinaryString(c+d));
    }
 
    public void sub(String a, String b) {

        int c = Integer.parseInt(a ,2);
        int d = Integer.parseInt(b ,2);

        setValue(Integer.toBinaryString(c-d));
    }

    public void equals(String a, String b) {

        if(a.equals(b)) setValue("1");
        else setValue("0");
    }

    public void slt(String a, String b) {

        int c = Integer.parseInt(a ,2);
        int d = Integer.parseInt(b ,2);

        if(c < d) setValue("1");
        else setValue("0");
    }
}
  
class AC extends Registrador {
      
    public AC(){

        setValue("0000000000000000");
    }
}

class S1 extends Registrador {
    
    private final int id = 001;

    public S1(){
        
        setValue("0000000000000000");
        this.entrada = 7;
        this.saida = 6;
    }

    public int getId() {

        return id;
    }
}  

class S2 extends Registrador {
      
    private final int id = 010;

    public S2(){
        
        setValue("0000000000000000");
        this.entrada = 9;
        this.saida = 8;
    }

    public int getId() {

        return id;
    }
}
  
class S3 extends Registrador {

    private final int id = 011;
      
    public S3(){
        
        setValue("0000000000000000");
        this.entrada = 11;
        this.saida = 10;
    }

    public int getId() {

        return id;
    }
}
  
class S4 extends Registrador {

    private final int id = 100;
      
    public S4(){
        
        setValue("0000000000000000");
        this.entrada = 13;
        this.saida = 12;
    }

    public int getId() {

        return id;
    }
}
  


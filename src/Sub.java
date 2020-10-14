public class Sub implements OpCode{
    
    public void microoperacao(int tempo){

        String code = Memoria.linhas.get(Integer.parseInt(Processador.ir.getValue(), 2)).getCode(0);
        String chaveDestino = Memoria.linhas.get(Integer.parseInt(Processador.ir.getValue(), 2)).getCode(1);
        String chave = Memoria.linhas.get(Integer.parseInt(Processador.ir.getValue(), 2)).getCode(2);
        
        Registrador destino = Processador.esses.get(chaveDestino);
        Registrador registrador = Processador.esses.get(chave);

        if(code.equals("00111")){ //sub com constante

            if(tempo == 1) {
                
                //t1: mar <- ir

                Processador.openDoor(3);
                Processador.openDoor(15);

                Processador.mar.setValue(Processador.ir.getValue()); 

                Processador.tempoExec = 2;                
            }

            else if(tempo == 2) {
                
                //t2: memória <- mar

                Processador.openDoor(19);
                Processador.openDoor(22);

                Processador.memoria.setValue(Processador.mar.getValue());

                Processador.tempoExec = 3;

            }
            else if(tempo == 3) {  
                
                //t2: mbr <- memória

                Processador.openDoor(21);
                Processador.openDoor(23);
                
                Processador.mbr.setValue(Memoria.linhas.get(Integer.parseInt(Processador.memoria.getValue(), 2)).getCode(3));

                Processador.tempoExec = 4;
            }

            else if(tempo == 4) {  
                
                //t3: ula <- mbr

                Processador.openDoor(4);
                Processador.openDoor(17);

                Processador.ula.setValue(Processador.mbr.getValue());

                Processador.tempoExec = 5;
            }

            else if(tempo == 5) {  
                
                //t4: ula <- ula + s1~4

                Processador.openDoor(17);
                Processador.openDoor(registrador.saida);

                Processador.ula.sub(Processador.ula.getValue(), registrador.getValue());
                
                Processador.tempoExec = 6;
            }

            else if(tempo == 6) { 
                
                //t5: s1~4 <- ac

                Processador.openDoor(18);
                Processador.openDoor(destino.entrada);

                destino.setValue(Processador.ac.getValue());

                Processador.tempoBusca = 1;
                Processador.tempoExec = null;
            }

        } else { //sub com dois registradores
           
            String chave2 = Memoria.linhas.get(Integer.parseInt(Processador.ir.getValue(), 2)).getCode(3);
            Registrador registrador2 = Processador.esses.get(chave2);

            if(tempo == 1) {
                
                //t1: x <- s1~4

                Processador.openDoor(16);
                Processador.openDoor(registrador.saida);

                Processador.x.setValue(registrador.getValue());

                Processador.tempoExec = 2;
            }

            else if(tempo == 2){
               
                //t2: ula <- x
                //ula <- ula - s1~4

                Processador.openDoor(17);
                Processador.openDoor(registrador2.saida);
                
                Processador.ula.setValue(Processador.x.getValue());
                Processador.ula.sub(Processador.ula.getValue(), registrador2.getValue());
                
                Processador.tempoExec = 3;                
            }

            else if(tempo == 3){   
                
                //t3: s1~4 <- ac

                Processador.openDoor(18);
                Processador.openDoor(destino.entrada);
                
                destino.setValue(Processador.ac.getValue());

                Processador.tempoBusca = 1;
                Processador.tempoExec = null;
            }
        }
    }
}
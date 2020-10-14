public class Lw implements OpCode {

    @Override
    public void microoperacao(int tempo) {
        
        if(tempo == 1) {

            //t1: mbr <- memoria

            Processador.openDoor(21);
            Processador.openDoor(23);

            Processador.mbr.setValue(Processador.memoria.getValue()); 

            Processador.tempoExec = 2;
        }

        else if(tempo == 2) {

            //t2: s1~4 <- mbr

            Processador.openDoor(4);            

            String chave = Memoria.linhas.get(Integer.parseInt(Processador.ir.getValue(), 2)).getCode(1);
            Registrador registrador = Processador.esses.get(chave);
            
            Processador.openDoor(registrador.entrada);
            registrador.setValue(Processador.mbr.getValue());

            Processador.tempoBusca = 1;
            Processador.tempoExec = null;
        }
    }
}
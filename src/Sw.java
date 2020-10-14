public class Sw implements OpCode {

    @Override
    public void microoperacao(int tempo) {
        
        if(tempo == 1) {

            //t1: mbr <- s1~4

            Processador.openDoor(5);
            Processador.openDoor(6);

            String chave = Memoria.linhas.get(Integer.parseInt(Processador.ir.getValue(), 2)).getCode(1);
            Registrador registrador = Processador.esses.get(chave);
            
            Processador.openDoor(registrador.entrada);
            Processador.mbr.setValue(registrador.getValue());

            Processador.tempoExec = 2;
        }
        
        else if(tempo == 2) {

            //t2: memoria <- mbr

            Processador.openDoor(20);
            Processador.openDoor(22);

            Processador.memoria.setValue(Processador.mbr.getValue());

            Processador.tempoBusca = 1;
            Processador.tempoExec = null;
        }
    }
}
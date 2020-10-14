public class Data implements OpCode {

    @Override
    public void microoperacao(int tempo) {
        
        if(tempo == 1) {

            //t1: memoria <- mar

            Processador.openDoor(19);
            Processador.openDoor(22);

            Processador.memoria.setValue(Processador.mar.getValue());

            Processador.tempoExec = 2;
        }  
        
        else if(tempo == 2) {

            //t2: mbr <- memoria

            Processador.openDoor(23);
            Processador.openDoor(21);
   
            Processador.mbr.setValue(Processador.memoria.getValue()); 

            Processador.tempoBusca = 1;
            Processador.tempoExec = null;     
        }
    }
}

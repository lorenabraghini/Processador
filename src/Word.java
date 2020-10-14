public class Word implements OpCode {

    @Override
    public void microoperacao(int tempo) {
        
        if(tempo == 1){

            Processador.openDoor(5);
            Processador.openDoor(15);

            Processador.mbr.setValue(Processador.ir.getValue());

            Processador.tempoExec = 2;
        }

        else if(tempo == 2) {

            Processador.openDoor(20);
            Processador.openDoor(22);

            Processador.memoria.setValue(Processador.mbr.getValue());

            Processador.tempoBusca = 1;
            Processador.tempoExec = null;
        }
    }
}

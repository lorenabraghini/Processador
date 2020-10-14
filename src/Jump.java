public class Jump implements OpCode {

    @Override
    public void microoperacao(int tempo) {
        
        if(tempo == 1) {

            //t1: mar <- linha que é pra pular

            Processador.openDoor(15);
            Processador.openDoor(3);

            Processador.mar.setValue(Memoria.linhas.get(Integer.parseInt(Processador.ir.getValue(), 2)).getCode(1));

            Processador.tempoExec = 2;
        }

        else if(tempo == 2) {

            //t2: memoria <- mar

            Processador.openDoor(19);
            Processador.openDoor(22);

            Processador.memoria.setValue(Processador.mar.getValue());

            Processador.tempoExec = 3; 
        }

        else if(tempo == 3) {

            //t3: mbr <- memoria

            Processador.openDoor(23);
            Processador.openDoor(21);

            Processador.mbr.setValue(Processador.memoria.getValue());

            Processador.tempoExec = 4;
        }

        else if(tempo == 4) {

            //t4: pc <- mbr

            Processador.openDoor(4);
            Processador.openDoor(2);

            Processador.pc.setValue(Processador.mbr.getValue());

            Processador.tempoBusca = 1;
            Processador.tempoExec = null;
        }
    }
}
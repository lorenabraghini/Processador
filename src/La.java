public class La implements OpCode {

    @Override
    public void microoperacao(int tempo) {
        
        //define registrador a ser usado
        String chave = Memoria.linhas.get(Integer.parseInt(Processador.ir.getValue(), 2)).getCode(1);
        Registrador registrador = Processador.esses.get(chave);

        if(tempo == 1) {

            //t1: mar <- endereço a ser carregado

            Processador.openDoor(15);
            Processador.openDoor(3);

            Processador.mar.setValue(Memoria.linhas.get(Integer.parseInt(Processador.ir.getValue(), 2)).getCode(2));

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

            //t4: s1~4 <- mbr

            Processador.openDoor(4);
            Processador.openDoor(registrador.entrada);

            registrador.setValue(Processador.mbr.getValue());

            Processador.tempoBusca = 1;
            Processador.tempoExec = null;
        }
    }
}
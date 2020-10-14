public class Slt implements OpCode {

    @Override
    public void microoperacao(int tempo) {
        
        //define registradores a serem utilizados
        String chaveDestino = Memoria.linhas.get(Integer.parseInt(Processador.ir.getValue(), 2)).getCode(1);
        String chave = Memoria.linhas.get(Integer.parseInt(Processador.ir.getValue(), 2)).getCode(2);
        String chave2 = Memoria.linhas.get(Integer.parseInt(Processador.ir.getValue(), 2)).getCode(3);

        Registrador registrador = Processador.esses.get(chave);
        Registrador registrador2 = Processador.esses.get(chave2);
        Registrador destino = Processador.esses.get(chaveDestino);
        
        if(tempo == 1) {

            //t1: x <- s1~4

            Processador.openDoor(registrador.saida);
            Processador.openDoor(16);

            Processador.x.setValue(registrador.getValue());

            Processador.tempoExec = 2;
        }

        else if(tempo == 2) {

            //t2: ula <- s1~4 < s1~4

            Processador.ula.setValue(Processador.x.getValue());

            Processador.openDoor(17);
            Processador.openDoor(registrador2.saida);

            Processador.ula.slt(registrador.getValue(), registrador2.getValue());

            Processador.tempoExec = 3; 
        }

        else if(tempo == 3) {

            //t3: s1~4 <- ac

            Processador.openDoor(destino.entrada);
            Processador.openDoor(18);

            destino.setValue(Processador.ac.getValue());

            Processador.tempoBusca = 1;
            Processador.tempoExec = null;
        }
    }
}
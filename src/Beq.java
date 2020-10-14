public class Beq implements OpCode {

    @Override
    public void microoperacao(int tempo) {

        //define os registradores usados na operação
        String chave = Memoria.linhas.get(Integer.parseInt(Processador.ir.getValue(), 2)).getCode(1);
        String chave2 = Memoria.linhas.get(Integer.parseInt(Processador.ir.getValue(), 2)).getCode(2);
        Registrador registrador = Processador.esses.get(chave);
        Registrador registrador2 = Processador.esses.get(chave2);
        
        if(tempo == 1) {

            //t1: x <- s1~4

            Processador.openDoor(registrador.saida);
            Processador.openDoor(16);

            Processador.x.setValue(registrador.getValue());

            Processador.tempoExec = 2;
        }

        else if(tempo == 2) {

            //t2: ula <- x
            //ula <- s1~4 == s1~4

            Processador.ula.setValue(Processador.x.getValue());

            Processador.openDoor(17);
            Processador.openDoor(registrador2.saida);

            Processador.ula.equals(registrador.getValue(), registrador2.getValue());

            Processador.tempoExec = 3; 
        }

        else if(tempo == 3) {

            //t3: pc <- linha que é pra pular, somente se o resultado da expressão é verdadeiro

            Processador.openDoor(2);
            Processador.openDoor(18);

            if(Processador.ac.getValue().equals("1")) Processador.pc.setValue(Memoria.linhas.get(Integer.parseInt(Processador.ir.getValue(), 2)).getCode(3));

            Processador.tempoBusca = 1;
            Processador.tempoExec = null;
        }
    }
}
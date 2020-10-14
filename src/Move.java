public class Move implements OpCode {

    @Override
    public void microoperacao(int tempo) {
        
        if(tempo == 1) {
            
            //t1: s1~4 <- s1~4

            String chave = Memoria.linhas.get(Integer.parseInt(Processador.ir.getValue(), 2)).getCode(1);
            String chave2 = Memoria.linhas.get(Integer.parseInt(Processador.ir.getValue(), 2)).getCode(2);

            Registrador registrador = Processador.esses.get(chave);
            Registrador registrador2 = Processador.esses.get(chave2);

            registrador.setValue(registrador2.getValue());
            
            Processador.tempoBusca = 1;
            Processador.tempoExec = null;
        }
    }
}
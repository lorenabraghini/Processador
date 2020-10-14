public interface OpCode {

    //interface que representa os opcodes. possui apenas o método que executa a microoperação dado o tempo

    public void microoperacao(int tempo);
}

class Operacao {

    //classe que recebe a operação a ser executada

    private OpCode opcode = null;

    public Operacao(OpCode opcode) {

        this.opcode = opcode;
    }

    public void microoperacao(int tempo){
        
        //metodo que executa a microoperação dado o tempo
        
        opcode.microoperacao(tempo);
    }
}
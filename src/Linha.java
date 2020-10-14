import java.util.HashMap;
import java.util.LinkedList;

public class Linha {

    /*Essa classe representa cada linha do programa. Quando é instanciada, já é feita
    também a conversão pra linguagem de maquina que é armazenada em "codigos". 
    Ele fica armazenado em uma lista para ser mais facil de recuperar depois. Ex: o valor armazenado
    no índice 0 da lista é o opcode que representa a operação "add" */
    
    private static HashMap<String, OpCode> opcodes = new HashMap<>();

    private String conteudo;
    public LinkedList<String> codigos = new LinkedList<>();
    public OpCode operacao;

    public Linha(String conteudo) {

        this.conteudo = conteudo;
        setOpCodes();
        geraCodigos();
    }

    private void geraCodigos() {

        String [] separa = conteudo.split(" ");
        for(String s : separa) {
        
            //verifica se a string é uma operação. se for, adiciona o opcode correspondente na lista de codigos e setta o objeto da operação
            if(Processador.opcodes.containsKey(s)) {
                
                this.operacao = opcodes.get(s);
                codigos.add(Processador.opcodes.get(s));
            }   

            //verifica se é um registrador e adiciona o codigo correspondente na lista de codigos
            else if(Processador.registradores.containsKey(s)) codigos.add(Processador.registradores.get(s));
            
            //senão, trata-se de uma constante. faz a conversão para binário
            else {

                if(s.length() > 1){

                    if(s.charAt(s.length()-1) == ',') {

                        String p = "";
                        for(int i = 0; i < s.length()-1; i++) {
                            String q = String.valueOf(s.charAt(i));
                            p = p + q;
                        }

                        s = p;
                    }
                }

                int n = Integer.parseInt(s);
                codigos.add(Integer.toBinaryString(n));

                if(codigos.get(0).equals("01000")){

                    codigos.removeFirst();
                    codigos.addFirst("00111");
                }

                else if(codigos.get(0).equals("01010")){

                    codigos.removeFirst();
                    codigos.addFirst("01001");
                }
            }
        }
    }

    public String getCode(int i) {

        //retorna o codigo de indice i da lista

        return codigos.get(i);
    }

    private static void setOpCodes() {

        //método que carrega as relações de instruções com seu objeto correspondente
        
        opcodes.put(".data", new Data());
        opcodes.put(".word", new Word());
        opcodes.put(".text", new Text());
        opcodes.put("li", new Li()); 
        opcodes.put("sw", new Sw());
        opcodes.put("move", new Move());
        opcodes.put("add", new Add());
        opcodes.put("sub", new Sub());
        opcodes.put("beq", new Beq());
        opcodes.put("bne", new Bne());
        opcodes.put("j", new Jump());
        opcodes.put("slt", new Slt());
        opcodes.put("la", new La());
        opcodes.put("lw", new Lw());
    }
}
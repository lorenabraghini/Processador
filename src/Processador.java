import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Processador {

    //mapas usados para relacionar strings e objetos de registradores, portas, opcodes e operações
    public static HashMap<String, String> opcodes = new HashMap<>();
    public static HashMap<String, String> registradores = new HashMap<>();
    public static HashMap<Integer, Porta> portas = new HashMap<>();
    public static HashMap<String, Registrador> esses = new HashMap<>();

    public static Scanner op = new Scanner(System.in);

    public static PC pc = new PC();
    public static MAR mar = new MAR();
    public static MBR mbr = new MBR();
    public static IR ir = new IR();
    public static X x = new X();
    public static AC ac = new AC();
    public static ULA ula = new ULA();
    public static S1 s1 = new S1();
    public static S2 s2 = new S2();
    public static S3 s3 = new S3();
    public static S4 s4 = new S4();
    public static Memoria memoria = new Memoria();

    public static Integer tempoBusca = 1;
    public static Integer tempoExec = null;

    public static void main(String[] args) {
        
        carregaCodigos();

        System.out.println("Insira o nome do arquivo do programa (Ex: programa.txt)");
        String arquivo = op.nextLine();

        File programa = new File(arquivo);
        leArquivo(programa); 
       
        imprimeLinhas();

        System.out.println("Os valores iniciais nos registradores sao: \n");
        imprimePortas();

        System.out.println("\nAperte enter para executar uma micro-operacao");

        System.out.println("---------------------------------------------------");

        String pr = op.nextLine();

        Operacao busca = new Operacao(new CicloBusca());
        Operacao execucao = null;
    
        //while que executa cada microoperação conforme o usuario aperta enter
        while(pr.equals("")){

            //verifica se é pra realizar ciclo de busca
            if(tempoBusca != null){

                if(tempoBusca == 1) System.out.println("Iniciando Ciclo de Busca \n");

                System.out.println("t" + tempoBusca + "\n");
                busca.microoperacao(tempoBusca);
            } 

            //verifica se é pra realizar ciclo de execução
            else if(tempoExec == 1) {

                //verifica a linha do programa a ser executada. se for uma linha que não existe, a execução é encerrada
                int indice = Integer.parseInt(Processador.ir.getValue(), 2);

                if(indice >= Memoria.linhas.size()) {

                    System.out.println("Fim da Execucao");
                    System.exit(0);
                }

                //se a linha existir, define a operação a ser realizada com base no opcode
                OpCode operacao = Memoria.linhas.get(indice).operacao;
                execucao = new Operacao(operacao);

                System.out.println("Iniciando Ciclo de " + operacao.getClass().getName() + "\n");

                System.out.println("t" + tempoExec + "\n");
                execucao.microoperacao(tempoExec);
            }

            else {

                //se não for o tempo 1 do ciclo de execução, basta executar o proximo tempo
                System.out.println("t" + tempoExec + "\n");
                execucao.microoperacao(tempoExec);
            } 

            imprimePortas();
            System.out.println("---------------------------------------------------");

            closeDoors();

            pr = op.nextLine();
        }

        op.close();
    }

    public static void imprimePortas() {

        //método que imprime o estado de todos os registradores e portas

        System.out.printf("PC: %016d", Integer.parseInt(pc.getValue()));
        System.out.printf("     MAR: %016d", Integer.parseInt(mar.getValue()));
        System.out.printf("\nMBR: %016d", Integer.parseInt(mbr.getValue()));
        System.out.printf("    IR: %016d", Integer.parseInt(ir.getValue()));
        System.out.printf("\nX: %016d", Integer.parseInt(x.getValue()));
        System.out.printf("      ULA: %016d", Integer.parseInt(ula.getValue()));
        System.out.printf("\nAC: %016d", Integer.parseInt(ac.getValue()));
        System.out.printf("     S1: %016d", Integer.parseInt(s1.getValue()));
        System.out.printf("\nS2: %016d", Integer.parseInt(s2.getValue()));
        System.out.printf("     S3: %016d", Integer.parseInt(s3.getValue()));
        System.out.printf("     S4: %016d", Integer.parseInt(s4.getValue()));

        System.out.println("\n\nEstado das portas: \n");

        for(int i = 1; i < 24; i++) {

            if(i%4 == 0) System.out.print("P" + portas.get(i).getId() + ": " + portas.get(i).getState() + "\n");
            else System.out.print("P" + portas.get(i).getId() + ": " + portas.get(i).getState() + "  ");
        } 

        System.out.print("\n");
    }

    public static void carregaCodigos() {

        //método que carrega todas relações de códigos nos mapas da classe

        opcodes.put(".data", "00001");
        opcodes.put(".word", "00010");
        opcodes.put(".text", "00011");
        opcodes.put("li", "00100"); 
        opcodes.put("sw", "00101");
        opcodes.put("move", "00110");
        opcodes.put("addC", "00111");
        opcodes.put("add", "01000");
        opcodes.put("subC", "01001");
        opcodes.put("sub", "01010");
        opcodes.put("beq", "01011");
        opcodes.put("bne", "01100");
        opcodes.put("j", "01101");
        opcodes.put("slt", "01110");
        opcodes.put("la", "01111");
        opcodes.put("lw", "10000");

        registradores.put("$s1", "001");
        registradores.put("$s2", "010");
        registradores.put("$s3", "011");
        registradores.put("$s4", "100");
        registradores.put("$s1,", "001");
        registradores.put("$s2,", "010");
        registradores.put("$s3,", "011");
        registradores.put("$s4,", "100");
        registradores.put("($s1)", "001");
        registradores.put("($s2)", "010");
        registradores.put("($s3)", "011");
        registradores.put("($s4)", "100");  
        registradores.put("($s1),", "001");
        registradores.put("($s2),", "010");
        registradores.put("($s3),", "011");
        registradores.put("($s4),", "100");
        
        portas.put(1, new Porta(1));
        portas.put(2, new Porta(2));
        portas.put(3, new Porta(3));
        portas.put(4, new Porta(4));
        portas.put(5, new Porta(5));
        portas.put(6, new Porta(6));
        portas.put(7, new Porta(7));
        portas.put(8, new Porta(8));
        portas.put(9, new Porta(9));
        portas.put(10, new Porta(10));
        portas.put(11, new Porta(11));
        portas.put(12, new Porta(12));
        portas.put(13, new Porta(13));
        portas.put(14, new Porta(14));
        portas.put(15, new Porta(15));
        portas.put(16, new Porta(16));
        portas.put(17, new Porta(17));
        portas.put(18, new Porta(18));
        portas.put(19, new Porta(19));
        portas.put(20, new Porta(20));
        portas.put(21, new Porta(21));
        portas.put(22, new Porta(22));
        portas.put(23, new Porta(23));

        esses.put("001", s1);
        esses.put("010", s2);
        esses.put("011", s3);
        esses.put("100", s4);
    }

    public static void leArquivo(File programa) {

        //método que lê as linhas do programa, as converte para linguagem de máquina e salva na memória

        Scanner sc = null;        
        
        try {

            sc = new Scanner(programa);

        } catch (FileNotFoundException e) {

            System.out.println("Arquivo não encontrado.");
            System.exit(1);
        }     
        
        //essa linha será o indice 0 da lista, não será usada para nada. Serve apenas para realizar a execução na ordem correta.
        Memoria.linhas.add(new Linha(".data"));

        while(sc.hasNextLine()) {

            Memoria.linhas.add(new Linha(sc.nextLine()));
        }
    }

    public static void openDoor(int i) {

        //método que abre uma porta i

        portas.get(i).open();
    }

    public static void closeDoors() {

        //método que fecha todas as portas

        for(Porta p : portas.values()) p.close();
    }

    public static void imprimeLinhas() {

        //método que imprime as linhas do programa em formato de linguagem de máquina

        System.out.println("Sera executado o seguinte codigo: \n");

        for(Linha l : Memoria.linhas) {

            for(int i = 0; i < l.codigos.size(); i++)
                System.out.print(l.getCode(i) + " ");

            System.out.println();
        }

        System.out.println();
    }
}
public class CicloBusca implements OpCode {

    @Override
    public void microoperacao(int tempo) {
    
        if(tempo == 1) {

            //t1: mar <- pc
            //ula <- pc

            Processador.openDoor(2);
            Processador.openDoor(3);
            Processador.openDoor(17);        

            Processador.mar.setValue(Processador.pc.getValue());
            Processador.ula.setValue(Processador.pc.getValue());

            Processador.tempoBusca = 2;
        }

        else if(tempo == 2) {

            //t2: memoria <- mar

            Processador.openDoor(18);
            Processador.openDoor(22);

            Processador.memoria.setValue(Processador.mar.getValue()); 
            
            Processador.tempoBusca = 3;
        }

        else if(tempo == 3) {

            //t3: mbr <- memoria
            //ula <- ula + 1
            //pc <- ac

            Processador.openDoor(18);
            Processador.openDoor(2);
            Processador.openDoor(21);
            Processador.openDoor(23);
                        
            Processador.mbr.setValue(Processador.memoria.getValue());
            Processador.ula.add(Processador.ula.getValue(), "1");
            Processador.pc.setValue(Processador.ac.getValue());

            Processador.tempoBusca = 4;
        }

        else if(tempo == 4) {

            //t4: ir <- mbr

            Processador.openDoor(4);
            Processador.openDoor(14);

            Processador.ir.setValue(Processador.mbr.getValue());
            
            Processador.tempoBusca = null;
            Processador.tempoExec = 1;
        }
    }

}
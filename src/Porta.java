public class Porta {

    //classe que representa uma porta

    private int id;
    private int state;


    public Porta(int id) {

        setID(id);
        close();
    }

    private void setID(int id) {
        
        this.id = id;
    }

    public int getId() {
        
        return id;
    }

    public int getState() {
        
        return state;
    }

    public void open() {
        
        this.state = 1;
    }

    public void close() {
        
        this.state = 0;
    }
}
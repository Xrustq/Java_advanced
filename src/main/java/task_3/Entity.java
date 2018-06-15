package task_3;

public class Entity {

    public void add(){
    }

    @Secured(count = 3, type = "any")
    public void set(){}

    @Secured(count = 1)
    private void get(){
    }
}

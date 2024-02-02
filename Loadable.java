import java.util.Deque;

public interface Loadable<T extends ObjectWithPosition>{
    public void load(T vehicle); 
    public T unload();
    double[] getCurrentPos();
    int getMaxLoad();
    Deque<T> getCurrentLoad();
}

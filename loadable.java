import java.util.Deque;

interface Loadable<T extends ObjectWithPosition>{
    public void load(T vehicle); 
    public T unload();
    double[] getCurrentPos();
    int getMaxLoad();
    Deque<T> getCurrentLoad();
}

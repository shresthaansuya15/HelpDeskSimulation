public interface QueueInterface<T> 
{
    public void enqueue(T element);
    public T dequeue();
    public boolean isFull();
    public boolean isEmpty();
    public Integer length();    
}

public class ArrayBoundedQueue<T> implements QueueInterface<T>
{
    private T[] elements;
    private int numElements;
    private int front;
    private int rear;
    private int max_capacity;

    public ArrayBoundedQueue(int capacity)
    {
        max_capacity = capacity;
        elements = (T[]) new Object[max_capacity];
        front = 0;
        rear = 0;
        numElements = 0;
    }

    public void enqueue(T item)
    {
        if (isFull())
        {
            System.out.println("Sorry, the queue is full.");
            return;
        }

        elements[rear] = item;
        rear = (rear + 1) % max_capacity;
        numElements++;
    }

    public T dequeue()
    {
        if (isEmpty())
        {
            System.out.println("Sorry, the queue is empty.");
            return null;
        }

        T temp = elements[front];
        front = (front + 1) % max_capacity;
        numElements--;
        return temp;
    }

    public boolean isFull()
    {
        return numElements == max_capacity;
    }

    public boolean isEmpty()
    {
        return numElements == 0;
    }

    public Integer length()
    {
        return numElements;
    }    
}
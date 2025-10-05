public class ArrayBoundedQueue<T> implements QueueInterface<T>
{
    private T[] elements;
    private int numElements = 0;
    private int front = 0;
    private int rear = 0;
    private int max_capacity;

    public ArrayBoundedQueue(int max_capacity)
    {
        this.max_capacity = max_capacity;
        elements = (T[]) new Object[max_capacity];
    }

    public void enqueue(T item)
    {
        if (isFull())
        {
            System.out.println("Sorry, the queue is full.");
            return;
        }

        if (rear == max_capacity - 1)
        {
            rear = 0;
            elements[rear] = item;
            numElements++;
            return;
        }

        rear++;
        elements[rear] = item;
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
        numElements--;

        if (front == max_capacity - 1)
        {
            front = 0;
            return temp;
        }

        front++;
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
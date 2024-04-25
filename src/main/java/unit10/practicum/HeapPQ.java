package unit10.practicum;

public class HeapPQ<E> implements Queue<E> {
    private int size;
    private ArrayHeap arrayHeap;

    public HeapPQ() {
        this.arrayHeap = new ArrayHeap();
        this.size = 0;
    }

    @Override
    public void enqueue(E value) {
        arrayHeap.add((Integer)value);
        size++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E dequeue() {
        Integer minValue = arrayHeap.remove();
        size--;
        return (E)minValue;
    }

    @Override
    public int size() {
        return this.size;
    }
    
}

package unit10.practicum;

import java.util.LinkedList;

public class ListPQ<E extends Comparable<E>> implements Queue<E> {
    private LinkedList<E> priorityQueue;

    public ListPQ() {
        this.priorityQueue = new LinkedList<>();
    }

    @Override
    public void enqueue(E value) {
        int index = 0;
        while(index < priorityQueue.size() && value.compareTo(priorityQueue.get(index)) > 0) {
            index++;
        }
        priorityQueue.add(index, value);
    }

    @Override
    public E dequeue() {
        return priorityQueue.remove();
    }

    @Override
    public int size() {
        return priorityQueue.size();
    }
    
}

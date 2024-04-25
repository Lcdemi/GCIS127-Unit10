package unit10;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unit10.practicum.ListPQ;

public class ListPQTest<E> {
    @Test
    public void constructor() {
        // setup
        ListPQ<Integer> priorityQueue = new ListPQ<>();
        
        // invoke

        // analyze
        assertEquals(0, priorityQueue.size());
    }

    @Test
    public void enqueue() {
        // setup
        ListPQ<Integer> priorityQueue = new ListPQ<>();
        
        // invoke
        priorityQueue.enqueue(1);
        priorityQueue.enqueue(5);
        priorityQueue.enqueue(9);
        priorityQueue.enqueue(3);

        // analyze
        assertEquals(4, priorityQueue.size());
    }

    @Test
    public void testEnqueueDequeue() {
        // setup
        ListPQ<Integer> priorityQueue = new ListPQ<>();

        // invoke
        priorityQueue.enqueue(3);
        priorityQueue.enqueue(1);
        priorityQueue.dequeue();
        priorityQueue.enqueue(2);
        priorityQueue.enqueue(4);
        priorityQueue.dequeue();

        assertEquals(3, (int) priorityQueue.dequeue());
        assertEquals(4, (int) priorityQueue.dequeue());
    }
}


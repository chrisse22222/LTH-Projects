package testqueue;

import org.junit.Assert;
import org.junit.Test;
import queue_singlelinkedlist.FifoQueue;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class TestAppendFifoQueue {

    @Test
    public void emptyQueues(){
        FifoQueue<Integer> q1 = new FifoQueue<>();
        FifoQueue<Integer> q2 = new FifoQueue<>();

        // fattar inte vad () -> q1.append(q2) gör (lambda uttrycket)
        assertThrows(NullPointerException.class, () ->q1.append(q2));
        assertEquals(0, q1.size());
    }

    @Test
    public void q1Empty(){
        FifoQueue<Integer> q1 = new FifoQueue<>();
        FifoQueue<Integer> q2 = new FifoQueue<>();
        for (int i = 1; i <= 5; i++) {
            q2.offer(i);
        }

        q1.append(q2);
        assertEquals(5, q1.size());
        for (int i = 1; i <= 5; i++) {
            assertEquals(i, q1.poll());
        }

        assertEquals(0, q2.size());
    }

    @Test
    public void q1NotEmpty(){
        FifoQueue<Integer> q1 = new FifoQueue<>();
        FifoQueue<Integer> q2 = new FifoQueue<>();
        for (int i = 1; i <= 5; i++) {
            q1.offer(i);
        }

        assertThrows(NullPointerException.class, () ->q1.append(q2));
        assertEquals(5, q1.size());
        for (int i = 1; i <= 5; i++) {
            assertEquals(i, q1.poll());
        }
        assertEquals(0, q2.size());
    }

    @Test
    public void noneQueueEmpty(){
        FifoQueue<Integer> q1 = new FifoQueue<>();
        FifoQueue<Integer> q2 = new FifoQueue<>();

        for (int i = 1; i <= 5 ; i++) {
            q1.offer(i);
            q2.offer(i);
        }

        q1.append(q2);
        assertEquals(10, q1.size());

        for (int i = 0; i < 2; i++) {
            for (int j = 1; j <= 5; j++) {
                assertEquals(j, q1.poll());
            }
        }

        assertEquals(0, q2.size());
    }

    @Test
    public void appendsSelf(){
        FifoQueue<Integer> q1 = new FifoQueue<>();
        q1.add(1);
        q1.add(2);

        assertThrows(IllegalArgumentException.class, () ->q1.append(q1));
        assertEquals(2, q1.size());
        for (int i = 1; i <= 2 ; i++) {
            assertEquals(i, q1.poll());
        }

        assertEquals(0, q1.size());
    }
}

package queue_singlelinkedlist;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	e the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E e) {
		QueueNode<E> node = new QueueNode<>(e);

		if (last == null){
			last = node;
			last.next = last;
		} else {
			node.next = last.next;
			last.next = node;
			last = node;
		}

		size++;
		return true;
	}
	
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return size;
	}
	
	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		if (last == null){
			return null;
		}

		return last.next.element;
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		if (last == null){
			return null;
		}

		QueueNode<E> node = last.next;
		size--;
		
		if (last.next == node.next){
			last = null;
			return node.element;
		}

		last.next = node.next;
		node.next = null;
		return node.element;
	}
	
	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}

	/**
	 * Appends the specified queue to this queue
	 * post: all elements from the specified queue are appended
	 * to this queue. The specified queue (q) is empty after the call.
	 * @param q the queue to append
	 * @throws IllegalArgumentException if this queue and q are identical
	 */
	public void append(FifoQueue<E> q){
		if (this.equals(q)){
			throw new IllegalArgumentException();
		}

		if (q.last == null){
			throw new NullPointerException();
		}

		size+= q.size();
		if (last != null) {
			QueueNode<E> head = last.next; // sparar f??rsta elementet i denna listan
			last.next = q.last.next; // L??nkar sista elementet i denna listan med Q:s f??rsta element
			q.last.next = head; // s??tter att Q:s sista element pekar tillbaka p?? f??rsta elementet i denna listan
		}

		last = q.last; // S??tter att dennas listas sista element ??r Q:s listas sista
		q.last = null;
		q.size = 0;
	}

	private class QueueIterator implements Iterator<E>{
		private QueueNode<E> pos;
		private QueueIterator(){
			pos = last;
		}

		@Override
		public boolean hasNext(){
			return pos != null;
		}

		@Override
		public E next(){
			if (pos == null){
				throw new NoSuchElementException();
			}

			pos = pos.next; // B??rjar p?? startpos och stegrar d??refter igenom listan
			E retVal = pos.element;
			if (pos == last){
				pos = null;
			}

			return  retVal;
		}
	}
	
	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

}

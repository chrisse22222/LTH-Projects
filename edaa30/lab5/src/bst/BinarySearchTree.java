package bst;

import java.util.ArrayList;
import java.util.Comparator;


public class BinarySearchTree<E> {
	BinaryNode<E> root;  // Används också i BSTVisaulizer
  	private int size; // Används också i BSTVisaulizer
  	private Comparator<E> comp;
	/**
	 * Constructs an empty binary search tree.
	 */
	public BinarySearchTree() {
		root = null;
		size = 0;
		comp = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
	}
	
	/**
	 * Constructs an empty binary search tree, sorted according to the specified comparator.
	 */
	public BinarySearchTree(Comparator<E> comparator) {
		root = null;
		size = 0;
		comp = comparator;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		if (x == null)
			return false;

		return addElement(root, x);
	}

	private boolean addElement(BinaryNode<E> e, E x){
		if (root == null){
			root = new BinaryNode<>(x);
			size++;
			return true;
		} else{
			int val = comp.compare(x, e.element);
			if (val < 0) { // Gå i vänsta ledet
				if (e.left != null) {
					addElement(e.left, x);
				} else{
					e.left = new BinaryNode<>(x);
					size++;
					return true;
				}

			} else if (val > 0) { // gå i högra ledet
				if (e.right != null){
					addElement(e.right, x);
				} else{
					e.right = new BinaryNode<>(x);
					size++;
					return true;
				}
			}
		}

		return false; // Om det redan finns, returna false
	}

	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		return findHeight(root);
	}

	private int findHeight(BinaryNode<E> e){
		if (root == null){
			return 0;
		} else{
			if (e != null){
				int lHeight = findHeight(e.left);
				int rHeight = findHeight(e.right);

				if (lHeight > rHeight){
					return lHeight + 1;
				} else{
					return rHeight + 1;
				}
			} else{
				return 0;
			}
		}
	}

	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() {
		root = null;
		size = 0;
	}
	
	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		StringBuilder sb = new StringBuilder();
		print(root, sb);
		sb.delete(sb.length() - 2, sb.length() - 1);
		System.out.println("Binary tree: " + "\n" + sb.toString());
	}

	private void print(BinaryNode<E> e, StringBuilder sb){
		if (e != null){
			print(e.left, sb);
			sb.append("Element: " + e.element + ", ");
			print(e.right, sb);
		}
	}

	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		ArrayList<E> sorted = new ArrayList<>();
		toArray(root, sorted);

		root = buildTree(sorted, 0, sorted.size() - 1);
	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the list sorted.
	 */
	private void toArray(BinaryNode<E> n, ArrayList<E> sorted) {
		if (n != null){
			toArray(n.left, sorted);
			sorted.add(n.element);
			toArray(n.right, sorted);
		}
	}
	
	/*
	 * Builds a complete tree from the elements from position first to 
	 * last in the list sorted.
	 * Elements in the list a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) {
		// basfall
		if (first > last){
			return null;
		}

		int mid = (first + last)/2;
		BinaryNode<E> middleNode = new BinaryNode<>(sorted.get(mid));

		middleNode.left = buildTree(sorted, first, mid - 1);
		middleNode.right = buildTree(sorted, mid + 1, last);

		return  middleNode;
	}

	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}	
	}
	
}

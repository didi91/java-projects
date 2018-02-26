
public class Heap {

	private Tank[] data;
	private int size;

	/**
	 * Creates an empty list.
	 */
	public Heap() {
		data = new Tank[10000];
		size = 0;
	}

	/**
	 * Returns the size of the heap.
	 * 
	 * @return the size of the heap
	 */
	public int size() {
		return this.size;
	}

	/**
	 * Inserts a given tank into the heap. Should run in time O(log(n)).
	 * 
	 * @param t
	 *            - the tank to be inserted.
	 */
	public void insert(Tank t) {
		++size;
		data[size - 1] = t;
		maxHeap(size - 1);
	}
/**
 * private function to hapify (make a max heap)
 */
	private void maxHeap(int i) {
		Tank temp;
		Tank parent = data[(i - 1) / 2];
		Tank child = data[i];
		if (i > 0) {
			if (child.compareTo(parent) == 1) {
				temp = parent;
				data[(i - 1) / 2] = child;
				data[i] = temp;
				maxHeap((i - 1) / 2);
			}
		}

	}

	/**
	 * Returns the tank with the highest serial number in the heap. Should run
	 * in time O(1).
	 * 
	 * @return the tank with the highest serial number in the heap.
	 */
	public Tank findMax() {
		return data[0];
	}

	/**
	 * Removes the tank with the highest serial number from the heap. Should run
	 * in time O(log(n)).
	 * 
	 */
	public void extractMax() {
		data[0] = data[size - 1];
		data[size - 1] = null;
		size--;
		fixExtractMax(0);
	}
/**
 * private function to fix the heap when we remove a node from it (the max)
 * @param index 
 */
	private void fixExtractMax(int index) {
		if (size >= ((index * 2) + 3)) {
			Tank tmp;
			Tank tmp2;
			Tank parent = data[index];
			Tank child1 = data[(index * 2) + 1];
			Tank child2 = data[(index * 2) + 2];

			if ((child1.compareTo(parent) == 1) || (child2.compareTo(parent) == 1)) {
				tmp = parent;
				if (child1.compareTo(child2) == 1)
					tmp2 = child1;
				else
					tmp2 = child2;

				data[index] = tmp2;
				if (data[index].compareTo(child1) == 0) {
					data[(2 * index) + 1] = tmp;
					fixExtractMax(2 * index + 1);
				} else {
					data[index * 2 + 2] = tmp;
					fixExtractMax(index * 2 + 2);
				}
			}
		}
	}

	/**
	 * Returns the tank with the k highest serial number in the heap. Should run
	 * in time O(klog(n)).
	 * 
	 * @param k
	 * @return the tank with the k highest serial number in the heap.
	 */
	public Tank findKbiggest(int k) {
		Tank[] temp = new Tank[k];
		for (int i = 0; i < k; i++) {
			temp[i] = findMax();
			extractMax();
		}
		for (int i = 0; i < k; i++) {
			insert(temp[i]);
		}
		return temp[k - 1];
	}

	/**
	 * Removes the tank with the k highest serial number from the heap. Should
	 * run in time O(klog(n)).
	 * 
	 * @param k
	 */
	public void removeKbiggest(int k) {
		Tank[] temp = new Tank[k];
		for (int i = 0; i < k; i++) {
			temp[i] = findMax();
			extractMax();
		}
		for (int i = 0; i < k - 1; i++) {
			insert(temp[i]);
		}
	}

	/**
	 * Checks if a given tank is a part of the heap.
	 * 
	 * @param t
	 *            - the tank to be checked
	 * @return true if and only if the tank is in the heap.
	 */
	public boolean contains(Tank t) {
		for (int i = 0; i < size; i++) {
			if (data[i].compareTo(t) == 0)
				return true;
		}
		return false;
	}

}

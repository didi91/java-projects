
public class TankEstimator {

	private Heap h;

	/**
	 * Creates a new estimator with an empty heap.
	 */
	public TankEstimator() {
		h = new Heap();
	}

	/**
	 * Adds the data of a new captured tank
	 * 
	 * @param t
	 *            - the captured tank.
	 */
	public void captureTank(Tank t) {
		h.insert(t);
	}

	/**
	 * Estimates the total number of produced tanks, based on the information of
	 * captured tanks. Estimation is done according to the formula presented in
	 * the assignment's document.
	 * 
	 * @return an estimation to the total number of produced tanks
	 */
	public int estimateProduction() {
		int x = h.findMax().serialNumber();
		return x + (x / h.size()) - 1;
	}

	/**
	 * a function to check if the tank that we want to insert is already in the
	 * heap if no- we will insert it to the heap
	 * 
	 * @return true/false (if true, the tank already in the heap
	 */
	public boolean captured(Tank t) {
		if (h.contains(t))
			return true;
		captureTank(t);
		return false;
	}

}

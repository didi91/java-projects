
public class Tank implements Comparable<Tank> {

	private String serialNumber;

	/**
	 * A standard constructor for the tank class.
	 * 
	 * @param serialNumber
	 *            - the serial number of the tank
	 */
	public Tank(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * Compares this tank with another tank for order. Returns a negative
	 * integer, zero, or a positive integer as the serial number of this tank is
	 * is less than, equal to, or greater than the serial number of the other
	 * tank.
	 * 
	 * @param other
	 *            - the tank to be compared
	 * @return a negative integer, zero, or a positive integer as the serial
	 *         number of this tank is less than, equal to, or greater than the
	 *         serial number of the other tank.
	 */
	public int compareTo(Tank other) {
		if (this.serialNumber() == other.serialNumber())
			return 0;
		return (this.serialNumber() > other.serialNumber()) ? 1 : -1;
	}

	/**
	 * Convert the string serial number into a positive integer as explained in
	 * the assignment's document.
	 * 
	 * @return the integer represented by the serial number.
	 */
	public int serialNumber() {
		String x = this.serialNumber;
		int sn = 0;
		int converted;
		if (x.length() < 1)
			return -1;
		for (int i = 0; i < x.length(); i++) {
			converted = (x.charAt(i) - 97 < 0) ? x.charAt(i) - 65 : x.charAt(i) - 97;
			sn += (converted * Math.pow(26, (x.length() - 1) - i));
		}
		return sn;
	}

}

package HW5;

public class AVLTree {
	private static int i = 0; // global integer for the inorder function
	AVLNode root; // The tree root.
	int size; // The size of the tree.

	/**
	 * Construct an empty tree.
	 */
	public AVLTree() {
		root = null;
	}

	/**
	 * Insert into the tree; You may assume that every tank is unique. That is,
	 * no tank will appear twice.
	 * 
	 * @param t
	 *            the tank to insert.
	 */
	public void insert(Tank t) {
		size++; // check if its good
		if (root == null)
			root = new AVLNode(t);
		else
			insert(t, root);
	}

	private void insert(Tank t, AVLNode root) {
		if (root.t.compareTo(t) > 0) {
			if (root.left == null)
				root.left = new AVLNode(t, null, null, root);
			else
				insert(t, root.left);
		} else {
			if (root.right == null)
				root.right = new AVLNode(t, null, null, root);
			else
				insert(t, root.right);
		}
		checkAVL(root);
	}

	/**
	 * helper function to delete tank from the AVL tree
	 * 
	 * @param t
	 */
	private void delete(AVLNode node) {
		// node without childrens
		if (node.left == null && node.right == null) {
			if (node.parent == null)
				root = null;
			else {
				AVLNode parent = node.parent;
				if (parent.left == node) {
					parent.left = null;
				} else
					parent.right = null;
				checkAVL(parent);
			}
			size--;
			return;
		}
		// node with one child
		if (node.left != null) {
			AVLNode child = node.left;
			while (child.right != null)
				child = child.right;
			node.t = child.t;
			delete(child);
		} else {
			AVLNode child = node.right;
			while (child.left != null)
				child = child.left;
			node.t = child.t;
			delete(child);
		}
	}
/**
 * Recursive function to delete a node with given tank
 * @param tank
 */
	public void delete(Tank tank) {
		if (root == null)
			return;
		AVLNode node = root;
		AVLNode child = root;

		while (child != null) {
			node = child;
			child = (tank.compareTo(node.t) >= 0) ? node.right : node.left;
			if (tank == node.t) {
				delete(node);
				return;
			}
		}
	}
/**	 
 * function to check the balance in the AVLtree
 * @param root
 */
	private void checkAVL(AVLNode root) {
		int leftH, rightH;
		leftH = getHeight(root.left);
		rightH = getHeight(root.right);

		if (leftH - rightH <= 1 || rightH - leftH <= 1) // the tree is hold
			root.height = Math.max(leftH, rightH) + 1;
		else
			fixAVL(root, leftH, rightH);

	}

	private int getHeight(AVLNode node) {
		return (node == null) ? -1 : node.height;
	}
/**
 * function to fix the balance in the AVLtree
 * @param node
 * @param leftH
 * @param rightH
 */
	private void fixAVL(AVLNode node, int leftH, int rightH) {
		// the balance tips to the left
		if (leftH > rightH) {
			if ((getHeight(node.left.left) > getHeight(node.left.right)))
				// ========= case 1 =========== //
				rightRotation(node);

			else if (getHeight(node.left.left) < getHeight(node.left.right)) {
				// ========= case 2 =========== //
				leftRotation(node.left);
				rightRotation(node);
			}
		}
		if (leftH < rightH) {
			if (getHeight(node.right.right) > getHeight(node.right.left))
				// ========= case 3 =========== //
				leftRotation(node);
			else if (getHeight(node.right.right) < getHeight(node.right.left)) {
				// ========= case 4 =========== //
				rightRotation(node.right);
				leftRotation(node);
			}
		}

	}
/*
 * function to make a left rotation with a given node
 */
	private void leftRotation(AVLNode node) {
		AVLNode rep = node.right;
		if (node.parent != null)
			replaceChild(node.parent, node, rep);
		else {
			root = rep;
			rep.parent = null;
		}
		replaceChild(node, node.right, rep.left);
		rep.left = node;
		node.parent = rep;
		node.setHeight();
		rep.setHeight();
	}
	
/*
 * function to make a right rotation with a given node
*/
	private void rightRotation(AVLNode node) {
		AVLNode rep = node.left;
		if (node.parent != null)
			replaceChild(node.parent, node, rep);
		else {
			root = rep;
			rep.parent = null;
		}
		replaceChild(node, node.left, node.right);
		rep.right = node;
		node.parent = rep;
		node.setHeight();
		rep.setHeight();

	}
/**
 * function to replace child of given node with new given child
 * @param node
 * @param curChild
 * @param newChild
 */
	private void replaceChild(AVLNode node, AVLNode curChild, AVLNode newChild) {
		if (node.left == curChild)
			node.left = newChild;
		else if (node.right == curChild)
			node.right = newChild;
		if (newChild != null)
			newChild.parent = node;
	}

	/**
	 * Search for a tank in the tree.
	 * 
	 * @param t
	 *            the tank to search for.
	 * @return the matching tank or null if not found.
	 */
	public Tank search(Tank t) {
		return search(t, root);
	}

	private Tank search(Tank t, AVLNode root) {
		if (root == null)
			return null;
		if (t.compareTo(root.t) == 0)
			return root.t;

		return (t.compareTo(root.t) < 0) ? search(t, root.left) : search(t, root.right);
	}

	/**
	 * Traverse the contents of this tree in an 'inorder' manner and return and
	 * array containing the traversal of the tree.
	 * 
	 * @return a sorted array of the tree's content.
	 */
	Tank[] inorder() {
		Tank[] tank = new Tank[size];
		if (root == null)
			return null;
		else
			arrayMaker(root, tank);

		i = 0; // change i to 0 for the next call
		return tank;
	}
/**
 * recursive function to fill the array 
 * @param node
 * @param tank
 */
	private void arrayMaker(AVLNode node, Tank[] tank) {
		if (node == null)
			return;
		if (node.left != null)
			arrayMaker(node.left, tank);
		tank[i++] = node.t;
		if (node.right != null)
			arrayMaker(node.right, tank);
	}
}

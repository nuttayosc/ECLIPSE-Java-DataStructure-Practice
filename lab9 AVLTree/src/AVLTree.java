public class AVLTree {
	AVLNode root;
	int size;

	public AVLTree() {
		root = null;
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void makeEmpty() {
		root = null;
		size = 0;
	}

	public Iterator findMin() {
		return findMin(root);
	}

	public Iterator findMin(AVLNode n) {
		if (n == null)
			return null;
		if (n.left == null) {
			Iterator itr = new AVLTreeIterator(n);
			return itr;
		}
		return findMin(n.left);
	}

	public Iterator findMax() {
		return findMax(root);
	}

	public Iterator findMax(AVLNode n) {
		if (n == null)
			return null;
		if (n.right == null) {
			Iterator itr = new AVLTreeIterator(n);
			return itr;
		}
		return findMax(n.right);
	}

	public Iterator find(int v) {
		return find(v, root);
	}

	public Iterator find(int v, AVLNode n) {
		if (n == null)
			return null;
		if (v == n.data)
			return new AVLTreeIterator(n);
		if (v < n.data)
			return find(v, n.left);
		else
			return find(v, n.right);
	}

	public AVLNode insert(int v) {
		return insert(v, root, null);
	}

	// return the node n after v was added into the tree
	public AVLNode insert(int v, AVLNode n, AVLNode parent) {
		if (n == null) {
			n = new AVLNode(v, null, null, parent, 0);
			size++;
		} else if (v < n.data) {
			n.left = insert(v, n.left, n);
		} else if (v > n.data) {
			n.right = insert(v, n.right, n);
		}
		n = rebalance(n);
		return n;
	}

	public AVLNode insertNoBalance(int v) {
		return insertNoBalance(v, root, null);
	}

	private AVLNode insertNoBalance(int v, AVLNode n, AVLNode parent) {
		if (n == null) {
			n = new AVLNode(v, null, null, parent, 0);
			size++;
		} else if (v < n.data) {
			n.left = insertNoBalance(v, n.left, n);
		} else if (v > n.data) {
			n.right = insertNoBalance(v, n.right, n);
		}
		AVLNode.updateHeight(n);
		return n;
	}

	public AVLNode remove(int v) {
		return remove(v, root, null);
	}

	// return the node n after v was removed from the tree
	public AVLNode remove(int v, AVLNode n, AVLNode parent) {
		if (n == null)
			; // do nothing, there is nothing to be removed
		else if (v < n.data) {
			n.left = remove(v, n.left, n);
		} else if (v > n.data) {
			n.right = remove(v, n.right, n);
		} else {
			if (n.left == null && n.right == null) {
				n = null;
				size--;
			} else if (n.left != null && n.right == null) {
				n.left.parent = parent;
				n = n.left;
				size--;
			} else if (n.right != null && n.left == null) {
				n.right.parent = parent;
				n = n.right;
				size--;
			} else {
				AVLTreeIterator i = (AVLTreeIterator) findMin(n.right);
				int minInRightSubtree = i.currentNode.data;
				n.data = minInRightSubtree;
				n.right = remove(minInRightSubtree, n.right, n);
			}
		}
		n = rebalance(n);
		return n;
	}

	public AVLNode rebalance(AVLNode n) {
		if (n == null)
			return n;
		int balance = AVLNode.tiltDegree(n);
		if (balance >= 2) {
			if (AVLNode.tiltDegree(n.left) <= -1) // 3rd case
				n.left = rotateRightChild(n.left);
			n = rotateLeftChild(n); // 1st case
		} else if (balance <= -2) {
			if (AVLNode.tiltDegree(n.right) >= 1) // 4th case
				n.right = rotateLeftChild(n.right);
			n = rotateRightChild(n); // 2nd case
		}
		AVLNode.updateHeight(n);
		return n;
	}

	public AVLNode rotateLeftChild(AVLNode n) {
		AVLNode l = n.left;
		AVLNode lr = n.left.right; // can be null
		n.left = lr;
		if (lr != null) {
			lr.parent = n;
		}
		l.right = n;
		l.parent = n.parent;
		n.parent = l;

		AVLNode.updateHeight(n);
		AVLNode.updateHeight(l);
		return l;
	}

	public AVLNode rotateRightChild(AVLNode n) {
		AVLNode r = n.right;
		AVLNode rl = n.right.left; // can be null
		n.right = rl;
		if (rl != null) {
			rl.parent = n;
		}
		r.left = n;
		r.parent = n.parent;
		n.parent = r;

		AVLNode.updateHeight(n);
		AVLNode.updateHeight(r);
		return r;
	}

	public void makeAVL() throws Exception {
		//code this method
		if (this.isAVL() || this.isEmpty()) {
			return;
		}
		
		AVLTree nTree = new AVLTree();
		nTree.root = nTree.insert(root.data);
		
		AVLTreeIterator itr = (AVLTreeIterator) findMin();
		for (int i = 0; i != size; i++) {
			nTree.root = nTree.insert(itr.currentNode.data);
			if (itr.hasNext()) {
				itr.next();
			}
		}
		
		root = nTree.root;
		return;
	}

	public boolean isAVL() throws Exception {
		// code this method
		boolean ret = true;
		
		if (this.isEmpty()) {
			return true;
		}
		
		if (root != null) {
			AVLTreeIterator itr = (AVLTreeIterator) findMin();
			for (int i = 0; i != size; i++) {
				if (Math.abs(AVLNode.tiltDegree(itr.currentNode)) <= 1) {
					if (itr.hasNext()) {
						itr.next();
					}
				} else {
					ret = false;
					break;
				}
			}
		}
		
		return ret;
	}
	


	public static boolean same(AVLTree t1, AVLTree t2) {
		//code this method		
		if (t1.isEmpty() && t2.isEmpty()) {
			return true;
		}
		
		if (t1.size != t2.size) {
			return false;
		}
		
		return nodeSame(t1.root, t2.root);
	}
	
	private static boolean nodeSame(AVLNode n1, AVLNode n2) {
		if (n1 == null && n2 == null) {
			return true;
		} 
		
		if (n1.data == n2.data && nodeSame(n1.left,n2.left) && nodeSame(n1.right, n2.right)) {
			return true;
		}
		
		return false;
	}

	

	public static void main(String[] args) throws Exception {
		// example: print a tree

		AVLTree t = new AVLTree();

		t.root = t.insertNoBalance(33);
		t.root = t.insertNoBalance(4);
		t.root = t.insertNoBalance(1);
		t.root = t.insertNoBalance(66);
		t.root = t.insertNoBalance(2);
		t.root = t.insertNoBalance(6);

		BTreePrinter.printNode(t.root);

	}

}

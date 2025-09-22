package DictionaryBST;

public class DictionaryBST<K extends Comparable<K>, V> implements
Dictionary<K, V> {

	private Node root;
	private int size;

	public DictionaryBST() {
		root = null;
		size = 0;
	}

	@Override
	public V get(K key) {
		Node found = find(key);
		if (found != null) {
			return found.value;
		}
		return null;
	}

	private Node find(K key) {
		Node current = root;
		boolean found = false;
		while (!found && current != null) {
			int d = current.key.compareTo(key);
			if (d == 0) {
				found = true;
			} else if (d > 0) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		if (found) {
			return current;
		} else {
			return null;
		}

	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public V put(K key, V value) {
		if (key == null || value == null) {
			throw new IllegalArgumentException("Key and value cannot be null");
		}

		if (root == null) {
			root = new Node(key, value);
			size++;
			return null;
		}

		Node current = root;
		while (true) {
			int comparison = key.compareTo(current.key);
			if (comparison == 0) {
				V oldValue = current.value;
				current.value = value;
				return oldValue;
			} else if (comparison < 0) {
				if (current.left == null) {
					current.left = new Node(key, value);
					size++;
					return null;
				}
				current = current.left;
			} else {
				if (current.right == null) {
					current.right = new Node(key, value);
					size++;
					return null;
				}
				current = current.right;
			}
		}
	}

	@Override
	public V remove(K key) {
		if (key == null) return null;

		Node nodeToRemove = find(key);
		if (nodeToRemove == null) return null;

		V removedValue = nodeToRemove.value;
		root = removeNode(root, key);
		size--;
		return removedValue;
	}

	private Node removeNode(Node node, K key) {
		if (node == null) return null;

		int comparison = key.compareTo(node.key);
		if (comparison < 0) {
			node.left = removeNode(node.left, key);
		} else if (comparison > 0) {
			node.right = removeNode(node.right, key);
		} else {
			if (node.left == null) {
				return node.right;
			} else if (node.right == null) {
				return node.left;
			} else {
				Node successor = findMin(node.right);
				node.key = successor.key;
				node.value = successor.value;
				node.right = removeNode(node.right, successor.key);
			}
		}
		return node;
	}

	private Node findMin(Node node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}

	@Override
	public int size() {
		return size;
	}

	private class Node {
		private K key;
		private V value;
		private Node left;
		private Node right;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			this.left = null;
			this.right = null;
		}


	}

}

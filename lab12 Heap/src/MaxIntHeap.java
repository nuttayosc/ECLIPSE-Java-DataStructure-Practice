
public class MaxIntHeap extends Heap{
	public void add(Object element) {
		// Add your code here
		if (++size == mData.length) {
			Object[] newHeap = new Object[2 * mData.length];
			System.arraycopy(mData, 0, newHeap, 0, size);
			mData = newHeap;
		}
		mData[size - 1] = (int)element*-1;
		percolateUp();
	}
	
	public Object pop() throws Exception {
		// Add your code here
		if (size == 0)
			throw new Exception("Priority queue empty.");
		Object maxElem = (int)mData[0]*-1;
		mData[0] = mData[size - 1];
		size--;
		percolateDown(0);
		return maxElem;
	}
}

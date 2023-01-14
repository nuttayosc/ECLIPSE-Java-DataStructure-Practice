
public class PriorityQueue {
	MyQueue q;

	public PriorityQueue(MyQueue q) {
		super();
		this.q = q;
	}

	// implement this.
	public void push(int x) throws Exception {
		if (q.isEmpty()) {
			q.insertLast(x);
			return;
		}
		
		// queue x into q if x is smallest value
		if (q.back() >= x) {
			q.insertLast(x);
			return;
		}
		
		MyQueue temp = new QueueArray();
		
		while (!q.isEmpty()){
			// find spot to plug in x, temp.inserLast(q.removeFirst()) number bigger than x, until front is smaller than x 
			if (q.front() >= x) {
				temp.insertLast(q.removeFirst());
			} else {
				//loop temp.inserLast(q.removeFirst()) after finding position of x
				temp.insertLast(x);
				x = q.back();
			}
		}
		q=temp;
		return;
		}

	// implement this.
	public void pop() throws Exception {
		MyQueue temp = new QueueArray();
		
		System.out.print(" POP"+q.back()+" ");
		
		while(q.size() != 1) {
			temp.insertLast(q.removeFirst());
			System.out.print("\\"+temp.back());
		}
		q = temp;
		return;
	}

	// implement this
	public int top() throws Exception {
		return q.back();
	}

}

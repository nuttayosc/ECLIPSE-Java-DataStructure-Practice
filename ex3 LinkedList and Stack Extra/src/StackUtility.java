
public class StackUtility {
	public static MyStack removeRange(MyStack s, int i, int j) throws Exception {
		MyStack temp = new StackArray();
		
		for (int start = 0; start < i; start++) {
			temp.push(s.top());
			s.pop();
		}
		
		for (int end = i; end < j+1 ; end++) {
			s.pop();
		}
		
		while (!temp.isEmpty()) {
			s.push(temp.top());
			temp.pop();
		}
		
		return s;
	}
}

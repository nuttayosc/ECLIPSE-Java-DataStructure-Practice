
public class StudentList extends CDLinkedList {
    // you can write additional methods.

	// implement this method
	public void swapNode(DListIterator i1, DListIterator i2) throws Exception {
		DListIterator p1 = new DListIterator(i1.currentNode.previousNode);
		DListIterator n1 = new DListIterator(i1.currentNode.nextNode);
		
		DListIterator p2 = new DListIterator(i2.currentNode.previousNode);
		DListIterator n2 = new DListIterator(i2.currentNode.nextNode);
		
		i1.currentNode.previousNode = p2.currentNode;
		p2.currentNode.nextNode = i1.currentNode;
		
		i1.currentNode.nextNode = n2.currentNode;
		n2.currentNode.previousNode = i1.currentNode;
		
		i2.currentNode.previousNode = p1.currentNode;
		p1.currentNode.nextNode = i2.currentNode;
		
		i2.currentNode.nextNode = n1.currentNode;
		n1.currentNode.previousNode = i2.currentNode;
		
//		 DListNode t1 = i1.currentNode;
//	     DListNode p1 = t1.previousNode;
//	     DListNode n1 = t1.nextNode;
//	     
//	     DListNode t2 = i2.currentNode;
//	     DListNode p2 = t2.previousNode;
//	     DListNode n2 = t2.nextNode;
//	     
//	     // [p1->i2] [i2<-n1]
//	     p1.nextNode = i2.currentNode;
//	     n1.previousNode = i2.currentNode;
//	     
//	     // [p2->i1] [i1<-n2]
//	     p2.nextNode = i1.currentNode;
//	     n2.previousNode = i1.currentNode;
//	     
//	     // [p1>t2>n1] 
//	     t2.previousNode = p1;
//	     t2.nextNode = n1;
//	     
//	     // [p2>t1>n2]
//	     t1.previousNode = p2;
//	     t1.nextNode = n2;
	}

	
	// implement this method
	public void insertList(DListIterator i1, CDLinkedList lst) {
		if (lst.isEmpty()) {
            return;
        }
		
		DListIterator n1 = new DListIterator(i1.currentNode.nextNode);
		
		i1.currentNode.nextNode = lst.header.nextNode;
		lst.header.nextNode.previousNode = i1.currentNode;
		
		lst.header.previousNode.nextNode = n1.currentNode;
		n1.currentNode.previousNode = lst.header.previousNode;

//        DListNode n1 = i1.currentNode.nextNode;
//        
//        // [i1 -> lstn] [i1 <- lstn]
//        i1.currentNode.nextNode = lst.header.nextNode;
//        lst.header.nextNode.previousNode = i1.currentNode;
//        
//        //[lstp -> n1] [ lstp <- n1]
//        lst.header.previousNode.nextNode = n1;
//        n1.previousNode = lst.header.previousNode;
	}

	// implement this method
	public void gender(String g) throws Exception {
		DListIterator itr = new DListIterator(header);
		DListIterator front = new DListIterator(header);
		
		while (itr.hasNext() && itr.currentNode.nextNode != header) {	
			Student n = (Student)(itr.next());
			String gender = n.getSex();
			if(gender.equals(g)) {
//				System.out.print(n.getName());
				insert(n,front);
				front.next();
				removeAt(itr);
			}
		}
	}

}
 
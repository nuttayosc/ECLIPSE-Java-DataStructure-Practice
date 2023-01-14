import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class TestMaxHeap {
    @Test
	public void time() throws Exception {
        Heap h = new Heap();
        Random rnd = new Random();
        int n = 1000000;
        
        int[] d = new int[n];
        for (int i = 0; i < d.length; i++) 
            d[i] = rnd.nextInt();
        
        long time = 0;
        long start = System.currentTimeMillis();
        
        for(int i = 0; i < d.length; i++) 
            h.add(d[i]);
        
        for(int i = 0; i < d.length; i++) 
            h.pop();
        
        time = System.currentTimeMillis()- start;
        System.out.println(n + " " + time + " ms");
        assertTrue(h.isEmpty());
    }
    
	@Test
	public void test() throws Exception {
		int[] x = {20,40,10,5,100,79,26,30};
		Heap h = new MaxIntHeap();
		for(int i=0;i<x.length;i++) 
			h.add(x[i]);
		assertEquals(100,h.pop());
		assertEquals(79,h.pop());
		assertEquals(40,h.pop());
	}

}

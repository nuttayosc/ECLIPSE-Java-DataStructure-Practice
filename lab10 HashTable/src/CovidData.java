import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;

public class CovidData {
	Hashtable<String,Integer> data;
	public CovidData(String filename) {
		data = new Hashtable<String,Integer>();
		try {
			Scanner in = new Scanner(new File("src/"+filename));
			// Add your code here 
			while (in.hasNext()) {
				String line = in.nextLine();
				String [] split = line.split(",");
				data.put(split[0], Integer.valueOf(split[1]));
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	public int find(String d) {
		// Add your code here 
		if (data.containsKey(d)) {
			return data.get(d);
		}
		
		return -1;
	}
}

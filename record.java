package Pandas;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//An object of record is the row in a dataframe
public class record {
	Map<String,Object> row=new HashMap<>();
	public record(List<String> col_names,List<String> values) {
		int counter=0;
		List<String> char_list= Arrays.asList("State","Gender","Name");
		for(String s:col_names) {
			if (char_list.contains(s)) {
				this.row.put(s,values.get(counter));
			}
			else {
				this.row.put(s,Integer.parseInt(values.get(counter)));
			}
			counter+=1;
		}
	}
}

/* 
 * 
 *  
 *  Interface Comparator <record> is used for sorting 
 */

//This class sorts the data by State 
class StateComparator implements Comparator<record>{
	// Compare function is overwritten in the interface
	public int compare(record r1, record r2) {
		String r1_val=r1.row.get("State").toString();
		String r2_val=r2.row.get("State").toString();
		return (r1_val.compareTo(r2_val));
	}
}

//This class sorts the data by Gender
class GenderComparator implements Comparator<record>{
	// Compare function is over-riding in the interface
	public int compare(record r1, record r2) {
		String r1_val=r1.row.get("Gender").toString();
		String r2_val=r2.row.get("Gender").toString();
		return (r1_val.compareTo(r2_val));
	}
}

//This class sorts the data by Name 
class NameComparator implements Comparator<record>{
	// Compare function is over-riding in the interface
	public int compare(record r1, record r2) {
		String r1_val=r1.row.get("Name").toString();
		String r2_val=r2.row.get("Name").toString();
		return (r1_val.compareTo(r2_val));
	}
}

//This class sorts the data by Year 
class YearComparator implements Comparator<record>{
	// Compare function is over-riding in the interface
	public int compare(record r1, record r2) {
		int r1_val=(Integer) r1.row.get("Year");
		int r2_val=(Integer) r2.row.get("Year");
		return (r1_val-r2_val);
	}
}

//This class sorts the data by Count 
class CountComparator implements Comparator<record>{
	// Compare function is over-riding in the interface
	public int compare(record r1, record r2) {
		int r1_val=(Integer) r1.row.get("Count");
		int r2_val=(Integer) r2.row.get("Count");
		return (r1_val-r2_val);
	}
}
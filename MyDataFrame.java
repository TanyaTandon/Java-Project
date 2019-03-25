package Pandas;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;



public class MyDataFrame {
	List<String> col_names;
	ArrayList<record> data;
	
	//Constructor 1 with input values: List of column names and data 
	public MyDataFrame(List<String> col_names,ArrayList<record> data) {
		this.col_names=col_names;
		this.data=data;
	}
	
	
	//Constructor 2 with no input values
	public MyDataFrame() {
		this.col_names=Arrays.asList("None");
		this.data=null;		
	}
	
	
	//Main is empty 
	public static void main(String[] args) {	
	}
	
	
	//To print the data frame 
	public String toString() {
		String f = ""; 
		String header = "" ; 
		for( int i = 0; i < col_names.size(); i++)
		{
			header = header + String.format("| %10s |" , col_names.get(i)) ; //The string will occupy 10 space, filling empty places with space 
		}	
		f = header + "\n"; 
		for (record row:this.data) {
			String row_dt = "";
			for (String col:this.col_names) {
					row_dt=row_dt+String.format("| %10s |", row.row.get(col)); //The string will occupy 10 space, filling empty places with space 								

			}
			f = f + row_dt + "\n"; 	
		}	
		return (f) ; 	
	}
	
	
	//Returns the first n rows of the data.
	public MyDataFrame head(int n) {
		//Checks for exception 
		// If n not in data, throws index out of range
		if(n>this.data.size()) {
			MyDataFrame head_df= new MyDataFrame();
			System.out.println("Index out of range!");
			return head_df;	
		}
		else {
			ArrayList<record> subset = new ArrayList<>();
			for(int i=0;i<n;i++) {
				subset.add(this.data.get(i));
			}
			List<String> subs_col_names=this.col_names;
			MyDataFrame head_df= new MyDataFrame(subs_col_names,subset);
			return head_df;				
		}
	}
	
	
	//Returns the last n rows of the data.
	public MyDataFrame tail(int n) {
		//Checks for exception 
		// If n not in data, throws index out of range
		if(n>this.data.size()) {
			MyDataFrame tail_df= new MyDataFrame();
			System.out.println("Index out of range!");
			return tail_df;	
		}
		else {
			ArrayList<record> subset = new ArrayList<>();
			for(int i=this.data.size()-n;i<this.data.size();i++) {
				subset.add(this.data.get(i));
			}
			List<String> subs_col_names=this.col_names;
			MyDataFrame tail_df= new MyDataFrame(subs_col_names,subset);
			return tail_df;				
		}
	}	
	
	
	//Returns the type of the column specified by index. If the type is not uniform, return â€˜Stringâ€™.
	public String dType(int index )
	{ 
		//Checks for exception 
		// If n not in data, throws index out of range
	if (index>this.data.size())
	{
		String empty = "";
		System.out.println("Index out of range!");
		return empty; 	
	}
		
		else {
		String name = col_names.get(index - 1); 
		String type = " "; 
		record p =data.get(1);
		String t = p.row.get(name).getClass().getSimpleName();
		for (record row:data) {
					String f = row.row.get(name).getClass().getSimpleName(); // Stores 'Integer'or 'String'
					if (!f.equals(t))
					{
						type = "String" ;
						break ; 
					}
					else {
						type = f; 
					}	
				}
		return( type); 
	} 
	}
	
	
	//Returns the type of the column specified by name. If the type is not uniform, return â€˜Stringâ€™.
	public String dType(String name )
	{ 
		//Checks for exception
		// If name is not in Column names then this will print Column Index Not found!
		int n=-1;
		for(int i=0;i<this.col_names.size();i++) {
			if(this.col_names.get(i).equals(name)) {
				n=i+1;
			}
		}
		if (n==-1) {
			String empty = ""; 
			System.out.println("Column Index Not found!");
			return empty;
		}
	else {
		String type = " "; 
		record p =data.get(1);
		String t = p.row.get(name).getClass().getSimpleName();
		for (record row:data) {
					String f = row.row.get(name).getClass().getSimpleName(); // Stores 'Integer'or 'String'
					if (!f.equals(t)) 
					{
						type = "String" ;
						break ; 
					}
					else {
						type = f;  
					}	
				}
		
		return( type); 
	} 
	}
	
	
	//Returns the column specified by index.
	public MyDataFrame slice(int n) {
		//Checks for exception 
		// If n not in data, throws index out of range
			if(n>this.col_names.size()) {
				MyDataFrame slice_df= new MyDataFrame();
				System.out.println("Column Index out of range!");
				return slice_df;					
			}
			else {
				ArrayList<record> subset = new ArrayList<>();
				String col_name=this.col_names.get(n-1);
				List<String> subs_col_names=Arrays.asList(col_name);
				for(int i=0;i<this.data.size();i++) {
					List<String> strList = new ArrayList<>();					
					strList.add(this.data.get(i).row.get(col_name).toString());					
					record row=new record(subs_col_names,strList);	
					subset.add(row);
				}
				MyDataFrame tail_df= new MyDataFrame(subs_col_names,subset);
				return tail_df;					
			}
	}
	
	
	//Returns the column specified by name.
	public MyDataFrame slice(String name) {
		//Checks for exception
		// If name is not in Column names then this will print Column Index Not found!
		int n=-1;
		for(int i=0;i<this.col_names.size();i++) {
			if(this.col_names.get(i).equals(name)) {
				n=i+1;
			}
		}
		if (n==-1) {
			MyDataFrame slice_df= new MyDataFrame();
			System.out.println("Column Index Not found!");
			return slice_df;			
		}
		else {
			ArrayList<record> subset = new ArrayList<>();
			String col_name=this.col_names.get(n-1);
			List<String> subs_col_names=Arrays.asList(col_name);
			for(int i=0;i<this.data.size();i++) {
				List<String> strList=new ArrayList<>();
				strList.add(this.data.get(i).row.get(col_name).toString());					
				record row=new record(subs_col_names,strList);	
				subset.add(row);
			}
			MyDataFrame slice_df= new MyDataFrame(subs_col_names,subset);
			return slice_df;					
		}
	}
	
	
	//Returns the columns specified by an index array.
	public MyDataFrame slice(int[] indexarr) {
		//Checks for exception 
		// If index in index array is not in the data,it throws index out of range
		ArrayList<record> subset = new ArrayList<>();
		List<String> subs_col_names=new ArrayList<String>();
		for(int n:indexarr) {		
			if(n>this.col_names.size() || n<=0) {
				MyDataFrame slice_df= new MyDataFrame();
				System.out.println("Column Index out of range!");
				return slice_df;					
			}
			else {
				subs_col_names.add(this.col_names.get(n-1));
			}
		}
		for (int i=0;i<this.data.size();i++) {		
			List<String> row_vals=new ArrayList<String>();		
			for(int n:indexarr) {
				String col_name=this.col_names.get(n-1);
				row_vals.add(this.data.get(i).row.get(col_name).toString());
			}							
			record row=new record(subs_col_names,row_vals);	
			subset.add(row);
		}
		 MyDataFrame slice_df= new MyDataFrame(subs_col_names,subset);
		 return slice_df;					
	}
	
	
	//Returns the columns specified by a name array.
	public MyDataFrame slice(String[] indexarr_str) {
		//Checks for exception
		// If names given in the array is not in Column names then this will print Column Index Not found!
		int master_flag=0;
		int[] indexarr=new int[indexarr_str.length];
		for(int i=0;i<indexarr_str.length;i++) {
			int flag=1;
			for(int j=0;i<this.col_names.size();j++) {
				if (indexarr_str[i].equals(this.col_names.get(j))){
					indexarr[i]=j+1;
					flag=0;
					break;
				}
			}
			master_flag+=flag;
		}
		if (master_flag==0) {
			return this.slice(indexarr);
		}
		else {
			MyDataFrame slice_df= new MyDataFrame();
			System.out.println("Column Not Found!");
			return slice_df;		
		}
	}
 
	
	//Returns the rows starting from index.
	public MyDataFrame loc(int n) {
		ArrayList<record> subset = new ArrayList<>();
		List<String> subs_col_names=this.col_names;
		if(n>this.data.size() || n<=0) {
			System.out.println("Index out of range");
			MyDataFrame slice_df= new MyDataFrame();
			return slice_df;			
		}
		else {
			for(int i=n-1;i<this.data.size();i++) {
				subset.add(this.data.get(i));
			}
		MyDataFrame df=new MyDataFrame(subs_col_names,subset);
		return df;
		}
	}

	
	//Returns the rows between from and to (including from and to).
	public MyDataFrame loc(int a,int b) {
		ArrayList<record> subset = new ArrayList<>();
		List<String> subs_col_names=this.col_names;
		if(a>b) {
			System.out.println("Lower bound to be smaller than upper bound");
		}
		else if(a>this.data.size() || b>this.data.size()) {
			System.out.println("Index to be smaller than dataframe size");
		}
		else if(a<=0 || b<=0) {
			System.out.println("Index to be greater than zero");
		}
		else {
			for(int i=a-1;i<b;i++) {
				subset.add(this.data.get(i));
			}
		MyDataFrame df=new MyDataFrame(subs_col_names,subset);
		return df;
		}
		MyDataFrame slice_df= new MyDataFrame();
		return slice_df;		
	}
	
	
	//Returns data filtered by applying â€œcol op oâ€� on MyDataFrame object, e.g. â€œcount 10â€�, â€œstate = â€˜ILâ€™â€�.
	public MyDataFrame filter(String col,String op,Object o){
		String otype=o.getClass().getSimpleName();
		List<String> char_list= Arrays.asList("State","Gender","Name");
		String val=null;
		if(otype.equals("String") || otype.equals("Integer") ) {
			val=o.toString();
		}
		else {
			System.out.println("Operation type not supported for Non-string/Integer object");
		}
		ArrayList<record> subset = new ArrayList<>();
		int n=-1;
		for(int i=0;i<this.col_names.size();i++) {
			if(this.col_names.get(i).equals(col)) {
				n=i+1;
			}
		}
		if (n==-1) {
			MyDataFrame slice_df= new MyDataFrame();
			System.out.println("Column Not found!");
			return slice_df;			
		}
		String col_type=this.dType(col);
		if(col_type.equals(otype)) {		
			for(int i=0;i<this.data.size();i++) {
				record r=this.data.get(i);
				String row_val=null;
				row_val=r.row.get(col).toString();
				if (char_list.contains(col)) {
					switch(op) {
					case "==":
						if(row_val.equals(val)) {
							subset.add(r);
						}
						break;
					case "!=":
						if(!row_val.equals(val)) {
							subset.add(r);
						}		
						break;
					case ">=":
						if(row_val.compareTo(val)>=0) {
							subset.add(r);
						}	
						break;
					case "<=":
						if(row_val.compareTo(val)<=0) {
							subset.add(r);
						}
						break;
					case ">":
						if(row_val.compareTo(val)>0) {
							subset.add(r);
						}
						break;
					case "<":
						if(row_val.compareTo(val)<0) {
							subset.add(r);
						}
						break;
					default:
						System.out.println("Operator not supported!");
						MyDataFrame slice_df= new MyDataFrame();
						return slice_df;
					}					
				}	
				else {
					switch(op) {
					case "==":
						if(Integer.parseInt(row_val)==Integer.parseInt(val)) {
							subset.add(r);
						}
						break;
					case "!=":
						if(Integer.parseInt(row_val)!=Integer.parseInt(val)) {
							subset.add(r);
						}		
						break;
					case ">=":
						if(Integer.parseInt(row_val)>=Integer.parseInt(val)) {
							subset.add(r);
						}	
						break;
					case "<=":
						if(Integer.parseInt(row_val)<=Integer.parseInt(val)) {
							subset.add(r);
						}
						break;
					case ">":
						if(Integer.parseInt(row_val)>Integer.parseInt(val)) {
							subset.add(r);
						}
						break;
					case "<":
						if(Integer.parseInt(row_val)<Integer.parseInt(val)) {
							subset.add(r);
						}
						break;
					default:
						System.out.println("Operator not supported!");
						MyDataFrame slice_df= new MyDataFrame();
						return slice_df;
					}						
				}		
			}
			MyDataFrame df=new MyDataFrame(this.col_names,subset);
			return df;
		}
		else {
			System.out.println("Column type & Input Object type dont match");
			MyDataFrame slice_df= new MyDataFrame();
			return slice_df;
		}
	}
	
	
	//Returns the data sorted by the column specified by index.
	public MyDataFrame sort(int n) {
		if(n>this.col_names.size()) {
			MyDataFrame slice_df= new MyDataFrame();
			System.out.println("Column Index out of range!");
			return slice_df;					
		}
		else {
			String col_name=this.col_names.get(n-1);
			ArrayList<record> df=new ArrayList<>();
			df=this.data;
			switch(col_name) {
			case "State":
				StateComparator state=new StateComparator();
				Collections.sort(df,state);
				break;
			case "Gender":
				GenderComparator gender=new GenderComparator();
				Collections.sort(df,gender);
				break;
			case "Name":
				NameComparator name=new NameComparator();
				Collections.sort(df,name);
				break;
			case "Year":
				YearComparator year=new YearComparator();
				Collections.sort(df,year);
				break;
			case "Count":
				CountComparator count=new CountComparator();
				Collections.sort(df,count);
				break;
			default:
				break;
			}
			MyDataFrame sort_df=new MyDataFrame(this.col_names,df);
			return sort_df;
		}
	}
	
	
	//Returns the data sorted by the column specified by name.
	public MyDataFrame sort(String name) {
		int n=-1;
		for(int i=0;i<this.col_names.size();i++) {
			if(this.col_names.get(i).equals(name)) {
				n=i+1;
			}
		}
		if (n==-1) {
			MyDataFrame slice_df= new MyDataFrame();
			System.out.println("Column Index Not found!");
			return slice_df;			
		}
		else {
			String col_name=name;
			ArrayList<record> df=new ArrayList<>();
			df=this.data;
			switch(col_name) {
			case "State":
				StateComparator state=new StateComparator();
				Collections.sort(df,state);
				break;
			case "Gender":
				GenderComparator gender=new GenderComparator();
				Collections.sort(df,gender);
				break;
			case "Name":
				NameComparator namec=new NameComparator();
				Collections.sort(df,namec);
				break;
			case "Year":
				YearComparator year=new YearComparator();
				Collections.sort(df,year);
				break;
			case "Count":
				CountComparator count=new CountComparator();
				Collections.sort(df,count);
				break;
			default:
				break;
			}
			MyDataFrame sort_df=new MyDataFrame(this.col_names,df);
			return sort_df;
		}
	}
	
	
	// Returns the maximum value of the list of type String
	public String MaxString(ArrayList<String> list) {
	    String max = "";
		for (int i = 0; i < list.size(); i++) {
			String str = list.get(i).toString();
			if (str.compareTo(max) > 0) {
				max = str;
			}
		} 
		return (max);
	}

	
   // Returns the maximum value of the list of type integer
	public int Maxint(ArrayList<Integer> list) {
		
	    int max = 0;
		for (int i = 0; i < list.size(); i++) {
			int temp = list.get(i);
			
			if (temp > max) {
				max = temp;
			}
		} 
		return (max);
	}
   
	
	//Returns the maximum element of the column specified by index.
	public Object getMax(int index) {
		if(index > this.col_names.size()) {
			
			String f = ""; 
			System.out.println("Column Index out of range!");
			return f;					
		}
		else {
		String col = col_names.get(index - 1); 
		String Typecol = dType(col) ; 
		
		if(Typecol.compareTo("String") == 0)
		{
	   ArrayList<String> row_dt = new ArrayList<>();
		for (record row:data) {
		
			
			row_dt.add(String.valueOf(row.row.get(col))); 										

			}
	
		String value_return = MaxString(row_dt);
		return (value_return) ; 
		}
		else
		{
			ArrayList<Integer> row_dt = new ArrayList<>();
			for (record row:data) {
				
				
				row_dt.add((Integer) row.row.get(col)); 										

				}
		
			int value_return = Maxint(row_dt);
			return (value_return);  
			
			
		}
		}
	}
	
	//Returns the maximum element of the column specified by col.	
	public Object getMax(String col) {
		int n=-1;
		for(int i=0;i<this.col_names.size();i++) {
			if(this.col_names.get(i).equals(col)) {
				n=i+1;
			}
		}
		if (n==-1) {
			String f = " "; 
			System.out.println("Column Index Not found!");
			return f;			
		}
		else {
			
			String Typecol = dType(col) ; 
			
			if(Typecol.compareTo("String") == 0)
			{
		   ArrayList<String> row_dt = new ArrayList<>();
			for (record row:data) {

				row_dt.add(String.valueOf(row.row.get(col))); 										
	
				}
		
			String value_return = MaxString(row_dt);
			return (value_return) ; 
			}
			else
			{
				ArrayList<Integer> row_dt = new ArrayList<>();
				for (record row:data) {

					row_dt.add((Integer) row.row.get(col)); 										
	
					}
			
				int value_return = Maxint(row_dt);
				return (value_return);  

			}
		}
		
	}	
	
	
	// Returns the minimum value of the list of type String
	public String MinString(ArrayList<String> list) {	
	    String min = list.get(0);
		for (int i = 0; i < list.size(); i++) {
			String str = list.get(i).toString();
			
			if (str.compareTo(min) < 0) {
				min = str;
			}
		}
		return (min);
	}
	
	
	// Returns the minimum value of the list of type integer
	public int Minint(ArrayList<Integer> list) {
	int min = list.get(0);
	for (int i = 0; i < list.size(); i++) {
		int temp = list.get(i);
		
		if (temp < min) {
			min = temp;
			}
		}
		return (min);
	}
	
	
	//Returns the minimum element of the column specified by index.
	public Object getMin(int index) {
		if(index > this.col_names.size()) {
			
			String f = ""; 
			System.out.println("Column Index out of range!");
			return f;					
		}
		else {
		String col = col_names.get(index - 1); 
		String Typecol = dType(col) ; 
		
		if(Typecol.compareTo("String") == 0)
		{
	   ArrayList<String> row_dt = new ArrayList<>();
		for (record row:data) {
		
			
			row_dt.add(String.valueOf(row.row.get(col))); 										
	
			}
	
		String value_return = MinString(row_dt);
		return (value_return) ; 
		}
		else
		{
			ArrayList<Integer> row_dt = new ArrayList<>();
			for (record row:data) {
				
				
				row_dt.add((Integer) row.row.get(col)); 										
	
				}
		
			int value_return = Minint(row_dt);
			return (value_return);  
			
			
		}
		}
	}
	
	
	//Returns the minimum element of the column specified by col
	public Object getMin(String col) {
	int n=-1;
	for(int i=0;i<this.col_names.size();i++) {
		if(this.col_names.get(i).equals(col)) {
			n=i+1;
		}
	}
	if (n==-1) {
		String f = " "; 
		System.out.println("Column Index Not found!");
		return f;			
	}
	else {
		
		String Typecol = dType(col) ; 
		
		if(Typecol.compareTo("String") == 0)
		{
	   ArrayList<String> row_dt = new ArrayList<>();
		for (record row:data) {
		
			
			row_dt.add(String.valueOf(row.row.get(col))); 										
	
			}
	
		String value_return = MinString(row_dt);
		return (value_return) ; 
		}
		else
		{
			ArrayList<Integer> row_dt = new ArrayList<>();
			for (record row:data) {
				
				
				row_dt.add((Integer) row.row.get(col)); 										
	
				}
		
			int value_return = Minint(row_dt);
			return (value_return);  
					
		}
	  }
	
	}	
}	
		
	

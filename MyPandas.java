package Pandas;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyPandas {
	
	
	//This function reads the data
	public static MyDataFrame readCSV(String path) throws FileNotFoundException {
		ArrayList<record> data=new ArrayList<record>();
		File file = new File(path); 
		Scanner in = new Scanner(file); 
		String [] header = in.nextLine().split(",");
		List<String> a1=new ArrayList<String>();
		a1 = Arrays.asList(header);
		
		while (in.hasNextLine()) {
			String [] element = in.nextLine().split(",");
			List<String> values=new ArrayList<>();
			values=Arrays.asList(element);
			record r=new record(a1,values);
			data.add(r);
		}
		MyDataFrame df=new MyDataFrame(a1,data);
		return df;
	}
	
	
	// This function writes the data into a new file
	public static void writeCSV(MyDataFrame p2,String path) throws IOException {
		FileWriter writer = new FileWriter(path);
		String header="";
		for(String h:p2.col_names) {
			header=header+h+",";
		}
		writer.write(header);
		writer.write("\r\n");							
		ArrayList<record> data=p2.data;
		for (record row:data) {
			String row_dt = "";
			for (String col:p2.col_names) {
					row_dt=row_dt+row.row.get(col)+",";										

			}
			writer.write(row_dt);
			writer.write("\r\n");
		}
		writer.close();
	}
	
	
	//This function concatenates two data sets 
	public static MyDataFrame concat(MyDataFrame p1,MyDataFrame p2) {
		if(p1.col_names.toString().equals(p2.col_names.toString())) {
			ArrayList<record> concat_data=new ArrayList<>(p1.data);
			concat_data.addAll(p2.data);
			MyDataFrame concat_df=new MyDataFrame(p1.col_names,concat_data);
			return concat_df;
		}
		else {
			MyDataFrame concat_df=new MyDataFrame();
			System.out.print("Column attributes don't match");
			return concat_df;
		}
	}
	
	//The main function tests all the methods
	public static void main(String[] args) throws IOException {
				String path="Test.csv";
				//Testing readCSV function //
				MyDataFrame obj1=readCSV(path);
				//System.out.println(obj1);
				
				//Testing concat function
				String path2="Test2.csv";
				MyDataFrame obja=readCSV(path2);
				MyDataFrame objb=readCSV(path2);
				MyDataFrame objc=concat(obja,objb);
				System.out.println(objc);
				writeCSV(objc,"Output2.csv");
				
				//Testing head and tail
				MyDataFrame obj2=obj1.head(10);
				System.out.println("Head Output");
				System.out.println(obj2);
				MyDataFrame obj3=obj1.tail(10);
				System.out.println("Tail output");
				System.out.println(obj3);
				
				//dtype(int) function
				System.out.println(obj1.dType(3));
				System.out.println(obj1.dType(4));
				
				//dtype(string) function
				System.out.println(obj1.dType("State"));
				System.out.println(obj1.dType("Count"));
				
				//Slicing slice(int index)
				MyDataFrame obj4 = obj1.slice(1);
				System.out.println(obj4);
				
				//Slicing slice(String)
				MyDataFrame obj5 = obj1.slice("Year");
				System.out.println(obj5);
		
				//Slicing slice(int[] )
				int b[]= {2,4};
				MyDataFrame obj6 = obj1.slice(b);
				System.out.println(obj6);

				//Slicing slice(String[] )
				String c[]= {"Year","Count"};
				MyDataFrame obj7 = obj1.slice(c);
				System.out.println(obj7);
		
				//Filtering
				MyDataFrame obj8 = obj1.filter("Count",">=",10);
				System.out.println(obj8);
				
				//loc(int n)
				MyDataFrame obj9 = obj1.loc(15);
				System.out.println(obj9);
				
				//loc(int a,int b)
				MyDataFrame obj10 = obj1.loc(15,20);
				System.out.println(obj10);
				
				//Sort(int n)
				MyDataFrame obj11=obj1.sort(1);
				System.out.println(obj11);
				
				//Sort(String name)
				MyDataFrame obj12=obj1.sort("Name");
				System.out.println(obj12);
				
				//Aggregation
				System.out.println(obj1.getMin(5));
				System.out.println(obj1.getMin("State"));
				System.out.println(obj1.getMax(3));
				System.out.println(obj1.getMax("Count"));				

	}
}
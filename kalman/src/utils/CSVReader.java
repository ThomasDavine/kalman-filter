package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {
	
	private File file;
	
	private String sep = ",";
	
	private double[] values;

	public CSVReader(String path){
		this.file = new File(path);
	}
	
	public CSVReader(String path, String sep){
		this.file = new File(path);
		this.sep = sep;
	}
	
	public double[] read(){
		try {
			String line = "";
			ArrayList<Double> list = new ArrayList<Double>();
			BufferedReader br = new BufferedReader(new FileReader(file));
			while((line = br.readLine())!=null){
				list.add(Double.valueOf(line.split(sep)[2]));
			}
			
			br.close();
			values = new double[list.size()];
			for(int i=0; i<list.size(); i++){
				values[i] = list.get(i);
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return values;
	}

}

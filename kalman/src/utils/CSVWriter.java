package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {

	private File file;

	public CSVWriter(String path) {
		this.file = new File(path);
	}

	public void write(double[] list1, double[] list2) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
			if(list1.length == list2.length){
				for(int i=0; i<list1.length;i++){
					bw.write(String.valueOf(list1[i]+";"+list2[i]));
					bw.newLine();
				}
			}else{
				System.err.println("size does not match");
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

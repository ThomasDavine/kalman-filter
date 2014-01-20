package main;


import utils.CSVReader;
import utils.CSVWriter;

public class DataManager {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String path = "/home/black/Documents/Computer/Kalman/USDCAD60.csv";
		CSVReader reader = new CSVReader(path);
		double[] observation = reader.read();
		double[] values = new filter.KalmanFilter(observation, 0.0000001).filter();
		
		new CSVWriter("/home/black/Documents/Computer/Kalman/filter.csv").write(observation, values);
		
		
	}

}

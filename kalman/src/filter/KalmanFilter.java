package filter;

import org.apache.commons.math3.stat.StatUtils;

public class KalmanFilter {
	
private int numOfMeasurements;
	
	private double processVariance, estimationVariance;
	
	private double[] observations;
	
	public KalmanFilter(double[] observations, double processVariance){
		this.processVariance = processVariance;
		this.observations=observations;
		this.numOfMeasurements=observations.length;
		this.estimationVariance=Math.pow(StatUtils.populationVariance(observations),2);
	}
	
	public double[] filter(){
		
		// Number of measurements
		int N = numOfMeasurements; 

		// measurements with mean = .5, sigma = .1;
		double z[] = observations;
		

		double Q = processVariance,
				//	Math.min(1.0, StatUtils.populationVariance(observations)),
				
				//	R = StatUtils.populationVariance(observations);
				R = estimationVariance;
		// Estimation variance

		double[] xhat = new double[N],	// estimated true value (posteri)
		xhat_prime = new double[N],   // estimated true value (priori)
		p = new double[N],    // estimated error (posteri)
		p_prime = new double[N],	// estimated error (priori)
		k = new double[N];    // kalman gain

		double cur_ave = 0;

		// Initial guesses
		xhat[0] = observations[0]; 
		p[0] = observations[0];

		//	System.out.println(xhat.length);
		//	System.out.println(observations.length);
		
		for(int i = 1; i <= N-1; i++) {
		    // time update
		    xhat_prime[i] = xhat[i-1];
		    p_prime[i] = p[i-1] + Q;

		    // measurement update
		    k[i] = p_prime[i]/(p_prime[i] + R);
		    xhat[i] = xhat_prime[i] + k[i]*(z[i] - xhat_prime[i]);
		    p[i] = (1-k[i])*p_prime[i];

		    // calculate running average
		    cur_ave = (cur_ave*(i-1) + z[i])/((double)i);
		    
		    //System.out.printf("%04f;%04f;%04f\n", z[i], xhat[i], cur_ave);
		}
		
		return xhat;
	}
}
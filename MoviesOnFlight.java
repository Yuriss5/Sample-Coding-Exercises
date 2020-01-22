import java.util.Arrays;

/*You are on a flight and wanna watch two movies during this flight.
You are given int[] movie_duration which includes all the movie durations.
You are also given the duration of the flight which is d in minutes.
Now, you need to pick two movies and the total duration of the two movies is less than or equal to (d - 30min).
Find the pair of movies with the longest total duration. If multiple found, return the pair with the longest movie.

e.g.
Input
movie_duration: [90, 85, 75, 60, 120, 150, 125]
d: 250

Output from aonecode.com
[90, 125]
90min + 125min = 215 is the maximum number within 220 (250min - 30min)
 * */

public class MoviesOnFlight {
	
	private short recessTime=30;
	
	public static void main(String[] args) {
		//Declare an array of type short since we won't be handling movies larger than three or four hours and no decimals
		short movies[] = new short[]{90, 85, 75, 60, 120, 150, 125};
		
		//short movies[] = new short[]{125,150,120,60,75,85,90};
		
		//What's the total duration for this flight
		int totalDuration = 250;
		
		MoviesOnFlight moviesOnFlight = new MoviesOnFlight();
		moviesOnFlight.getMoviesForFlight(movies, totalDuration);
	}
	
	private void getMoviesForFlightV1(short movies[], int totalDuration) {
		short movie1, movie2;
		int viewableTime = totalDuration - recessTime; 

		movie1=movies[0];
		movie2=movies[1];
		for(int i=2; i<=movies.length-1;i++) {
			if(movies[i]>movie2 && movies[i]+movie1<=viewableTime){
				movie2=movies[i];
			}else
				continue;
		}
		System.out.println(movie1+" "+movie2);
	}
	
	private void getMoviesForFlight(short movies[], int totalDuration) throws NullPointerException, ArrayIndexOutOfBoundsException{
		
		short movie1, movie2;
		int viewableTime = totalDuration - recessTime; 
		
		//By sorting we will avoid having greater values at the beginning which below logic is expecting
		Arrays.sort(movies);
		
		//We'll start assigning movie1 and movie2 first values, this assumes they may have our ideal values
		movie1=movies[0];
		movie2=movies[1];
		for(int i=2; i<=movies.length-1;i++) {
			//Compare following value added to movie2(remember array is sorted) is still within duration, then movie1 can be replaced since we know it's smaller  
			if(movies[i]+movie2<=viewableTime) {
				movie1=movie2;
				movie2=movies[i];
			//When current value from above comparison not within duration, there is still a chance it can replace movie2
			}else if(movies[i]+movie1<=viewableTime) {
				movie2=movies[i];
			}
		}
		System.out.println(movie1+" "+movie2);
	}
}

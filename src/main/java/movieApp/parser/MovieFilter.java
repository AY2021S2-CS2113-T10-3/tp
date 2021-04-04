package movieApp.parser;

import movieApp.Cineplex;
import movieApp.Movie;
import movieApp.command.ViewBooking;
import movieApp.storage.Database;
import movieApp.user.User;

import java.util.ArrayList;
import java.util.Scanner;

public class MovieFilter {

	public static void printMovieList(ArrayList<Movie> MovieList) {
		int i;
		System.out.println("\n===========================");
		System.out.println("Movie List:");
		for (i=0;i<MovieList.size();i++) {
			System.out.println((i+1)+". "+MovieList.get(i).getMovieTitle());
		}
		System.out.println("===========================\n");
	}
	

	public static int getFilter() {
		int filter = -1;
		Scanner sc = new Scanner(System.in);
		
		while ((filter < 1) || (filter > 10)) {
			System.out.println("======= Movie Filter =======");
			System.out.println(" 1 Filter by genre");
			System.out.println(" 2 Filter by rating");
			System.out.println(" 3 Filter by showing status");
			System.out.println(" 4 Filter by cineplex");
			System.out.println(" 5 Filter by title");
			System.out.println(" 6 Select movie");
			System.out.println(" 7 List filtered movies");
			System.out.println(" 8 Clear all filters");
			System.out.println(" 9 View Bookings");
			System.out.println(" 10 Back to Main Menu");
			System.out.println("============================");
			System.out.println("Please indicate your choice:");

			if (!sc.hasNextInt()) {
				System.out.println("Please input an integer.\n");
				sc.next();
				continue;
			} 
			filter = sc.nextInt();
			if ((filter < 1) ||(filter > 10)) {
				System.out.println("Please input an integer between 1 and 10.\n");
			}
		}
		return filter;
	}
	
	////////////////////////////////////////////////////////////////////////////
	public static String getGenre() {
		int genre_choice = -1;
		Scanner sc = new Scanner(System.in);
		
		while ((genre_choice < 1) || (genre_choice > 7)) {
			System.out.println("======= Select Genre =======");
			System.out.println(" 1 Sci-fi");
			System.out.println(" 2 Action");
			System.out.println(" 3 Comedy");
			System.out.println(" 4 Family");
			System.out.println(" 5 Horror");
			System.out.println(" 6 Romance");
			System.out.println(" 7 Drama");
			System.out.println("============================");
			System.out.println("Please indicate your choice:");

			if (!sc.hasNextInt()) {
				System.out.println("Please input an integer.\n");
				sc.next();
				continue;
			} 
			genre_choice = sc.nextInt();
			if ((genre_choice < 1) ||(genre_choice >7)) {
				System.out.println("Please input an integer between 1 and 7.\n");
			}
		}
		
		switch (genre_choice) {
		case 1:
			return "Sci-fi";
		case 2:
			return "Action";
		case 3:
			return "Comedy";
		case 4:
			return "Family";
		case 5:
			return "Horror";
		case 6:
			return "Romance";
		case 7:
			return "Drama";
		default:
			return "No such genre.";
		}
	}
	
	////////////////////////////////////////////////////////////////////////////
	public static ArrayList<Movie> filterByGenre(ArrayList<Movie> movieList){
		int i=0;
		String genre = getGenre();
		System.out.println("\nThe selected genre is: "+genre);
		
		while (i<movieList.size()) {
			if (!genre.equals(movieList.get(i).getGenre())){
				movieList.remove(i);
				i--;
			}
			i++;
		}
		return movieList;
	}

	////////////////////////////////////////////////////////////////////////////
	public static float getRating() {
		float rating = -1;
		Scanner sc = new Scanner(System.in);
		
		while ((rating < 0) || (rating > 5)) {
			System.out.println("Select the cut-off rating (0-5): ");
			if (!sc.hasNextFloat()) {
				System.out.println("Please input a float.\n");
				sc.next();
				continue;
			} 
			rating = sc.nextFloat();
			if ((rating < 0) ||(rating >5)) {
				System.out.println("Please input a float between 0 and 5.\n");
			}
		}
		return rating;
	}
	
	////////////////////////////////////////////////////////////////////////////
	public static ArrayList<Movie> filterByRating(ArrayList<Movie> movieList){
		int i=0;
		float rating = getRating();
		System.out.println("\nThe selected cut-off rating is: "+rating);
		
		while (i<movieList.size()) {
			if (movieList.get(i).getReviewSize() == 0 || rating > (movieList.get(i).calculateOverallRating())){
				movieList.remove(i);
				i--;
			}
			i++;
		}
		return movieList;
	}
	
	////////////////////////////////////////////////////////////////////////////
	public static String getShowingStatus() {
		int showing_status_choice = -1;
		Scanner sc = new Scanner(System.in);
		
		while ((showing_status_choice < 1) || (showing_status_choice > 4)) {
			System.out.println("======= Select Genre =======");
			System.out.println("== Select Showing Status ===");
			System.out.println(" 1 Coming Soon");
			System.out.println(" 2 Pre-Order");
			System.out.println(" 3 Now Showing");
			System.out.println(" 4 End of Showing");
			System.out.println("============================");
			System.out.println("Please indicate your choice:");

			if (!sc.hasNextInt()) {
				System.out.println("Please input an integer.\n");
				sc.next();
				continue;
			} 
			showing_status_choice = sc.nextInt();
			if ((showing_status_choice < 1) ||(showing_status_choice > 4)) {
				System.out.println("Please input an integer between 1 and 4.\n");
			}
		}
	
		switch (showing_status_choice) {
		case 1:
			return "COMINGSOON";
		case 2:
			return "PREORDER";
		case 3:
			return "NOWSHOWING";
		case 4:
			return "ENDSHOWING";
		default:
			return "No such showing status.";
		}
	}

	////////////////////////////////////////////////////////////////////////////
	public static ArrayList<Movie> filterByShowingStatus(ArrayList<Movie> movieList){
		int i=0;
		String showingStatus = getShowingStatus();
		System.out.println("\nThe selected showing status is: "+ showingStatus);
		
		while (i<movieList.size()) {
			if (!showingStatus.equals(movieList.get(i).getShowingStatus())){
				movieList.remove(i);
				i--;
			}
			i++;
		}
		return movieList;
	}
	
	////////////////////////////////////////////////////////////////////////////
	public static int getCineplex() {
		int cineplex_choice = -1;
		Scanner sc = new Scanner(System.in);
		
		while ((cineplex_choice < 1) || (cineplex_choice > 3)) {
			System.out.println(" 1 Jurong Point");
			System.out.println(" 2 VivoCity");
			System.out.println(" 3 Bishan");
			System.out.println("============================");
			System.out.println("Please indicate your choice:");

			if (!sc.hasNextInt()) {
				System.out.println("Please input an integer.\n");
				sc.next();
				continue;
			} 
			cineplex_choice = sc.nextInt();
			if ((cineplex_choice < 1) ||(cineplex_choice > 3)) {
				System.out.println("Please input an integer between 1 and 3.\n");
			}
		}
		return (cineplex_choice-1);
	}
	
	////////////////////////////////////////////////////////////////////////////
	public static ArrayList<Movie> filterByCineplex(ArrayList<Movie> movieList, ArrayList<Cineplex> CineplexDatabase){
		
		int cineplexID = getCineplex();
		Cineplex cineplex = CineplexDatabase.get(cineplexID);
		System.out.println("\nThe selected cineplex is: " + cineplex.getCineplexName());
		ArrayList<Integer> cineplexMovies = cineplex.getMovieList();
		int i=0;
		
		while (i<movieList.size()){
			if (!cineplexMovies.contains(movieList.get(i).getMovieID())){
				movieList.remove(i);
				i--;
			}
			i++;
		}
		return movieList;
	}

	////////////////////////////////////////////////////////////////////////////
	public static String getTitle() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Search: ");
		return sc.nextLine();
	}
	
	////////////////////////////////////////////////////////////////////////////
	public static ArrayList<Movie> filterByTitle(ArrayList<Movie> movieList){
		String title = getTitle();
		System.out.println("movies containing "+title);
		int i=0;
		while (i<movieList.size()){
			if (!movieList.get(i).getMovieTitle().toLowerCase().contains(title.toLowerCase())){
				movieList.remove(i);
				i--;
			}
			i++;
		}
		return movieList;
		
	}

	////////////////////////////////////////////////////////////////////////////
	public static int getMovieChoice(int size) {
		int movie_choice = -1;
		Scanner sc = new Scanner(System.in);
		
		while ((movie_choice < 1) || (movie_choice > size)) {
			System.out.println("Please indicate your choice:");
			if (!sc.hasNextInt()) {
				System.out.println("Please input an integer.\n");
				sc.next();
				continue;
			} 
			movie_choice = sc.nextInt();
			if ((movie_choice < 1) ||(movie_choice > size)) {
				System.out.println("Please input an integer between 1 and "+size+".\n");
			}
		}
		return movie_choice-1;
	}
	
	////////////////////////////////////////////////////////////////////////////
	public static ArrayList<Movie> selectMovie(ArrayList<Movie> movieList){
		ArrayList<Movie> result = new ArrayList<>();
		printMovieList(movieList);
		result.add(movieList.get(getMovieChoice(movieList.size())));
		return result;
	}

	////////////////////////////////////////////////////////////////////////////
	public static void listMovie(ArrayList<Movie> movieList){
		printMovieList(movieList);
	}

	////////////////////////////////////////////////////////////////////////////
	public static Movie filter(ArrayList<Movie> movieList, ArrayList<Cineplex> CineplexDatabase, User user) {
		int Filter;
		loop: while (true) {

				Filter = getFilter();
				if(Filter==10) {
					return null;
				}
				
				switch(Filter) {
				case 1:
					System.out.println("===== Filter by Genre ======");
					movieList = filterByGenre(movieList);
					break;
				case 2:
					System.out.println("===== Filter by Rating =====");
					movieList = filterByRating(movieList);
					break;
				case 3:
					System.out.println("= Filter by Showing Status =");
					movieList = filterByShowingStatus(movieList);
					break;
				case 4:
					System.out.println("==== Filter by Cineplex ====");
					movieList = filterByCineplex(movieList,CineplexDatabase);
					break;
				case 5:
					System.out.println("===== Filter by Title ======");
					movieList = filterByTitle(movieList);
					break;
				case 6:
					System.out.println("======= Select Movie =======");
					movieList = selectMovie(movieList);
					break loop;
				case 7:
					System.out.println("======= Movie List =======");
					listMovie(movieList);
					break;
				case 8:
					movieList = new ArrayList<>(Database.MovieDatabase);
					break;
				case 9:
					ViewBooking currentUserBookings = new ViewBooking(user);
					currentUserBookings.printBookings();
					break;
				case 10:
					return null;
				default:
					System.out.println("Invalid input. Please try again.");
					continue;
				}

				if (movieList.size() == 0) {
					System.out.println("\nSorry! We couldn't find any matches.");
					System.out.println("We have reset the filters.");
					System.out.println("Would you like to try something else?");
					movieList = new ArrayList<>(Database.MovieDatabase);
				} else {
					printMovieList(movieList);
				}
		}

		return movieList.get(0);
	}
}
package movieApp;

import java.io.Serializable;
import java.util.Calendar;

public class Showtimes implements Serializable {
    private static final long serialVersionUID = -6009080018075884803L;
    protected Movie mv;
    private final int showtimeID;
    private final Calendar dateTime;
    private final int cinemaID;
    private final int cineplexID;
    private final int movieID;
    private final String movieTitle;
    private final Seat[][] seatPlan; //true means taken, false means empty

    Showtimes(int id, Calendar date, Cinema cinema, Movie movie) {
        int r = cinema.getR();
        int c = cinema.getC();
        this.seatPlan = new Seat[r][c];

        //seatplan for each showtime
        for (int row = 0; row < r; row++) {
            for (int col = 0; col < c; col++) {
                seatPlan[row][col] = new Seat(row, col, false);
            }
        }

        this.showtimeID = id;
        this.dateTime = date;
        this.movieID = movie.getMovieID();
        this.movieTitle = movie.getMovieTitle();
        this.cinemaID = cinema.getCinemaID();
        this.cineplexID = cinema.getCineplexID();
        this.mv = movie;

    }

    public void showInfo() {
        System.out.println("Showtime ID " + showtimeID + ", Movie title: " + movieTitle + ", Datetime: " + dateTime.get(Calendar.YEAR) + " " + (dateTime.get(Calendar.MONTH) + 1) + " " + dateTime.get(Calendar.DATE) + " " + dateTime.get(Calendar.HOUR_OF_DAY) + " " + dateTime.get(Calendar.MINUTE));
    }

    public int getShowtimeID() {
        return showtimeID;
    }

    public int getMovieID() {
        return movieID;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public Calendar getDateTime() {
        return dateTime;
    }

    public void printSeats() {
        int rownum = seatPlan.length;
        int colnum = seatPlan[0].length;
        System.out.println("---------------- <THE SCREEN>-----------------");
        System.out.println();
        System.out.print("columns  ");
        for (int i = 0; i < colnum; i++) {
            System.out.print(i + 1 + "     ");
        }
        System.out.println();
        for (int row = 0; row < rownum; row++) {
            System.out.print("row " + (row + 1) + " ");
            for (int col = 0; col < colnum; col++) {
                if (checkSeatTaken(row, col)) {
                    System.out.print("  XX  ");
                } else {
                    System.out.print("  --  ");
                }
            }
            System.out.println();
        }
    }

    public boolean checkSeatTaken(int[] RC) {
        return seatPlan[RC[0] - 1][RC[1] - 1].getStatus();
    }

    public boolean checkSeatTaken(int row, int col) {
        return seatPlan[row][col].getStatus();
    }

    public void setSeatStatus(int row, int col, boolean status) {
        seatPlan[row][col].setStatus(status);
    }

    public Seat getSeat(int row, int col) {
        return seatPlan[row][col];
    }

    public int getCinemaID() {
        return cinemaID;
    }

    public int getCineplexID() {
        return cineplexID;
    }

    public int getMaxRow() {
        return seatPlan.length;
    }

    public int getMaxColumn() {
        return seatPlan[0].length;
    }

}
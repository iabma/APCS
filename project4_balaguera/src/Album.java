/**
 * Stores and retrieves album information, taking the following parameters:
 * The artist name, the album name, the total track time, the total price,
 * and the number of tracks on the album.
 *
 * @author Ian Balaguera
 * @version 10.12.18
 */
public class Album {
    private String artistName;
    private String albumName;
    private int netTime;
    private double netPrice;
    private int numTracks;

    /**
     * Creates an album.
     * @param artistName the artist of the album
     * @param albumName the name
     * @param netTime the sum of the track lengths
     * @param netPrice the sum of the track prices
     * @param numTracks the number of tracks
     */
    public Album(String artistName, String albumName, int netTime,
                 double netPrice, int numTracks) {
        this.artistName = artistName;
        this.albumName = albumName;
        this.netTime = netTime;
        this.netPrice = netPrice;
        this.numTracks = numTracks;
    }

    /**
     * Gets the artist name.
     * @return artist name
     */
    public String getArtistName() {
        return artistName;
    }

    /**
     * Gets the album name.
     * @return album name
     */
    public String getAlbumName() {
        return albumName;
    }

    /**
     * Gets the total time in minutes and seconds.
     * @return minutes and seconds
     */
    public int[] getNetTime() {
        int min = netTime / 60;
        int sec = netTime % 60;
        int[] net = {min, sec};
        return net;
    }

    /**
     * Gets the number of tracks.
     * @return number of tracks
     */
    public int getNumTracks() {
        return numTracks;
    }

    /**
     * Gets the average track length in minutes and seconds.
     * @return minutes and seconds
     */
    public double[] getAvgTime() {
        double avgTime = (double) netTime / numTracks;
        double min = avgTime / 60;
        double sec = avgTime % 60;
        double[] net = {min, sec};
        return net;
    }

    /**
     * Retrieves the net price of the tracks.
     * @return net price
     */
    public double getNetPrice() {
        return netPrice;
    }

    /**
     * Retrieves the price of the album if bought completely.
     * @return discounted price
     */
    public double getDiscountPrice() {
        return netPrice * .75;
    }

    /**
     * Sets the artist name to a specified value.
     * @param artistName artist name
     */
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    /**
     * Sets the album name to a specified value.
     * @param albumName album name
     */
    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    /**
     * Sets the net time to a specified value.
     * @param netTime net time
     */
    public void setNetTime(int netTime) {
        this.netTime = netTime;
    }

    /**
     * Sets the net price to a specified value.
     * @param netPrice net price
     */
    public void setNetPrice(double netPrice) {
        this.netPrice = netPrice;
    }

    /**
     * Sets the net price to a specified value.
     * @param numTracks net price
     */
    public void setNumTracks(int numTracks) {
        this.numTracks = numTracks;
    }

    /**
     * Returns a summary of the album information.
     * @return the full album price, the total price of individual tracks, the
     * total time, and the average track length
     */
    @Override
    public String toString() {
        return String.format("%s\n%s\nFull Album Price: $%.2f\nTotal Price of " +
                        "Individual Tracks: $%.2f\nTotal Time: %d minutes, %d" +
                        " seconds\nAverage Track Length: %d minutes, %.1f " +
                        "seconds",
                getArtistName(), getAlbumName(), getDiscountPrice(),
                getNetPrice(), getNetTime()[0], getNetTime()[1],
                (int) getAvgTime()[0], getAvgTime()[1]);
    }
}

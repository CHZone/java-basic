package basic.proxy.staticproxy;

public class Main {
    public static void main(String args[]){
        HeiHeiHeiMovie h = new HeiHeiHeiMovie();
        Cinema cinema = new Cinema(h);
        cinema.play();
    }
}

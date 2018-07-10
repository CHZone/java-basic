package basic.proxy.staticproxy;

public class Cinema implements Movie {
    
    private Movie realMovie;
    
    public Cinema(Movie movie){
        this.realMovie = movie;
    }

    @Override
    public void play() {
        beforePlay();
        this.realMovie.play();
        afterPaly();
    }
    
    public void beforePlay(){
        System.out.println("before play");
    }
    
    public void afterPaly(){
        System.out.println("after play");
    }

}

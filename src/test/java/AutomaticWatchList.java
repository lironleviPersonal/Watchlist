import imdbpages.imdbSearchPage;
import org.testng.annotations.Test;
import tvshow.watchListFileCreation;
import tvshow.TvShowInfo;

import java.io.IOException;
import java.util.ArrayList;

public class AutomaticWatchList extends BaseTest {

    private imdbSearchPage searchPage;
    private ArrayList<TvShowInfo> tvShows;

    @Test
    public void createWatchlist()throws IOException{
        tvShows = new ArrayList<>();
        searchPage = new imdbSearchPage(driver);

        searchPage.navigateToImdb(configFile.getProductionUrl());
        addTvShowsWithMinimumRating();
        printWatchlistToConsole();
        addWatchListToFile(configFile.getWatchListDirectory());
    }

    private void addTvShowsWithMinimumRating(){
        String[] listsArray = watchList.getTvShows();
        double minimumRating = watchList.getRating();

        for(String str : listsArray) {
            TvShowInfo tvShow = searchPage.searchTvShowContent(str)
                                            .createPotentialTvShowForWatchlist();
            if(minimumRating <= tvShow.getGrade()){
                tvShows.add(tvShow);
            }
        }
    }

    private void printWatchlistToConsole(){
        for(TvShowInfo tvShowInfo : tvShows){
            System.out.println(tvShowInfo.getName());
            System.out.println(tvShowInfo.getGrade());
            System.out.println("---------------------");
        }
    }

    private void addWatchListToFile(String filePath) throws IOException {
        watchListFileCreation initWatchListFile = new watchListFileCreation(filePath);
        initWatchListFile.addValuesToFile(tvShows);
    }

}

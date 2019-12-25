package tvshow;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class watchListFileCreation {

    private File watchListFile;
    private String path;

    public watchListFileCreation(String filePath){
        path = filePath + File.separator + "watcList-"+getCurrentDate() +".txt";
        createLogsDirectory(filePath);
    }

    public void addValuesToFile(ArrayList<TvShowInfo> watchList) throws IOException{
        createNewFile();
        addWatchListData(watchList);
    }

    private void addWatchListData(ArrayList<TvShowInfo> watchList) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(watchListFile));
        int i=1;
        String header = "watchList:";

        writer.write(header);
        writer.newLine();

        for(TvShowInfo tvshow : watchList){
            String tvshowString = i + ". name: " + tvshow.getName() + " Rating: " + tvshow.getGrade();
            writer.write(tvshowString);
            writer.newLine();
            i++;
        }
        writer.close();
    }

    private void createLogsDirectory(String path){

        File file = new File(path);
            if (!file.exists()){
                if (file.mkdir()) {
                    System.out.println("Directory is created!");
                } else {
                    System.out.println("Failed to create directory!");
                }
            }
    }

    private void createNewFile()throws IOException {
        watchListFile = new File(path);
        if(watchListFile.createNewFile()){
            System.out.println("Watch list created");
        }
    }

    private String getCurrentDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        return dateFormat.format( new Date());
    }
}

package ned;

import ned.exceptions.NedException;
import ned.tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private File cacheFile;

    private String cacheFilePath;
    public Storage(String filePath) throws NedException {
            this.cacheFilePath = filePath;
            File f = new File(filePath);
            this.cacheFile = f;
    }

    public ArrayList<Task> load() throws NedException{
        //returns the arraylist which TaskList will save
        ArrayList<Task> newListOfTasks = new ArrayList<>();
        try {
            Scanner s = new Scanner(this.cacheFile);
            while (s.hasNext()){
                Task newTask = Parser.parseSavedTask(s.nextLine());
                newListOfTasks.add(newTask);
            }
        } catch (FileNotFoundException  e) {
            throw new NedException("M'lord, do not be alarmed, but it appears that there was no previous saved task " +
                    "file. Not to worry, we'll sort this out yet...");
        }
        return newListOfTasks;
    };

    public void save(TaskList listOfTasks) throws NedException{
        int sizeOfList = listOfTasks.getSize();
        try {
            FileWriter fw = new FileWriter(this.cacheFilePath);
            for (int i = 0; i < sizeOfList; i++) {
                fw.write(listOfTasks.getTaskTextForm(i) + "\n");
            }
            fw.close();
        } catch (FileNotFoundException e) {
            throw new NedException("M'lord, it appears that the cache file cannot be accessed, ensure that it is in a " +
                    "writable " +
                    "place");
        } catch (IOException e) {
            throw new NedException("M'lord, it appears there was an error accessing the cached file, please check " +
                    "that I am able to access it");
        }
    }
}

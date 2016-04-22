package googlenews;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NewsList {
    public static final String ANSI_BLUE = "\u001B[34m";    // Console color (Only supported in unicode console e.g. uxrvt
    public static final String ANSI_WHITE = "\u001B[37m";
    private List<News> newsList;                            // List of news

    public NewsList() {
        newsList = new ArrayList<>();
    }

    public void addNews(News news) {
        newsList.add(news);
    }

    /**
     * Print the news items on each line with their particular Ids
     * @param count No of news to show
     * @param options r=reverse the list
     */
    public void printNewsWithId(int count, String options) {
        if (options.equals("r"))
            Collections.reverse(newsList);

        if (count > 0) {
            for (int i = 0; i < count; i++) {
                News news = newsList.get(i);
                System.out.println(
                        news.getId() + " " + ANSI_BLUE + news.getText() + ANSI_WHITE);
            }
        } else {
            newsList.forEach(x -> System.out.println(
                            x.getId() + " " + ANSI_BLUE + x.getText() + ANSI_WHITE)
            );
        }
    }

    /**
     * Get a particular News item
     *
     * @param id News Id in the ArrayList, starts from 0. Adjust by 1 from caller
     * @return News item
     */
    public News getNews(int id) {
        return newsList.stream().filter(x -> x.getId() == id).findFirst().get();
    }
}

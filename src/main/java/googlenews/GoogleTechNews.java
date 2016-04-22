package googlenews;

import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Scanner;

public class GoogleTechNews {
    static final String GOOGLE_NEWS_LINK = "https://news.google.com/news/section?topic=tc";
    static NewsList newsList = new NewsList();
    static boolean VIEWING_NEWS = true;

    public static void main(String[] args) throws IOException {
        Document googleNewsDoc = Jsoup.connect(GOOGLE_NEWS_LINK).get();
        Elements googleNewsHeadlines = googleNewsDoc.select(".titletext");

        for (int i = 0; i < googleNewsHeadlines.size(); i++) {
            Element googleNewsHeadline = googleNewsHeadlines.get(i);
            newsList.addNews(new News(i + 1, googleNewsHeadline.text(),
                    googleNewsHeadline.parent().attr("href").toString()));
        }

        String options = "";
        int count = -1;


        if (args.length == 1) {
            if (StringUtil.isNumeric(args[0]))
                count = Integer.valueOf(args[0]);
            else
                options += args[0];
        } else if (args.length == 2) {
            if (StringUtil.isNumeric(args[0]))
                count = Integer.valueOf(args[0]);
            else
                options += args[0];

            if (StringUtil.isNumeric(args[1]))
                count = Integer.valueOf(args[1]);
            else
                options += args[1];
        }
        printAndSelect(count, options);
    }

    private static void printAndSelect(int count, String options) {
        Scanner sc = new Scanner(System.in);

        if (count > 0)
            System.out.println("Getting " + count + " items" +
                    (options.equals("r")  ? " in reverse order" : ""));

        newsList.printNewsWithId(count, options);
        System.out.println("\nPress id to open the link, q (or any char) to quit");

        while (VIEWING_NEWS) {
            String input = sc.next();

            if (StringUtil.isNumeric(input)) {
                Integer inputSelection = Integer.valueOf(input);
                News news = newsList.getNews(inputSelection);

                if (news != null) {
                    Process proc = null;
                    try {
                        proc = Runtime.getRuntime().exec("xdg-open " + news.getLink());
                        proc.waitFor();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                VIEWING_NEWS = false;
            }
        }
    }
}

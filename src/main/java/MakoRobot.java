import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.HashMap;
import java.util.Map;
public class MakoRobot extends BaseRobot {
public MakoRobot(String rootWebsiteUrl) {
    super(rootWebsiteUrl);
        }

public String getRootWebsiteUrl() {
        return rootWebsiteUrl;
        }

public void setRootWebsiteUrl(String rootWebsiteUrl) {
        this.rootWebsiteUrl = rootWebsiteUrl;
        }

public  int countInArticlesTitles(String text) {
    int locationMainTitles = 0;
    int count = 0;
    Document web;
    String mainTitle;
    String subTitle;
    Element link;
    String href;
    try {
        Document website = Jsoup.connect(this.rootWebsiteUrl).get();
        Elements titles = website.getElementsByClass("headLine");
        Elements titles1 = titles.parents();
        Elements titles2;
        while (locationMainTitles < 34) { //Goes through the 5 main report on the site
            link = titles1.get(locationMainTitles).getElementsByTag("a").get(0);
            href = link.attr("href");
            if (locationMainTitles == 0) {
                locationMainTitles = 16;
            }
            locationMainTitles = locationMainTitles + 4;
            if (href.charAt(0) == 'h') {
                web = Jsoup.connect(href).get();
            } else {
                web = Jsoup.connect("https://www.mako.co.il/" + href).get();
            }
            titles2 = web.getElementsByTag("h1");
            mainTitle = titles2.text();
            if (mainTitle.contains(text)) {
                count++;
            }
            titles2 = web.getElementsByTag("h2");
            subTitle = titles2.text();
            if (subTitle.contains(text)) {
                count++;
            }
        }
        titles1 = website.getElementsByClass("element");
        for (int i = 9; i < titles1.size(); i++) { //Goes over the rest of the site reports
            link = titles1.get(i).getElementsByTag("a").get(0);
            String linked = link.attr("href");
            try{
            if (linked.charAt(0) == 'h')
                web = Jsoup.connect(linked).get();
            else
                web = Jsoup.connect("https://www.mako.co.il/" + linked).get();
            titles2 = web.getElementsByTag("h1");
            mainTitle = titles2.text();
            if (mainTitle.contains(text)) {
                count++;
            }
            titles2 = web.getElementsByTag("h2");
            subTitle = titles2.text();
            if (subTitle.contains(text)) {
                count++;
            }
            }catch (StringIndexOutOfBoundsException ignored){}
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return count;
}

    public  String getLongestArticleTitle(){
        int tempLongest = -1;
        String titleArticleLongest = null;
        int countWords = 0;
        int locationMainTitles = 0;
        Map<String, Integer> wordInMako1 = new HashMap<>();
        Document web;
        try {
            Document website = Jsoup.connect(this.rootWebsiteUrl).get();
            Elements titles = website.getElementsByClass("headLine");
            Elements titles6 = titles.parents();
            Elements titles5;
            Element wordInWebsite;
            String link1;
            while (locationMainTitles < 34) {
                wordInWebsite = titles6.get(locationMainTitles).getElementsByTag("a").get(0);
                link1 = wordInWebsite.attr("href");
                if (locationMainTitles == 0) {
                    locationMainTitles = 16;
                }
                locationMainTitles = locationMainTitles + 4;
                if (link1.charAt(0) == 'h')
                    web = Jsoup.connect(link1).get();
                else
                    web = Jsoup.connect(this.rootWebsiteUrl + link1).get();
                titles5 = web.getElementsByTag("p");
                for (Element element : titles5) {
                    String[] s = element.text().split(" ");
                    for (String value : s) {
                        countWords++;
                        if (wordInMako1.containsKey(value)) {
                            int y = wordInMako1.get(value) + 1;
                            wordInMako1.put(value, y);
                        } else {
                            wordInMako1.put(value, 1);
                        }
                    }
                }
                if(countWords > tempLongest){
                    tempLongest = countWords;
                    titleArticleLongest = wordInWebsite.text();
                    countWords = 0;
                }
            }
            Element wordInWebsite1;
            Elements titles1 = website.getElementsByClass("element");
            for (int o = 9; o < titles1.size(); o++) {
                wordInWebsite1 = titles1.get(o).getElementsByTag("a").get(0);
                String linked = wordInWebsite1.attr("href");
                try {
                    if (linked.charAt(0) == 'h')
                        web = Jsoup.connect(linked).get();
                    else
                        web = Jsoup.connect(this.rootWebsiteUrl + linked).get();
                titles5 = web.getElementsByTag("p");
                for (Element element : titles5) {
                    String[] s = element.text().split(" ");
                    for (String value : s) {
                        countWords++;
                        if (wordInMako1.containsKey(value)) {
                            int y = wordInMako1.get(value) + 1;
                            wordInMako1.put(value, y);
                        } else {
                            wordInMako1.put(value, 1);
                        }
                    }
                }
                if(countWords > tempLongest){
                    tempLongest = countWords;
                    titleArticleLongest = wordInWebsite1.text();
                    countWords = 0;
                }
                }catch (StringIndexOutOfBoundsException ignored){}
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return titleArticleLongest;
    }

    public Map<String, Integer> getWordsStatistics() {
        int locationMainTitles = 0;
        Map<String, Integer> wordInMako1 = new HashMap<>();
        Document web;
        try {
            Document website = Jsoup.connect(this.rootWebsiteUrl).get();
            Elements titles = website.getElementsByClass("headLine");
            Elements linkMainTitles = titles.parents();
            Elements wordsInWebsite;
            Element enterLink;
            String link1;
            while (locationMainTitles < 34) {
                enterLink = linkMainTitles.get(locationMainTitles).getElementsByTag("a").get(0);
                link1 = enterLink.attr("href");
                if (locationMainTitles == 0) {
                    locationMainTitles = 16;
                }
                locationMainTitles = locationMainTitles + 4;
                if (link1.charAt(0) == 'h')
                    web = Jsoup.connect(link1).get();
                else
                    web = Jsoup.connect(this.rootWebsiteUrl + link1).get();
                wordsInWebsite = web.getElementsByTag("p");
                for (Element element : wordsInWebsite) {
                    String[] s = element.text().split(" ");
                    for (String value : s) {
                        if (wordInMako1.containsKey(value)) {
                            int y = wordInMako1.get(value) + 1;
                            wordInMako1.put(value, y);
                        } else {
                            wordInMako1.put(value, 1);
                        }
                    }
                }
            }
            Element link;
            Elements titles1 = website.getElementsByClass("element");
            for (int o = 9; o < titles1.size(); o++) {
                link = titles1.get(o).getElementsByTag("a").get(0);
                String linked = link.attr("href");
                try{
                if (linked.charAt(0) == 'h')
                    web = Jsoup.connect(linked).get();
                else
                    web = Jsoup.connect(this.rootWebsiteUrl + linked).get();
                wordsInWebsite = web.getElementsByTag("p");
                for (Element element : wordsInWebsite) {
                    String[] s = element.text().split(" ");
                    for (String value : s) {
                        if (wordInMako1.containsKey(value)) {
                            int y = wordInMako1.get(value) + 1;
                            wordInMako1.put(value, y);
                        } else {
                            wordInMako1.put(value, 1);
                        }
                    }
                }
                }catch (StringIndexOutOfBoundsException ignored){}
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return wordInMako1;
    }
}


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.HashMap;
import java.util.Map;

public class YnetRobot extends BaseRobot {

    public YnetRobot(String rootWebsiteUrl) {
        super(rootWebsiteUrl);
    }

    public String getRootWebsiteUrl() {
        return rootWebsiteUrl;
    }

    public void setRootWebsiteUrl(String rootWebsiteUrl) {
        this.rootWebsiteUrl = rootWebsiteUrl;
    }

    public  Map<String, Integer> getWordsStatistics(){
        Map<String, Integer> wordInYnet = new HashMap<>();
        try {
            Document website = Jsoup.connect(this.rootWebsiteUrl).get();
            Elements titles = website.getElementsByClass("slotTitle");//slotSubTitle
            Document web;
            for (Element title : titles) {
                try {
                    Element element = title.getElementsByTag("a").get(0);
                    String link = element.attr("href");
                    web = Jsoup.connect(link).get();
                    Elements titles2 = web.getElementsByClass("text_editor_paragraph");
                    for (Element elements : titles2) {
                        String[] wordInWebsite = elements.text().split(" ");
                        for (String value : wordInWebsite) {
                            if (wordInYnet.containsKey(value)) {
                                int y = wordInYnet.get(value) + 1;
                                wordInYnet.put(value, y);
                            } else {
                                wordInYnet.put(value, 1);
                            }
                        }
                    }
                    Element mainTitle = web.getElementsByClass("subTitle").get(0);
                    String[] wordInMainTitle = mainTitle.text().split(" ");
                    for (String value : wordInMainTitle) {
                        if (wordInYnet.containsKey(value)) {
                            int y = wordInYnet.get(value) + 1;
                            wordInYnet.put(value, y);
                        } else {
                            wordInYnet.put(value, 1);
                        }
                    }
                    mainTitle = web.getElementsByClass("mainTitleWrapper").get(0);
                    String[] Subheading = mainTitle.text().split(" ");
                    for (String value : Subheading) {
                        if (wordInYnet.containsKey(value)) {
                            int y = wordInYnet.get(value) + 1;
                            wordInYnet.put(value, y);
                        } else {
                            wordInYnet.put(value, 1);
                        }
                    }
                } catch (Exception ignored) {

                }
            }

        } catch (Exception ignored) {
        }
        return wordInYnet;
    }



    public  int countInArticlesTitles(String text){
        int count = 0;
        try {
            Document website = Jsoup.connect(this.rootWebsiteUrl).get();
            Elements titles = website.getElementsByClass("slotTitle");//slotSubTitle
            Document web;
            for (Element title : titles) {
                try {
                    Element wordInTitles = title.getElementsByTag("a").get(0);
                    String link = wordInTitles.attr("href");
                    web = Jsoup.connect(link).get();
                    Element titles1 = web.getElementsByClass("subTitle").get(0);
                    String subTitle = titles1.text();
                    if (subTitle.contains(text)) {
                        count++;
                    }
                    titles1 = web.getElementsByClass("mainTitleWrapper").get(0);
                    String mainTitle = titles1.text();
                    if (mainTitle.contains(text)) {
                        count++;
                    }
                } catch (Exception ignored) {

                }
            }

        }catch (Exception ignored){
        }
        return count;
    }

    public  String getLongestArticleTitle() {
        int tempLongest = 0;
        String titleArticleLongest = null;
        int count = 0;
        try {
            Document website = Jsoup.connect(this.rootWebsiteUrl).get();
            Elements titles = website.getElementsByClass("slotTitle");//slotSubTitle
            Document web;
            for (Element title : titles) {
                try {
                    Element wordInWebsite = title.getElementsByTag("a").get(0);
                    String link = wordInWebsite.attr("href");
                    web = Jsoup.connect(link).get();
                    Elements titles2 = web.getElementsByClass("text_editor_paragraph");
                    for (Element element : titles2) {
                        String[] s = element.text().split(" ");
                        count = s.length;
                    }
                    if (count > tempLongest) {
                        tempLongest = count;
                        titleArticleLongest = title.text();
                        count = 0;
                    }
                } catch (Exception ignored) {

                }
            }

        } catch (Exception ignored) {
        }
        return titleArticleLongest;
    }
}



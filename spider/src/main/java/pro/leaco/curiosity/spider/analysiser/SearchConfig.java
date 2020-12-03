package pro.leaco.curiosity.spider.analysiser;

public class SearchConfig {
    /**
     * 关键搜索词
     */
    private final String searchWord;

    public SearchConfig(String searchWord) {
        this.searchWord = searchWord;
    }

    public String getSearchWord() {
        return searchWord;
    }
}

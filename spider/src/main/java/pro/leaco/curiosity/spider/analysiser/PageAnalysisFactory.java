package pro.leaco.curiosity.spider.analysiser;

import pro.leaco.curiosity.spider.analysiser.impl.BaiduPageAnalysiser;
import pro.leaco.curiosity.spider.analysiser.impl.SmartPageAnalysiser;
import pro.leaco.curiosity.util.ListUtil;
import us.codecraft.webmagic.Page;

import java.util.List;

public class PageAnalysisFactory {

    List<PageAnalysiser> analysisers;

    PageAnalysiser smartAnalysiser;

    public PageAnalysisFactory(SearchConfig config) {
        this.analysisers = ListUtil.asList(
                new BaiduPageAnalysiser(config)
        );
        this.smartAnalysiser = new SmartPageAnalysiser(config);
    }

    public PageAnalysiser findBestAnalysiser(Page page) {

        for (PageAnalysiser analysiser : analysisers) {
            if (analysiser.support(page)) {
                return analysiser;
            }
        }
        return smartAnalysiser;
    }


}

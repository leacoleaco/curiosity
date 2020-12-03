package pro.leaco.curiosity.spider.analysiser;

import pro.leaco.curiosity.spider.vo.Data;
import us.codecraft.webmagic.Page;

import java.util.List;

public abstract class PageAnalysiser {

    protected final SearchConfig searchConfig;

    protected PageAnalysiser(SearchConfig searchConfig) {
        this.searchConfig = searchConfig;
    }

    /**
     * 分析器是否适用于当前页面
     *
     * @param page
     * @return
     */
    public abstract boolean support(Page page);

    /**
     * 分析当前页面兴趣数据
     *
     * @param page
     * @return
     */
    public abstract List<Data> analysisInterestData(Page page);

    /**
     * 检测下一页地址
     *
     * @param page
     * @return
     */
    public abstract List<String> analysisNextUrl(Page page);

}

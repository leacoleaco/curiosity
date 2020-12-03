package pro.leaco.curiosity.spider.analysiser.impl;

import pro.leaco.curiosity.spider.analysiser.PageAnalysiser;
import pro.leaco.curiosity.spider.analysiser.SearchConfig;
import pro.leaco.curiosity.spider.vo.Data;
import us.codecraft.webmagic.Page;

import java.util.List;

/**
 * 通用智能分析器
 * <p>
 * 使用关键词匹配可能可用的模块
 */
public class SmartPageAnalysiser extends PageAnalysiser {

    public SmartPageAnalysiser(SearchConfig searchConfig) {
        super(searchConfig);
    }

    @Override
    public boolean support(Page page) {
        //支持所有类型网页
        return true;
    }

    @Override
    public List<Data> analysisInterestData(Page page) {
        return null;
    }

    @Override
    public List<String> analysisNextUrl(Page page) {
        return null;
    }
}

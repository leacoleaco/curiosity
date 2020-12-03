package pro.leaco.curiosity.spider.analysiser;

import pro.leaco.curiosity.db.g.service.GDataDto;
import us.codecraft.webmagic.Page;

import java.util.List;

public interface PageAnalysiser {

    /**
     * 分析当前页面兴趣数据
     * @param page
     * @return
     */
    List<GDataDto> analysisInterestData(Page page);

    /**
     * 检测下一页地址
     *
     * @param page
     * @return
     */
    List<String> analysisNextUrl(Page page);

}

package pro.leaco.curiosity.spider.analysiser;

import org.apache.commons.lang3.StringUtils;
import pro.leaco.curiosity.db.g.service.GDataDto;
import pro.leaco.curiosity.spider.vo.Data;
import pro.leaco.curiosity.util.ListUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;
import java.util.stream.Collectors;

public class BaiduPageAnalysiser implements PageAnalysiser {

    @Override
    public List<GDataDto> analysisInterestData(Page page) {
        List<Selectable> nodes = page.getHtml().css(".result").nodes();
        if (nodes == null) {
            return null;
        }
        return nodes.stream()
                .map(s -> this.collectData(s, page.getRequest()))
                .filter(x -> StringUtils.isNotEmpty(x.getTitle()))
                .collect(Collectors.toList());
    }

    private Data collectData(Selectable s, Request request) {
        String title = s.$(".t a").xpath("/a/text()").get();
        String abs = s.$(".c-abstract").xpath("/div/text()").get();
        String data = s.get();
        String fromUrl = request.getUrl();
        String toUrl = s.$(".se_st_footer a").xpath("/a/@href").get();
        Integer ruleId = 0;

        Data result = new Data(title, abs, data, GDataDto.Type.REF, GDataDto.ContentType.TEXT, ruleId, fromUrl);
        result.setToUrl(toUrl);
        result.setPriority(0);
        return result;
    }

    @Override
    public List<String> analysisNextUrl(Page page) {
        String url = page.getHtml().$("#page a:contains(>)", "href").get();
        return ListUtil.asList(url);
    }
}

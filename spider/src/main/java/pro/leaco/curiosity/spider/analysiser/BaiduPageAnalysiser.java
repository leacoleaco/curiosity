package pro.leaco.curiosity.spider.analysiser;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.StringUtils;
import pro.leaco.curiosity.db.g.service.GDataDto;
import pro.leaco.curiosity.util.ListUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;
import java.util.stream.Collectors;

public class BaiduPageAnalysiser implements PageAnalysiser {


    @Override
    public List<GDataDto> analysisPageDatas(Page page) {
        List<Selectable> nodes = page.getHtml().css(".result").nodes();
        if(nodes==null){
            return null;
        }
        return nodes.stream()
                .map(this::collectData)
                .filter(x -> StringUtils.isNotEmpty(x.getTitle()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> analysisNextUrl(Page page) {
        String url = page.getHtml().$("#page a:contains(>)", "href").get();
        return ListUtil.asList(url);
    }

    private GDataDto collectData(Selectable s) {
        String title = s.$(".t a").xpath("/a/text()").get();
        String abs = s.$(".c-abstract").xpath("/div/text()").get();
        String data = s.get();
        String url = s.$(".se_st_footer a").xpath("/a/@href").get();
        Short type = GDataDto.Type.REF.getValue();
        Short contentType= GDataDto.ContentType.TEXT.getValue();
        Integer ruleId = 0;

        return new GDataDto()
                .withTitle(title)
                .withAbs(abs)
                .withData(data)
                .withType(type)
                .withContentType(contentType)
                .withRuleId(ruleId)
                .withUrl(url);
    }
}

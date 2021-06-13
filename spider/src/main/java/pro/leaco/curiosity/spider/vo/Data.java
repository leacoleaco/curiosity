package pro.leaco.curiosity.spider.vo;

import pro.leaco.curiosity.db.g.service.GDataDto;
import pro.leaco.curiosity.spider.analysiser.PageAnalysiser;
import pro.leaco.curiosity.spider.magic.CuriosityRequest;
import us.codecraft.webmagic.Request;

public class Data extends GDataDto {
    public Data(
            String title,
            String abs,
            String data,
            Type type,
            ContentType contentType,
            String url,
            PageAnalysiser analysiser,
            Request request) {
        this.withTitle(title)
                .withAbs(abs)
                .withData(data)
                .withType(type.getValue())
                .withContentType(contentType.getValue())
                .withUrl(url)
                .withPriority(0)
                .withAnalysiser(analysiser.getClass().getSimpleName())
                .withDeep(CuriosityRequest.getDeepValue(request));
    }
}

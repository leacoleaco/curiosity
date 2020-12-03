package pro.leaco.curiosity.spider.vo;

import pro.leaco.curiosity.db.g.service.GDataDto;
import pro.leaco.curiosity.spider.analysiser.PageAnalysiser;

public class Data extends GDataDto {
    public Data(
            String title,
            String abs,
            String data,
            Type type,
            ContentType contentType,
            String url,
            PageAnalysiser analysiser
    ) {
        this.withTitle(title)
                .withAbs(abs)
                .withData(data)
                .withType(type.getValue())
                .withContentType(contentType.getValue())
                .withUrl(url)
                .withPriority(0)
                .withAnalysiser(analysiser.getClass().getSimpleName())
        ;
    }
}

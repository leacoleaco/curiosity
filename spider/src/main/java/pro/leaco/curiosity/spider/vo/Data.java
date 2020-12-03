package pro.leaco.curiosity.spider.vo;

import pro.leaco.curiosity.db.g.service.GDataDto;

public class Data extends GDataDto {
    public Data(
            String title,
            String abs,
            String data,
            Type type,
            ContentType contentType,
            Integer ruleId,
            String url
    ) {
        this.withTitle(title)
                .withAbs(abs)
                .withData(data)
                .withType(type.getValue())
                .withContentType(contentType.getValue())
                .withRuleId(ruleId)
                .withUrl(url)
                .withPriority(0);
    }
}

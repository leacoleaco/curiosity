package pro.leaco.curiosity.spider.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.leaco.curiosity.db.g.dao.GDataDtoMapper;
import pro.leaco.curiosity.db.g.service.GDataDto;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DataService implements Pipeline {

    private static Logger logger = LoggerFactory.getLogger(DataService.class);

    @Resource
    private GDataDtoMapper gDataDtoMapper;

    @Override
    public void process(ResultItems resultItems, Task task) {
        if (resultItems.isSkip()) {
            return;
        }
        List<GDataDto> result = resultItems.get("result");
        if (result == null) {
            return;
        }
        for (GDataDto dto : result) {
            gDataDtoMapper.insert(dto);
            logger.debug("dto {} insert to db!", dto.getTitle());
        }

    }
}

package pro.leaco.curiosity.spider.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pro.leaco.curiosity.spider.SpringBootSpiderApplication;

import javax.annotation.Resource;

@SpringBootTest(classes = SpringBootSpiderApplication.class)
@RunWith(SpringRunner.class)
public class SpiderServiceTest {

    @Resource
    SpiderService spiderService;

    @Test
    public void testGrap() {
        spiderService.grap("spider");
    }

}

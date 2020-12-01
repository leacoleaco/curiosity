package pro.leaco.curiosity.spider.service;

import org.springframework.stereotype.Service;
import pro.leaco.curiosity.spider.processor.GithubRepoPageProcessor;
import us.codecraft.webmagic.Spider;

@Service
public class SpiderService {


    /**
     * 开始抓取网页
     */
    public void grap(){

        Spider.create(new GithubRepoPageProcessor())
                .addUrl("https://github.com/code4craft")
                .thread(5)
                .run();

    }

}


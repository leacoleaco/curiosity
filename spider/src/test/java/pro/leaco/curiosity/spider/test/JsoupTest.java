package pro.leaco.curiosity.spider.test;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.HtmlNode;
import us.codecraft.webmagic.selector.Selectable;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class JsoupTest {

    @Test
    public void test() throws IOException {

        URL resource = JsoupTest.class.getResource("/test.html");
        String htmlText = FileUtils.readFileToString(new File(resource.getPath()), "UTF-8");

        Html html = new Html(htmlText,"http://www.baidu.com");


        Selectable s1 = html.$("#page a:contains(>)");

        System.out.println(s1);

    }
}

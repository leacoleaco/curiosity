package pro.leaco.curiosity.spider.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("/")
public class TestController {

    @RequestMapping()
    @ResponseBody
    public Object index() {
        return "hello world";
    }

}

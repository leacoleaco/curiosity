package pro.leaco.curiosity.spider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("pro.leaco.curiosity.db.**.dao")
@SpringBootApplication (scanBasePackages = {"pro.leaco.curiosity.spider.service"})
public class SpringBootSpiderApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootSpiderApplication.class, args);
    }
}

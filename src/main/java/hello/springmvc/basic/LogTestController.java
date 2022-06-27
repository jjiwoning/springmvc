package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController // 문자를 반환시 문자를 바로 웹으로 쏴준다. -> http message body에 데이터를 넣어서 보냄.
public class LogTestController {

    //private final Logger log = LoggerFactory.getLogger(getClass()); -> slf4j가 알아서 세팅해준다.

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";

        System.out.println("name = " + name);

        //로그를 찍을 때, 레벨을 지정할 수 있다. -> 이 로그는 어떤 상태의 로그이다.
        //아래의 코드는 레벨 순서
        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }
}

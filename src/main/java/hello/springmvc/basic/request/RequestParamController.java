package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {}", username);
        log.info("age = {}", age);

        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge
    ){
        log.info("username = {}", memberName);
        log.info("age = {}", memberAge);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username, // 변수명과 파라미터명을 맞추면 생략 가능.
            @RequestParam int age
    ){
        log.info("username = {}", username);
        log.info("age = {}", age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age){ // 변수명과 요청 파라미터 이름이 맞고 단순 타입일 시 완전 생략도 가능하다.
        log.info("username = {}", username);
        log.info("age = {}", age);
        return "ok";
    }

    /**
     * @RequestParam.required
     * /request-param-required -> username이 없으므로 예외
     *
     * 주의!
     * /request-param-required?username= -> 빈문자로 통과
     *
     * 주의!
     * /request-param-required
     * int age -> null을 int에 입력하는 것은 불가능, 따라서 Integer 변경해야 함(또는 다음에 나오는
    defaultValue 사용)
     int는 기본형 -> null 값 못 들어감, Integer는 객체형 -> null 들어갈 수 있음.
     */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) Integer age){
        //required가 true이면 해당 파라미터 값은 무조건 있어야 한다. -> 없으면 튕겨낸다. + default 세팅 값
        log.info("username = {}", username);
        log.info("age = {}", age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(defaultValue = "guest") String username,
            @RequestParam(defaultValue = "-1") int age){
        //default value가 있기 때문에 required가 굳이 의미가 있지 않다.
        //default value는 빈 문자의 경우 설정한 기본 값이 적용이 된다.
        log.info("username = {}", username);
        log.info("age = {}", age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap){
        log.info("username = {}", paramMap.get("username"));
        log.info("age = {}", paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){
        log.info("HelloData = {}", helloData);
        log.info("username = {}", helloData.getUsername());
        log.info("age = {}", helloData.getAge());

        return "ok";
    }

    // ModelAttribute 생략 가능
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){
        log.info("HelloData = {}", helloData);
        log.info("username = {}", helloData.getUsername());
        log.info("age = {}", helloData.getAge());

        return "ok";
    }
}

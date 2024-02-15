package jpabook.jpashop.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j  // Logger log = LoggerFactory.getLogger(getClass()); 이거랑 같은것
public class HomeController {



    @RequestMapping("/") //첫번째 화면으로 잡힌다.
    public String home(){
        log.info("home controller"); //home controller에 대한게 찍히게 된다.
        return "home"; //home.html로 타임리프 파일을 찾아간다.
    }
}

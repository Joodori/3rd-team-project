package lx.edu.levelup;

import org.slf4j.Logger; // ★★★ org.slf4j.Logger로 import 하세요! ★★★
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;
// ... 다른 import

@Controller
public class HomeController {

    // 클래스 상단에 Logger 객체를 생성합니다.
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        logger.info("Welcome home! The client locale is {}.", locale);

        // ★★★ 확인을 위해 새로운 로그를 추가해 보세요! ★★★
        //logger.info("======= Logback 테스트 성공! =======");
        //logger.warn("======= 경고(WARN) 레벨 테스트 =======");
        //logger.error("======= 에러(ERROR) 레벨 테스트 =======");

        // ... 기존 코드 ...
        return "home";
    }
}
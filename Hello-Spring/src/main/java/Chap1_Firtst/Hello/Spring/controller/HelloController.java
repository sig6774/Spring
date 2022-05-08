package Chap1_Firtst.Hello.Spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")// web에서 /hello라고 들어오면 밑의 메소드를 실행
    public String hello(Model model){
        model.addAttribute("data", "하잉~!");
        return "hello";
//        "resorces/templates안에 hello를 찾아서 return" 해라 라는 뜻
    }
}

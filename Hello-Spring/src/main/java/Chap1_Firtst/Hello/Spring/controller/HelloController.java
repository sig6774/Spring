package Chap1_Firtst.Hello.Spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")// web에서 /hello라고 들어오면 밑의 메소드를 실행
    public String hello(Model model){
        model.addAttribute("data", "하잉~!");
        return "hello";
//        "resorces/templates안에 hello를 찾아서 return" 해라 라는 뜻
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name = "name", required = true) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
        // name이라는 이름의 값을 string으로 받아서 model에 이름이 name이고 값은 문자열로 저장된 값을 model에 넣어줌
        // 넣어준 값은 resources/templates안에 hello-template로 return
        /* 동작방식
           ?뒤에 name= 값을 주면 컨트롤러에서 name이 모델에 담기고 html에서 ${}을 통해 모델의 키값을 바탕으로 값을 출력해줌
           */
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello" + name;
        // responsebody : 응답 바디에 값을 직접 넣어주겠다라는 뜻
        // 만약에 name에 spring을 넣으면 hello spring으로 바뀜
        // http://localhost:8080/hello-string?name=spring!!!!! 적게 되면 return의 name에 spring!!!!!이 바디에 직접 들어가게 됨
        // 그대로 페이지에 들어감
        // 근데 이걸 쓸 일이 거의 없음
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){

        Hello hello = new Hello();
        hello.setName(name);
        return hello;
        // json이라는 방식이며 json은 key : value로 구성된 구조
        // 그래서 페이지에서 key는 name이고 value는 사용자가 직접 지정한 spring!!!!!이 입력됨
        // ResponseBody가 있으면 templates에 들어가서 찾지 않고 그냥 httsMessageConverter를 통해서 변환해서 홈페이지에 던져줌
        // ResponseBody는 객체가 오면 기본으로 json으로 바꿔서 페이지에 응답을 하는게 기본이고 문자면 String으로 응답
    }
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public String setName(String name) {
            this.name = name;
            return name;
        }
        // 단축키 ctrl + enter
    }

}

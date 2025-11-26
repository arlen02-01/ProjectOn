package com.arlen.ProjectOn.web.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class force500{

 @GetMapping("/force-error")
 public String forceError() {
     // 강제로 예외 발생
     throw new RuntimeException("테스트용 500 에러 발생");
 }
}

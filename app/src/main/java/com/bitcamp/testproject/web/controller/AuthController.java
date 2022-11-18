package com.bitcamp.testproject.web.controller;

import java.util.Random;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.bitcamp.testproject.service.EmailService;
import com.bitcamp.testproject.service.MemberService;
import com.bitcamp.testproject.vo.Member;

@Controller
@RequestMapping("/auth/")
public class AuthController {

  @Autowired
  EmailService emailService;

  @Autowired
  MemberService memberService;

  public AuthController(MemberService memberService) {
    System.out.println("AuthController() 호출됨!");
    this.memberService = memberService;
  }

  // 헌식
  @GetMapping("form")
  public String form(@CookieValue(name = "id", defaultValue = "") String id, Model model, HttpServletRequest request)
      throws Exception {

    String referer = request.getHeader("Referer");
    request.getSession().setAttribute("redirectURI", referer);

    model.addAttribute("id", id);
    return "auth/form";
  }

  @PostMapping("login")
  public ModelAndView login(String id, String password, HttpServletResponse response,
      HttpSession session) throws Exception {
    //    , String beforePageURL
    Member member = memberService.get(id, password);

    if (member != null) {
      session.setAttribute("loginMember", member);
    }

    Cookie cookie = new Cookie("id", id);
    if (id == null) {
      cookie.setMaxAge(0);
    } else {
      cookie.setMaxAge(60 * 60 * 24 * 7); // 7일
    }
    response.addCookie(cookie);

    if(member != null) {
      ModelAndView mv = new ModelAndView("redirect:/");
      //      ModelAndView mv = new ModelAndView("redirect:" + beforePageURL);
      mv.addObject("member", member);
      return mv;
    }

    ModelAndView mv = new ModelAndView("auth/loginResult");
    mv.addObject("member", member);
    return mv;
  }


  @GetMapping("findId")
  public String findId() {
    return "auth/findId";
  }

  @GetMapping("findPassword")
  public String findIdPassword() {
    return "auth/findPassword";
  }


  @GetMapping("sendMail")
  public String sendMail() {
    return "auth/sendMail";
  }

  @GetMapping("findById")
  public ModelAndView findById(String name, String email, HttpServletResponse response,
      HttpSession session) throws Exception {

    Member member = memberService.getId(name, email);

    if (member != null) {
      session.setAttribute("findId", member);
    }

    ModelAndView mv = new ModelAndView("auth/findIdResult");
    mv.addObject("member", member);
    return mv;
  }




  @PostMapping("mail/send")
  @ResponseBody
  public String send(String email) {

    Random random = new Random();
    int SecCode = random.nextInt(888888) + 111111;

    emailService.sendSimpleMessage(email, SecCode);
    return Integer.toString(SecCode);
  }


  @GetMapping("findByPassword")
  public ModelAndView findByPassword(String id, String email, String secCode,
      HttpServletResponse response, HttpSession session) throws Exception {

    Member member = memberService.getByPassword(id, email, secCode);

    if (member != null) {
      session.setAttribute("findByPassword", member);
    }

    ModelAndView mv = new ModelAndView("auth/newPassword");
    mv.addObject("member", member);
    return mv;
  }

  @GetMapping("newPassword")
  public String newPassword(String email, String id, Model model) {
    model.addAttribute("email", email);
    model.addAttribute("id", id);
    return "auth/newPassword";
  }

  @GetMapping("logout")
  public String logout(HttpSession session, HttpServletRequest request) throws Exception {

    String beforePageURL = request.getHeader("Referer");
    request.getSession().setAttribute("redirectURI", beforePageURL);


    session.invalidate();
    //    return "redirect:../";
    return "redirect:" + beforePageURL;
  }


  @PostMapping("updatePW")
  public String updatePW(String password, String email, String id, HttpSession session) throws Exception {
    boolean result = memberService.updatePW(password, email, id);

    if (result != false) {
      System.out.println("변경 실패");
    } 
    return "redirect:form";
  }

  @PostMapping("idEmailCheck")
  @ResponseBody
  public String idEmailCheck( String id,String email , HttpSession session) throws Exception {
    Member result = memberService.idEmailCheck(id,email);

    if (result == null) {
      System.out.println("회원 없음");
      return "false";
    } 
    return "true";
  }


  // 헌식 끝

}



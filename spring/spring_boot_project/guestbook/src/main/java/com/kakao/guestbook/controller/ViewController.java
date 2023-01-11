package com.kakao.guestbook.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kakao.guestbook.dto.GuestBookDTO;
import com.kakao.guestbook.dto.PageRequestDTO;
import com.kakao.guestbook.service.GuestBookService;
import com.kakao.guestbook.service.GuestBookServiceImpl;

@Log4j2
@Controller
@RequiredArgsConstructor    // 자동 주입 어노테이션
public class ViewController {
    private final GuestBookService guestBookService;

    @GetMapping("/")
    public String list(){
        log.info("메인 화면....");
        return "/guestbook/list";
    }

    @GetMapping("/guestbook/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        model.addAttribute("result", guestBookService.getList(pageRequestDTO));
    }

    //등록을 위한 메서드
    @GetMapping("/guestbook/register") public void register() {
        log.info("데이터 삽입 요청...");
    }

    @PostMapping( "/guestbook/register")
    public String registerPost(GuestBookDTO dto, RedirectAttributes redirectAttributes){
        log.info("dto..." + dto);
        //새로 추가된 Entity의 번호
        Long gno = guestBookService.register(dto);
        redirectAttributes.addFlashAttribute("msg", gno + " 작성");
        return "redirect:/guestbook/list";
    }
}

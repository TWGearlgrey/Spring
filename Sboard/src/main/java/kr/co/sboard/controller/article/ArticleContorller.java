package kr.co.sboard.controller.article;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.dto.PageRequestDTO;
import kr.co.sboard.dto.PageResponseDTO;
import kr.co.sboard.entity.ArticleEntity;
import kr.co.sboard.service.ArticleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@Controller
public class ArticleContorller {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/article/list")
    public String list(Model model, PageRequestDTO pageRequestDTO) {

        PageResponseDTO pageResponseDTO = articleService.findByParentAndCate(pageRequestDTO);

        log.info("testdebug_ pageResponseDTO pg    : " + pageResponseDTO.getPg());
        log.info("testdebug_ pageResponseDTO size  : " + pageResponseDTO.getSize());
        log.info("testdebug_ pageResponseDTO total : " + pageResponseDTO.getTotal());
        log.info("testdebug_ pageResponseDTO start : " + pageResponseDTO.getStart());
        log.info("testdebug_ pageResponseDTO end   : " + pageResponseDTO.getEnd());
        log.info("testdebug_ pageResponseDTO prev  : " + pageResponseDTO.isPrev());
        log.info("testdebug_ pageResponseDTO next  : " + pageResponseDTO.isNext());

        model.addAttribute(pageResponseDTO);
        //log.info("testdebug : " + articleService.findAll());
        return "article/list";
    }

    @GetMapping("/article/write")
    public String write(PageRequestDTO pageRequestDTO) {
        log.info("testdebug_ cate : " + pageRequestDTO.getCate());

        // model 참조와 param 어노테이션이 생략되어 있음. -> model attribute
        // 따라서 model 선언을 하지 않아도 됨
        return "article/write";
    }

    @PostMapping("/article/write")
    public String write(HttpServletRequest request, ArticleDTO dto) {

        log.info("testdebug_ write DTO : " + dto);

        // regip set
        dto.setRegip(request.getRemoteAddr());

        log.info("testdebug_ write DTO : " + dto);

        // 데이터 전송
        articleService.save(dto);

        return "redirect:/article/list";
    }

}
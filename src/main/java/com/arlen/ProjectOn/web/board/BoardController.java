package com.arlen.ProjectOn.web.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.arlen.ProjectOn.service.BoardService;
import com.arlen.ProjectOn.web.board.dto.PostWriteRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@Controller @RequestMapping("/board") @RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;
	
	@GetMapping
	public String list(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size,
			Model model
			) {
		var pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
		Page<?> posts = boardService.list(pageable);
		model.addAttribute("posts",posts);
		return "board/list";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteGet(@PathVariable(name="id") Long id) {
	    return "redirect:/board/" + id;
	}
	
	@GetMapping("/{id}")
	public String detail(@PathVariable(name = "id") Long id,Model model) {
		model.addAttribute("post", boardService.get(id));
		return "board/detail";
	}
	
	@GetMapping("/write") 
	public String writeForm(Model model) {
		model.addAttribute("form", new PostWriteRequest());
		return "board/write";
	}
	@GetMapping("/edit/{id}") 
	public String updateForm(@PathVariable(name = "id")Long id, Model model) {
	    var post = boardService.get(id);
	    PostWriteRequest form = new PostWriteRequest();
	    form.setTitle(post.getTitle());
	    form.setBody(post.getContent());
	    model.addAttribute("update", post);
	    model.addAttribute("form", form);
		return "board/edit";
	}
	
	@PostMapping("/write") 
	public String writeSubmit(@Valid @ModelAttribute("form") PostWriteRequest form,
			HttpServletRequest req) {
		String author = (String)req.getSession().getAttribute("LOGIN_USER");
		if(author ==null || author.isBlank()) author = "anonymous";
		Long id = boardService.write(form.getTitle(), form.getBody(), author);
		return "redirect:/board/"+id; 
	}
	@PutMapping("/edit/{id}") 
	public String updateSubmit(@Valid @ModelAttribute("form") PostWriteRequest form, 
			@PathVariable(name = "id") Long id,
			HttpServletRequest req) {
		String author = (String)req.getSession().getAttribute("LOGIN_USER");
		if(author ==null || author.isBlank()) author = "anonymous";
		boardService.update(form.getTitle(), form.getBody(), id);
		return "redirect:/board/"+id; 
	}
	@PostMapping("/delete/{id}")
	public String deleteOne(@PathVariable(name="id") Long id,HttpServletRequest req) {
		String author = (String)req.getSession().getAttribute("LOGIN_USER");
		if(author ==null || author.isBlank()) author = "anonymous";
		
		var post = boardService.get(id);
		if(!java.util.Objects.equals(author, post.getAuthor())) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN,"권한이 없습니다.");
		}
		
		boardService.delete(id);
		return "redirect:/board";
	}
}


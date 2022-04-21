package com.javagyeongmin.MVCboard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javagyeongmin.MVCboard.command.BCommand;
import com.javagyeongmin.MVCboard.command.BContentCommand;
import com.javagyeongmin.MVCboard.command.BDeleteCommand;
import com.javagyeongmin.MVCboard.command.BListCommand;
import com.javagyeongmin.MVCboard.command.BModifyCommand;
import com.javagyeongmin.MVCboard.command.BReplyCommand;
import com.javagyeongmin.MVCboard.command.BReplyViewCommand;
import com.javagyeongmin.MVCboard.command.BWriteCommand;

@Controller
public class BController {

	BCommand command = null;
	
	@RequestMapping(value = "/list")
	public String list(Model model) {
		
		command = new BListCommand();
		command.execute(model);
		
		return "list";
	}
	
	@RequestMapping(value = "/write_view")
	public String write_view() {
		
		return "write_view";
	}
	
	@RequestMapping(value = "/content_view")
	public String content_view(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		
		command = new BContentCommand();
		command.execute(model);	
		
		return "content_view";
	}
	
	@RequestMapping(value = "/reply_view")
	public String reply_view(HttpServletRequest request, Model model) { // 전달하기 위해서 Model 필요하다
		
		model.addAttribute("request", request); // 받은 리퀘스트를 이름 정해서 바로 넣어준다
		
		
		command = new BReplyViewCommand();
		command.execute(model);
		
		return "reply_view";
	}
	
	@RequestMapping(value = "/write")
	public String write(HttpServletRequest request, Model model) { // 전달하기 위해서 Model 필요하다
		
		model.addAttribute("request", request); // 받은 리퀘스트를 이름 정해서 바로 넣어준다
		
		
		command = new BWriteCommand();
		command.execute(model);
		
		
		return "redirect:list";
	}
	
	//아아
	@RequestMapping(value = "/modify")
	public String modify(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request); // 받은 리퀘스트를 이름 정해서 바로 넣어준다
		
		
		command = new BModifyCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		command = new BDeleteCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping(value = "/reply")
	public String reply(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request); // 받은 리퀘스트를 이름 정해서 바로 넣어준다
		
		
		command = new BReplyCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
}

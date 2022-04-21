package com.javagyeongmin.MVCboard.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.javagyeongmin.MVCboard.dao.BDao;
import com.javagyeongmin.MVCboard.dto.BDto;

public class BListCommand implements BCommand {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		
		BDao dao = new BDao();
		
		ArrayList<BDto> dtos = dao.list();
		model.addAttribute("list", dtos); // 리퀘스트객체말고 모델로 담아준다 (전에했던건 리퀘스트객체로함)
	}

}

package com.javagyeongmin.MVCboard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.javagyeongmin.MVCboard.dao.BDao;
import com.javagyeongmin.MVCboard.dto.BDto;

public class BReplyCommand implements BCommand {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = model.asMap(); // 모델에 asMap하면 맵 형태로 반환 
		HttpServletRequest request = (HttpServletRequest) map.get("request"); // 모델 안에있는 객체들은 이런식으로 뽑는다?
		
		String bId = request.getParameter("bid");
		String bName = request.getParameter("bname");
		String bTitle = request.getParameter("btitle");
		String bContent = request.getParameter("bcontent");
		String bGroup = request.getParameter("bgroup");
		String bStep = request.getParameter("bstep");
		String bIndent = request.getParameter("bindent");
		
		
		BDao dao = new BDao();

		dao.reply(bId, bName, bTitle, bContent, bGroup, bStep, bIndent);
	}

}

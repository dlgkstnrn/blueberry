package com.blackberry.s20240130103.ykm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blackberry.s20240130103.ykm.model.YkmBoardComm;
import com.blackberry.s20240130103.ykm.model.YkmBoardCommReply;
import com.blackberry.s20240130103.ykm.service.YkmService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class YkmBoardRestController {

	private final YkmService ykmService;
	
	
	/* 댓글 comment */

	// 세션 정보 요청
	@GetMapping("/sessionData")
	public Map<String, Object> getSessionData(HttpServletRequest request) {
		System.out.println("YkmBoardRestController getSessionData start ---*");
		Map<String, Object> sessionData = new HashMap<>();
		sessionData.put("seesion_user_no", request.getSession().getAttribute("user_no"));
	    sessionData.put("seesion_user_nic", request.getSession().getAttribute("user_nic"));
	    sessionData.put("seesion_user_profile", request.getSession().getAttribute("user_profile"));
	    sessionData.put("profile_image_path", "/upload/userImg/");
	    
	    System.out.println("profile_image_path");
	    
		return sessionData;
	}
	
	// 댓글 리스트 조회
	@GetMapping("/comment")
	public List<YkmBoardCommReply> getCommentList(@RequestParam("cboard_no") int cboard_no, HttpServletRequest request) {
		System.out.println("YkmBoardRestController getCommentList start ---*");
		List<YkmBoardCommReply> getCommentList = ykmService.getCommentList(cboard_no);
		System.out.println("YkmBoardRestController getCommentList --> " + getCommentList.size());
				
		return getCommentList;
	}

	// 댓글 등록
	@PostMapping("/comment")
	public int writeComment(@RequestBody YkmBoardCommReply ykmBoardCommReply, HttpServletRequest request) {
		Long user_no = (Long)request.getSession().getAttribute("user_no");
	    ykmBoardCommReply.setUser_no(user_no);
		return ykmService.writeComment(ykmBoardCommReply);
	}

	// 댓글 삭제
	@DeleteMapping("/comment/{creply_no}")
	public int deleteComment(@PathVariable("creply_no") int creply_no) {
		System.out.println("deletemapping creply_no : " + creply_no);
		int result = ykmService.deleteComment(creply_no);
		System.out.println("YkmBoardRestController deleteComment ===> "+result);
		return result;
	}

	// 댓글 수정
	@PutMapping("/comment")
	public int updateComment(@RequestBody YkmBoardCommReply ykmBoardCommReply) {
		return ykmService.updateComment(ykmBoardCommReply);
	}

	// 대댓글 등록
	@PostMapping("/replys")
	public int writeReply(@RequestBody YkmBoardCommReply ykmBoardCommReply, HttpServletRequest request) {
		System.out.println("YkmBoardRestController writeReply start ---*");
		Long user_no = (Long)request.getSession().getAttribute("user_no");
		ykmBoardCommReply.setUser_no(user_no);
		YkmBoardCommReply replyValue = ykmService.getReplyValue(ykmBoardCommReply);
		System.out.println("test : " + replyValue);
		System.out.println("YkmBoardRestController writeReply ykmBoardCommReply:"+ykmBoardCommReply);
		ykmBoardCommReply.setCreply_group(replyValue.getCreply_group());
		ykmBoardCommReply.setCreply_level(replyValue.getCreply_level()+1);
		ykmBoardCommReply.setCreply_indent(replyValue.getCreply_indent()+1);
		int updateResult = ykmService.updateReply(ykmBoardCommReply);
		int result = ykmService.writeReply(ykmBoardCommReply);
		System.out.println("YkmBoardRestController writeReply result : "+result);
		
		return result;
	}
	
	
	/* 모집상태 변경 */
	@PutMapping(value = "/recruitment")
	public int updateRecruitment(@RequestBody YkmBoardComm ykmBoardComm) {
		System.out.println("YkmBoardRestController updateRecruitment start---*");
		int result = ykmService.updateRecruitment(ykmBoardComm);
		System.out.println("YkmBoardRestController updateRecruitment result : " + result);
		return result;
	}
	

}

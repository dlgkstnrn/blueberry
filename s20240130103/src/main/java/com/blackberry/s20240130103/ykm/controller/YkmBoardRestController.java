package com.blackberry.s20240130103.ykm.controller;

import java.util.List;

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
	// 로그인한 사용자의 번호는 ${sessionScope.user_no}로 jsp에서 찾을 수 있음
	@GetMapping("/userNo")
	public Long getUserNo(HttpServletRequest request) {
		System.out.println("YkmBoardRestController getUserNo start ---*");
		Long result = (Long)request.getSession().getAttribute("user_no"); 
		System.out.println("YkmBoardRestController result -->" +result);
		return result;
	}
	
	
	@GetMapping("/comment")
	public List<YkmBoardCommReply> getCommentList(@RequestParam("cboard_no") int cboard_no) {
		System.out.println("YkmBoardRestController getCommentList start ---*");
		List<YkmBoardCommReply> getCommentList = ykmService.getCommentList(cboard_no);
		System.out.println("YkmBoardRestController getCommentList --> " + getCommentList.size());
		
		return getCommentList;
	}

	@PostMapping("/comment")
	public int writeComment(@RequestBody YkmBoardCommReply ykmBoardCommReply, HttpServletRequest request) {
		Long user_no = (Long)request.getSession().getAttribute("user_no");
	    ykmBoardCommReply.setUser_no(user_no);
		return ykmService.writeComment(ykmBoardCommReply);
	}

	@DeleteMapping("/comment/{creply_no}")
	public int deleteComment(@PathVariable("creply_no") int creply_no) {
		System.out.println("deletemapping creply_no : " + creply_no);
		int result = ykmService.deleteComment(creply_no);
		System.out.println("YkmBoardRestController deleteComment ===> "+result);
		return result;
	}

	@PutMapping("/comment")
	public int updateComment(@RequestBody YkmBoardCommReply ykmBoardCommReply) {
		return ykmService.updateComment(ykmBoardCommReply);
	}

	// 대댓글
	@PostMapping("/reply")
	public int writeReply(@RequestBody YkmBoardCommReply ykmBoardCommReply, HttpServletRequest request) {
		System.out.println("YkmBoardRestController writeReply start ---*");
		Long user_no = (Long)request.getSession().getAttribute("user_no");
		ykmBoardCommReply.setUser_no(user_no);
		System.out.println("YkmBoardRestController writeReply ykmBoardCommReply:"+ykmBoardCommReply);
		int result = ykmService.writeReply(ykmBoardCommReply);
		System.out.println("YkmBoardRestController writeReply result : "+result);
		return result;
	}
	
	
	@GetMapping("/reply")
	public List<YkmBoardCommReply> getReplyList(@RequestParam("creply_no") int creply_no) {
		System.out.println("YkmBoardRestController getReplyList start ---*");
		List<YkmBoardCommReply> getReplyList = ykmService.getReplyList(creply_no);
		System.out.println("YkmBoardRestController getReplyList : "+getReplyList);
		return getReplyList;
	}
	
	
	
	
	
	
	
	
	
	
	/* study post */

	@PutMapping(value = "/recruitment")
	public int updateRecruitment(@RequestBody YkmBoardComm ykmBoardComm) {
		System.out.println("YkmBoardRestController updateRecruitment start---*");
		int result = ykmService.updateRecruitment(ykmBoardComm);
		System.out.println("YkmBoardRestController updateRecruitment result : " + result);
		return result;
	}
	
	/*
	 댓글 : comment
	 CommentService, CommentServiceImpl, CommentDao, CommentDaoImpl, CommentController
	 getCommentList
	 getComment
	 writeComment
	 deleteComment
	 updateComment
	 */

}
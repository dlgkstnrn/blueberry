package com.blackberry.s20240130103.lsl.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blackberry.s20240130103.lsl.Service.LslService;
import com.blackberry.s20240130103.lsl.model.LslCommReply;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LslRestController {
	
	private final LslService ls;
	
	@GetMapping("/userno")
	public int boardUserNo(HttpServletRequest request) {
		return (int)request.getSession().getAttribute("user_no");
	}
	
	// 게시글 
	
	
	
	
	
	
	// 댓글
	// 게시판 댓글 리스트
   @GetMapping("/reply")
	public List<LslCommReply> replyBoardFreeAskList(@RequestParam("cboard_no") int cboard_no) {
    	System.out.println("LslRestController replyBoardFreeList Start..");
		List<LslCommReply> replyBoardFreeAskList = ls.replyBoardFreeAskList(cboard_no);
		System.out.println("LslRestController replyBoardFreeList.size() ->" + replyBoardFreeAskList.size());
		 // 댓글 목록에 댓글 번호를 추가
        for (LslCommReply reply : replyBoardFreeAskList) {
            reply.setCreply_no(reply.getCreply_no()); // 댓글 번호를 가져와서 설정
        }
        
        return replyBoardFreeAskList;
	   }
   
    
   //  게시판 댓글 등록 
	@PostMapping("/replys")
	public int insertBoardReply(@RequestBody LslCommReply lslCommReply, HttpServletRequest request) {
		Long user_no = (Long) request.getSession().getAttribute("user_no");
		System.out.println("LslRestController insertBoardReply Start....");
		lslCommReply.setUser_no(user_no);
		int boardFreeAskResult = ls.insertBoardReply(lslCommReply);
		System.out.println("LslRestController boardFreeAskResult ->" + boardFreeAskResult);
		return boardFreeAskResult;
	}
	
	// 게시판 댓글 삭제 
	@PutMapping("/replys")
	public int deleteBoardReply(@RequestParam("creply_no") int creply_no ) {
		System.out.println("LslRestController deleteBoardReply Start....");
		int boardFreeAskResult = ls.deleteBoardReply(creply_no);
		
		return boardFreeAskResult;
	}
	
	
	
	

}
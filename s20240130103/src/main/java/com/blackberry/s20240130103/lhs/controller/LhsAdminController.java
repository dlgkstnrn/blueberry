package com.blackberry.s20240130103.lhs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blackberry.s20240130103.lhs.model.BoardAdmin;
import com.blackberry.s20240130103.lhs.model.BoardComm;
import com.blackberry.s20240130103.lhs.model.Reply;
import com.blackberry.s20240130103.lhs.model.User;
import com.blackberry.s20240130103.lhs.service.AdminService;
import com.blackberry.s20240130103.lhs.service.LhsPaging;
import com.blackberry.s20240130103.lhs.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;

@Controller
@RequiredArgsConstructor
public class LhsAdminController {
	
	private final AdminService adminService;
	private final UserService userService;
	
	@GetMapping("/adminMain")
	public String adminMain(Model model) {
		Map<String, Long> tableCntMap = adminService.selectTablesCnt();
		List<User> deleteRequestUserList = adminService.selectUsersDeleteRequest();
		List<BoardAdmin> boardAdminList = adminService.selectBoardAdminList();
		model.addAttribute("cntMap",tableCntMap);
		model.addAttribute("deleteUserList", deleteRequestUserList);
		model.addAttribute("boardAdminList", boardAdminList);
		return "admin/admin_main";
	}
	
	@GetMapping("userCntListAjax")
	@ResponseBody
	public String listMapUserCnt(){
		List<Map<String, Long>> userJoinCntList = adminService.selectUserJoinCnt();
		Gson gson = new GsonBuilder().setDateFormat("MM-dd").create();
		String jsonstr = gson.toJson(userJoinCntList);
		System.out.println(jsonstr);
		return jsonstr;
	}
	
	@GetMapping("admin_boardList")
	public String adminBoardList(BoardComm board,Model model) {
		int cboardCnt = adminService.selectBoardCommCnt(board);
		LhsPaging paging = new LhsPaging(cboardCnt, board.getCurrentPage());
		board.setStart(paging.getStart());
		board.setEnd(paging.getEnd());
		List<BoardComm> cboardList = adminService.selectBoardCommList(board);
		model.addAttribute("paging", paging);
		model.addAttribute("boardList", cboardList);
		model.addAttribute("searchkind", board.getSearchkind());
		model.addAttribute("searchValue", board.getSearchValue());
		return "admin/admin_boardList";
	}
	
	@GetMapping("admin_cboard_detail")
	public String adminBoardDetail(BoardComm board,Model model) {
		BoardComm detailBoard = adminService.selectBoard(board);
		model.addAttribute("board", detailBoard);
		return "admin/admin_boardDetail";
	}
	
	@GetMapping("admin_boardDelete")
	public String adminBoardDelete(BoardComm board) {
		int result = adminService.deleteBoard(board);
		return "redirect:/admin_boardList";
	}
	
	@GetMapping("admin_users")
	public String adminUserList(User user,Model model) {
		int userCnt = adminService.selectUsersCnt(user);
		LhsPaging paging = new LhsPaging(userCnt, user.getCurrentPage());
		user.setStart(paging.getStart());
		user.setEnd(paging.getEnd());
		List<User> userList = adminService.selectUsersList(user);
		model.addAttribute("paging", paging);
		model.addAttribute("userList", userList);
		model.addAttribute("searchkind", user.getSearchkind());
		model.addAttribute("searchValue", user.getSearchValue());
		return "admin/admin_userList";
	}
	
	@GetMapping("admin_user_detail")
	public String adminUserDetail(User user,Model model) {
		User userdetail = adminService.selectUserDetail(user);
		model.addAttribute("user", userdetail);
		return "admin/admin_userDetail";
	}
	
	@GetMapping("admin_userDelete")
	public String adminUserDelete(User user,Model model) {
		int result = adminService.deleteUser(user);
		return "redirect:/admin_users";
	}
	
	@GetMapping("admin_reply")
	public String adminReplyList(Reply reply,Model model) {
		int replyCnt = adminService.selectReplyCnt(reply);
		LhsPaging paging = new LhsPaging(replyCnt, reply.getCurrentPage());
		reply.setStart(paging.getStart());
		reply.setEnd(paging.getEnd());
		List<Reply> replyList = adminService.selectReplyList(reply);
		model.addAttribute("replyList", replyList);
		model.addAttribute("paging", paging);
		model.addAttribute("searchkind", reply.getSearchkind());
		model.addAttribute("searchValue", reply.getSearchValue());
		return "admin/admin_replyList";
	}
	
	@GetMapping("admin_replyDelte")
	public String adminReplyDelete(Reply reply) {
		int result = adminService.deleteReply(reply);
		return "redirect:/admin_reply";
	}
	
	@GetMapping("adminRegisterForm")
	public String adminRegisterForm() {
		return "admin/admin_AddForm";
	}
	
	@PostMapping("adminRegister")
	public String adminRegister(com.blackberry.s20240130103.lhs.domain.User user) {
		userService.joinUser(user);
		return "redirect:/adminMain";
	}
	
}

package com.blackberry.s20240130103.kdw.controller;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.blackberry.s20240130103.kdw.model.BoardProject;
import com.blackberry.s20240130103.kdw.model.BoardProjectFile;
import com.blackberry.s20240130103.kdw.model.Message;
import com.blackberry.s20240130103.kdw.model.MessageFile;
import com.blackberry.s20240130103.kdw.service.KdwProjectBoardPaging;
import com.blackberry.s20240130103.kdw.service.KdwProjectBoardService;
import com.blackberry.s20240130103.kph.model.KphBoardProject;
import com.blackberry.s20240130103.kph.model.KphUserBoardProject;
import com.blackberry.s20240130103.kph.service.KphProjectService;
import com.blackberry.s20240130103.lhs.model.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class KdwProjectBoardController {
	
	private final KdwProjectBoardService pBoardService;
	private final KphProjectService kphProjectService;
	
	// 프로젝트 공유게시판 리스트
	@GetMapping(value = "boardProject")
	public String getProjectBoardList(HttpServletRequest request, Model model, 
			@RequestParam(name = "currentPage", defaultValue = "1") String currentPage,
			@RequestParam(name = "keyword", required = false) String keyword,
	        @RequestParam(name = "type", defaultValue = "all") String type)  {
		System.out.println("projectBoardController getProjectBoardList Start...");
		
		// 글작성 유저넘버(로그인유저)
		Long userNo = (Long) request.getSession().getAttribute("user_no");
		System.out.println("projectBoardController getProjectBoardList userNo: " +  userNo);
		// 글작성하는 프로젝트 넘버
		Long projectNo = Long.parseLong(request.getParameter("project_no"));
		System.out.println("projectBoardController getProjectBoardList projectNo: " +  projectNo);
		
		// 게시글 수 가져오기
		int totPboardListCnt = 0;
		// 검색한 게시글 개수
	    if (keyword != null && !keyword.isEmpty()) {
	    	totPboardListCnt = pBoardService.searchPboardListCnt(userNo, projectNo, keyword, type);
	    } else {
	    	totPboardListCnt = pBoardService.totPboardListCnt(userNo, projectNo);
	    }
	    
	    // 페이징 처리
		KdwProjectBoardPaging page = new KdwProjectBoardPaging(totPboardListCnt, currentPage);
		
		List<BoardProject> pboardList;
		// 검색한 게시글 리스트
	    if (keyword != null && !keyword.isEmpty()) {
	    	pboardList = pBoardService.searchProjectBoardList(userNo, projectNo, keyword, type, page.getStart(), page.getEnd());
	    } else {
	    	pboardList = pBoardService.getProjectBoardList(userNo, projectNo, page.getStart(), page.getEnd());
	    }

		model.addAttribute("userNo", userNo);
		model.addAttribute("projectNo", projectNo);
		model.addAttribute("totPboardListCnt", totPboardListCnt);
		model.addAttribute("pboardList", pboardList);
	    model.addAttribute("page", page);
	    model.addAttribute("keyword", keyword);
	    model.addAttribute("type", type);
		
		return "kdw/projectBoard";
	}
	
	// 게시글 쓰기 페이지로 이동(쪽지쓰기 버튼)
	@GetMapping(value = "projectBoardWrite")
	public String pboardWritePage(HttpServletRequest request, Model model) {
		System.out.println("projectBoardController pboardWritePage Start...");
		
		 // 글작성 유저넘버(로그인유저) 
		Long userNo = (Long) request.getSession().getAttribute("user_no");
		System.out.println("projectBoardController pboardWritePage userNo: " + userNo); // 글작성하는 프로젝트 넘버 
		Long projectNo = Long.parseLong(request.getParameter("project_no"));
		System.out.println("projectBoardController pboardWritePage projectNo: " +projectNo);
		 
		model.addAttribute("userNo", userNo);
		model.addAttribute("projectNo", projectNo);
		return "kdw/projectBoardWrite";
	}
	
	/*============== 글작성 & 업로드 ==============*/
    // 글쓰기 - 멀티 업로드(보내기 버튼)
	@ResponseBody
    @PostMapping(value = "writeSave")
    public String writeSave (BoardProject boardProject, @RequestParam("files") MultipartFile[] files, HttpServletRequest request) {

    	System.out.println("projectBoardController writeSave boardProject : " + boardProject);
        try {
    		// 글작성 유저넘버(로그인유저)
    		Long userNo = (Long) request.getSession().getAttribute("user_no");
    		System.out.println("projectBoardController writeSave userNo: " +  userNo);
    		// 글작성하는 프로젝트 넘버
    		Long projectNo = Long.parseLong(request.getParameter("project_no"));
    		System.out.println("projectBoardController writeSave projectNo: " +  projectNo);
    		
			// 업로드 경로 설정
            String path = request.getSession().getServletContext().getRealPath("/upload/boardProjectFile/");
            boardProject.setUser_no(userNo);
            boardProject.setProject_no(projectNo);
            
            pBoardService.writeSave(boardProject, files, path); // 메시지 전송 서비스 호출

            return "writeSave successfully";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("projectBoardController writeSave Exception ->" + e.getMessage());
            return "Error writeSave";
        }
    }
	/*============== 업데이트 ==============*/
	// 업데이트 작성 폼 이동
	@GetMapping(value = "projectBoardUpdate")
	public String detailBoardUpdateForm(HttpServletRequest request, Model model) {
		System.out.println("projectBoardController detailBoardUpdateForm Start.." );
		
		// 게시글 작성 유저넘버(로그인유저)
		Long userNo = (Long) request.getSession().getAttribute("user_no");
		System.out.println("projectBoardController detailBoardUpdateForm userNo: " +  userNo);
		// 게시글 작성 프로젝트 넘버
		Long projectNo = Long.parseLong(request.getParameter("project_no"));
		System.out.println("projectBoardController detailBoardUpdateForm projectNo: " +  projectNo);
		Long pboardNo = Long.parseLong(request.getParameter("pboard_no"));
		System.out.println("projectBoardController detailBoardUpdateForm pboardNo: " +  pboardNo);
		// 게시글 작성 정보
		KphBoardProject kphBoardProject = new KphBoardProject();
        kphBoardProject.setProject_no(projectNo);
        kphBoardProject.setPboard_no(pboardNo);
        KphUserBoardProject board = kphProjectService.getBoardProject(kphBoardProject);
        System.out.println("projectBoardController detailBoardUpdateForm board: " + board);
        
        model.addAttribute("userNo", userNo);
        model.addAttribute("projectNo", projectNo);
        model.addAttribute("pboardNo", pboardNo);
        model.addAttribute("board", board);
        if (board != null) {
            model.addAttribute("uploadFileList", board.getFileList());
        }
        
		return "kdw/projectBoardUpdate";
	}
    
	
    // 글수정 - 멀티 업로드(수정 버튼)
	@ResponseBody
    @PostMapping(value = "updateSave")
    public String updateSave (BoardProject boardProject, @RequestParam("files") MultipartFile[] files, HttpServletRequest request) {

    	System.out.println("projectBoardController updateSave boardProject : " + boardProject);
        try {
    		// 글작성 유저넘버(로그인유저)
    		Long userNo = (Long) request.getSession().getAttribute("user_no");
    		System.out.println("projectBoardController updateSave userNo: " +  userNo);
    		// 글작성하는 프로젝트 넘버
    		Long projectNo = Long.parseLong(request.getParameter("project_no"));
    		System.out.println("projectBoardController updateSave projectNo: " +  projectNo);
    		Long pboardNo = Long.parseLong(request.getParameter("pboard_no"));
    		System.out.println("projectBoardController updateSave pboardNo: " +  pboardNo);
    		
			// 업로드 경로 설정
            String path = request.getSession().getServletContext().getRealPath("/upload/boardProjectFile/");
            boardProject.setUser_no(userNo);
            boardProject.setProject_no(projectNo);
            boardProject.setPboard_no(pboardNo);
            pBoardService.updateSave(boardProject, files, path);

            return " updateSave successfully";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("projectBoardController updateSave Exception ->" + e.getMessage());
            return "Error updateSave";
        }
    }
	
	
	
    // 수정 버튼 클릭 시 업로드된 폴더&DB에서 파일 삭제
    @PostMapping(value = "/delete-file")
    public void permanentDeleteFiles(@RequestBody Map<String, List<Long>> requestData,HttpServletRequest request, HttpServletResponse response) {
        List<Long> pboardNos = requestData.get("pboardNo");
        log.info("projectBoardController permanentDeleteFiles start...");
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        System.out.println("permanentDeleteFiles delete request for pboardNos: " + pboardNos);
        try {
            for (Long pboardNo : pboardNos) {
                // 첨부된 파일 목록 조회
                List<BoardProjectFile> files = pBoardService.getBoardProjectFiles(pboardNo);

                // 파일 시스템에서 파일 삭제
                for (BoardProjectFile file : files) {
                    String filePath = request.getSession().getServletContext().getRealPath("/upload/boardProjectFile/") + file.getPboard_file_name();
                    File f = new File(filePath);
                    if (f.exists() && f.delete()) {
                        log.info("File deleted successfully: " + filePath);
                    } else {
                        log.error("Failed to delete the file: " + filePath);
                    }
                }

                // 데이터베이스에서 파일 정보 삭제
                pBoardService.deleteFilesByPboardNo(pboardNo);
            }

            response.getWriter().write("파일들이 성공적으로 삭제되었습니다.");
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            log.error("파일 삭제에 실패했습니다.", e);
        }
    }
	
	
}

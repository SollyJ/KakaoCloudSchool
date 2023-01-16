package com.kakao.board.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kakao.board.dto.ReplyDTO;
import com.kakao.board.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/replies")	// 공통 url 설정
public class ReplyController {
	private final ReplyService replyService;

	// 게시글 번호를 가지고 댓글을 리턴해주는 메서드
	@GetMapping(value="/board/{bno}")
	public ResponseEntity<List<ReplyDTO>> getByBoard(@PathVariable("bno") Long bno) {
		log.info("bno: " + bno);
		return new ResponseEntity<>(replyService.getList(bno), HttpStatus.OK);
	}

	// 댓글 추가 요청 처리
	@PostMapping("")
	public ResponseEntity<Long> register(@RequestBody ReplyDTO replyDTO) {
		log.info(replyDTO);
		Long rno = replyService.register(replyDTO);
		return new ResponseEntity<>(rno, HttpStatus.OK);
	}

	@DeleteMapping("/{rno}")
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno) {
		log.info("Rno: " + rno);
		replyService.remove(rno);
		return new ResponseEntity<>(rno + " 삭제", HttpStatus.OK);
	}

	@PutMapping("/{rno}")
	public ResponseEntity<String> modify(@RequestBody ReplyDTO replyDTO) {
		log.info(replyDTO);
		replyService.modify(replyDTO);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
}

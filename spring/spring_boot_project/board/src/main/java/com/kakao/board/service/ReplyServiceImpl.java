package com.kakao.board.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kakao.board.domain.Board;
import com.kakao.board.domain.Reply;
import com.kakao.board.dto.ReplyDTO;
import com.kakao.board.persistence.ReplyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
	private final ReplyRepository replyRepository;

	@Override
	public Long register(ReplyDTO replyDTO) {
		Reply reply = dtoToEntity(replyDTO);
		replyRepository.save(reply);
		return reply.getRno();
	}

	@Override
	public List<ReplyDTO> getList(Long bno) {
		List<Reply> result = replyRepository.findByBoardOrderByRno(Board.builder().bno(bno).build());
		// result의 내용을 정렬하기 - 수정한 시간의 내림차순
		result.sort(new Comparator<Reply>() {
			@Override
			public int compare(Reply o1, Reply o2) {
				return o1.getModDate().compareTo(o2.getModDate());
			}
		});

		// Reply의 List를 ReplyDTO의 List로 변경
		return result.stream().map(reply -> entityToDTO(reply))
			.collect(Collectors.toList());
	}

	@Override
	public Long modify(ReplyDTO replyDTO) {
		Reply reply = dtoToEntity(replyDTO);
		replyRepository.save(reply);
		return reply.getRno();
	}

	@Override
	public Long remove(Long rno) {
		replyRepository.deleteById(rno);
		return rno;
	}
}

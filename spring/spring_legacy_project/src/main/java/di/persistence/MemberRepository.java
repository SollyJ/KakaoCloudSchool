package di.persistence;

import di.entity.MemberEntity;

public interface MemberRepository {
	// 기본키를 가지고 하나의 데이터를 찾아오는 메서드
	public MemberEntity findById(String id);
}

package di.persistence;

import org.springframework.stereotype.Repository;

import di.entity.MemberEntity;

@Repository
public class MemberRepositoryImpl implements MemberRepository{

	@Override
	public MemberEntity findById(String id) {
		MemberEntity memberEntity = 
				MemberEntity.builder()
							.id("jsl")
							.password("1114")
							.nickname("리리솔리")
							.build();
		return memberEntity;
	}
}

package jp.co.taxis.funsite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.taxis.funsite.entity.MemberEntity;
import jp.co.taxis.funsite.repository.MemberRepository;

@Transactional
@Service
public class LoginService {

	@Autowired
	private MemberRepository memberRepository;

	/**
	 * メールアドレスとパスワードでメンバーを検索
	 * 
	 * @param memberEntity
	 * @return
	 */
	public MemberEntity getBook(MemberEntity memberEntity) {
		return memberRepository.selectByMailAddlessAndPassword(memberEntity.getMailAddress(),
				memberEntity.getPassword());
	}

}

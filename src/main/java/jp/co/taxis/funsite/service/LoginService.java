package jp.co.taxis.funsite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.taxis.funsite.entity.MemberEntity;
import jp.co.taxis.funsite.exception.ApplicationException;
import jp.co.taxis.funsite.repository.MemberRepository;

@Transactional
@Service
public class LoginService {

	@Autowired
	private MemberRepository memberRepository;

	/**
	 * メールアドレスとパスワードでメンバーを検索 存在しない場合はエラー
	 * 
	 * @param memberEntity
	 * @return
	 */
	public MemberEntity getMember(MemberEntity memberEntity) {
		MemberEntity returnntity = memberRepository.selectByMailAddlessAndPassword(memberEntity.getMailAddress(),
				memberEntity.getPassword());

		if (returnntity == null)
			throw new ApplicationException("login.error");

		return returnntity;
	}

}

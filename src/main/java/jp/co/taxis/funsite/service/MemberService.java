package jp.co.taxis.funsite.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.taxis.funsite.entity.MemberEntity;
import jp.co.taxis.funsite.exception.ApplicationException;
import jp.co.taxis.funsite.repository.MemberRepository;

@Transactional
@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;

	public List<MemberEntity> selectAll() {
		List<MemberEntity> memberList = memberRepository.findAll();
		return memberList;
	}

	public MemberEntity insert(MemberEntity member) {// 入力されたメアドと会員名がDBにすでに存在する場合エラー

		MemberEntity returnMailAddress = memberRepository.selectByMailAddless(member.getMailAddress());

		if (returnMailAddress != null) {
			throw new ApplicationException("register_mail.error");
		}	
		
		MemberEntity returnName = memberRepository.selectByDisplayName(member.getDisplayName());
		
		if(returnName != null) {
			throw new ApplicationException("register_name.error");
			
		}

		member.setInvalidFlg(false);
		member.setVersion(1);
		MemberEntity resultMember = memberRepository.save(member);
		return resultMember;
	}
	
	public MemberEntity selectById(int id) {
		MemberEntity member = memberRepository.findById(id).orElse(null);
		return member;
	}

}

package jp.co.taxis.funsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.taxis.funsite.entity.MemberEntity;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {

	@Query("SELECT member FROM MemberEntity member WHERE member.mailAddress = :mailaddress AND member.password = :password ")
	public MemberEntity selectByMailAddlessAndPassword(@Param("mailaddress") String mailaddress,
			@Param("password") String password);

}

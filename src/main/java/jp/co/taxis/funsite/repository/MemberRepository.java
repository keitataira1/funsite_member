package jp.co.taxis.funsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.taxis.funsite.entity.MemberEntity;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {

	@Query(nativeQuery = true, value = "SELECT * FROM MemberEntity m WHERE m.mailAddress = :mailaddress AND m.password = :password AND m.invalidFlg = 0")
	public MemberEntity selectByMailAddlessAndPassword(@Param("mailaddress") String mailaddress,
			@Param("password") String password);

}

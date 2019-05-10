package com.coderlook.tgs.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.coderlook.tgs.dto.SstUserAccount;

public interface SstUserAccountRepository extends CrudRepository<SstUserAccount, Integer> {

	List<SstUserAccount> findByStatus(String status);

	List<SstUserAccount> findBySstUserId(int id);

	List<SstUserAccount> findBySstUserIdAndStatus(int id, String status);

	SstUserAccount findBySstAccountLoginIdAndSstUserId(String loginid, int userId);

	SstUserAccount findBySstAccountLoginId(String loginId);

	SstUserAccount findBySstAccountLoginIdAndSstUserIdAndStatus(String loginId, int userId, String active);

	List<SstUserAccount> findBySstAccountLoginIdAndStatus(String loginId, String active);

}

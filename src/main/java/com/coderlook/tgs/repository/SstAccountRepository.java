package com.coderlook.tgs.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.coderlook.tgs.dto.SstAccount;

public interface SstAccountRepository extends CrudRepository<SstAccount, String> {

	List<SstAccount> findByStatus(String status);

	List<SstAccount> findByGeoLocTypeAndGeoLocCode(String locType, String locCode);

	SstAccount findByLoginId(String id);

	List<SstAccount> findByLoginIdStartingWith(String id);

	List<SstAccount> findByUserTypeId(short id);

	SstAccount findByEmail(String email);

	List<SstAccount> findByGeoLocCodeAndGeoLocTypeAndStatus(String code, String type, String active);

	List<SstAccount> findByStatusAndUserTypeId(String status, Short id);

}

package com.coderlook.tgs.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.coderlook.tgs.dto.TargetConfirmationHistory;

public interface TargetConfirmationHistoryRepository extends CrudRepository<TargetConfirmationHistory, Long> {

	List<TargetConfirmationHistory> findByInputMonth(String inputMonth);
}

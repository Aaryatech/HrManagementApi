package com.ats.hrmgt.claim.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.hrmgt.model.ClaimStructureDetail;

public interface ClaimStructureDetailRepo  extends JpaRepository<ClaimStructureDetail, Integer>{

	List<ClaimStructureDetail> findByClmStructHeadIdAndDelStatus(int headId, int i);
	

}

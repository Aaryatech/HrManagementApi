package com.ats.hrmgt.claim.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.hrmgt.model.ClaimProof;

public interface ClaimProofRepo extends JpaRepository<ClaimProof, Integer> {

	List<ClaimProof> findByClaimIdAndDelStatus(int claimId, int i);

}

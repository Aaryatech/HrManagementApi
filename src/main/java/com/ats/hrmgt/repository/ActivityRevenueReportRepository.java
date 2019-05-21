package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.ActivityRevenueReport;

public interface ActivityRevenueReportRepository extends JpaRepository<ActivityRevenueReport, Integer>{

	@Query(value = "select ph.project_type_id , sum(ph.ex_var1) as revenue ,coalesce((select sum(pallot_daily_hrs*(DATEDIFF ( pallot_todt,pallot_fromdt)+1)) "
			+ "from project_allotment,project_header  where project_header.project_type_id =ph.project_type_id and project_header.project_id=project_allotment.project_id),0)"
			+ " as resource_cost,pt.project_type_title,pt.project_type_title_short from project_header ph,project_type pt where pt.project_type_id=ph.project_type_id and "
			+ "ph.company_id=:compId group by ph.project_type_id ", nativeQuery = true)
	List<ActivityRevenueReport> revenueReportProjectCategoryWise(@Param("compId") int compId);

}

package com.repository;

import com.model.IncidentReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IrRepository extends JpaRepository<IncidentReport,Long> {

    public IncidentReport getIncidentReportByTitle(String title);
}

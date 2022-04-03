package com.service;


import com.dao.BaseRepository;
import com.model.IncidentReport;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class IncidentReportService extends BaseService{

    @Autowired
    protected BaseRepository repository;

    public IncidentReport saveIncidentReport(IncidentReport report) {
        IncidentReport incident = repository.saveOrUpdate(report);
        return incident;
    }

    public IncidentReport findIncidentById(Long id) {
        validateId(id);
        IncidentReport incidentReport = repository.findById(IncidentReport.class, id);
        validateObject(incidentReport, "incidentReport",id);
        return incidentReport;
    }

    public void updateIncidentReport(IncidentReport newIR) {
        validateId(newIR.getId());
        IncidentReport oldIR = repository.findById(IncidentReport.class, newIR.getId());
        validateObject(oldIR, "incidentReport",newIR.getId());
        compareAndUpdate(newIR,oldIR);
    }



    public void deleteReport(Long id) {
        validateId(id);
        IncidentReport incidentReport = repository.findById(IncidentReport.class, id);
        validateObject(incidentReport, IncidentReport.class.getName(),id);
        repository.delete(incidentReport);

    }



    public List<IncidentReport> findAllIncidentReport() {
        List<IncidentReport> all = repository.findAll(IncidentReport.class.getName());
        return all;
    }


    private void compareAndUpdate(IncidentReport newIR, IncidentReport oldIR) {
        if(newIR.getAssignedUser()!=null && StringUtils.isNotEmpty(newIR.getAssignedUser().getUsername())){

           }
        if(newIR.getStatus()!=null){
            oldIR.setStatus(newIR.getStatus());
        }
        if(StringUtils.isNotEmpty(newIR.getTitle())){
            oldIR.setTitle(newIR.getTitle());
        }
        repository.saveOrUpdate(oldIR);
    }
}

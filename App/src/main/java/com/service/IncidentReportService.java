package com.service;


import com.ExceptionHandling.AppExceptions;
import com.enums.ReportStatus;
import com.repository.BaseRepository;
import com.model.IncidentReport;
import com.repository.IrRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidentReportService extends BaseService{

    @Autowired
    private IrRepository irRepository;
    @Autowired
    private BaseRepository repository;
    @Autowired
    private UserService userService;

    public IncidentReport saveIncidentReport(IncidentReport report) {
        validateAndUpdate(report);
        IncidentReport incident = repository.saveOrUpdate(report);
        return incident;
    }

    private void validateAndUpdate(IncidentReport report) {
        if(report.getStatus()==null){
            report.setStatus(ReportStatus.New);
        }
        userService.validateAndGetUser(report.getAssignee());
        report.setCreatedBy(getLoggedInUser());
        report.setUpdatedBy(getLoggedInUser());
    }



    public void updateIncidentReport(IncidentReport newIR) {
        IncidentReport oldIR=null;
        if(newIR.getId()!=null){
            oldIR = validateAndGetReportById(newIR.getId());
        }else if(StringUtils.isNotEmpty(newIR.getTitle())){
            oldIR = validateAndGetReportByTitle(newIR.getTitle());
        }else{
            throw new AppExceptions("Need either id or title to update Incident Report");
        }

        compareAndUpdate(newIR,oldIR);
        repository.saveOrUpdate(oldIR);
    }

    public IncidentReport validateAndGetReportByTitle(String title) {
        IncidentReport incidentReportByTitle = null;
        if(StringUtils.isNotEmpty(title)) {
            incidentReportByTitle=irRepository.getIncidentReportByTitle(title);
        }
        if(incidentReportByTitle==null){
            throw new AppExceptions("IncidentReport Non found","with tile :-"+title);
        }
        return incidentReportByTitle;
    }


    public void deleteReport(Long id) {
        IncidentReport incidentReport = validateAndGetReportById(id);
        repository.delete(incidentReport);

    }

    public IncidentReport validateAndGetReportById(Long id) {
        IncidentReport incidentReport=null;
        if(id!=null) {
            incidentReport = repository.findById(IncidentReport.class, id);
        }
        if (incidentReport==null) {
            throw new AppExceptions("IncidentReport Not found "+id);
        }
        return incidentReport;
    }


    public List<IncidentReport> findAllIncidentReport() {
        List<IncidentReport> all = repository.findAll(IncidentReport.class.getName());
        return all;
    }


    private void compareAndUpdate(IncidentReport newIR, IncidentReport oldIR) {
       boolean assignee= userService.compareWithLoggedInUser(oldIR.getAssignee());
       boolean creator=userService.compareWithLoggedInUser(oldIR.getCreatedBy());
       if(!creator && !assignee){
           throw new AppExceptions("Cannot update", "Only assignee/creator can update the Report");
       }
       if(creator || assignee) {
           if (newIR.getStatus() != null && oldIR.getStatus() != null && !newIR.getStatus().equals(oldIR.getStatus())) {
               if (!assignee) {
                   throw new AppExceptions("Cannot update", "Only assignee can update the Status of the Report");
               }
               oldIR.setStatus(newIR.getStatus());
           }
           if (StringUtils.isNotEmpty(newIR.getTitle())) {
               oldIR.setTitle(newIR.getTitle());
           }

           if (StringUtils.isNotEmpty(newIR.getAssignee())) {
               userService.validateAndGetUser(newIR.getAssignee());
               oldIR.setTitle(newIR.getAssignee());
           }
       }

    }
}

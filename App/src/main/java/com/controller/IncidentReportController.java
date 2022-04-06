package com.controller;


import com.constants.StatusConstants;
import com.dto.request.IncidentReportVO;
import com.dto.response.GenericResponse;
import com.model.IncidentReport;
import com.service.IncidentReportService;
import com.service.mapper.IncidentReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/IncidentReport")
public class IncidentReportController {


    @Autowired
    private IncidentReportService reportService;
    @Autowired
    private IncidentReportMapper mapper;

    @GetMapping
    public ResponseEntity<GenericResponse> getAllReport(){
        List<IncidentReport> reportList = reportService.findAllIncidentReport();
        List<IncidentReportVO> incidentReportVOS = mapper.toDtoList(reportList);
        return ResponseEntity.ok(new GenericResponse(StatusConstants.ok,"Incident Report List",incidentReportVOS));
    }

    @GetMapping("/{irId}")
    public ResponseEntity<GenericResponse> getTheReport(@PathVariable Long irId){
        IncidentReport report = reportService.validateAndGetReportById(irId);
        IncidentReportVO incidentReportVO = mapper.toDto(report);
        return ResponseEntity.ok(new GenericResponse(StatusConstants.ok,"Incident Report",incidentReportVO));
    }

    @PostMapping
    public ResponseEntity<GenericResponse> saveReport(@Valid @RequestBody IncidentReportVO report) {
        IncidentReport incidentReport = mapper.toEntity(report);
        IncidentReport savedReport= reportService.saveIncidentReport(incidentReport);
        return ResponseEntity.ok(new GenericResponse(StatusConstants.ok,"Incident Report saved",savedReport.getId()));
    }



    @PutMapping
    public ResponseEntity<GenericResponse> updateReport(@RequestBody IncidentReportVO report){
        IncidentReport incidentReport = mapper.toEntity(report);
        reportService.updateIncidentReport(incidentReport);
        return ResponseEntity.ok(new GenericResponse(StatusConstants.ok,"Incident Report updated",null));
    }

    @DeleteMapping("/{irId}")
    public ResponseEntity<GenericResponse> deleteIR(@PathVariable Long irId){
        reportService.deleteReport(irId);
        return ResponseEntity.ok(new GenericResponse(StatusConstants.ok,"Incident Report deleted",null));
    }






}

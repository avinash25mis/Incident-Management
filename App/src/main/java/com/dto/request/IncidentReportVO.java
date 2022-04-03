package com.dto.request;

import com.enums.ReportStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class IncidentReportVO implements Serializable {

    private Long id;
    @NotNull
    private String title;
    @NotNull
    private ReportStatus status;
    private String assignee;
    private String createdBy;
}

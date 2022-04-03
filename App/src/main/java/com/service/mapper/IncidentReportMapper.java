package com.service.mapper;

import com.dto.request.IncidentReportVO;
import com.model.IncidentReport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IncidentReportMapper{

    IncidentReportMapper INSTANCE = Mappers.getMapper(IncidentReportMapper.class);

    IncidentReportVO toDto(IncidentReport incidentReport);

    IncidentReport toEntity(IncidentReportVO incidentReportVO);

    List<IncidentReportVO> toDtoList(List<IncidentReport> incidentReports);

}

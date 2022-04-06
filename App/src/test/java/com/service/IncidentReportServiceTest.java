package com.service;

import com.ExceptionHandling.AppExceptions;
import com.enums.ReportStatus;
import com.model.IncidentReport;
import com.repository.BaseRepository;
import com.repository.IrRepository;
import com.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest
public class IncidentReportServiceTest {


    @Mock
    private IrRepository irRepository;
    @Mock
    private BaseRepository repository;
    @Mock
    private UserService userService;
    @Mock
    private BaseService baseService;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private IncidentReportService incidentReportService;

    @Spy
    private IncidentReportService incidentReportServiceSpy;

    public static final String title="default Report";
    public static final String username ="default";


    @Test
    @DisplayName("Save incidentReport")
    void saveIncidentReport() {
        IncidentReport dummyIncidentReport = getDummyIncidentReport(title, username);
        when(repository.saveOrUpdate(dummyIncidentReport)).thenReturn(dummyIncidentReport);
        IncidentReport incidentReport = incidentReportService.saveIncidentReport(dummyIncidentReport);
        assertEquals(1l,incidentReport.getId());
    }


    @Test
    @DisplayName("Save incidentReport without title")
    void saveUserWithNoUserName() {
        IncidentReport dummyIncidentReport = getDummyIncidentReport(null, username);
        when(repository.saveOrUpdate(dummyIncidentReport)).thenThrow(TransactionSystemException.class);
        assertThrows(TransactionSystemException.class,()->incidentReportService.saveIncidentReport(dummyIncidentReport));
    }

    @Test
    void updateIncidentReportById() {
        IncidentReport newIr= getDummyIncidentReport(null, username);
        IncidentReport oldIr = getDummyIncidentReport(username, username);
        when(userService.compareWithLoggedInUser(Mockito.anyString())).thenReturn(true);
        when(repository.findById(IncidentReport.class,1l)).thenReturn(oldIr);
       when(incidentReportService.validateAndGetReportById(1l)).thenReturn(oldIr);
        when(repository.saveOrUpdate(oldIr)).thenReturn(oldIr);
        incidentReportService.updateIncidentReport(newIr);

    }

    @Test
    void updateIncidentReportByTitle() {
        IncidentReport newIr= getDummyIncidentReport(title, username);
        newIr.setId(null);
        IncidentReport oldIr = getDummyIncidentReport(title, username);
        when(userService.compareWithLoggedInUser(Mockito.anyString())).thenReturn(true);
        when(irRepository.getIncidentReportByTitle(title)).thenReturn(oldIr);
        when(incidentReportService.validateAndGetReportByTitle(title)).thenReturn(oldIr);
        when(repository.saveOrUpdate(oldIr)).thenReturn(oldIr);
        incidentReportService.updateIncidentReport(newIr);
    }

    @Test
    void deleteReport() {
       IncidentReport incidentReport = getDummyIncidentReport(title, username);
        when( repository.findById(IncidentReport.class,1l)).thenReturn(incidentReport);
        incidentReportService.deleteReport(1l);
    }

    @Test
    void deleteReportReportNotFound() {
        IncidentReport incidentReport = getDummyIncidentReport(title, username);
        when( repository.findById(IncidentReport.class,2l)).thenReturn(incidentReport);
        AppExceptions appExceptions = assertThrows(AppExceptions.class, () -> incidentReportService.deleteReport(1l));
        String expectedMessage="User Not found";
        String actualMessage=appExceptions.getErrorMsg();

    }


    @Test
    void findAllIncidentReport() {
        List<IncidentReport> expectedList=new ArrayList<>();
        IncidentReport incidentReport = getDummyIncidentReport("The Incident", "john");
        expectedList.add(incidentReport);
        when(repository.findAll(IncidentReport.class.getName())).thenReturn(expectedList);
        List<IncidentReport> actualList = incidentReportService.findAllIncidentReport();
        assertEquals(expectedList.size(),actualList.size());
    }


    private IncidentReport getDummyIncidentReport(String title, String assignee){
        IncidentReport obj= new IncidentReport();
        obj.setId(1l);
        obj.setTitle(title);
        obj.setAssignee(assignee);
        obj.setStatus(ReportStatus.New);
        return obj;
    }
}
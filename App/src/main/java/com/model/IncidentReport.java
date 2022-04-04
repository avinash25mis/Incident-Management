package com.model;

import com.enums.ReportStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.model.common.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author avinash.a.mishra
 */
@Entity
@Table(name="incident_report",indexes = {
        @Index(columnList = "title", name = "ir_title_index")
})
@Data
public class IncidentReport extends BaseEntity {


   @Id
   @SequenceGenerator(name = "ir_id", sequenceName = "seq_ir_id", allocationSize = 1)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ir_id")
   private Long id;
   @NotNull
   @Column(unique = true)
   private String title;
   @Enumerated(EnumType.STRING)
   private ReportStatus status;
   private String assignee;


}

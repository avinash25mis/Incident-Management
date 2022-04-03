package com.model;

import com.enums.ReportStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.model.common.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author avinash.a.mishra
 */
@Entity
@Table(name="incident_report")
@Data
public class IncidentReport extends BaseEntity {


   @Id
   @SequenceGenerator(name = "ir_id", sequenceName = "seq_ir_id", allocationSize = 1)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ir_id")
   private Long id;
   @NotNull
   private String title;
   @Enumerated(EnumType.STRING)
   private ReportStatus status;
   @OneToOne
   private User assignedUser;
   @Transient
   private String assignee;


}

package com.model;

import com.model.common.BaseEntity;
import com.model.common.GenericEntity;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author avinash.a.mishra
 */
@Entity
@Table(name="app_user",indexes = {
        @Index(columnList = "username", name = "app_username_index")
})
@Data
public class User extends BaseEntity {


   @Id
   @SequenceGenerator(name = "user_id", sequenceName = "seq_user_id", allocationSize = 1)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id")
   private Long id;
   @NotNull
   @Column(unique = true)
   private String username;
   private String password;
   @NotNull
   private String firstName;
   private String lastName;

   private boolean enabled;



}

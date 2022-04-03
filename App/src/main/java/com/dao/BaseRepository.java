package com.dao;


import com.model.IncidentReport;
import com.model.User;
import com.model.common.GenericEntity;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Repository
@Transactional
public class BaseRepository {

    private static Logger logger = LoggerFactory.getLogger(BaseRepository.class);

    @PersistenceContext
    EntityManager em;



   public <T extends GenericEntity> T saveOrUpdate(T entity){
        T managedEntity=null;
        if(entity!=null && entity.getId()==null) {
            em.persist(entity);
            managedEntity=entity;
       }else{
            managedEntity= em.merge(entity);
       }
        return managedEntity;
    }


    public <T extends GenericEntity> void delete(T entity){

       if(entity!=null && entity.getId()!=null) {
            em.remove(entity);
        }

    }


   public <T extends GenericEntity> List<T> saveOrUpdateList(List<T> entityList){
      List<T> managedEntityList=new ArrayList<>();
       for(T entity:entityList) {
          if (entity.getId() == null) {
              em.persist(entity);
          } else {
              em.merge(entity);

          }
           managedEntityList.add(entity);
      }
       return managedEntityList;
   }


    public <T extends GenericEntity> T findById(Class<T> entity, Long id){
        return em.find(entity,id);

    }



    public List  findAll(String className){
        Query query = em.createQuery("FROM "+className);
        List resultList = query.getResultList();
        if(className.equals("IncidentReport")){
            formatIncidentReport(resultList);
        }
        return resultList;

    }

    private void formatIncidentReport(List<IncidentReport> resultList) {
       for(IncidentReport ir :resultList){
           if(ir.getAssignedUser()!=null){
               ir.setAssignee(ir.getAssignedUser().getUsername());
           }
       }
    }


    public User getUserByUserName(String username){
        List<User> list= new ArrayList<>();
        User user=null;
        Query query = em.createQuery("from User u where u.username=:username");
        query.setParameter("username",username);
        list=query.getResultList();
        if(CollectionUtils.isNotEmpty(list)){
            user=list.get(0);
        }
        return user;
    }


}

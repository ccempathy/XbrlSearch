package com.example.demo.repository;

import com.example.demo.entity.Xbrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2019/8/5 0005.
 */
@Repository
public interface XbrlRepository extends JpaRepository<Xbrl,Long>{

    List<Xbrl> getById(Long id);
//    @Query(value = "select xbrlparsecontent->combineFlag from xbrl where 'xbrlname'= 'xx'")
//    @Query(value = "select xbrlpath from xbrl" , nativeQuery = true)
    List<Xbrl> findByName();

}

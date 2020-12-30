/**
 *
 *                      UJM * EMSE
 *
 *                 * Aleksei PASHININ *
 *
 *                     WMP Project
 *
 */

package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Window;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WindowDao extends JpaRepository<Window, Long> , WindowDaoCustom{

    List<Window> findById(String id);

    @Query("select c from Window c where c.name=:name")
    Window findByName(@Param("name") String name);

    @Query("select c from Window c where c.WindowStatus=:windowStatus")
    Window findByWindowStatus(@Param("windowStatus") String windowStatus);

    @Modifying
    @Query("delete from Window c where c.name = ?1")
    void deleteByName(String name);
}

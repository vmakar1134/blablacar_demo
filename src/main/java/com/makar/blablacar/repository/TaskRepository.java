package com.makar.blablacar.repository;

import com.makar.blablacar.domain.Task;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "select t from Task t join fetch t.assignee a join fetch t.attachments where a.id = :id")
    List<Task> findAllByAssigneeIdFetch(Long id, Sort sort);

    @Query(value = "select t from Task t fetch all properties where t.id = :id")
    Optional<Task> findByIdFetch(Long id);

}

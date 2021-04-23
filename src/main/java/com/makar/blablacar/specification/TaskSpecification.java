package com.makar.blablacar.specification;

import com.makar.blablacar.domain.FilterCriteria;
import com.makar.blablacar.domain.Task;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

import static java.util.Objects.isNull;

@AllArgsConstructor
public class TaskSpecification implements Specification<Task> {

    private FilterCriteria criteria;

    @Override
    public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (isNull(criteria.getFieldName()) || isNull(criteria.getFieldValue())) {
            return null;
        }
        root.fetch("assignee", JoinType.LEFT);
        return criteriaBuilder.equal(root.get(criteria.getFieldName()), criteria.getFieldValue());
    }

}

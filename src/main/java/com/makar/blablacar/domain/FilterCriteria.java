package com.makar.blablacar.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FilterCriteria {

    private String fieldName;

    private String fieldValue;

}

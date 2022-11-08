package edu.depaul.cdm.se452.group2.userAuth.services.validation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Violation {
    private final String fieldName;
    private final String message;
}

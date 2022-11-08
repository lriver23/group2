package edu.depaul.cdm.se452.group2.userAuth.services.validation;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ValidationErrorResponse {
    private List<Violation> violations = new ArrayList<>();
}

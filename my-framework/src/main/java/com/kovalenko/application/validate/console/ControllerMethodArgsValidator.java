package com.kovalenko.application.validate.console;

import com.kovalenko.application.exception.ApplicationException;
import com.kovalenko.application.input.entity.ConsoleRequest;
import com.kovalenko.application.resolve.annotation.PathVariable;
import com.kovalenko.application.resolve.entity.RequestPathMatchResult;
import com.kovalenko.application.validate.Validator;
import com.kovalenko.application.validate.annotation.Constraint;
import com.kovalenko.application.validate.annotation.Messaged;
import com.kovalenko.application.validate.constraint.ConstraintValidator;
import com.kovalenko.application.validate.constraint.annotation.FilePath;
import com.kovalenko.application.validate.constraint.annotation.NotBlank;
import com.kovalenko.application.validate.constraint.annotation.NotEmpty;
import com.kovalenko.application.validate.constraint.annotation.NotNull;
import com.kovalenko.ioc.constant.ErrorMessage;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerMethodArgsValidator implements Validator<RequestPathMatchResult, ConsoleRequest> {

    private static final Class[] VALIDATE_ANNOTATIONS = new Class[]{FilePath.class, NotNull.class, NotEmpty.class, NotBlank.class};

    @Override
    public void validate(RequestPathMatchResult matchResult, ConsoleRequest request) throws ApplicationException {
        for (Parameter parameter: matchResult.getRequestPathMethod().getParameters()) {
            for (Annotation annotation: filterValidateAnnotations(parameter)) {
                processConstraintValidator(request, parameter, annotation);
            }
        }
    }

    private void processConstraintValidator(ConsoleRequest request, Parameter parameter, Annotation annotation) throws ApplicationException {
        ConstraintValidator constraintValidator = initConstraintValidator(annotation);
        String parameterName = parameter.getAnnotation(PathVariable.class).name();
        boolean result = constraintValidator.isValid(request.getRequestParameters().get(parameterName));
        if (!result) { handleValidateResult(annotation, parameterName); }
    }

    private ConstraintValidator initConstraintValidator(Annotation annotation) throws ApplicationException {
        Constraint constraint = annotation.annotationType().getAnnotation(Constraint.class);
        Class constraintValidatorClass = constraint.validatedBy();
        try {
            return (ConstraintValidator) constraintValidatorClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new ApplicationException(ErrorMessage.CANNOT_FIND_CONSTRAINT_VALIDATOR.getValue());
        }
    }

    private void handleValidateResult(Annotation annotation, String parameterName) throws ApplicationException {
        String message = annotation.annotationType().getAnnotation(Messaged.class).message();
        throw new ApplicationException(MessageFormat.format(ErrorMessage.VALIDATE_MESSAGE.getValue(), parameterName, message));
    }

    private List<Annotation> filterValidateAnnotations(Parameter parameter) {
        return Arrays.stream(parameter.getAnnotations())
            .filter(annotation ->
                Arrays.stream(VALIDATE_ANNOTATIONS).anyMatch(va -> va.equals(annotation.annotationType())))
            .collect(Collectors.toList());
    }
}

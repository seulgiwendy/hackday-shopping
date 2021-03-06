package com.naver.wheejuni.domain.validation.annotation;

import com.naver.wheejuni.domain.validation.validators.FiletypeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FiletypeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EligibleFileTypes {

    String message() default "지원 가능한 파일 형식이 아닙니다.";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

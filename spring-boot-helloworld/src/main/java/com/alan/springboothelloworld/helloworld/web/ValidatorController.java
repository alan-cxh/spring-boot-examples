package com.alan.springboothelloworld.helloworld.web;

import com.alan.springboothelloworld.helloworld.entity.NicResult;
import com.alan.springboothelloworld.helloworld.entity.User;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 该方法校验 Springboot-validator 的作用
 */
@RestController
@RequestMapping("validator")
public class ValidatorController {

    @RequestMapping("hello")
    public Object hello(@Valid User user, BindingResult bindingResult){
        if (bindingResult != null && bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            if (errors != null && errors.size() > 0) {
                return NicResult.build(200, true, errors.get(0).getDefaultMessage());
            }
        }
        return null;
    }
}

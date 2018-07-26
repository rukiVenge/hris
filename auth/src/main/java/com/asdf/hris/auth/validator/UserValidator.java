package com.asdf.hris.auth.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.asdf.hris.auth.model.User;
import com.asdf.hris.auth.service.UserService;

/**
 * @author markkelvinpineda
 *
 */

@Component
public class UserValidator implements Validator{
	
	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> aClass) {		
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		User user = (User) o;
		
		ValidationUtils.rejectIfEmpty(errors, "username", "NotEmpty");
		if(user.getUsername().length() < 6 || user.getUsername().length() > 30){
			errors.rejectValue("username", "Size.userForm.username");
		}
		if(userService.findByUsername(user.getUsername()) != null){
			errors.rejectValue("username", "Duplicate.userForm.username");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		if(user.getPassword().length() < 8){
			errors.rejectValue("password", "Size.userForm.password");
		}
		if(!user.getConfirmPassword().equals(user.getPassword())){
			errors.rejectValue("confirmPassword", "Diff.userForm.confirmPassword");
		}
	}

}

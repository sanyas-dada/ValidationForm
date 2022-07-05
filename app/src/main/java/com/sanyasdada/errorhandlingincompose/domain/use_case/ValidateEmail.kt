package com.sanyasdada.errorhandlingincompose.domain.use_case

import android.util.Patterns

class ValidateEmail {
    fun execute(email:String):ValidationResult{
        if(email.isBlank()){
           return ValidationResult(
               successful = false,
               errorMessage = "The Email can't be blank"
           )
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return ValidationResult(
                successful = false,
                errorMessage = "That is not a valid email"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}
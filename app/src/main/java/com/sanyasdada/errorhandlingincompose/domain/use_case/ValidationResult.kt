package com.sanyasdada.errorhandlingincompose.domain.use_case

class ValidationResult(
    val successful:Boolean,
    val errorMessage:String? = null
)
package com.github.sgeorgiev24.leaf.interactor.validator

import javax.inject.Inject

class StringValidators
@Inject
constructor(
    val getEmailErrorOrNull: ValidateEmail,
    val getPasswordErrorOrNull: ValidatePassword,
    val getConfirmPasswordErrorOrNull: ValidateConfirmPassword,
    val getNameErrorOrNull: ValidateUserName,
    val validateCategoryName: ValidateCategoryName
)
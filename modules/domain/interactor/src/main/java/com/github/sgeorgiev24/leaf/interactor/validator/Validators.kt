package com.github.sgeorgiev24.leaf.interactor.validator

import javax.inject.Inject

class Validators
@Inject
constructor(
    val getEmailErrorOrNull: ValidateEmail
)
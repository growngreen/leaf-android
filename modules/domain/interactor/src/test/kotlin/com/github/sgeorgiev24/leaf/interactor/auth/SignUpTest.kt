package com.github.sgeorgiev24.leaf.interactor.auth

import com.github.sgeorgiev24.leaf.interactor.auth.Constants.EMAIL
import com.github.sgeorgiev24.leaf.interactor.auth.Constants.NAME
import com.github.sgeorgiev24.leaf.interactor.auth.Constants.PASSWORD
import com.github.sgeorgiev24.leaf.interactor.auth.Constants.STATE_EVENT
import com.github.sgeorgiev24.leaf.interactor.auth.Constants.STATE_EVENT_ERROR
import com.github.sgeorgiev24.leaf.interactor.validator.Validators
import com.github.sgeorgiev24.leaf.model.state.DataState
import com.github.sgeorgiev24.leaf.repository.auth.AuthRepository
import com.github.sgeorgiev24.leaf.repository.auth.AuthRepositoryImpl
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import javax.inject.Inject

object Constants {
    val EMAIL = "test@test.com"
    val NAME = "Stamat"
    val PASSWORD = "password"
    val NOT_VALID_PASSWORD = "pass"
    val STATE_EVENT = AuthStateEvent.SignUp(EMAIL, NAME, PASSWORD)
    val STATE_EVENT_ERROR = AuthStateEvent.SignUp(EMAIL, NAME, NOT_VALID_PASSWORD)
}

class SignUpTest : FunSpec({
    val authRepository: AuthRepository = FakeAuthRepository()
    val signUp = SignUp(authRepository)

    test("GIVEN user info WHEN sign up THEN result is data state of unit").config {
        signUp(STATE_EVENT) shouldBe DataState(data = Unit)
    }

    test("GIVEN wrong user info WHEN sign up THEN result is data state with empty data").config {
        signUp(STATE_EVENT_ERROR) shouldBe DataState(data = null)
    }
})
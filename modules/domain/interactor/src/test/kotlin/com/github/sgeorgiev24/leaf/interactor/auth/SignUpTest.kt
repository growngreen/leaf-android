package com.github.sgeorgiev24.leaf.interactor.auth

import com.github.sgeorgiev24.leaf.model.state.DataState
import com.github.sgeorgiev24.leaf.repository.auth.AuthRepository
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.*

const val EMAIL = "test@test.com"
const val NAME = "Stamat"
const val PASSWORD = "password"
const val NOT_VALID_PASSWORD = "pass"

class SignUpTest : FunSpec({
    val stateEvent = AuthStateEvent.SignUp(EMAIL, NAME, PASSWORD)
    val stateEventError = AuthStateEvent.SignUp(EMAIL, NAME, NOT_VALID_PASSWORD)

    val authRepository: AuthRepository = FakeAuthRepository()
    val signUp = SignUp(authRepository)

    test("GIVEN user info WHEN sign up THEN result is data state of unit").config {
        signUp(stateEvent) shouldBe DataState(data = Unit)
    }

    test("GIVEN wrong user info WHEN sign up THEN result is data state with empty data").config {
        signUp(stateEventError) shouldBe DataState(data = null)
    }
})
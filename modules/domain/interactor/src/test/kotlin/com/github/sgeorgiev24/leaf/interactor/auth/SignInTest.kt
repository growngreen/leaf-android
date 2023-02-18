package com.github.sgeorgiev24.leaf.interactor.auth

import com.github.sgeorgiev24.leaf.model.state.DataState
import com.github.sgeorgiev24.leaf.repository.auth.AuthRepository
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

internal class SignInTest : FunSpec({
    val stateEvent = AuthStateEvent.SignIn(EMAIL, PASSWORD)
    val stateEventError = AuthStateEvent.SignIn(EMAIL, NOT_VALID_PASSWORD)
    
    val authRepository: AuthRepository = FakeAuthRepository()
    val signIn = SignIn(authRepository)

    test("GIVEN credentials WHEN sign in THEN result is data state of unit").config {
        signIn(stateEvent) shouldBe DataState(data = Unit)
    }

    test("GIVEN wrong credentials WHEN sign in THEN result is data state with empty data").config {
        signIn(stateEventError) shouldBe DataState(data = null)
    }
})
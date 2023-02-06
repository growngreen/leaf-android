package com.github.sgeorgiev24.leaf.network.auth

import com.github.sgeorgiev24.leaf.network.auth.Constants.EMAIL
import com.github.sgeorgiev24.leaf.network.auth.Constants.PASSWORD
import com.github.sgeorgiev24.leaf.network.auth.Constants.WRONG_PASSWORD
import com.github.sgeorgiev24.leaf.network.util.NetworkResult
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk

object Constants {
    val EMAIL = "test@test.com"
    val PASSWORD = "password"
    val WRONG_PASSWORD = "wrongpassword"
}

class AuthDataSourceTest : FunSpec({
    val auth: AuthDataSource = mockk()

    beforeAny {
        coEvery { auth.signIn(EMAIL, PASSWORD) } returns NetworkResult.Success(Unit)
        coEvery { auth.signIn(EMAIL, WRONG_PASSWORD) } returns NetworkResult.Error()
    }

    test("GIVEN valid email and pass WHEN sign in THEN result is success").config(test = {
        auth.signIn(EMAIL, PASSWORD) shouldBe NetworkResult.Success(Unit)
    })

    test("GIVEN email and wrong pass WHEN sign in THEN result is error").config(test = {
        auth.signIn(EMAIL, WRONG_PASSWORD) shouldBe NetworkResult.Error()
    })
})
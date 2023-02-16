package com.github.sgeorgiev24.leaf.network.auth

import android.app.Activity
import com.github.sgeorgiev24.leaf.network.auth.Constants.EMAIL
import com.github.sgeorgiev24.leaf.network.auth.Constants.PASSWORD
import com.github.sgeorgiev24.leaf.network.auth.Constants.WRONG_PASSWORD
import com.github.sgeorgiev24.leaf.network.util.NetworkResult
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import java.util.concurrent.Executor

object Constants {
    val EMAIL = "test@test.com"
    val NAME = "Stamat"
    val PASSWORD = "password"
    val WRONG_PASSWORD = "wrongpassword"
}

class AuthDataSourceTest : FunSpec({
    lateinit var successTask: Task<AuthResult>
    lateinit var failureTask: Task<AuthResult>
    lateinit var signedInUser: Task<AuthResult>

    beforeAny {
        successTask = object : Task<AuthResult>() {
            override fun isComplete(): Boolean = true

            override fun isSuccessful(): Boolean = true

            override fun addOnCompleteListener(onCompleteListener: OnCompleteListener<AuthResult>): Task<AuthResult> {
                onCompleteListener.onComplete(successTask)
                return successTask
            }

            override fun isCanceled() = false

            override fun getResult() = null

            override fun <X : Throwable?> getResult(p0: Class<X>) = null

            override fun getException() = Exception()

            override fun addOnSuccessListener(p0: OnSuccessListener<in AuthResult>) = successTask

            override fun addOnSuccessListener(p0: Executor, p1: OnSuccessListener<in AuthResult>) =
                successTask

            override fun addOnSuccessListener(p0: Activity, p1: OnSuccessListener<in AuthResult>) =
                successTask

            override fun addOnFailureListener(p0: OnFailureListener) = failureTask

            override fun addOnFailureListener(p0: Executor, p1: OnFailureListener) = failureTask

            override fun addOnFailureListener(p0: Activity, p1: OnFailureListener) = failureTask
        }
        failureTask = object : Task<AuthResult>() {
            override fun isComplete(): Boolean = true

            override fun isSuccessful(): Boolean = false

            override fun addOnCompleteListener(onCompleteListener: OnCompleteListener<AuthResult>): Task<AuthResult> {
                onCompleteListener.onComplete(failureTask)
                return failureTask
            }

            override fun isCanceled() = false

            override fun getResult() = null

            override fun <X : Throwable?> getResult(p0: Class<X>) = null

            override fun getException() = Exception()

            override fun addOnSuccessListener(p0: OnSuccessListener<in AuthResult>) = successTask

            override fun addOnSuccessListener(p0: Executor, p1: OnSuccessListener<in AuthResult>) =
                successTask

            override fun addOnSuccessListener(p0: Activity, p1: OnSuccessListener<in AuthResult>) =
                successTask

            override fun addOnFailureListener(p0: OnFailureListener) = failureTask

            override fun addOnFailureListener(p0: Executor, p1: OnFailureListener) = failureTask

            override fun addOnFailureListener(p0: Activity, p1: OnFailureListener) = failureTask
        }
    }

    val firebaseAuth: FirebaseAuth = mockk()
    val auth: AuthDataSource = AuthDataSourceImpl(firebaseAuth)

    test("GIVEN valid email and pass WHEN sign in THEN result is success").config(test = {
        coEvery { firebaseAuth.signInWithEmailAndPassword(EMAIL, PASSWORD) } returns successTask
        auth.signIn(EMAIL, PASSWORD) shouldBe NetworkResult.Success(Unit)
    })

    test("GIVEN email and wrong pass WHEN sign in THEN result is error").config(test = {
        coEvery { firebaseAuth.signInWithEmailAndPassword(EMAIL, WRONG_PASSWORD) } returns failureTask
        auth.signIn(EMAIL, WRONG_PASSWORD) shouldBe NetworkResult.Error()
    })
})
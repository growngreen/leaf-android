package com.github.sgeorgiev24.leaf.network.auth

import com.github.sgeorgiev24.leaf.network.auth.model.toUserDto
import com.github.sgeorgiev24.leaf.network.util.NetworkResult
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import javax.inject.Inject
import kotlin.coroutines.Continuation
import kotlin.coroutines.suspendCoroutine

class AuthDataSourceImpl
@Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthDataSource {

    override suspend fun signUp(
        email: String,
        name: String,
        password: String
    ) = suspendCoroutine { continuation ->
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    updateProfile(
                        task = task,
                        continuation = continuation,
                        name = name
                    )
                    continuation.resumeWith(Result.success(NetworkResult.Success(Unit)))
                } else {
                    continuation.resumeWith(Result.success(NetworkResult.Error(message = task.exception?.message)))
                }
            }
    }

    override suspend fun signIn(email: String, password: String) =
        suspendCoroutine { continuation ->
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        continuation.resumeWith(Result.success(NetworkResult.Success(Unit)))
                    } else {
                        continuation.resumeWith(Result.success(NetworkResult.Error(message = task.exception?.message)))
                    }
                }
        }

    override fun getUser() =
        firebaseAuth.currentUser?.let {
            it.uid
            NetworkResult.Success(it.toUserDto())
        } ?: run {
            NetworkResult.Success(null)
        }

    override fun signOut(): NetworkResult<Unit> {
        firebaseAuth.signOut()
        return NetworkResult.Success(Unit)
    }

    private fun updateProfile(
        task: Task<AuthResult>,
        continuation: Continuation<NetworkResult<Unit>>,
        name: String
    ) {
        task.result?.user?.let { user ->
            val profileChangeRequest = UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build()
            user.updateProfile(profileChangeRequest)
                .addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        continuation.resumeWith(Result.success(NetworkResult.Error(message = task.exception?.message)))
                    }
                }
        }
    }
}
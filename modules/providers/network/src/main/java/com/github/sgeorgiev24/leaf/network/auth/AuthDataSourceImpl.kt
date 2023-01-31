package com.github.sgeorgiev24.leaf.network.auth

// class AuthDataSourceImpl
// @Inject constructor(
//    private val firebaseAuth: FirebaseAuth
// ) : AuthDataSource {
//
//    override suspend fun signUp(
//        email: String,
//        name: String,
//        password: String
//    ) = suspendCoroutine { continuation ->
//        firebaseAuth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    updateProfile(
//                        task = task,
//                        continuation = continuation,
//                        name = name
//                    )
//                    continuation.resumeWith(Result.success(NetworkResult.Success(Unit)))
//                } else {
//                    continuation.resumeWith(Result.success(NetworkResult.Error(message = task.exception?.message)))
//                }
//            }
//    }
//
//    override suspend fun signIn(email: String, password: String) =
//        suspendCoroutine { continuation ->
//            firebaseAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener { task ->
//                    if (task.isSuccessful) {
//                        continuation.resumeWith(Result.success(NetworkResult.Success(Unit)))
//                    } else {
//                        continuation.resumeWith(Result.success(NetworkResult.Error(message = task.exception?.message)))
//                    }
//                }
//        }
//
//    override suspend fun getUser() =
//        suspendCoroutine { continuation ->
//            firebaseAuth.currentUser?.let {
//                continuation.resumeWith(Result.success(NetworkResult.Success(it.toUserDto())))
//            } ?: run {
//                continuation.resumeWith(Result.success(NetworkResult.Success(null)))
//            }
//        }
//
//    private fun updateProfile(
//        task: Task<AuthResult>,
//        continuation: Continuation<NetworkResult<Unit>>,
//        name: String
//    ) {
//        task.result?.user?.let { user ->
//            val profileChangeRequest = UserProfileChangeRequest.Builder()
//                .setDisplayName(name)
//                .build()
//            user.updateProfile(profileChangeRequest)
//                .addOnCompleteListener { task ->
//                    if (!task.isSuccessful) {
//                        continuation.resumeWith(Result.success(NetworkResult.Error(message = task.exception?.message)))
//                    }
//                }
//        }
//    }
// }
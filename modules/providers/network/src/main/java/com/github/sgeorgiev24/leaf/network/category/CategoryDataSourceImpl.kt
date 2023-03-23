package com.github.sgeorgiev24.leaf.network.category

import com.github.sgeorgiev24.leaf.network.util.NetworkResult
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class CategoryDataSourceImpl
@Inject
constructor(
    private val storage: FirebaseStorage
) : CategoryDataSource {

    override suspend fun getCategoryIconsNames() =
        suspendCoroutine { continuation ->
            val result = mutableListOf<String>()

            storage.reference.listAll()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        task.result?.items?.forEach {
                            result.add(it.name)
                        }
                        continuation.resumeWith(Result.success(NetworkResult.Success(result)))
                    } else {
                        continuation.resumeWith(Result.success(NetworkResult.Error(message = task.exception?.message)))
                    }
                }
        }
}
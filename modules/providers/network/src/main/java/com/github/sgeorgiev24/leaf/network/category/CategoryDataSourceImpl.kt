package com.github.sgeorgiev24.leaf.network.category

import com.github.sgeorgiev24.leaf.network.category.model.CategoryDto
import com.github.sgeorgiev24.leaf.network.category.model.toFirestoreDocument
import com.github.sgeorgiev24.leaf.network.util.NetworkResult
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

private const val CATEGORIES_COLLECTION_PATH = "categories"

class CategoryDataSourceImpl
@Inject
constructor(
    private val storage: FirebaseStorage,
    private val firestore: FirebaseFirestore
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

    override suspend fun addCategory(category: CategoryDto) =
        suspendCoroutine { continuation ->
            firestore.collection(CATEGORIES_COLLECTION_PATH)
                .add(category.toFirestoreDocument())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        continuation.resumeWith(Result.success(NetworkResult.Success(Unit)))
                    } else {
                        continuation.resumeWith(Result.success(NetworkResult.Error(message = task.exception?.message)))
                    }
                }
        }

    override suspend fun getCategories(userId: String) =
        suspendCoroutine { continuation ->
            val result = mutableListOf<CategoryDto>()

            firestore.collection(CATEGORIES_COLLECTION_PATH)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        task.result?.forEach {
                            val category = it.toObject(CategoryDto::class.java)
                            if (category.userId == userId) {
                                result.add(category)
                            }
                        }
                        continuation.resumeWith(Result.success(NetworkResult.Success(result)))
                    } else {
                        continuation.resumeWith(Result.success(NetworkResult.Error(message = task.exception?.message)))
                    }
                }
        }
}
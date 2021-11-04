package com.mota.pebblebee.domain.use_case

import com.mota.pebblebee.common.Resource
import com.mota.pebblebee.data.dto.toUser
import com.mota.pebblebee.domain.model.User
import com.mota.pebblebee.domain.repository.PebblebeeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: PebblebeeRepository
) {

    operator fun invoke(): Flow<Resource<User>> = flow {
        try {
            emit(Resource.Loading())
            val user = repository.getUser().toUser()
            emit(Resource.Success(user))
        } catch (e: HttpException) {
            emit(Resource.Error<User>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<User>(e.localizedMessage ?: "Couldn't reach server"))
        }
    }
}
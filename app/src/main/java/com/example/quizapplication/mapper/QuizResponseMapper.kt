package com.example.quizapplication.mapper

import com.example.quizapplication.manager.AssertManager
import com.example.quizapplication.model.Data
import com.example.quizapplication.utils.CustomResponse
import com.example.quizapplication.utils.LocalException
import com.example.quizapplication.warehouse.Constants.Companion.ERROR_SERVER
import retrofit2.Response

object QuizResponseMapper {

    fun map(quizDataResponse: Response<List<Data>>): CustomResponse<List<Data>, LocalException> {
        return if (quizDataResponse.isSuccessful && quizDataResponse.code() == 200) {
            CustomResponse.Success(quizDataResponse.body() ?: arrayListOf())
        } else {
            CustomResponse.Failure(LocalException(ERROR_SERVER))
        }
    }

    fun mapp(assertManager: AssertManager):CustomResponse<List<Data>?,LocalException>{
        return if(assertManager.loadQuizData().status == true){
            CustomResponse.Success(assertManager.loadQuizData().data)
        } else{
            CustomResponse.Failure(LocalException(ERROR_SERVER))
        }
    }
}
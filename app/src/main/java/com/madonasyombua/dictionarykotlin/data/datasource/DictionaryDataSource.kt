package com.madonasyombua.dictionarykotlin.data.datasource


import com.madonasyombua.dictionarykotlin.data.response.BaseResponse
import com.madonasyombua.dictionarykotlin.data.response.Results
import com.madonasyombua.dictionarykotlin.data.services.Api
//import com.madonasyombua.dictionarykotlin.data.services.MainApi
import com.madonasyombua.dictionarykotlin.data.utils.ERROR_STATUS
import com.madonasyombua.dictionarykotlin.data.utils.ErrorHelper
import com.madonasyombua.dictionarykotlin.data.utils.safeApiCall


/**
 * @author madona
 * The data source class gets the Api's
 */

class DictionaryDataSource(private val api: Api){
    suspend fun  definition(word: String) = safeApiCall(
        call ={getDefinition(word)}
    )

    // get definition
    private suspend fun getDefinition(word: String): Results<BaseResponse> {
        api.getDefinitionAsync(word).await().let {
            if (!it.whenEmpty()) {
                return Results.Success(it)
            }
            return Results.Error(ErrorHelper(ERROR_STATUS.ERR))
        }
    }


}
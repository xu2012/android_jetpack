package com.xu.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * Description:
 * @author: xuyunlong
 * Date: 2020/7/24 10:51
 * @version 2.2
 */
fun <T : BaseModel> BaseViewModel<T>.request(
    block: suspend () -> Unit,
    loadState: MutableLiveData<LoadState>,
    isLoading: Boolean = true,
    tag: String = ""
) {
    viewModelScope.launch {
        if (isLoading) {
            loadState.value = LoadState(StateType.SHOW_DIALOG)
        }
        runCatching {
            block()
        }.onSuccess {
            if (isLoading) {
                loadState.postValue(LoadState(StateType.HIDE_DIALOG))
            }
        }.onFailure {
            if (isLoading) {
                loadState.postValue(LoadState(StateType.HIDE_DIALOG))
                if (it is ApiException) {
                    loadState.postValue(LoadState(StateType.ERROR, it.localizedMessage, it.code!!, tag))
                } else {
                    loadState.postValue(
                        LoadState(
                            StateType.ERROR,
                            message = it.localizedMessage,
                            tag = tag
                        )
                    )
                }
            }

        }
    }
}

fun <T : BaseModel> BaseViewModel<T>.initLoad(
    block: suspend () -> Unit,
    loadState: MutableLiveData<LoadState>
) {
    viewModelScope.launch {
        loadState.value = LoadState(StateType.SHOW_LOADING)
        runCatching {
            block()
        }.onSuccess {
        }.onFailure {
            if (it is ApiException) {
                loadState.postValue(
                    LoadState(
                        StateType.SHOW_ERROR,
                        it.localizedMessage,
                        it.code!!
                    )
                )
            } else {
                loadState.postValue(
                    LoadState(
                        StateType.SHOW_ERROR,
                        message = it.localizedMessage
                    )
                )
            }
        }
    }
}

fun <T> ApiResult<T>.initConvert(loadState: MutableLiveData<LoadState>): T? {
    when (code) {
        "0" -> {
            if (content == null) {
                loadState.postValue(LoadState(StateType.SHOW_EMPTY))
            } else {
                loadState.postValue(LoadState(StateType.SHOW_SUCCESS))
            }
            return content
        }
        else -> {
            throw ApiException(code, error?.message)
        }
    }
}
fun <T> ApiResult<T>.dataConvert(): T? {
    when (code) {
        "0" -> {
            return content
        }
        else -> {
            throw ApiException(code, error?.message)
        }
    }
}
fun <T> ApiResult<T>.dataConvert(loadState: MutableLiveData<LoadState>): T? {
    when (code) {
        "0" -> {
            return content
        }
        else -> {
            throw ApiException(code, error?.message)
        }
    }
}

/*
 * Copyright 2023 Stream.IO, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mvi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.getstream.whatsappclone.data.coroutines.WhileSubscribedOrRetained
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import mvi.state.WhatsAppUserUiState

class WhatsAppCallsViewModel  constructor(
) : ViewModel() {

  val whatsAppUserState: StateFlow<WhatsAppUserUiState> =
    flow<String> {
      delay(2000)
      emit("wocao")

    }
      .flatMapLatest {
        if (it.isNullOrEmpty().not()) {
          flowOf(
            WhatsAppUserUiState.Success(
              it
            )
          )
        } else {
          flowOf(WhatsAppUserUiState.Error)
        }
      }
      .stateIn(
        scope = viewModelScope,
        started = WhileSubscribedOrRetained,
        initialValue = WhatsAppUserUiState.Loading
      )
}

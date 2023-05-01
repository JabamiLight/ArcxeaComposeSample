package mvi.state

public sealed interface WhatsAppUserUiState {
  public object Error : WhatsAppUserUiState

  public object Loading : WhatsAppUserUiState

  public data class Success(
    public val `data`: String,
  ) : WhatsAppUserUiState
}

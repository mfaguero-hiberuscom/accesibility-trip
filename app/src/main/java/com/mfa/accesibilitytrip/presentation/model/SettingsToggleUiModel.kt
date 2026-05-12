package com.mfa.accesibilitytrip.presentation.model

data class SettingsToggleUiModel(
    val id: String,
    val title: String,
    val description: String,
    val enabled: Boolean,
) {
    companion object {
        fun mockList(): List<SettingsToggleUiModel> {
            return listOf(
                SettingsToggleUiModel(
                    id = "dark-mode",
                    title = "Modo oscuro",
                    description = "Cambia entre el tema claro y oscuro de la aplicación.",
                    enabled = false,
                ),
                SettingsToggleUiModel(
                    id = "spoken-updates",
                    title = "Anuncios hablados",
                    description = "Lee cambios importantes del viaje cuando una tarjeta se actualiza o desaparece.",
                    enabled = true,
                ),
                SettingsToggleUiModel(
                    id = "high-contrast",
                    title = "Contraste reforzado",
                    description = "Mantiene una capa visual estable para validar contraste y foco visible.",
                    enabled = false,
                ),
                SettingsToggleUiModel(
                    id = "large-copy",
                    title = "Textos ampliados",
                    description = "Simula una preferencia de contenido más amplio para revisar desbordes y scroll.",
                    enabled = true,
                ),
            )
        }
    }
}

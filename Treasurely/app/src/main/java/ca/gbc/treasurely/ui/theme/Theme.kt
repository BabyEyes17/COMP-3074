package ca.gbc.treasurely.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = CoralPrimary,
    onPrimary = Color.White,

    secondary = PeachSecondary,
    onSecondary = Color.White,

    tertiary = SandTertiary,

    background = BackgroundCream,
    onBackground = TextPrimaryDark,

    surface = SurfaceWhite,
    onSurface = TextPrimaryDark,

    surfaceVariant = SurfaceSoftPeach,
    onSurfaceVariant = TextSecondaryDark,

    error = ErrorRed,
    onError = Color.White
)

@Composable
fun TreasurelyTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography,
        shapes = AppShapes,
        content = content
    )
}

package ca.gbc.treasurely.ui.poi.qr

import android.annotation.SuppressLint
import android.util.Size
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnsafeOptInUsageError")
@Composable
fun QrCodeScannerScreen(
    viewModel: Any,
    onScanned: (String) -> Unit,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current   // ✅ THE FIX

    var cameraProvider by remember { mutableStateOf<ProcessCameraProvider?>(null) }

    // Load camera provider async
    LaunchedEffect(Unit) {
        cameraProvider = ProcessCameraProvider.getInstance(context).get()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Scan QR Code") },
                navigationIcon = {
                    IconButton(onClick = onBack) { Text("←") }
                }
            )
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { ctx ->
                    PreviewView(ctx).apply {
                        layoutParams = FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        scaleType = PreviewView.ScaleType.FILL_CENTER
                    }
                },
                update = { previewView ->
                    cameraProvider?.let {
                        bindCamera(
                            previewView = previewView,
                            cameraProvider = it,
                            lifecycleOwner = lifecycleOwner,
                            onQrDetected = onScanned
                        )
                    }
                }
            )
        }
    }
}

@SuppressLint("UnsafeOptInUsageError")
private fun bindCamera(
    previewView: PreviewView,
    cameraProvider: ProcessCameraProvider,
    lifecycleOwner: LifecycleOwner,
    onQrDetected: (String) -> Unit
) {
    val preview = Preview.Builder().build().apply {
        setSurfaceProvider(previewView.surfaceProvider)
    }

    val analysis = ImageAnalysis.Builder()
        .setTargetResolution(Size(1280, 720))
        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
        .build()

    val scanner = BarcodeScanning.getClient()

    analysis.setAnalyzer(ContextCompat.getMainExecutor(previewView.context)) { imageProxy ->
        val mediaImage = imageProxy.image ?: run {
            imageProxy.close()
            return@setAnalyzer
        }

        val image = InputImage.fromMediaImage(
            mediaImage,
            imageProxy.imageInfo.rotationDegrees
        )

        scanner.process(image)
            .addOnSuccessListener { barcodes ->
                barcodes.firstOrNull()?.rawValue?.let { value ->
                    cameraProvider.unbindAll()
                    onQrDetected(value)
                }
            }
            .addOnCompleteListener { imageProxy.close() }
    }

    try {
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(
            lifecycleOwner,   // ✅ Correct lifecycle owner
            CameraSelector.DEFAULT_BACK_CAMERA,
            preview,
            analysis
        )
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

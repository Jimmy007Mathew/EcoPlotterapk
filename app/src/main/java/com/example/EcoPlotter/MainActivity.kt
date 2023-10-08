package com.example.EcoPlotter

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
var isClicked1 = mutableStateOf(true)
var isClicked2 =  mutableStateOf(false)
//import android.os.Bundle

//import android.webkit.WebSettings
//import android.webkit.WebView
//import android.webkit.WebViewClient

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView = findViewById<WebView>(R.id.webView)
        val webSettings = webView.settings
        webView.webViewClient = WebViewClient()

        val url = intent.getStringExtra("www.spaceappschallenge.org/2023/challenges/explore-a-biodiversity-hotspot-with-imaging-spectroscopy/?tab=resources")
        if (!url.isNullOrBlank()) {
            webView.loadUrl(url)
            webView.settings.javaScriptEnabled = true
            webView.settings.setSupportZoom(true)
            webSettings.useWideViewPort = true
            webSettings.loadWithOverviewMode = true
            webSettings.textZoom = 100
            webSettings.setBuiltInZoomControls(true)
            webSettings.displayZoomControls = false
            webSettings.cacheMode = WebSettings.LOAD_DEFAULT
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            Surface(
                color = MaterialTheme.colorScheme.background
            ) {
                MainScreen()

            }
        }
    }

    data class Recipe(
        val ingredients1: List<Ingredient>,
        val ingredients2: List<Ingredient>
    )

    data class Ingredient(@DrawableRes val image: Int, val title: String, val subtitle: String)

    val Imp = Recipe(
        ingredients1 = listOf(
            Ingredient(R.drawable.aves, "Aves", ""),
            Ingredient(R.drawable.amphibia, "Amphibia", ""),
            Ingredient(R.drawable.reptilia, "Reptilia ", ""),
            Ingredient(R.drawable.mammal, "Mammalia", ""),
            Ingredient(R.drawable.actinopterygii, "Actinopterygii", ""),
            Ingredient(R.drawable.mollusca, "Mollusca", ""),
            Ingredient(R.drawable.arachnida, "Arachnida", ""),
            Ingredient(R.drawable.insecta, "Insecta", ""),
            Ingredient(R.drawable.protozoa, "Protozoa", ""),
        ),
        ingredients2 = listOf(
            Ingredient(R.drawable.plantae, "Plantae", ""),
            Ingredient(R.drawable.fungi, "Fungi", ""),
    )
    )
    private fun openUrlInBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        // Check if a web browser is available on the device
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }


@Composable
fun ClickableImage(imageResource: Int, onClick: () -> Unit) {
    var isClicked by remember { mutableStateOf(false) }
    Image(
        painter = painterResource(id = R.drawable.cape),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
            .height(350.dp)
            .clickable { isClicked = !isClicked
                onClick() }
    )
            if (isClicked) {
                setContentView(R.layout.activity_main)

                val webView = findViewById<WebView>(R.id.webView)
                val webSettings = webView.settings
                webView.webViewClient = WebViewClient()
                webView.loadUrl("https://www.spaceappschallenge.org/2023/challenges/explore-a-biodiversity-hotspot-with-imaging-spectroscopy/?tab=resources")
                webView.settings.javaScriptEnabled = true
                webView.settings.setSupportZoom(true)
                webSettings.useWideViewPort = true
                webSettings.loadWithOverviewMode = true
                // Set the initial zoom level (e.g., 100%)
                webSettings.textZoom = 100
                // Allow the zooming/panning controls to show// Enable zoom controls (pinch-to-zoom and zoom buttons)
                webSettings.setBuiltInZoomControls(true)
                webSettings.displayZoomControls = false
                webSettings.cacheMode = WebSettings.LOAD_DEFAULT // Use app

                fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
                    // Check if the "back" button is pressed
                    if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
                        // Navigate back in the WebView's history
                        webView.goBack()
                        return true // Consumes the event
                    }
                    return super.onKeyDown(keyCode, event)
                }
                fun onConfigurationChanged(newConfig: Configuration) {
                    super.onConfigurationChanged(newConfig)
                    // Handle screen orientation changes here if needed
                }
                }else{
                    openUrlInBrowser("https://www.google.com/")
                }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colorStops = arrayOf(
                        Pair(0.4f, Transparent),
                        Pair(1f, White)
                    )
                )
            )
    )
}
@Preview
@Composable
fun MainScreen() {



    Column (
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .background(White)
    ){
        Box(
            modifier = Modifier
                .height(320.dp)
        ) {
            ClickableImage(R.drawable.cape) {

            }

        }
    Column(modifier = Modifier.fillMaxSize()) {
        Box {
            Image(painter = painterResource(id = R.drawable.text_png), contentDescription = null
            ,modifier = Modifier
                .fillMaxWidth()

                .padding(top = 16.dp)
                .padding(end = 180.dp)
                    .padding(start = 10.dp)

                    )



            Text(
                text = "Explore Cape Floristic Region's rich biodiversity through our app. Utilizing NASA data, discover unique plant species in this UNESCO World Heritage hotspot, contributing to its conservation and appreciation...",
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .padding(top = 60.dp),
                color = Color.Black,
            )}
            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .background(LightGray)
                    .clip(RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .height(44.dp)
            ) {

                TabButton("FAUNA", isClicked1.value, Modifier.weight(1f))
                TabButton("FLORA", isClicked2.value, Modifier.weight(1f))
            }
            Choices()
}
@Composable
fun PageContent(index: Int) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .clip(RoundedCornerShape(8.dp)),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
    ) {
        Text(
            text = "Page $index",
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )}}}}
fun First_Click(){
    isClicked1.value = true
    isClicked2.value = false
}
fun Second_Click(){
    isClicked1.value = false
    isClicked2.value = true
}
@Composable
fun Choices() {
    if (isClicked1.value == true) (IngredientsList1(Imp))
    else (
            IngredientsList2(Imp)
            )
}
@Composable
fun TabButton(text: String, active: Boolean, modifier: Modifier) {
    androidx.compose.material.Button(
        onClick ={
            if (text=="FAUNA")(First_Click())
            else(Second_Click())
                 },
        modifier = modifier.fillMaxHeight(),
        elevation = null,
        colors = if (active) ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF09630D),
            contentColor = White
        ) else ButtonDefaults.buttonColors(
            backgroundColor = LightGray,
            contentColor = DarkGray
        )

    ) {
        androidx.compose.material.Text(text)
    }
}}
@Composable
fun <T> EasyGrid(nColumns: Int, items: List<T>, content: @Composable (T) -> Unit){
    Column(Modifier.padding(16.dp)) {
        for (i in items.indices step nColumns) {
            Row {
                for (j in 0 until nColumns) {
                    if (i + j < items.size) {
                        Box(
                            contentAlignment = Alignment.TopCenter,
                            modifier = Modifier.weight(1f)
                        ) {
                            content(items[i + j])
                        }
                    } else {
                        Spacer(Modifier.weight(1f, fill = true))
                    }
                }
            }
        }
    }
}

@Composable
fun IngredientsList1(recipe: MainActivity.Recipe) {EasyGrid(nColumns = 3, items =recipe.ingredients1) {
    IngredientCard1(it.image, it.title, it.subtitle, Modifier)
}
}

@Composable
fun IngredientCard1(
    @DrawableRes iconResource: Int,
    title: String,
    subtitle: String,
    modifier: Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(bottom = 16.dp)
    ) {
        androidx.compose.material.Card(
            elevation = 0.dp,
            backgroundColor = White,
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .padding(bottom = 8.dp)
        ) {
            Image(
                painter = painterResource(id = iconResource),
                contentDescription = null,
                modifier = Modifier.padding(16.dp)
            )
        }
        androidx.compose.material.Text(
            text = title,
            modifier = Modifier.width(100.dp)
                .padding(start = 8.dp)
            .align(Alignment.CenterHorizontally),

            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
        androidx.compose.material.Text(
            text = subtitle,
            color = DarkGray,
            modifier = Modifier.width(100.dp),
            fontSize = 14.sp
        )
    }
}
@Composable
fun IngredientsList2(recipe: MainActivity.Recipe) {EasyGrid(nColumns = 3, items =recipe.ingredients2) {
    IngredientCard2(it.image, it.title, it.subtitle, Modifier)
}
}

@Composable
fun IngredientCard2(
    @DrawableRes iconResource: Int,
    title: String,
    subtitle: String,
    modifier: Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(bottom = 16.dp)
    ) {
        androidx.compose.material.Card(
            elevation = 0.dp,
            backgroundColor = White,
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .padding(bottom = 8.dp)
        ) {
            Image(
                painter = painterResource(id = iconResource),
                contentDescription = null,
                modifier = Modifier.padding(16.dp)
            )
        }
        androidx.compose.material.Text(
            text = title,
            modifier = Modifier.width(100.dp)
                .padding(start = 8.dp)
                .align(Alignment.CenterHorizontally),

            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
        androidx.compose.material.Text(
            text = subtitle,
            color = DarkGray,
            modifier = Modifier.width(100.dp),
            fontSize = 14.sp
        )
    }
}

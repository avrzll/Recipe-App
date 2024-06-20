package com.avrzll.recipe

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.content.FileProvider
import com.avrzll.recipe.data.api.FoodList
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.imageview.ShapeableImageView
import java.io.File
import java.io.FileOutputStream

class DetailFoods : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_foods)
        enableEdgeToEdge()

        val getData: FoodList = intent.getParcelableExtra("data")!!

        val imgDetail = findViewById<ShapeableImageView>(R.id.img_foods_detail)

        val btnBack = findViewById<ImageButton>(R.id.btn_back_detail)
        val btnShare = findViewById<ImageButton>(R.id.btn_share_detail)
        val btnPlayYT = findViewById<RelativeLayout>(R.id.btn_tonton_nanti)

        val titleFoods = findViewById<TextView>(R.id.judul_resep)
        val textIngreds = findViewById<TextView>(R.id.text_bahan_value)
        val textIntructions = findViewById<TextView>(R.id.text_intruksi_value)

        val formattedIngredients = StringBuilder()
        for (ingredient in getData.ingredients) {
            formattedIngredients.append("• ").append(ingredient).append("\n")
        }

        imgDetail.loadImage(getData.image)
        titleFoods.text = getData.name
        textIngreds.text = formattedIngredients.toString()
        textIntructions.text = getData.steps

        btnBack.setOnClickListener {
            finish()
        }

        btnPlayYT.setOnClickListener {
            playYoutubeVideo(getData.yt)
        }

        btnShare.setOnClickListener {
            val imageUri = saveImageToCache(imgDetail)

            if (imageUri != null) {
                // Membuat intent untuk berbagi gambar dan teks
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_STREAM, imageUri)
                    putExtra(Intent.EXTRA_TEXT, "${getData.name}\n\nBahan-bahan:\n$formattedIngredients\n\nSteps:\n${getData.steps}\n\nTutorial YouTube:\n${getData.yt}")
                    type = "image/*"
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }

                // Memeriksa apakah WhatsApp terinstal
                val whatsappInstalled =
                    isPackageInstalled("com.whatsapp") || isPackageInstalled("com.whatsapp.w4b")
                if (whatsappInstalled) {
                    // Jika WhatsApp terinstal, atur paket intent ke "com.whatsapp" dan mulai activity
                    sendIntent.setPackage("com.whatsapp")
                    startActivity(sendIntent)
                } else {
                    // Jika WhatsApp tidak terinstal, tampilkan pesan toast
                    Toast.makeText(this, "WhatsApp tidak terinstal.", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Tampilkan pesan kesalahan jika URI gambar null
                Toast.makeText(this, "Gagal menyimpan gambar.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun playYoutubeVideo(youtubeUrl: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.setPackage("com.google.android.youtube")
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            // Jika aplikasi YouTube tidak terinstal, buka di browser
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl))
            startActivity(webIntent)
        }
    }

    private fun ImageView.loadImage(url: String) {
        Glide.with(this)
            .load(url)
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    this@loadImage.setImageDrawable(resource)
                }
                override fun onLoadCleared(placeholder: Drawable?) {
                    // handle cleanup here
                }
            })
    }

    private fun isPackageInstalled(packageName: String): Boolean {
        return try {
            // Mencoba mendapatkan informasi paket
            packageManager.getPackageInfo(packageName, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            // Jika paket tidak ditemukan, kembalikan false
            false
        }
    }

    private fun saveImageToCache(imageView: ShapeableImageView): Uri? {
        return try {
            val bitmapDrawable = imageView.drawable as BitmapDrawable
            val bitmap = bitmapDrawable.bitmap

            val cachePath = File(cacheDir, "images")
            cachePath.mkdirs()
            val file = File(cachePath, "shared_image.png")
            val fileOutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
            fileOutputStream.close()

            FileProvider.getUriForFile(this, "${applicationContext.packageName}.fileprovider", file)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

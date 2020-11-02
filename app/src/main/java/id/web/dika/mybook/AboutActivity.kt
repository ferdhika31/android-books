package id.web.dika.mybook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val actionBar = supportActionBar
        actionBar!!.title= "About Me"
        actionBar.setDisplayHomeAsUpEnabled(true)

        val imgImages: ImageView = this.findViewById(R.id.author_photo)

        Glide.with(this)
            .load("https://s.gravatar.com/avatar/8c2ef75f6fd262a0d4002961a9436e0f?s=200&r=pg")
            .apply(RequestOptions())
            .into(imgImages)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
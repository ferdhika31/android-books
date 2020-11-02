package id.web.dika.mybook

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.AssetManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.web.dika.mybook.models.Book
import id.web.dika.mybook.utils.formatDate
import id.web.dika.mybook.utils.rupiah

class BookDetailActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        val bookId = getParams().toString()

        val txtImages: String
        val txtTitle: String
        val txtAuthor: String
        val txtPublishedDate: String
        val txtTotalPages: Int
        val txtPrice: Int
        val txtISBN: String
        val txtPublisher: String
        val txtDescription: String

        if(bookId!="null"){
            val jsonString = applicationContext.assets.readFile("data.json")
            val gson = Gson()
            val bookType = object : TypeToken<List<Book>>() {}.type
            val books: List<Book> = gson.fromJson(jsonString, bookType)

            val bookDetail: Book? = books.firstOrNull { it.id == bookId.toInt() }

            txtImages  = bookDetail?.images.toString()
            txtTitle  = bookDetail?.title.toString()
            txtAuthor  = bookDetail?.author.toString()
            txtPublishedDate  = bookDetail?.published_date.toString()
            txtTotalPages  = bookDetail?.total_pages!!
            txtPrice  = bookDetail?.price!!
            txtISBN  = bookDetail?.isbn.toString()
            txtPublisher  = bookDetail?.publisher.toString()
            txtDescription  = bookDetail?.description.toString()
        }else{
            txtImages  = intent.getStringExtra("images").toString()
            txtTitle  = intent.getStringExtra("title").toString()
            txtAuthor  = intent.getStringExtra("author").toString()
            txtPublishedDate  = intent.getStringExtra("published_date").toString()
            txtTotalPages  = intent.getIntExtra("total_pages", 0)
            txtPrice  = intent.getIntExtra("price", 0)
            txtISBN  = intent.getStringExtra("isbn").toString()
            txtPublisher  = intent.getStringExtra("publisher").toString()
            txtDescription  = intent.getStringExtra("description").toString()
        }

        val actionBar = supportActionBar
        actionBar!!.title= "Book : $txtTitle"
        actionBar.setDisplayHomeAsUpEnabled(true)

        val imgImages: ImageView = this.findViewById(R.id.img_images)
        val tvTitle: TextView = this.findViewById(R.id.tv_title)
        val tvAuthor: TextView = this.findViewById(R.id.tv_author)
        val tvPrice: TextView = this.findViewById(R.id.tv_price)
        val tvPublishedDate: TextView = this.findViewById(R.id.tv_published_date)
        val tvTotalPages: TextView = this.findViewById(R.id.tv_total_pages)
        val tvISBN: TextView = this.findViewById(R.id.tv_isbn)
        val tvPublisher: TextView = this.findViewById(R.id.tv_publisher)
        val btnShare: Button = this.findViewById(R.id.btn_share)
        val tvDescription: TextView = this.findViewById(R.id.tv_description)

        Glide.with(this)
            .load(txtImages)
            .apply(RequestOptions())
            .into(imgImages)
        tvTitle.text = txtTitle
        tvAuthor.text = txtAuthor
        tvPrice.text = rupiah(txtPrice.toDouble())
        tvPublishedDate.text = txtPublishedDate?.let { formatDate(it, "dd MMMM yyyy") }
        tvTotalPages.text = "$txtTotalPages Pages"
        tvISBN.text = "ISBN $txtISBN"
        tvPublisher.text = "Publisher $txtPublisher"
        tvDescription.text = txtDescription

        btnShare.setOnClickListener {
            val sText = "Baca buku $txtTitle oleh $txtAuthor yuk!"
            //Intent to share the text
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type="text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, sText);
            startActivity(Intent.createChooser(shareIntent,"Share via"))
        }

    }

    private fun getParams() : String? {
        val data = intent.data

        return try {
            (data?.getQueryParameter("id"))!!
        } catch (ex: Exception) {
            null
        }
    }

    private fun AssetManager.readFile(fileName: String) = open(fileName)
        .bufferedReader()
        .use { it.readText() }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
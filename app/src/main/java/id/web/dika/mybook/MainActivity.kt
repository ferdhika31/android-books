package id.web.dika.mybook

import android.content.Intent
import android.content.res.AssetManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.web.dika.mybook.adapter.ListBookAdapter
import id.web.dika.mybook.models.Book

class MainActivity : AppCompatActivity() {
    private lateinit var rvBook: RecyclerView
    private var list: ArrayList<Book> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar
        actionBar!!.title= "Catalog"
        rvBook = findViewById(R.id.rv_book)
        rvBook.setHasFixedSize(true)

        showList()
    }

    private fun showList() {
        val jsonString = applicationContext.assets.readFile("data.json")
        val gson = Gson()
        val bookType = object : TypeToken<List<Book>>() {}.type
        var books: List<Book> = gson.fromJson(jsonString, bookType)
        list.addAll(books)

        rvBook.layoutManager = LinearLayoutManager(this)
        val listUnivAdapter = ListBookAdapter(list)
        rvBook.adapter = listUnivAdapter
    }

    private fun AssetManager.readFile(fileName: String) = open(fileName)
        .bufferedReader()
        .use { it.readText() }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.item_about -> {
                val iAbout = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(iAbout)
            }
        }
    }}
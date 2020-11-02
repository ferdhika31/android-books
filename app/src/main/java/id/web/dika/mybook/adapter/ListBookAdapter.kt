package id.web.dika.mybook.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.web.dika.mybook.BookDetailActivity
import id.web.dika.mybook.R
import id.web.dika.mybook.models.Book
import id.web.dika.mybook.utils.rupiah
import kotlin.collections.ArrayList

class ListBookAdapter(private val listBook: ArrayList<Book>) : RecyclerView.Adapter<ListBookAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_book, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listBook.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (id, title, year, author, images, description,
                total_pages, published_date, isbn, language,
                publisher, weight, width, height, price) = listBook[position]

        Glide.with(holder.itemView.context)
            .load(images)
            .apply(RequestOptions())
            .into(holder.imgImages)

        holder.tvTitle.text = title
        holder.tvAuthor.text = author
        holder.tvPublishedDate.text = published_date
        holder.tvTotalPages.text = total_pages.toString()
        holder.tvPrice.text = rupiah(price.toDouble())

        val mContext = holder.itemView.context

        holder.itemView.setOnClickListener {
            val bookDetailActivity = Intent(mContext, BookDetailActivity::class.java)
            bookDetailActivity.putExtra("title", title)
            bookDetailActivity.putExtra("year", year)
            bookDetailActivity.putExtra("author", author)
            bookDetailActivity.putExtra("images", images)
            bookDetailActivity.putExtra("description", description)
            bookDetailActivity.putExtra("total_pages", total_pages)
            bookDetailActivity.putExtra("published_date", published_date)
            bookDetailActivity.putExtra("isbn", isbn)
            bookDetailActivity.putExtra("language", language)
            bookDetailActivity.putExtra("publisher", publisher)
            bookDetailActivity.putExtra("weight", weight)
            bookDetailActivity.putExtra("width", width)
            bookDetailActivity.putExtra("height", height)
            bookDetailActivity.putExtra("price", price)
            mContext.startActivity(bookDetailActivity)
        }
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgImages: ImageView = itemView.findViewById(R.id.img_images)
        var tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        var tvAuthor: TextView = itemView.findViewById(R.id.tv_author)
        var tvPublishedDate: TextView = itemView.findViewById(R.id.tv_published_date)
        var tvTotalPages: TextView = itemView.findViewById(R.id.tv_total_pages)
        var tvPrice: TextView = itemView.findViewById(R.id.tv_price)
    }

}
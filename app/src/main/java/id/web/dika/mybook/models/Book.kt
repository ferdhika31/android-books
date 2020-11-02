package id.web.dika.mybook.models

data class Book(
        var id: Int = 0,
        var title: String = "",
        var year: Int = 0,
        var author: String = "",
        var images: String = "",
        var description: String = "",
        var total_pages: Int = 0,
        var published_date: String = "",
        var isbn: String = "",
        var language: String = "",
        var publisher: String = "",
        var weight: String = "",
        var width: String = "",
        var height: String = "",
        var price: Int = 0
) {
    override fun toString(): String {
        return "Book [title: ${this.title}, year: ${this.year}, author: ${this.author}, images: ${this.images}]"
    }
}
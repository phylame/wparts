package pw.phylame.wparts.data

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
data class Post(
        @get:[Id GeneratedValue(strategy = GenerationType.IDENTITY)]
        var id: Int,

        @get:[NotEmpty Column(nullable = false, unique = true, length = 128)]
        var title: String,

        @get:[NotNull ManyToOne JoinColumn(name = "category_id")]
        var category: Category,

        @get:[ManyToMany]
        var tags: MutableCollection<Tag> = mutableListOf(),

        @get:[NotEmpty Column(nullable = false, length = 1024)]
        var excerpt: String,

        @get:[NotNull OneToOne JoinColumn(name = "content_id")]
        var content: Asset,

        @get:[NotNull]
        var creation: LocalDateTime = LocalDateTime.now(),

        @get:[NotNull]
        var isPublished: Boolean = true,

        @get:[OneToMany(mappedBy = "post")]
        var comments: MutableCollection<Comment> = mutableListOf(),

        @get:[OneToOne(mappedBy = "post")]
        var stat: PostStat? = null
) : Serializable

interface PostRepository : JpaRepository<Post, Int> {
    fun findAllByCategoryId(categoryId: Int, pageable: Pageable): Page<Post>

    fun findAllByTagsId(tagId: Int, pageable: Pageable): Page<Post>
}

@Entity
data class PostStat(
        @get:[Id GeneratedValue(strategy = GenerationType.IDENTITY)]
        var id: Int,

        @get:[OneToOne JoinColumn(name = "post_id")]
        var post: Post,

        var viewCount: Int = 0,

        var voteCount: Int = 0
) : Serializable

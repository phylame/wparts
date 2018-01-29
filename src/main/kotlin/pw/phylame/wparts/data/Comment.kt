package pw.phylame.wparts.data

import org.springframework.data.jpa.repository.JpaRepository
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
data class Comment(
        @get:[Id GeneratedValue(strategy = GenerationType.IDENTITY)]
        var id: Int,

        @get:[NotNull ManyToOne JoinColumn(name = "post_id")]
        var post: Post,

        @get:[NotEmpty Column(length = 2048)]
        var content: String,

        @get:[Column(length = 64)]
        var name: String? = null,

        @get:[Column(length = 128)]
        var email: String? = null,

        @get:[Column(length = 1024)]
        var link: String? = null,

        @get:[ManyToOne JoinColumn(name = "origin_id")]
        var origin: Comment? = null,

        @get:[OneToMany(mappedBy = "origin")]
        var replies: MutableCollection<Comment> = mutableListOf(),

        @get:[NotNull]
        var creation: LocalDateTime = LocalDateTime.now()
) : Serializable

interface CommentRepository : JpaRepository<Comment, Int> {
    fun findAllByPostId(articleId: Int): List<Comment>

    fun findAllByOriginId(originId: Int): List<Comment>
}

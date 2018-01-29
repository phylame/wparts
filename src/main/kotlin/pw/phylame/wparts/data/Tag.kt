package pw.phylame.wparts.data

import org.springframework.data.jpa.repository.JpaRepository
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["name", "target"])])
data class Tag(
        @get:[Id GeneratedValue(strategy = GenerationType.IDENTITY)]
        var id: Int,

        @get:[NotEmpty Column(nullable = false, length = 32)]
        var name: String,

        @get:[NotEmpty Column(nullable = false, length = 32)]
        var target: String,

        @get:[NotNull]
        var creation: LocalDateTime = LocalDateTime.now(),

        @get:[Min(0)]
        var rank: Int = 0
) : Serializable

interface TagRepository : JpaRepository<Tag, Int>

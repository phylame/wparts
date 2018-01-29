package pw.phylame.wparts.data

import org.springframework.data.jpa.repository.JpaRepository
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
data class Asset(
        @get:[Id GeneratedValue(strategy = GenerationType.IDENTITY)]
        var id: Int,

        @get:[NotEmpty Column(nullable = false, length = 512)]
        var name: String,

        @get:[NotEmpty Column(nullable = false, length = 128)]
        var mime: String,

        @get:[NotNull Min(0)]
        var length: Long,

        @get:[NotEmpty Column(nullable = false, unique = true, length = 40)]
        var sha1: String,

        @get:[NotEmpty Column(nullable = false, unique = true, length = 32)]
        var md5: String,

        @get:[NotEmpty Column(nullable = false, unique = true, length = 1024)]
        var url: String,

        @get:[NotNull]
        var created: LocalDateTime = LocalDateTime.now(),

        var modified: LocalDateTime? = null,

        @get:[NotNull]
        var description: String = ""
) : Serializable

interface AssetRepository : JpaRepository<Asset, Int>

package pw.phylame.wparts.post

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import pw.phylame.wparts.data.Post
import pw.phylame.wparts.data.PostRepository
import pw.phylame.wparts.data.Tag
import pw.phylame.wparts.data.TagRepository

@Service
class PostService {
    @Autowired
    private lateinit var postRepository: PostRepository

    @Autowired
    private lateinit var tagRepository: TagRepository

    @Cacheable("tag")
    fun getTags(): List<Tag> {
        return tagRepository.findAll()
    }

    @Cacheable("post")
    fun getPosts(pageable: Pageable): Page<Post> {
        return postRepository.findAll(pageable)
    }

    @Cacheable("post")
    fun getPostsByCategoryId(categoryId: Int, pageable: Pageable): Page<Post> {
        return postRepository.findAllByCategoryId(categoryId, pageable)
    }

    @Cacheable("post")
    fun getPostsByTagId(tagId: Int, pageable: Pageable): Page<Post> {
        return postRepository.findAllByTagsId(tagId, pageable)
    }

    @Cacheable("post")
    fun getPostById(id: Int): Post {
        return postRepository.getOne(id)
    }
}

package pw.phylame.wparts.post

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("post")
class PostController {
    @Autowired
    private lateinit var postService: PostService

    @ModelAttribute("tags")
    fun tags() = postService.getTags()

    @GetMapping
    fun list(model: Model, @RequestParam(defaultValue = "-1") category: Int, @RequestParam(defaultValue = "-1") tag: Int, pageable: Pageable): String {
        val posts = when {
            category != -1 -> postService.getPostsByCategoryId(category, pageable)
            tag != -1 -> postService.getPostsByTagId(tag, pageable)
            else -> postService.getPosts(pageable)
        }
        model["posts"] = posts
        return "post/list"
    }

    @GetMapping("{postId}")
    fun view(model: Model, @PathVariable postId: Int): String {
        model["post"] = postService.getPostById(postId)
        return "post/view"
    }
}

package pw.phylame.wparts.web

import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.HandlerMapping
import org.webjars.WebJarAssetLocator
import javax.servlet.http.HttpServletRequest

@Controller
class WebJarController {

    private val assetLocator = WebJarAssetLocator()

    @ResponseBody
    @GetMapping("/asset/{webjar}/**")
    fun locateAsset(@PathVariable webjar: String, request: HttpServletRequest): ResponseEntity<Resource> = try {
        val mvcPrefix = "/asset/$webjar/"
        val mvcPath = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE) as String
        val fullPath = assetLocator.getFullPath(webjar, mvcPath.substring(mvcPrefix.length))
        ResponseEntity(ClassPathResource(fullPath), HttpStatus.OK)
    } catch (e: Exception) {
        ResponseEntity(HttpStatus.NOT_FOUND)
    }
}

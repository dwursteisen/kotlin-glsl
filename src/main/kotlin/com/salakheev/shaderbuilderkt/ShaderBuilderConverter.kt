package com.salakheev.shaderbuilderkt

import com.salakheev.shaderbuilderkt.builder.ShaderBuilder
import com.salakheev.shaderbuilderkt.builder.ShaderBuilderComponent
import org.reflections.Reflections
import java.io.File

class ShaderBuilderConverter {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val shadersClass = Reflections().getSubTypesOf(ShaderBuilder::class.java)
                .filterNot { it == ShaderBuilderComponent::class.java }

            val rootPath = args.getOrNull(0) ?: ""
            shadersClass.forEach {
                val shader = it.newInstance().getSource()
                File(rootPath + "/" + it.simpleName + ".glsl").writeText(shader)
            }
        }
    }
}

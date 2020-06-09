package com.salakheev.shaderbuilderkt

import com.salakheev.shaderbuilderkt.builder.ShaderBuilder
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File

class ShaderBuilderConverterTest {

    class ExampleShader : ShaderBuilder()

    @TempDir
    lateinit var folder : File

    @Test
    fun `main | it converts a Shader class inter a shader source code`() {
        ShaderBuilderConverter.main(arrayOf(folder.absolutePath))

        assertTrue(folder.resolve("ExampleShader.glsl").exists())
    }
}

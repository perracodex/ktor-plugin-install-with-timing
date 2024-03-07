/*
 * Copyright (c) 2024-Present Perraco Labs. All rights reserved.
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>
 */

package perracodex

import io.ktor.server.application.*
import kotlin.system.measureTimeMillis

/**
 * Extension function to install a Ktor plugin and measure the time taken to install it.
 */
fun <B : Any, F : Any> Application.installWithTiming(
    plugin: Plugin<Application, B, F>,
    configure: B.() -> Unit = {}
): F {
    this.log.info("Installing plugin: ${plugin.key.name}.")

    val timeTaken: Long = measureTimeMillis {
        install(plugin = plugin, configure = configure)
    }

    this.log.info("Installed plugin: ${plugin.key.name}. Time taken: $timeTaken ms")
    return plugin(plugin = plugin)
}

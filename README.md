### A Ktor extenstion function to log how long a plugin took to install.

```kotlin
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
```

Then just replace any plugin ```install``` with ```installWithTiming```, and that is all. Now you should get install timings in the logs.

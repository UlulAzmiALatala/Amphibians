package ulul.azmi.a.latala.amphibians

import android.app.Application
import ulul.azmi.a.latala.amphibians.data.AppContainer
import ulul.azmi.a.latala.amphibians.data.DefaultAppContainer

class AmphibiansApplication : Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}

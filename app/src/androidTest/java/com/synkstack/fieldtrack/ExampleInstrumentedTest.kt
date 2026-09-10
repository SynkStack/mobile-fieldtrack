package com.synkstack.fieldtrack

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented sanity test verifying the app under test resolves to the
 * expected application ID. Serves as a placeholder until UI/instrumentation
 * tests are added alongside real screens.
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.synkstack.fieldtrack", appContext.packageName)
    }
}

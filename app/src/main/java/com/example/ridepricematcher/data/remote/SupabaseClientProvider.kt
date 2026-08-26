package com.example.ridepricematcher.data.remote

import com.example.ridepricematcher.BuildConfig
import io.github.jan_tennert.supabase.SupabaseClient
import io.github.jan_tennert.supabase.createSupabaseClient
import io.github.jan_tennert.supabase.auth.Auth
import io.github.jan_tennert.supabase.auth.auth
import io.github.jan_tennert.supabase.postgrest.Postgrest
import io.github.jan_tennert.supabase.postgrest.postgrest
import io.github.jan_tennert.supabase.realtime.Realtime
import kotlinx.serialization.json.Json

object SupabaseClientProvider {

    private val jsonConfig = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = true
    }

    val client: SupabaseClient by lazy {
        createSupabaseClient(
            supabaseUrl = BuildConfig.SUPABASE_URL,
            supabaseKey = BuildConfig.SUPABASE_ANON_KEY
        ) {
            install(Auth) {
                // Session auto-refresh is enabled by default
            }
            install(Postgrest) {
                defaultSchema = "public"
            }
            install(Realtime)
            defaultHttpConfig {
                // Ktor engine is auto-detected on Android
            }
        }
    }

    val auth get() = client.auth
    val postgrest get() = client.postgrest
}

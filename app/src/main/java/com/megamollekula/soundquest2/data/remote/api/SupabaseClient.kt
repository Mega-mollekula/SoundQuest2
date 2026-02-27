package com.megamollekula.soundquest2.data.remote.api

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

object SupabaseClient {
    val client: SupabaseClient by lazy {
        createSupabaseClient(
            supabaseUrl = "https://ekmnruxmfjnblokrzkwy.supabase.co",
            supabaseKey = "sb_publishable_cXoKIJX-OVIGYJjd3tLywQ_mmsr7uPV"
        ) {
            install(Postgrest)
            install(Storage)
        }
    }
}
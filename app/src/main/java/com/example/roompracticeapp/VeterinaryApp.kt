package com.example.roompracticeapp

import android.app.Application
import com.example.roompracticeapp.repository.veterinary.VeterinaryDatabase
import com.example.roompracticeapp.repository.veterinary.VeterinaryRepository
import com.example.roompracticeapp.utils.AppExecutors

class VeterinaryApp : Application() {

    private val appExecutors: AppExecutors by lazy { AppExecutors() }

    private fun getDatabase(): VeterinaryDatabase {
        return VeterinaryDatabase.getInstance(this)
    }

    fun getRepository(): VeterinaryRepository {
        return VeterinaryRepository(getDatabase(), appExecutors)
    }
}
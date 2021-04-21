package com.example.roompracticeapp.repository.veterinary

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roompracticeapp.repository.RepositoryConstants.VETERINARY_DATABASE_NAME
import com.example.roompracticeapp.repository.veterinary.daos.PetDao
import com.example.roompracticeapp.repository.veterinary.entities.PetEntity

@Database(entities = [PetEntity::class], version = 1)
abstract class VeterinaryDatabase : RoomDatabase() {

    abstract fun petDao(): PetDao

    companion object {
        @Volatile
        private var INSTANCE: VeterinaryDatabase? = null

        fun getInstance(context: Context): VeterinaryDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        VeterinaryDatabase::class.java,
                        VETERINARY_DATABASE_NAME
                    )
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}
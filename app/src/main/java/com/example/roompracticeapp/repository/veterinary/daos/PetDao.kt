package com.example.roompracticeapp.repository.veterinary.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roompracticeapp.repository.RepositoryConstants.CN_PET_NAME
import com.example.roompracticeapp.repository.RepositoryConstants.PET_TABLE_NAME
import com.example.roompracticeapp.repository.veterinary.entities.PetEntity

@Dao
interface PetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(petEntity: PetEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(pets: List<PetEntity>)

    @Delete
    fun delete(petEntity: PetEntity)

    @Update
    fun update(vararg petEntity: PetEntity)

    @Query("SELECT * FROM $PET_TABLE_NAME")
    fun getAll(): LiveData<List<PetEntity>>

    @Query("SELECT * FROM $PET_TABLE_NAME WHERE $CN_PET_NAME LIKE :dogName LIMIT 1")
    fun getPetByName(dogName: String): LiveData<PetEntity>
}

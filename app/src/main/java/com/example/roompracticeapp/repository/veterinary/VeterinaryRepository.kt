package com.example.roompracticeapp.repository.veterinary

import androidx.lifecycle.LiveData
import com.example.roompracticeapp.repository.veterinary.daos.PetDao
import com.example.roompracticeapp.repository.veterinary.entities.PetEntity
import com.example.roompracticeapp.utils.AppExecutors

class VeterinaryRepository(
    database: VeterinaryDatabase,
    private val appExecutors: AppExecutors
) {

    private val petDao: PetDao = database.petDao()

    fun getPets(): LiveData<List<PetEntity>> {
        return petDao.getAll()
    }

    fun getPetByName(petName : String) : LiveData<PetEntity>{
        return petDao.getPetByName(petName)
    }

    fun insert(pet: PetEntity) {
        appExecutors.diskIO().execute { petDao.insert(pet) }
    }

    fun insertAll(petsList: List<PetEntity>){
        appExecutors.diskIO().execute { petDao.insertAll(petsList) }
    }

    fun delete(pet : PetEntity){
        appExecutors.diskIO().execute { petDao.delete(pet) }
    }

    fun update(pet : PetEntity){
        appExecutors.diskIO().execute { petDao.update(pet) }
    }
}
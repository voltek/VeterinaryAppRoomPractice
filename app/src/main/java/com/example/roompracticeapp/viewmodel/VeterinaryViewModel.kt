package com.example.roompracticeapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.roompracticeapp.VeterinaryApp
import com.example.roompracticeapp.repository.veterinary.entities.PetEntity

class VeterinaryViewModel(application: Application) : AndroidViewModel(application) {

    private val veterinaryRepository = (application as VeterinaryApp).getRepository()
    var petSelected: PetEntity? = null

    fun getPets() = veterinaryRepository.getPets()

    fun savePet(pet: PetEntity) {
        veterinaryRepository.insert(pet)
    }

    fun updatePet(pet: PetEntity) {
        veterinaryRepository.update(pet)
    }

    fun deletePet(pet: PetEntity) {
        veterinaryRepository.delete(pet)
    }

    fun getPetByName(petName : String) = veterinaryRepository.getPetByName(petName)

    fun resetPetSelected() {
        petSelected = null
    }

    fun areFieldsNotEmpty(petName: String, petRace: String, petAge: String): Boolean {
        return petName.isNotEmpty() && petRace.isNotEmpty() && petAge.isNotEmpty()
    }
}